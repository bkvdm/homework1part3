package tel.bvm.homework1part3.service;

import tel.bvm.homework1part3.model.Student;

public interface StudentService {
    Student addStudent(Student student);

    Student findStudent(long id, Student student);

    void deleteStudent(long id);
}
