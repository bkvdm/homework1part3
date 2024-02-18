package tel.bvm.homework1part3.service;

import org.springframework.web.multipart.MultipartFile;
import tel.bvm.homework1part3.model.Avatar;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface AvatarService {
    void upLoadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;

    Avatar findAvatar(Long studentId);

    List<Avatar> getAllAvatar(Integer pageNumber, Integer pageSize);
}