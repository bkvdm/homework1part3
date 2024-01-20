package tel.bvm.homework1part3.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.repository.DataConstants;
import tel.bvm.homework1part3.repository.FacultyRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static tel.bvm.homework1part3.repository.DataConstants.*;

@ExtendWith(MockitoExtension.class)
class FacultyServiceImplTest {

    @InjectMocks
    FacultyServiceImpl facultyServiceImpl;

    @InjectMocks
    StudentServiceImpl studentServiceImpl;

    @Mock
    FacultyRepository facultyRepository;

    @BeforeEach
    void setUp() {


    }

    @Test
    void addFacultyVerify() {
        Mockito.when(facultyRepository.save(FACULTY_4)).thenReturn(FACULTY_4);
        Faculty expected = facultyServiceImpl.addFaculty(FACULTY_4);
        Mockito.verify(facultyRepository, Mockito.times(1)).save(FACULTY_4);
        assertEquals(expected, facultyServiceImpl.addFaculty(FACULTY_4));
    }

    @Test
    void findFacultyVerify() {
        Mockito.when(facultyRepository.findById(3L)).thenReturn(Optional.ofNullable(FACULTY_3));
        assertEquals(Optional.ofNullable(FACULTY_3), facultyRepository.findById(3L));
        Mockito.verify(facultyRepository, Mockito.times(1)).findById(3L);
    }

    @Test
    void findFacultyExceptionVerify() {
        Mockito.when(facultyRepository.findById(5L)).thenThrow(HttpClientErrorException.NotFound.class);
        assertThrows(HttpClientErrorException.NotFound.class, () -> facultyRepository.findById(5L));
    }

    @Test
    void editFacultyVerify() {
        Mockito.when(facultyRepository.save(FACULTY_2)).thenReturn(FACULTY_2);
        Faculty expected = facultyServiceImpl.editFaculty(2L, FACULTY_2);
        Mockito.verify(facultyRepository, Mockito.times(1)).save(FACULTY_2);
        assertEquals(expected, facultyServiceImpl.editFaculty(2L, FACULTY_2));
    }

    @Test
    void editFacultyIdParameterExceptionVerify() {
        Mockito.when(facultyServiceImpl.editFaculty(6L, null)).thenThrow(HttpClientErrorException.BadRequest.class);
        assertThrows(HttpClientErrorException.BadRequest.class, () -> facultyServiceImpl.editFaculty(6L, null));
    }

    @Test
    void editFacultyIdAndFacultyParameterExceptionVerify() {
        Mockito.when(facultyServiceImpl.editFaculty(7L, FACULTY_MAP.get(7L))).thenThrow(HttpClientErrorException.BadRequest.class);
        assertThrows(HttpClientErrorException.BadRequest.class, () -> facultyServiceImpl.editFaculty(7L, FACULTY_MAP.get(7L)));
    }

    @Test
    void editFacultyFacultyParameterExceptionVerify() {
        Mockito.when(facultyServiceImpl.editFaculty(null, FACULTY_MAP.get(7L))).thenThrow(HttpClientErrorException.BadRequest.class);
        assertThrows(HttpClientErrorException.BadRequest.class, () -> facultyServiceImpl.editFaculty(null, FACULTY_MAP.get(7L)));
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