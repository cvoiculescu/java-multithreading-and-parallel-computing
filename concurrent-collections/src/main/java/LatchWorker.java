import java.util.concurrent.CountDownLatch;

public class LatchWorker implements Runnable {

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
