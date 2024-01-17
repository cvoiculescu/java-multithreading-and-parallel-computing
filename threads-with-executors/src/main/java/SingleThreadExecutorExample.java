import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class SingleThreadExecutorExample {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        IntStream.range(1, 6).forEach(value -> service.execute(new Task(value)));
        service.shutdown();
    }
}
