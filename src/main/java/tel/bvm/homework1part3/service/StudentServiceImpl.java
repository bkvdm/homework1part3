package tel.bvm.homework1part3.service;

import org.springframework.stereotype.Service;
import tel.bvm.homework1part3.model.Student;
import tel.bvm.homework1part3.repository.StudentRepository;

import java.util.List;

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
        return studentRepository.findByAgeLessThanEqualAndGreaterThanEqual(from, to);
    }
}