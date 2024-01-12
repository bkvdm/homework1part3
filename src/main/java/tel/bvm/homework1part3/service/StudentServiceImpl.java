package tel.bvm.homework1part3.service;

import org.springframework.stereotype.Service;
import tel.bvm.homework1part3.model.Student;
import tel.bvm.homework1part3.repository.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    //    @Autowired
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
//    private final HashMap<Long, Student> students = new HashMap<Long, Student>();

//    private long count = 0;

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
//        student.setId(count++);
//        students.put(student.getId(), student);
//        return student;
    }

//    @Override
//    public Optional<Student> findStudent(long id) {
//        if (students.containsKey(id)) {
//            return Optional.of(students.get(id));
//        } else {
//            return Optional.empty();
//        }
//    }

//    @Override
//    public Optional<Student> findStudent(long id) {
//        return students.containsKey(id) ? Optional.of(students.get(id)) : Optional.empty();
//    }

    @Override
    public Student findStudent(long id) {
//        return students.get(id);
        return studentRepository.findById(id).get();
    }

    @Override
    public Student editStudent(long id, Student student) {
//        if (!students.containsKey(id)) {
//            return null;
//        }
//        students.put(id, student);
//        return student;
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
//        students.remove((id));
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> fingAllStudents() {
        return studentRepository.findAll();
    }
}
