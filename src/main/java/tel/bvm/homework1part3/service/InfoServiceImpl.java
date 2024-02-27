package tel.bvm.homework1part3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Service;


@Service
public class InfoServiceImpl implements InfoService {

    private final ServerProperties serverProperties;

    @Autowired
    public InfoServiceImpl(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Override
    @Value("${server.port}")
    public Integer getServerPort() {
        return serverProperties.getPort();
    }
}
