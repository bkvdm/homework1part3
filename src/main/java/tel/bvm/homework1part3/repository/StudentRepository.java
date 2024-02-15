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

//    @Query(value = "select count(*) from student", nativeQuery = true)
    @Query(value = "SELECT COUNT(*) FROM Student", nativeQuery = true)
    int countStudents();
//
//    @Query("SELECT AVG(age) FROM student")
//    int averageAge();
//
//    @Query("SELECT * FROM student ORDER BY id DESC LIMIT 5")
//    List<Student> getLastFiveStudents();
}