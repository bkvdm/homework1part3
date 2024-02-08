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
import org.springframework.http.*;
//import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;
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
                ArrayList.class));
        assertNotNull(LIST_FACULTY_WITH_STUDENT_INFO);
        assertEquals(4, LIST_FACULTY_WITH_STUDENT_INFO.size());
    }

//    @Test
//    public void testGetAllFaculties2() {
//        List<Faculty> response = restTemplate.getForEntity("http://localhost:" + port + "/faculty" + FACULTY_2.getName(), ArrayList.class));
//    }

//    @Test
//    public void testFindByNameThree() {
//        String nameFaculty = "Гриффиндор (Gryffindor)";
//        Assertions.assertThat(this.restTemplate.exchange("http://localhost:" + port + "/faculty/findByName/", nameFaculty, ArrayList.class))
//                .isNotNull();
//    }

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
//Гриффиндор (Gryffindor)", "Жёлтый (Yellow)
//        http://localhost:8080/faculty/findByNameAndColor/{findByNameAndColor}?name=%D0%93%D1%80%D0%B8%D1%84%D1%84%D0%B8%D0%BD%D0%B4%D0%BE%D1%80%20%28Gryffindor%29&color=%D0%96%D1%91%D0%BB%D1%82%D1%8B%D0%B9%20%28Yellow%29


//    @Test
//    public void testFindByNameAndColor() {
//        String url = "http://localhost:" + port + "/faculty/findByNameAndColor/?name=%D0%93%D1%80%D0%B8%D1%84%D1%84%D0%B8%D0%BD%D0%B4%D0%BE%D1%80%20%28Gryffindor%29&color=%D0%96%D1%91%D0%BB%D1%82%D1%8B%D0%B9%20%28Yellow%29";
////        String url = "http://localhost:" + port + "/faculty/findByNameAndColor/%D0%93%D1%80%D0%B8%D1%84%D1%84%D0%B8%D0%BD%D0%B4%D0%BE%D1%80%20%28Gryffindor%29/%D0%96%D1%91%D0%BB%D1%82%D1%8B%D0%B9%20%28Yellow%29";
//        var response = restTemplateLocal.getForObject(url, List.class);
//        assertThat(response).isNotNull();
////        System.out.println(LIST_FACULTY_2);
//    }
//    @Test
//    public void testFindByNameAndColor() {
//        String url = "http://localhost:" + port + "/faculty/findByNameAndColor/?name=%D0%93%D1%80%D0%B8%D1%84%D1%84%D0%B8%D0%BD%D0%B4%D0%BE%D1%80%20%28Gryffindor%29&color=%D0%96%D1%91%D0%BB%D1%82%D1%8B%D0%B9%20%28Yellow%29";
//        ResponseEntity<List<Faculty>> response = restTemplateLocal.exchange(url, ArrayList.class);
//        assertThat(response).isNotNull();
//    }

    @Test
    public void testFindByNameAndColor() {
//        String url = "http://localhost:" + port + "/faculty/findByNameAndColor/?name=%D0%93%D1%80%D0%B8%D1%84%D1%84%D0%B8%D0%BD%D0%B4%D0%BE%D1%80%20%28Gryffindor%29&color=%D0%96%D1%91%D0%BB%D1%82%D1%8B%D0%B9%20%28Yellow%29";
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
        assertThat(students).isEqualTo(STUDENTS_FACULTY_TEMPLATE(FACULTY_4));
    }

    @Test
    public void testAddFaculty() throws Exception {

        Faculty faculty = new Faculty(Long.MAX_VALUE, "FacultyNameForTest", "FacultyNameForTest", null);
        HttpEntity<Faculty> entity = new HttpEntity<>(faculty, headers);
        ResponseEntity<Faculty> response = restTemplate.exchange(
                new URL("http://localhost:" + port + "/").toString(), HttpMethod.POST, entity, Faculty.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
    }

    @Test
    public void testEditFaculty() {
        // Создание объекта Faculty
        Faculty faculty = new Faculty("Example Faculty");

        // Выполнение запроса на редактирование
        ResponseEntity<Faculty> response = restTemplate.exchange("http://localhost:" + port + "/{id}",
                HttpMethod.PUT, new HttpEntity<>(faculty, headers), Faculty.class, 1L);

        // Проверка статус кода ответа
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
//        String url = "http://localhost:" + port + "/faculty/findByNameAndColor/%D0%93%D1%80%D0%B8%D1%84%D1%84%D0%B8%D0%BD%D0%B4%D0%BE%D1%80%20%28Gryffindor%29/%D0%96%D1%91%D0%BB%D1%82%D1%8B%D0%B9%20%28Yellow%29";
//        System.out.println(LIST_FACULTY_2);
//        assertThat(response.size()).isGreaterThan(0);
////        assertThat(response.get(0)).isEqualTo(LIST_FACULTY_2);
//        var faculty = (Faculty) response.get(0);
//        assertThat(faculty.getName()).isEqualTo(LIST_FACULTY_2.get(0).getName());
//        System.out.println(response);
//  'http://localhost:8080/faculty/findByNameAndColor/%D0%93%D1%80%D0%B8%D1%84%D1%84%D0%B8%D0%BD%D0%B4%D0%BE%D1%80%20%28Gryffindor%29/%D0%96%D1%91%D0%BB%D1%82%D1%8B%D0%B9%20%28Yellow%29'

//    @Test
//    public void testFindByNameAndColor() throws JSONException {
//        ResponseEntity<List<Faculty>> response = this.restTemplate.exchange("http://localhost:" + port + "/findByNameAndColor/" + FACULTY_4.getColor(),
//                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
//                });
//        List<Faculty> faculties = response.getBody();
//        assertThat(faculties).isNotNull();
//        assertThat(faculties).isEqualTo(LIST_FACULTY_4);
//    }


//    @GetMapping("/findByNameAndColor/{findByNameAndColor}")
//    public ResponseEntity<List<Faculty>> findByNameColorContainingIgnoreCase(@RequestParam(required = false) String name, @RequestParam(required = false) String color) {
//        return facultyService.findByNameAndColorContainingIgnoreCase(name, color);
//    }


////    @Test
////    public void testFindByName() throws JSONException {
////        String nameFaculty = "Гриффиндор (Gryffindor)";
////        var httpheaders = new HttpHeaders();
////        httpheaders.setContentType(MediaType.APPLICATION_JSON);
////        var httpentity = new HttpEntity<>(httpheaders);
////
////        ResponseEntity<List<Faculty>> response = this.restTemplate.exchange("http://localhost:" + port + "/faculty/findByName/" + nameFaculty,
////                HttpMethod.GET, httpentity, new ParameterizedTypeReference<List<Faculty>>() {
////                });
////        List<Faculty> faculties = response.getBody();
////        assertThat(faculties).isNotNull();
////    }
//
////    @Test
////    public void testFindByNameTwo() throws JSONException {
////        String nameFaculty = "Гриффиндор (Gryffindor)";
////        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
////        parameters.add("name", nameFaculty);
////        ResponseEntity<List<Faculty>> response = this.restTemplate.exchange("http://localhost:" + port + "/faculty/findByName/",
////                HttpMethod.GET, new HttpEntity<>(parameters), new ParameterizedTypeReference<>() {
////                });
////        List<Faculty> faculties = response.getBody();
////        assertThat(faculties).isNotNull();
////    }
//
//    @Test
//    public void testFindByNameThree() throws JSONException {
//        String nameFaculty = "Гриффиндор (Gryffindor)";
//        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
//        parameters.add("findByName", nameFaculty);
//        ResponseEntity<List<Faculty>> response = this.restTemplate.exchange("http://localhost:" + port + "/faculty/findByName/",
//                HttpMethod.GET, new HttpEntity<>(parameters), new ParameterizedTypeReference<>() {
//                });
//        List<Faculty> faculties = response.getBody();
//        assertThat(faculties).isNotNull();
//    }


//                new Faculty(2, "Гриффиндор (Gryffindor)", "Жёлтый (Yellow)", List.of(
//                        new Student(STUDENT_4.getId(), STUDENT_4.getName(), STUDENT_4.getAge(), null),
//                        new Student(STUDENT_12.getId(), STUDENT_12.getName(), STUDENT_12.getAge(), null),
//                        new Student(STUDENT_14.getId(), STUDENT_14.getName(), STUDENT_14.getAge(), null),
//                        new Student(STUDENT_15.getId(), STUDENT_15.getName(), STUDENT_15.getAge(), null),
//                        new Student(STUDENT_16.getId(), STUDENT_16.getName(), STUDENT_16.getAge(), null),
//                        new Student(STUDENT_17.getId(), STUDENT_17.getName(), STUDENT_17.getAge(), null),
//                        new Student(STUDENT_18.getId(), STUDENT_18.getName(), STUDENT_18.getAge(), null),
//                        new Student(STUDENT_19.getId(), STUDENT_19.getName(), STUDENT_19.getAge(), null),
//                        new Student(STUDENT_22.getId(), STUDENT_22.getName(), STUDENT_22.getAge(), null))));
//        JSONArray jsonArray = new JSONArray(FACULTY_2);
//        assertThat(this.restTemplate.getForObject("http://localhost:"
//                + port + "/findByName/"
//                + FACULTY_2.getName(), JSONObject.class))
//                .isNotNull()
//                .isEqualTo(jsonArray);
//    }

//        List<Faculty> faculties =

//                JSONArray jsonArray = new JSONArray(FACULTY_2);
//
//        ResponseEntity<List<Faculty>> response = this.restTemplate.exchange("http://localhost:" + port + "/findByName/" + FACULTY_2.getName(),
//                HttpMethod.GET, null, new ParameterizedTypeReference<List<Faculty>>() {
//                });
//        List<Faculty> faculties = response.getBody();
//
//        assertThat(faculties)
//                .isNotNull()
//                .containsExactly(jsonArray);
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

