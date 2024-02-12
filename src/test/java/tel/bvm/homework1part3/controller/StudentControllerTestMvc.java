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
import tel.bvm.homework1part3.model.Student;
import tel.bvm.homework1part3.repository.StudentRepository;
import tel.bvm.homework1part3.service.AvatarServiceImpl;
import tel.bvm.homework1part3.service.StudentServiceImpl;
import static tel.bvm.homework1part3.repository.DataConstants.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.http.MediaType;

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
}
