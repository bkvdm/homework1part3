package tel.bvm.homework1part3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAgeBetween(Integer from, Integer to);

    Student findByIdOrNameIgnoreCase(Long id, String name);

    List<Student> findByFacultyIdAndFacultyNameIgnoreCase(Long id, String name);

}