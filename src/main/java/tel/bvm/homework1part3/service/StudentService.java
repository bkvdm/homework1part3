package tel.bvm.homework1part3.service;

import org.springframework.http.ResponseEntity;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;

import java.util.List;

public interface StudentService {

    Student addStudent(Student student);

    Student findStudent(long id);

    Student editStudent(long id, Student student);

    void deleteStudent(long id);

    List<Student> findAllStudents();

    List<Student> findByAgeBetween(Integer from, Integer to);

    List<Student> findByAgeLessThanEqualAndGreaterThanEqual(Integer from, Integer to);

    Faculty findByStudentOfFaculty(Long id, String name);

//    Faculty findByStudentIdOrStudentName(Long studentId, String studentName);

    //    public Faculty findByStudentOfFaculty(Long id, String name) {
//    List<Student> findByStudentOfFaculty(Long id, String name);

//    Faculty findByStudentOfFaculty(Long id, String name);
}