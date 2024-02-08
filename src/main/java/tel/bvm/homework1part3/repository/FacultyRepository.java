package tel.bvm.homework1part3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByNameContainingIgnoreCase(String name);

    List<Faculty> findByColorContainingIgnoreCase(String color);

    List<Faculty> findByNameAndColorContainingIgnoreCase(String name, String color);

    Faculty findByIdOrNameOrColorContainingIgnoreCase(Long id, String name, String color);

//    Faculty save(Faculty faculty);


}