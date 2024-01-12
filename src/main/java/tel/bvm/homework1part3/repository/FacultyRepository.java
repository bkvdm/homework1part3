package tel.bvm.homework1part3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tel.bvm.homework1part3.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}