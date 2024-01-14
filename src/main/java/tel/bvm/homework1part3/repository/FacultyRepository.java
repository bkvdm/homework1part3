package tel.bvm.homework1part3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tel.bvm.homework1part3.model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByNameContaining(String name);
    List<Faculty> findByColorContaining(String color);
    List<Faculty> findByNameAndColorContaining(String name, String color);
}
//    Добавить эндпоинт для поиска факультета по имени или цвету,
//    игнорируя регистр, т. е. в GET-запросе будет передана строка,
//    по которой будет происходить фильтрация.
