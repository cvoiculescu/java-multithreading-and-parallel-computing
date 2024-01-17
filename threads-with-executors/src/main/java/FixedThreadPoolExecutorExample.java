import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class FixedThreadPoolExecutorExample {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        IntStream.range(1, 11).forEach(value -> service.execute(new Task(value)));
        service.shutdown();
    }
}
