package tel.bvm.homework1part3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tel.bvm.homework1part3.model.Faculty;
import tel.bvm.homework1part3.model.Student;
import tel.bvm.homework1part3.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public Student addStudent(Student student) {
        logger.info("Calling addStudent method with student: {}", student);
        return studentRepository.save(student);
    }

    @Override
    public Student findStudent(long id) {
        logger.info("Calling findStudent method with id: {}", id);
        return studentRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Student editStudent(long id, Student student) {
        logger.info("Calling editStudent method with id: {} and student: {}", id, student);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        logger.info("Calling deleteStudent method with id: {}", id);
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> findAllStudents() {
        logger.info("Calling findAllStudents method");
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findByAgeBetween(Integer from, Integer to) {
        logger.info("Calling findByAgeBetween method with age range: {} to {}", from, to);
        return studentRepository.findByAgeBetween(from, to);
    }

    @Override
    public List<Student> findByAgeLessThanEqualAndGreaterThanEqual(Integer from, Integer to) {
        logger.info("Calling findByAgeLessThanEqualAndGreaterThanEqual method with parameters: from = {}, to = {}", from, to);
        if (Optional.ofNullable(from).isEmpty() || Optional.ofNullable(to).isEmpty()) {
            logger.error("No students found with the specified search criteria (BAD_REQUEST): from = {}, to = {}", from, to);
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else if (from != 0) {
            from = from - 1;
        }
        if (to != Integer.MAX_VALUE) {
            to = to + 1;
        }
        return studentRepository.findByAgeBetween(from, to);
    }

    @Override
    public Faculty findByStudentOfFaculty(Long id, String name) {
        logger.info("Method findByStudentOfFaculty is called with student id: " + id + " and name: " + name);
        if (Optional.ofNullable(studentRepository.findByIdOrNameIgnoreCase(id, name)).isEmpty()) {
            logger.error("Faculty for student with id: " + id + " and name: " + name + " was not found (NOT_FOUND)");
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return studentRepository.findByIdOrNameIgnoreCase(id, name).getFaculty();
    }

    @Override
    public int countStudents() {
        logger.info("Method countStudents is called.");
        return studentRepository.countStudents();
    }

    @Override
    public int averageAge() {
        logger.info("Method averageAge is called.");
        return studentRepository.averageAge();
    }

    @Override
    public List<Student> getLastFiveStudents() {
        logger.info("Method getLastFiveStudents is called.");
        return studentRepository.getLastFiveStudents();
    }

    @Override
    public List<Student> getAllStudents(Integer pageNumber, Integer pageSize) {
        logger.info("Method getAllStudents is called with pageNumber: " + pageNumber + " and pageSize: " + pageSize);
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return studentRepository.findAll(pageRequest).getContent();
    }
}