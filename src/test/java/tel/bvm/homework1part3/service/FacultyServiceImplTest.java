package tel.bvm.homework1part3.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.repository.DataConstants;
import tel.bvm.homework1part3.repository.FacultyRepository;
import static tel.bvm.homework1part3.repository.DataConstants.*;

@ExtendWith(MockitoExtension.class)
class FacultyServiceImplTest {

    @InjectMocks
    FacultyService facultyServiceOut;

    @InjectMocks
    StudentService studentServiceOut;

    @Mock
    FacultyRepository facultyRepository;

    @BeforeEach
    void setUp() {


    }

    @Test
    void addFaculty() {
    }

    @Test
    void findFaculty() {
    }

    @Test
    void editFaculty() {
    }

    @Test
    void deleteFaculty() {
    }

    @Test
    void findAllFaculties() {
    }

    @Test
    void findByNameContainingIgnoreCase() {
    }

    @Test
    void findByColorContainingIgnoreCase() {
    }

    @Test
    void findByNameAndColorContainingIgnoreCase() {
    }

    @Test
    void findByFacultyOfStudent() {
    }
}