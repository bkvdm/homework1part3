package tel.bvm.homework1part3.service;

import net.bytebuddy.implementation.bytecode.Throw;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static tel.bvm.homework1part3.repository.DataConstants.*;

@ExtendWith(MockitoExtension.class)
class FacultyServiceImplTest {

    @InjectMocks
    FacultyServiceImpl facultyServiceImpl;

    @InjectMocks
    StudentServiceImpl studentServiceImpl;

    @Mock
    FacultyRepository facultyRepository;

//    @BeforeEach
//    void setUp() {
//
//
//    }

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
    void deleteFacultyVerify() {
//        Mockito.doNothing().when(facultyRepository).deleteById(1L);
        facultyRepository.delete(FACULTY_1);
        Mockito.verify(facultyRepository, Mockito.times(1)).delete((FACULTY_1));
    }

    @Test
    void findAllFacultiesVerify() {
        Mockito.when(facultyServiceImpl.findAllFaculties()).thenReturn(FACULTY_LIST);
        facultyRepository.findAll();
        Mockito.verify(facultyRepository, Mockito.times(1)).findAll();
    }

    @Test
    void findByNameContainingIgnoreCase() {
        Mockito.when(facultyServiceImpl.findByNameContainingIgnoreCase("Пуффендуй (Hufflepuff)")).thenReturn(FACULTY_CONTAINS_NAME_3);
        facultyRepository.findByNameContainingIgnoreCase("Пуффендуй (Hufflepuff)");
        Mockito.verify(facultyRepository, Mockito.times(1)).findByNameContainingIgnoreCase("Пуффендуй (Hufflepuff)");
    }

    public static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.of("пУффE", DataConstants.filterByFragmentNameFaculty(FACULTY_LIST, "пУффE")),
                Arguments.of("hUfF", DataConstants.filterByFragmentNameFaculty(FACULTY_LIST, "hUfF")),
                Arguments.of("GryF", DataConstants.filterByFragmentNameFaculty(FACULTY_LIST, "GryF")),
                Arguments.of("cлИ", DataConstants.filterByFragmentNameFaculty(FACULTY_LIST, "слИ")),
                Arguments.of("КоГн", DataConstants.filterByFragmentNameFaculty(FACULTY_LIST, "КоГн"))
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsStream")
    void findByNameContainingIgnoreCase(String fragment, List<Faculty> FILTERED_FACULTY) {
        assertEquals(FILTERED_FACULTY, facultyServiceImpl.findByNameContainingIgnoreCase(fragment));
    }

    ///
    @Test
    void findByColorContainingIgnoreCase() {
        Mockito.when(facultyServiceImpl.findByNameContainingIgnoreCase("Зелёный (Green)")).thenReturn(FACULTY_CONTAINS_NAME_3);
        facultyRepository.findByNameContainingIgnoreCase("Зелёный (Green)");
        Mockito.verify(facultyRepository, Mockito.times(1)).findByNameContainingIgnoreCase("Зелёный (Green)");
    }

    public static Stream<Arguments> argumentsStreamColor() {
        return Stream.of(
                Arguments.of("зЕл", DataConstants.filterByFragmentColorFaculty(FACULTY_LIST, "зЕл")),
                Arguments.of("pUR", DataConstants.filterByFragmentColorFaculty(FACULTY_LIST, "pUR")),
                Arguments.of("RE", DataConstants.filterByFragmentColorFaculty(FACULTY_LIST, "RE")),
                Arguments.of("ЖёЛ", DataConstants.filterByFragmentColorFaculty(FACULTY_LIST, "ЖёЛ")),
                Arguments.of("кр", DataConstants.filterByFragmentColorFaculty(FACULTY_LIST, "кр"))
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsStream")
    void findByColorContainingIgnoreCase(String fragment, List<Faculty> FILTERED_FACULTY) {
        assertEquals(FILTERED_FACULTY, facultyServiceImpl.findByColorContainingIgnoreCase(fragment));
    }

    @Test
    void findByNameAndColorContainingIgnoreCaseVerify() {
        Mockito.when(facultyRepository.findByNameAndColorContainingIgnoreCase(FACULTY_1.getName(),
                FACULTY_1.getColor())).thenReturn(FACULTY_CONTAINS_NAME_1);
        facultyServiceImpl.findByNameAndColorContainingIgnoreCase(FACULTY_1.getName(),
                FACULTY_1.getColor());
        Mockito.verify(facultyRepository, Mockito.atLeastOnce()).findByNameAndColorContainingIgnoreCase("Когневран (Ravenclaw)", "Красный (Red)");
    }

    @Test
    void findByNameAndColorContainingIgnoreCaseExceptionVerify() {
        Mockito.verify(facultyRepository, never()).findByNameAndColorContainingIgnoreCase(null, null);
    }


//    @Test
//    void findByNameAndColorContainingIgnoreCase1() {
//        Mockito.when(facultyRepository.findByNameAndColorContainingIgnoreCase(FACULTY_1.getName(),
//                FACULTY_1.getColor())).thenReturn(FACULTY_CONTAINS_NAME_1);
//        facultyServiceImpl.findByNameAndColorContainingIgnoreCase(FACULTY_1.getName(),
//                FACULTY_1.getColor());
//        Mockito.verify(facultyRepository, Mockito.times(1)).findByNameAndColorContainingIgnoreCase("Когневран (Ravenclaw)", "Красный (Red)");
//    }
//    @Test
//    void findByNameAndColorContainingIgnoreCase() {
//        Mockito.when(facultyServiceImpl.findByNameAndColorContainingIgnoreCase(FACULTY_1.getName(),
//                FACULTY_1.getColor())).thenReturn(ResponseEntity.ok(FACULTY_CONTAINS_NAME_1));
//        facultyRepository.findByNameAndColorContainingIgnoreCase(FACULTY_1.getName(),
//                FACULTY_1.getColor());
//        Mockito.verify(facultyRepository, Mockito.times(1)).findByNameAndColorContainingIgnoreCase("Когневран (Ravenclaw)", "Красный (Red)");
//    }

//    @Test
//    void findByColorContainingIgnoreCase() {
//
//    }


    @Test
    void findByFacultyOfStudentVerify() {

    }
}