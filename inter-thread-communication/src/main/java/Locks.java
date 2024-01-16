import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {

    private static int counter = 0;
    private static final Lock lock = new ReentrantLock();

    private static void increment() {
        lock.lock();
        try {
            for (int i = 0; i < 10_000; i++) {
                counter++;
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            increment();
        });
        Thread t2 = new Thread(() -> {
            increment();
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("counter: " + counter);

    }
}
