package tel.bvm.homework1part3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tel.bvm.homework1part3.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByStudentId(Long studentId);
}