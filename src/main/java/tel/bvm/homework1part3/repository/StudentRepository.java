package tel.bvm.homework1part3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tel.bvm.homework1part3.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}