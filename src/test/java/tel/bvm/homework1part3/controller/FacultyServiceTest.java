package tel.bvm.homework1part3.controller;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import org.springframework.web.bind.annotation.RequestParam;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.repository.DataConstants;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
    void contextLoadsFaculty() throws Exception {
        assertThat(facultyController).isNotNull();
    }

    @Test
    public void testFindFacultyById() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/faculty/" + FACULTY_4_WITH_STUDENT_INFO.getId(), Faculty.class))
                .isEqualTo(FACULTY_4_WITH_STUDENT_INFO);
    }

//    @GetMapping()
//    public List<Faculty> getAllFaculties() {
//        return facultyService.findAllFaculties();
//    }

    @Test
    public void testGetAllFaculties() {
        assertThat(this.restTemplate.exchange(
                "http://localhost:" + port + "/faculty",
                HttpMethod.GET,
                null,
                ArrayList.class));
        assertNotNull(LIST_FACULTY_WITH_STUDENT_INFO);
        assertEquals(4, LIST_FACULTY_WITH_STUDENT_INFO.size());
    }

//    @Test
//    public void testGetAllFaculties2() {
//        List<Faculty> response = restTemplate.getForEntity("http://localhost:" + port + "/faculty" + FACULTY_2.getName(), ArrayList.class));
//    }

    @Test
    public void testFindByName() throws JSONException {
//        JSONArray jsonArray = new JSONArray(FACULTY_2);
//        assertThat(this.restTemplate.getForObject("http://localhost:"
//                + port + "/findByName/"
//                + FACULTY_2.getName(), JSONObject.class))
//                .isNotNull()
//                .isEqualTo(jsonArray);
//    }

//        List<Faculty> faculties =

                JSONArray jsonArray = new JSONArray(FACULTY_2);

        ResponseEntity<List<Faculty>> response = this.restTemplate.exchange("http://localhost:" + port + "/findByName/" + FACULTY_2.getName(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Faculty>>() {
                });
        List<Faculty> faculties = response.getBody();

        assertThat(faculties)
                .isNotNull()
                .containsExactly(jsonArray);
    }
}
//        List<Faculty> actualFaculties = restTemplate.exchange("/findByName/{findByName}", HttpMethod.GET, null, new ParameterizedTypeReference<List<Faculty>>() {}, FACULTY_2.getName()).getBody();
//        Assertions.assertThat(actualFaculties).containsExactlyInAnyOrder((Faculty) LIST_FACULTY_2);
//    Assertions.assertThat(сюда передаем лист).containsExactly(сюда передаем элементы, которые ожидаем в этом листе)

//    @Test
//    public void testFindByName() {
//        String name = "Engineering";
//
//        List<Faculty> response = restTemplate.exchange("http://localhost:" + port + "/findByName/{name}" + name, List.class);
//
//        assertThat(response.getStatusCode().value()).isEqualTo(200); // Проверяем статус код ответа
//        assertThat(response.getBody()).isNotEmpty();
//    }

//        assertEquals(response.getBody()).isNotEmpty();

//    @GetMapping("/findByName/{findByName}")
//    public List<Faculty> findByNameContainingIgnoreCase(@RequestParam String name) {
//        return facultyService.findByNameContainingIgnoreCase(name);
//    }

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

