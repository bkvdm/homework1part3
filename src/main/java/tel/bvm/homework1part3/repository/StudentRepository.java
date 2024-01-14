package tel.bvm.homework1part3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tel.bvm.homework1part3.model.Student;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
//    List<Student> findByAgeInRange(long from, long to);
    List<Student> findByAgeBetween(Integer from, Integer to);

    List<Student> findByAgeLessThanEqualAndGreaterThanEqual(Integer lowerBound, Integer upperBound);
}
    //    Добавить эндпоинт для получения всех студентов, возраст которых находится
//    в промежутке, пришедшем в запросе, т. е. в GET-запросе будут передаваться
//    два числа (min и max). Для этого в репозитории следует создать метод findByAgeBetween().
