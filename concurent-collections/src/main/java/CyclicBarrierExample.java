import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        // 5 number of parties / threads
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("All tasks have been finished");
        });
        // barrier waits until 5x barrier.await() called
        ExecutorService service = Executors.newFixedThreadPool(5);
        IntStream.rangeClosed(1, 5).forEach(value -> service.execute(new CyclicBarrierWorker(value, cyclicBarrier)));
        service.shutdown();
    }
}
