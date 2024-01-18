import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierWorker implements Runnable {

    private final int id;
    private final Random random = new Random();
    private final CyclicBarrier cyclicBarrier;

    public CyclicBarrierWorker(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        try {
            System.out.printf("Thread with id %d starts working...%n", id);
            Thread.sleep(random.nextInt(3000) + 1);
            System.out.printf("Thread with id %d ended working!%n", id);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
            Thread.currentThread().interrupt();
        }
        try {
            cyclicBarrier.await();
            System.out.printf("Thread with id %d after await() called...%n", id);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
            Thread.currentThread().interrupt();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
