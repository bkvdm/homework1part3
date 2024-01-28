package tel.bvm.homework1part3.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tel.bvm.homework1part3.model.Avatar;
import tel.bvm.homework1part3.model.Student;
import tel.bvm.homework1part3.repository.AvatarRepository;
import tel.bvm.homework1part3.repository.StudentRepository;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    @Value("@{path.to.avatars.folder}")
    private String avatarDir;

    public AvatarServiceImpl(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void upLoadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentRepository.getById(studentId);
        Path filePath = Path.of(avatarDir, student + "." + getExceptions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024)
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.getFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.getData(generateDataForDB(filePath));
        avatarRepository.save(avatar);
    }
}
