package tel.bvm.homework1part3.service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tel.bvm.homework1part3.model.Avatar;
import tel.bvm.homework1part3.model.Student;
import tel.bvm.homework1part3.repository.AvatarRepository;
import tel.bvm.homework1part3.repository.StudentRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository avatarRepository;

    @Autowired
    private final StudentRepository studentRepository;

    @Value("${path.to.avatars.folder}")
    private String avatarDir;

    public AvatarServiceImpl(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);

    @Override
    public void upLoadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("Method upLoadAvatar in file name: " + avatarFile + " student with id: " + studentId);
        Student student = studentRepository.getReferenceById(studentId);
//        Optional<Student> student = studentRepository.findById(studentId);
        Path filePath = Path.of(avatarDir, student.getName() + "." + getExtensions(avatarFile.getOriginalFilename()));

        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }
//        Files.createDirectories(filePath.getParent());

        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, StandardOpenOption.CREATE_NEW);
//                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
//        student.ifPresent(avatar::setStudent);
//        student.ifPresent(s -> avatar.setStudent(s));
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(generateDataForDB(filePath));
        avatarRepository.save(avatar);
    }

    //    @Override
    private byte[] generateDataForDB(Path filePath) throws IOException {
        logger.info("Generating data for database from file: " + filePath.getFileName());
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            logger.debug("Height of the original image: " + image.getHeight());
            logger.debug("Width of the original image: " + image.getWidth());
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics2D = preview.createGraphics();
            graphics2D.drawImage(image, 0, 0, 100, height, null);
            graphics2D.dispose();

            ImageIO.write(preview, getExtensions(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }

    @Override
    public Avatar findAvatar(Long studentId) {
        logger.info("Method findAvatar has been called with studentId: " + studentId);
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }

    private String getExtensions(String fileName) {
        logger.info("Method getExtensions has been called for file: " + fileName);
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    @Override
    public List<Avatar> getAllAvatar(Integer pageNumber, Integer pageSize) {
        logger.info("Method getAllAvatar has been called with pageNumber: " + pageNumber + " and pageSize: " + pageSize);
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return avatarRepository.findAll(pageRequest).getContent();
    }
}