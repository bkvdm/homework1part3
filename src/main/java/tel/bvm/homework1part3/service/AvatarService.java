package tel.bvm.homework1part3.service;

import org.springframework.web.multipart.MultipartFile;
import tel.bvm.homework1part3.model.Avatar;

import java.io.IOException;
import java.nio.file.Path;

public interface AvatarService {
    void upLoadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;

    byte[] generateDataForDB(Path filePath) throws IOException;

    Avatar findAvatar(Long studentId);
}
