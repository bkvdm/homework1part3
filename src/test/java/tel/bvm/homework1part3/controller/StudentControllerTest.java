package tel.bvm.homework1part3.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tel.bvm.homework1part3.model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static tel.bvm.homework1part3.repository.DataConstants.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StudentController studentController;

    @Test
    void contextLoadsStudent() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testGetStudentInfo() {
        ResponseEntity<Student> response = restTemplate.getForEntity("http://localhost:" + port + "/student/" + STUDENT_1.getId(), Student.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Student student = response.getBody();
        assertNotNull(student);
        assertEquals(STUDENT_1.getId(), student.getId());
        assertEquals(STUDENT_1.getName(), student.getName());
        assertEquals(STUDENT_1.getAge(), student.getAge());
    }

    @Test
    public void testGetAllStudents() {
        ResponseEntity<List<Student>> response = restTemplate.exchange("http://localhost:" + port + "/student", HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
        });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Student> students = response.getBody();
        assertNotNull(students);
        assertEquals(24, students.size());
    }

//    @Test
//    public void testFindByAgeBetween() {
//        ResponseEntity<List<Student>> response = restTemplate.exchange(
//                "http://localhost:" + port + "/student/findByAgeBetween?from=20&to=25",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Student>>() {
//                });
//
//        List<Student> students = response.getBody();
//
//        // Добавим проверку на то, что список студентов не пустой
//        assertFalse(students.isEmpty());
//
//        // Проверка на соответствие полученных данных ожидаемым
//        // Например, можно проверить, что все студенты в списке действительно имеют возраст от 20 до 25 лет
//        for (Student student : students) {
//            assertTrue(student.getAge() >= 20 && student.getAge() <= 25);
//        }
//    }
}
