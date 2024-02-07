package tel.bvm.homework1part3.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;

import static org.junit.jupiter.api.Assertions.*;
//import static sun.security.krb5.Confounder.intValue;
import static tel.bvm.homework1part3.repository.DataConstants.*;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StudentController studentController;

    public static Stream<Arguments> fromToVariations() {
        return Stream.of(Arguments.of(13, 10),
                Arguments.of(7, 14),
                Arguments.of(10, 25),
                Arguments.of(12, 33),
                Arguments.of(15, 60),
                Arguments.of(45, 70),
                Arguments.of(26, 85),
                Arguments.of(0, 300),
                Arguments.of(18, 200)
        );
    }

    public static Stream<Arguments> idNameVariations() {
        return Stream.of(Arguments.of((long) FACULTY_1.getId(), FACULTY_1.getName()),
                Arguments.of((long) FACULTY_2.getId(), FACULTY_2.getName()),
                Arguments.of((long) FACULTY_3.getId(), FACULTY_3.getName()),
                Arguments.of((long) FACULTY_4.getId(), FACULTY_4.getName())
        );
    }
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

    @ParameterizedTest
    @MethodSource("fromToVariations")
    public void testFindByAgeBetween(int from, int to) {

        ResponseEntity<List<Student>> response = restTemplate.exchange(
                "http://localhost:" + port + "/student/findByAgeBetween/?from=" + from + "&to=" + to,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {});
        List<Student> students = response.getBody();

        assertNotNull(students);
        for (Student student : students) {
            assertTrue(student.getAge() >= from && student.getAge() <= to);
        }
    }

    @ParameterizedTest
    @MethodSource("idNameVariations")
    public void testFindByStudentOfFaculty(Long id, String name) {
        ResponseEntity<List<Student>> response = restTemplate.exchange(
                "http://localhost:" + port + "/student/findByStudentOfFaculty/?id=" + id + "&name=" + name,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
                });
        List<Student> students = response.getBody();

        Faculty facultyExpected = FACULTY_INFO(id);
        List<Student> studentsExpected = STUDENT_LIST_TEMPLATE(facultyExpected);

        assertNotNull(students);
        assertEquals(studentsExpected.get(id.intValue() - 1).getId(), students.get(id.intValue() - 1).getId());
        assertEquals(studentsExpected.get(id.intValue() - 1).getName(), students.get(id.intValue() - 1).getName());
    }
}

