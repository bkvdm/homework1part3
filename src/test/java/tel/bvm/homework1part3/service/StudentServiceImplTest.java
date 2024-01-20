package tel.bvm.homework1part3.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tel.bvm.homework1part3.repository.StudentRepository;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @InjectMocks
    FacultyService facultyServiceOut;

    @InjectMocks
    StudentService studentServiceOut;

    @Mock
    StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void addStudent() {
    }

    @Test
    void findStudent() {
    }

    @Test
    void editStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void findAllStudents() {
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