package tel.bvm.homework1part3.service;

import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class ExamForStreamsImpl implements ExamForStreams {

    @Override
    public String ExamForStreamsSimple() {

        long startTime = System.currentTimeMillis();

        int sum = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        return "Ответ: " + sum + " Время выполнения (мс): " + elapsedTime;
    }

    @Override
    public String ExamForStreamsParallel() {

        long startTime = System.currentTimeMillis();

        int sum = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        return "Ответ: " + sum + " Время выполнения (мс): " + elapsedTime;
    }
}
