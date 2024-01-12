package tel.bvm.homework1part3.service;

import tel.bvm.homework1part3.model.Student;

import java.util.List;

public interface StudentService {

    Student addStudent(Student student);

    Student findStudent(long id);

    Student editStudent(long id, Student student);

    void deleteStudent(long id);

    List<Student> fingAllStudents();
}
