package tel.bvm.homework1part3;

//import org.junit.jupiter.api.Assertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import tel.bvm.homework1part3.controller.AvatarController;
import tel.bvm.homework1part3.controller.FacultyController;
import tel.bvm.homework1part3.controller.StudentController;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Homework1part3ApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private StudentController studentController;

    @Autowired
    private AvatarController avatarController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoadsFaculty() throws Exception{
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    void contextLoadsStudent() throws Exception{
        Assertions.assertThat(studentController).isNotNull();
    }
    @Test
    void contextLoadsAvatar() throws Exception{
        Assertions.assertThat(avatarController).isNotNull();
    }
}
