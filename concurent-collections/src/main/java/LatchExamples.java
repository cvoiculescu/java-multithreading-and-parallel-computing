import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;


public class LatchExamples {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService service = Executors.newSingleThreadExecutor();
        IntStream.rangeClosed(1, 5).forEach(value -> service.execute(new LatchWorker(value, latch)));
        latch.await();
        // api freezes until latch reaches 0
        System.out.println("All tasks completed");
        service.shutdown();
    }
}
