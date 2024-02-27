package tel.bvm.homework1part3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tel.bvm.homework1part3.service.ExamForStreams;

@RestController
@RequestMapping("/exam-streams")
public class ExamForStreamsController {

    private final ExamForStreams examForStreams;

    public ExamForStreamsController(ExamForStreams examForStreams) {
        this.examForStreams = examForStreams;
    }

    @GetMapping("/simple")
    public String ExamForStreamsSimple() {
        return examForStreams.ExamForStreamsSimple();
    }

    @GetMapping("/parallel")
    public String ExamForStreams() {
        return examForStreams.ExamForStreamsParallel();
    }
}
