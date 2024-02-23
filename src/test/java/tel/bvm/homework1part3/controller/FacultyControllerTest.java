package tel.bvm.homework1part3.controller;

import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
//import org.springframework.http.ResponseEntity;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static tel.bvm.homework1part3.repository.DataConstants.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final TestRestTemplate restTemplateLocal = new TestRestTemplate();

    //    TestRestTemplate restTemplate = new TestRestTemplate();
    private final HttpHeaders headers = new HttpHeaders();

    @Autowired
    private FacultyController facultyController;

    @Test
    void contextLoadsFaculty() throws Exception {
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    public void testFindFacultyById() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/" + FACULTY_4_WITH_STUDENT_INFO.getId(), Faculty.class))
                .isEqualTo(FACULTY_4_WITH_STUDENT_INFO);
    }

    @Test
    public void testGetAllFaculties() {
        Assertions.assertThat(this.restTemplate.exchange(
                "http://localhost:" + port + "/faculty",
                HttpMethod.GET,
                null,
                ArrayList.class));
        assertNotNull(LIST_FACULTY_WITH_STUDENT_INFO);
        assertEquals(4, LIST_FACULTY_WITH_STUDENT_INFO.size());
    }

    @Test
    public void testFindByName() throws JSONException {
        ResponseEntity<List<Faculty>> response = this.restTemplate.exchange("http://localhost:" + port + "/faculty/findByName/" + FACULTY_2.getName(),
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });
        List<Faculty> faculties = response.getBody();
        assertThat(faculties).isNotNull();
        assertThat(faculties).isEqualTo(LIST_FACULTY_2);
    }

    @Test
    public void testFindByColor() throws JSONException {
        ResponseEntity<List<Faculty>> response = this.restTemplate.exchange("http://localhost:" + port + "/faculty/findByColor/" + FACULTY_3.getColor(),
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });
        List<Faculty> faculties = response.getBody();
        assertThat(faculties).isNotNull();
        assertThat(faculties).isEqualTo(LIST_FACULTY_3);
    }

    @Test
    public void testFindByNameAndColor() {
        String url = "http://localhost:" + port + "/faculty/findByNameAndColor/?name=Гриффиндор (Gryffindor)&color=Жёлтый (Yellow)";
        ResponseEntity<List<Faculty>> response = restTemplateLocal.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Faculty>>() {
        });
        List<Faculty> faculties = response.getBody();
        assertThat(response).isNotNull();
        assertThat(faculties).isEqualTo(LIST_FACULTY_2);
    }

    @Test
    public void testFindByFacultyOfStudent() {
        ResponseEntity<List<Student>> response = restTemplate.exchange(
                "http://localhost:" + port + "/faculty/findByFacultyOfStudent/?id=4&name=%D0%A1%D0%BB%D0%B8%D0%B7%D0%B5%D1%80%D0%B8%D0%BD%20%28Slytherin%29&color=%D0%A4%D0%B8%D0%BE%D0%BB%D0%B5%D1%82%D0%BE%D0%B2%D1%8B%D0%B9%20%28Purple%29",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Student> students = response.getBody();
        assertNotNull(students);
        assertEquals(5, students.size());
        assertThat(students).isEqualTo(studentFacultyTemplate(FACULTY_4));
    }

    @Test
    public void testAddFaculty() throws Exception {
        Faculty facultyTest = new Faculty(Long.MAX_VALUE, "FacultyNameForTest", "FacultyColorForTest", null);

        HttpEntity<Faculty> entity = new HttpEntity<>(facultyTest, headers);
        ResponseEntity<Faculty> response = this.restTemplate.exchange(
                new URL("http://localhost:" + port + "/faculty").toString(), HttpMethod.POST, entity, Faculty.class);
        assertThat(response.getStatusCode()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void editFacultyReturnsUpdatedFaculty() {
        Faculty faculty = new Faculty(5, "EditFacultyNameForTest", "EditUpFacultyColorForTest", null);
        ResponseEntity<Faculty> response = restTemplate.exchange("http://localhost:" + port + "/faculty" + "/{id}", HttpMethod.PUT, new HttpEntity<>(faculty), Faculty.class, 5);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo("EditFacultyNameForTest");
        ResponseEntity<Void> responseEdit = restTemplate.exchange("http://localhost:" + port + "/faculty/" + 5, HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.OK, responseEdit.getStatusCode());
    }

    @Test
    public void testDeleteFaculty() {
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/faculty/" + 5, HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}