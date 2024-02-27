package tel.bvm.homework1part3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tel.bvm.homework1part3.service.InfoService;

@RestController
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/port")
    public String getServerPort() {
        return "The application is running on port: " + infoService.getServerPort();
    }
}