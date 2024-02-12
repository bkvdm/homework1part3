package tel.bvm.homework1part3.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.repository.FacultyRepository;
import tel.bvm.homework1part3.service.AvatarServiceImpl;
import tel.bvm.homework1part3.service.FacultyServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tel.bvm.homework1part3.repository.DataConstants.*;
//import com.google.gson.Gson;

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest
public class FacultyControllerTestMvc {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyServiceImpl facultyServiceImpl;

    @SpyBean
    private AvatarServiceImpl avatarServiceImpl;

//    @MockBean
//    private AvatarServiceImpl avatarServiceImpl;

    @InjectMocks
    private FacultyController facultyController;

    @InjectMocks
    private AvatarController avatarController;

    @Test
    public void testAddFaculty() throws Exception {
        JSONObject userObject = new JSONObject();
        userObject.put("id", FACULTY_5.getId());
        userObject.put("name", FACULTY_5.getName());
        userObject.put("color", FACULTY_5.getColor());
        userObject.put("students", FACULTY_5.getStudents());

        when(facultyRepository.save(any(Faculty.class))).thenReturn(FACULTY_5);

        mockMvc.perform(post("/faculty")
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.status().isCreated());
                .andExpect(jsonPath("$.id").value(FACULTY_5.getId()))
                .andExpect(jsonPath("$.name").value(FACULTY_5.getName()))
                .andExpect(jsonPath("$.color").value(FACULTY_5.getColor()))
                .andExpect(jsonPath("$.students").value(FACULTY_5.getStudents()));
    }

    @Test
    public void testGetStudentInfo() throws Exception {
        when(facultyRepository.findById(1L)).thenReturn(Optional.ofNullable(FACULTY_1));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + FACULTY_1.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(FACULTY_1.getId()))
                .andExpect(jsonPath("$.name").value(FACULTY_1.getName()))
                .andExpect(jsonPath("$.color").value(FACULTY_1.getColor()))
                .andExpect(jsonPath("$.students").value(FACULTY_1.getStudents()));
    }

    @Test
    public void testEditFaculty() throws Exception {
        Faculty facultyEdit = new Faculty(2L, "NewNameOfFaculty", "NewColorOfFaculty", null);
        JSONObject userObject = new JSONObject();
        userObject.put("id", facultyEdit.getId());
        userObject.put("name", facultyEdit.getName());
        userObject.put("color", facultyEdit.getColor());
        userObject.put("students", null);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(facultyEdit);

        mockMvc.perform(put("/faculty/" + facultyEdit.getId())
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(facultyEdit.getId()))
                .andExpect(jsonPath("$.name").value(facultyEdit.getName()))
                .andExpect(jsonPath("$.color").value(facultyEdit.getColor()))
                .andExpect(jsonPath("$.students").value(facultyEdit.getStudents()));
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        JSONObject userObject = new JSONObject();
        userObject.put("id", FACULTY_2.getId());
        userObject.put("name", FACULTY_2.getName());
        userObject.put("color", FACULTY_2.getColor());
        userObject.put("students", FACULTY_2.getStudents());

        when(facultyRepository.save(any(Faculty.class))).thenReturn(FACULTY_2);

        mockMvc.perform(delete("/faculty/" + FACULTY_2.getId())
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testGetAllFaculties() throws Exception {
        List<Faculty> actualFaculties = LIST_FACULTY_WITH_STUDENT_INFO;
        when(facultyRepository.findAll()).thenReturn(LIST_FACULTY_WITH_STUDENT_INFO);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/faculty")
                .accept(MediaType.APPLICATION_JSON));

        assertEquals(EXPECTED_LIST_FACULTY_WITH_STUDENT_INFO.size(), actualFaculties.size());
        for (int i = 0; i < EXPECTED_LIST_FACULTY_WITH_STUDENT_INFO.size(); i++) {
            assertEquals(EXPECTED_LIST_FACULTY_WITH_STUDENT_INFO.get(i).getName(), actualFaculties.get(i).getName());
        }
    }
}


//    @Test
//    public void testAddFaculty() {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(FACULTY_5);
//        mockMvc.perform(post("/faculty")
//                        .content(objectMapper.toString())
//                        .contentType("application/json")
//                        .content(json))
//                .andExpect(status().isCreated())
//                .andDo(print());
//
//        when(facultyRepository.save(any())).thenReturn(FACULTY_5);

//        mockMvc.perform(post("/faculty").content(objectMapper.toString()).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isCreated());

//        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(FACULTY_5));

//        Faculty faculty = new Faculty(123, "NameFacultyAddTest", "ColorFacultyAddTest", null);
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/faculty/" + FACULTY_4.getId())
//                        .content(facultyAdd.toString())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id")).value("id")
//                .andExpect(jsonPath("$.name")).value("name")
//                .andExpect(jsonPath("$.color")).value("color");


//    @Override
//    public Faculty addFaculty(Faculty faculty) {
//        return facultyRepository.save(faculty);
//    }