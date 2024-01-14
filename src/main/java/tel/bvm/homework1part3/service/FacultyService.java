package tel.bvm.homework1part3.service;

import org.springframework.http.ResponseEntity;
import tel.bvm.homework1part3.model.Faculty;

import java.util.List;

public interface FacultyService {

    Faculty addFaculty(Faculty faculty);

    Faculty findFaculty(long id);

    Faculty editFaculty(long id, Faculty faculty);

    void deleteFaculty(long id);

    List<Faculty> findAllFaculties();
    List<Faculty> findByNameContaining(String name);

    List<Faculty> findByColorContaining(String color);

    ResponseEntity<List<Faculty>> findByNameAndColorContaining(String name, String color);
}