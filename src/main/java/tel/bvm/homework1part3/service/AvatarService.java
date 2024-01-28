package tel.bvm.homework1part3.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AvatarService {
    void upLoadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;
}
