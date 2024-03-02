package tel.bvm.homework1part3.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;
import tel.bvm.homework1part3.model.Student;
import tel.bvm.homework1part3.repository.StudentRepository;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static tel.bvm.homework1part3.repository.DataConstants.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentServiceImpl studentServiceImpl;

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

    public static Stream<Arguments> argumentsAgeBetween() {
        return Stream.of(
                Arguments.of(20, 50),
                Arguments.of(30, 100),
                Arguments.of(10, 19),
                Arguments.of(100, 200),
                Arguments.of(0, 17)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsAgeBetween")
    void findByAgeBetweenVerify(int from, int to) {
        Mockito.when(studentRepository.findByAgeBetween(from, to)).thenReturn(studentAgeBetween(from, to));
        assertEquals(studentAgeBetween(from, to), studentServiceImpl.findByAgeBetween(from, to));
    }

    @Test
    void findByStudentOfFaculty() {

        Mockito.when(studentRepository.findByIdOrNameIgnoreCase(STUDENT_1.getId(), STUDENT_1.getName())).thenReturn(STUDENT_1);
        studentServiceImpl.findByStudentOfFaculty(STUDENT_1.getId(), STUDENT_1.getName());
        Mockito.verify(studentRepository, Mockito.atLeastOnce()).findByIdOrNameIgnoreCase(1L, "Луна Лавгуд (Luna Lovegood)");

        Mockito.when(studentRepository.findByIdOrNameIgnoreCase(null, STUDENT_15.getName())).thenReturn(STUDENT_15);
        studentServiceImpl.findByStudentOfFaculty(null, STUDENT_15.getName());
        Mockito.verify(studentRepository, Mockito.atLeastOnce()).findByIdOrNameIgnoreCase(null, "Колин Криви (Colin Creevey)");

        Mockito.when(studentRepository.findByIdOrNameIgnoreCase(STUDENT_19.getId(), null)).thenReturn(STUDENT_19);
        studentServiceImpl.findByStudentOfFaculty(STUDENT_19.getId(), null);
        Mockito.verify(studentRepository, Mockito.atLeastOnce()).findByIdOrNameIgnoreCase(19L, null);
    }
}