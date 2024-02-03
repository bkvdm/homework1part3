package tel.bvm.homework1part3.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.repository.DataConstants;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static tel.bvm.homework1part3.repository.DataConstants.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyServiceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FacultyController facultyController;

    @Test
    void contextLoadsFaculty() throws Exception{
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    public void testFindFacultyById() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/" + FACULTY_4_WITH_STUDENT_INFO.getId(), Faculty.class))
                .isEqualTo(FACULTY_4_WITH_STUDENT_INFO);
    }

//    @GetMapping()
//    public List<Faculty> getAllFaculties() {
//        return facultyService.findAllFaculties();
//    }

    @Test
    public void testGetAllFaculties() {
        Assertions.assertThat(this.restTemplate.exchange(
                "http://localhost:" + port + "/faculty",
                HttpMethod.GET,
                null,
                List.class));
        assertNotNull(LIST_FACULTY_WITH_STUDENT_INFO);
        assertEquals(4, LIST_FACULTY_WITH_STUDENT_INFO.size());
    }
}

        // Assert
//                assertThat(faculty.getId()).isEqualTo(facultyId);
//        assertThat(faculty.getName()).isNotNull();

//    @GetMapping("/{id}")
//    public ResponseEntity<Faculty> getStudentInfo(@PathVariable long id) {
//        Faculty faculty = facultyService.findFaculty(id);
//        if (faculty != null) {
//            return ResponseEntity.ok(faculty);
//        }
//        return ResponseEntity.notFound().build();
//    }

//    @Test
//    public void testDefaultMessage() throws Exception {
//        Assertions
//                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class))
//                .isEqualTo("WebApp is work");
//    }

