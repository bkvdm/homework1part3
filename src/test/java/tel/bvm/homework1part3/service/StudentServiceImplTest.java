package tel.bvm.homework1part3.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;
import tel.bvm.homework1part3.repository.StudentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static tel.bvm.homework1part3.repository.DataConstants.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    @Mock
    StudentRepository studentRepository;

//    @InjectMocks
//    FacultyService facultyServiceImpl;

    @InjectMocks
    StudentServiceImpl studentServiceImpl;


//    @BeforeEach
//    void setUp() {
//
//    }

    @Test
    void addStudentVerify() {
        Mockito.when(studentRepository.save(STUDENT_1)).thenReturn(STUDENT_1);
        studentServiceImpl.addStudent(STUDENT_1);
        Mockito.verify(studentRepository, Mockito.atLeastOnce()).save(STUDENT_1);
        Student expected = studentServiceImpl.addStudent(STUDENT_1);
        assertEquals(expected, studentServiceImpl.addStudent(STUDENT_1));
    }

    @Test
    void findStudentVerify() {
        Mockito.when(studentRepository.findById(3L)).thenReturn(Optional.ofNullable(STUDENT_3));
        assertEquals(Optional.ofNullable(STUDENT_3), studentRepository.findById(3L));
        Mockito.verify(studentRepository, Mockito.atLeastOnce()).findById(3L);
    }

    @Test
    void findStudentExceptionVerify() {
        Mockito.when(studentRepository.findById(20L)).thenThrow(HttpClientErrorException.NotFound.class);
        assertThrows(HttpClientErrorException.NotFound.class, () -> studentRepository.findById(20L));
    }

    @Test
    void editStudentVerify() {
        Mockito.when(studentRepository.save(STUDENT_12)).thenReturn(STUDENT_12);
        Student expected = studentServiceImpl.editStudent(12L, STUDENT_12);
        Mockito.verify(studentRepository, Mockito.atLeastOnce()).save(STUDENT_12);
        assertEquals(expected, studentServiceImpl.editStudent(12L, STUDENT_12));
    }

    @Test
    void editStudentIdParameterExceptionVerify() {
        Mockito.when(studentServiceImpl.editStudent(25L, null)).thenThrow(HttpClientErrorException.BadRequest.class);
        assertThrows(HttpClientErrorException.BadRequest.class, () -> studentServiceImpl.editStudent(25L, null));
    }

    @Test
    void deleteStudent() {
        studentRepository.delete(STUDENT_5);
        Mockito.verify(studentRepository, Mockito.times(1)).delete((STUDENT_5));
    }

    @Test
    void findAllStudents() {
        Mockito.when(studentServiceImpl.findAllStudents()).thenReturn(STUDENT_LIST);
        studentRepository.findAll();
        Mockito.verify(studentRepository, Mockito.atLeastOnce()).findAll();
    }

    @Test
    void findByAgeBetween() {

    }

    @Test
    void findByAgeLessThanEqualAndGreaterThanEqual() {
    }

    @Test
    void findByStudentOfFaculty() {
    }
}