package tel.bvm.homework1part3.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findStudent(long id) {
        return studentRepository.findById(id)
                .orElse(null);
//        return studentRepository.findById(id).get();
//        return studentRepository.getReferenceById(id);
    }

    @Override
    public Student editStudent(long id, Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findByAgeBetween(Integer from, Integer to) {
        return studentRepository.findByAgeBetween(from, to);
    }

    @Override
    public List<Student> findByAgeLessThanEqualAndGreaterThanEqual(Integer from, Integer to) {
        if (Optional.ofNullable(from).isEmpty() || Optional.ofNullable(to).isEmpty()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else if (from != 0) {
            from = from - 1;
        }
        if (to != Integer.MAX_VALUE) {
            to = to + 1;
        }
        return studentRepository.findByAgeBetween(from, to);
    }
//
//    @Override
//    public List<Student> findByAgeLessThanEqualAndGreaterThanEqual(Integer from, Integer to) {
//        return studentRepository.findByAgeLessThanEqualAndGreaterThanEqual(from, to);
//    }

//    @Override
//    public ResponseEntity<List<Student>> findByAgeIncludeBoundariesInSearchOrNo(Integer from, Integer to, String signInclusionBorders) {
//        if (Optional.ofNullable(from).isPresent() && Optional.ofNullable(to).isPresent()) {
//            if (signInclusionBorders.isEmpty()) {
//                return ResponseEntity.ok(studentRepository.findByAgeBetween(from, to));
//            } else {
//                return ResponseEntity.ok(studentRepository.findByAgeLessThanEqualAndGreaterThanEqual(from, to));
//            }
//        } else if (Optional.ofNullable(from).isEmpty() && Optional.ofNullable(to).isPresent()) {
//            from = Integer.MIN_VALUE;
//            return ResponseEntity.ok(studentRepository.findByAgeLessThanEqualAndGreaterThanEqual(from, to));
//        } else if (Optional.ofNullable(from).isPresent() && Optional.ofNullable(to).isEmpty()) {
//            to = Integer.MAX_VALUE;
//            return ResponseEntity.ok(studentRepository.findByAgeLessThanEqualAndGreaterThanEqual(from, to));
//        }
//        return ResponseEntity.ok(studentRepository.findAll());
//    }
}