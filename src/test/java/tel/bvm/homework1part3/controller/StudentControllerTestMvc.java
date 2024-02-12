package tel.bvm.homework1part3.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;
import tel.bvm.homework1part3.repository.StudentRepository;
import tel.bvm.homework1part3.service.AvatarServiceImpl;
import tel.bvm.homework1part3.service.StudentServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static tel.bvm.homework1part3.repository.DataConstants.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTestMvc {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @SpyBean
    private StudentServiceImpl studentServiceImpl;

    @SpyBean
    private AvatarServiceImpl avatarServiceImpl;

    @InjectMocks
    private StudentController studentController;

    @InjectMocks
    private AvatarController avatarController;

    @Test
    public void testAddStudent() throws Exception {

        JSONObject userObject = new JSONObject();
        userObject.put("id", STUDENT_25.getId());
        userObject.put("name", STUDENT_25.getName());
        userObject.put("age", STUDENT_25.getAge());
//        userObject.put("faculty", STUDENT_25.getFaculty());

        when(studentRepository.save(any(Student.class))).thenReturn(STUDENT_25);

        mockMvc.perform(post("/student")
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.status().isCreated());
                .andExpect(jsonPath("$.id").value(STUDENT_25.getId()))
                .andExpect(jsonPath("$.name").value(STUDENT_25.getName()))
                .andExpect(jsonPath("$.age").value(STUDENT_25.getAge()));
//                .andExpect(jsonPath("$.faculty").value(STUDENT_25.getFaculty()));
    }

    @Test
    public void testGetStudentInfo() throws Exception {

        when(studentRepository.findById(11L)).thenReturn(Optional.ofNullable(STUDENT_11));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + STUDENT_11.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(STUDENT_11.getId()))
                .andExpect(jsonPath("$.name").value(STUDENT_11.getName()))
                .andExpect(jsonPath("$.age").value(STUDENT_11.getAge()));
    }

    @Test
    public void testEditFaculty() throws Exception {
        Student studentEdit = new Student(10L, "NewNameOfStudent", 48, null);
        JSONObject userObject = new JSONObject();
        userObject.put("id", studentEdit.getId());
        userObject.put("name", studentEdit.getName());
        userObject.put("age", studentEdit.getAge());

        when(studentRepository.save(any(Student.class))).thenReturn(studentEdit);

        mockMvc.perform(put("/student/" + studentEdit.getId())
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(studentEdit.getId()))
            .andExpect(jsonPath("$.name").value(studentEdit.getName()))
            .andExpect(jsonPath("$.age").value(studentEdit.getAge()));
    }

    @Test
    public void testDeleteStudent() throws Exception {

        JSONObject userObject = new JSONObject();
        userObject.put("id", STUDENT_9.getId());
        userObject.put("name", STUDENT_9.getName());
        userObject.put("age", STUDENT_9.getAge());

        when(studentRepository.save(any(Student.class))).thenReturn(STUDENT_25);

        mockMvc.perform(delete("/student/" + STUDENT_9.getId())
                        .content(userObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllFaculties() throws Exception {
        List<Student> actualStudents = STUDENT_LIST;
        when(studentRepository.findAll()).thenReturn(STUDENT_LIST);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/student")
                .accept(MediaType.APPLICATION_JSON));

        assertEquals(STUDENT_LIST.size(), actualStudents.size());
        for (int i = 0; i < STUDENT_LIST.size(); i++) {
            assertEquals(STUDENT_LIST.get(i).getName(), actualStudents.get(i).getName());
        }
    }

}
