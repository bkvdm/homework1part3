package tel.bvm.homework1part3.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tel.bvm.homework1part3.model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static tel.bvm.homework1part3.repository.DataConstants.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StudentController studentController;

    @Test
    void contextLoadsStudent() throws Exception{
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
}
