package tel.bvm.homework1part3.service;

import org.springframework.stereotype.Service;
import tel.bvm.homework1part3.model.Student;

import java.util.HashMap;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    private final HashMap<Long, Student> students = new HashMap<Long, Student>();
    private long count = 0;

    @Override
    public Student addStudent(Student student) {
        student.setId(count++);
        students.put(student.getId(), student);
        return student;
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
        return students.get(id);
    }

    @Override
    public Student editStudent(long id, Student student) {
        if (!students.containsKey(id)) {
            return null;
        }
        students.put(id, student);
        return student;
    }

    @Override
    public void deleteStudent(long id) {
        students.remove((id));
    }
}
