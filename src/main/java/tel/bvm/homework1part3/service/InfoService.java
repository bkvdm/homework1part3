package tel.bvm.homework1part3.service;

import org.springframework.beans.factory.annotation.Value;

public interface InfoService {

    @Value("${server.port}")
    Integer getServerPort();
}
