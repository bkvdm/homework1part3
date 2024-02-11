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

//    Faculty findByStudentIdOrStudentName(Long id, String name);

//    Faculty findByIdOrNameIgnoreCase(Long id, String name);

//    @Query("select s from Student s join s.faculty f where f.id=:facultyId and lower(s.name) like lower(concat('%', :facultyName, '%'))")
//    List<Student> findByFacultyIdAndName(@Param("facultyId") Long facultyId, @Param("facultyName") String facultyName);

//    StudentRepository repo;
//    @Query(“SELECT s FROM Student s JOIN s.faculty f WHERE f.id = :facultyId AND s.name LIKE CONCAT(‘%’, :facultyName, ‘%’)”)
//    List<Student> findByFaculty(@Param(“facultyId”) Long facultyId, @Param(“facultyName”) String facultyName);
}