package tel.bvm.homework1part3.service;

import org.springframework.http.ResponseEntity;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student addStudent(Student student);

    Student findStudent(long id);

    Student editStudent(long id, Student student);

    void deleteStudent(long id);

    List<Student> findAllStudents();

    List<Student> findByAgeBetween(Integer from, Integer to);

    List<Student> findByAgeLessThanEqualAndGreaterThanEqual(Integer from, Integer to);

    Faculty findByStudentOfFaculty(Long id, String name);

    int countStudents();

    int averageAge();

    List<Student> getLastFiveStudents();

    List<Student> getAllStudents(Integer pageNumber, Integer pageSize);

    Optional<List<String>> getAllStudentsStartWithKey(String startWithKey);

    Double getAverageAgeOfStudent();

    String studentsPrintParallel();

    String studentsPrintThreadSynchronized();

    String studentsPrintParallelThread();
}