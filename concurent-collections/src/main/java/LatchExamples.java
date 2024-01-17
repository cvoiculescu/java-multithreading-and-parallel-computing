import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

class LatchWorker implements Runnable {

    private final int id;
    private final CountDownLatch latch;

    public LatchWorker(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        doWork();
        latch.countDown();
    }

    private void doWork() {
        try {
            System.out.printf("Thread with id %d starts working...%n", id);
            Thread.sleep(2000);
            System.out.printf("Thread with id %d ended working!%n", id);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
            Thread.currentThread().interrupt();
        }
    }
}

public class LatchExamples {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService service = Executors.newSingleThreadExecutor();
        IntStream.rangeClosed(1, 6).forEach(value -> service.execute(new LatchWorker(value, latch)));
        latch.await();
        System.out.println("All tasks completed");
        service.shutdown();
    }
}
