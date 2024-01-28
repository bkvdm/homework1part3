package tel.bvm.homework1part3.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tel.bvm.homework1part3.model.Student;
import tel.bvm.homework1part3.repository.AvatarRepository;
import tel.bvm.homework1part3.repository.StudentRepository;

import java.io.IOException;
import java.nio.file.Path;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService{

    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    @Value("@{path.to.avatars.folder}")
    private String avatarDir;

    public AvatarServiceImpl(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    public void upLoadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentRepository.getById(studentId);
        Path filePath = Path.of(avatarDir, student + "." + getExceptions(avatarFile.getOriginalFilename()));

    }
}
