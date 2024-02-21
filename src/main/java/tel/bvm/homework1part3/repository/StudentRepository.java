package tel.bvm.homework1part3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAgeBetween(Integer from, Integer to);

    Student findByIdOrNameIgnoreCase(Long id, String name);

    List<Student> findByFacultyIdAndFacultyNameIgnoreCase(Long id, String name);

    @Query(value = "SELECT COUNT(*) FROM Student", nativeQuery = true)
    int countStudents();

    @Query(value = "SELECT AVG(age) FROM Student", nativeQuery = true)
    int averageAge();

    @Query(value = "SELECT * FROM Student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> getLastFiveStudents();

//    @Query("SELECT COUNT(s) FROM Student s")
//    int countStudents();
//
//    @Query("SELECT AVG(s.age) FROM Student s")
//    int averageAge();
//
//    @Query("SELECT s FROM Student s ORDER BY s.id DESC")
//    List<Student> getLastFiveStudents();
}