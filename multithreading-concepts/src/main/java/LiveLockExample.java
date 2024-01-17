import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLockExample {

    private final Lock lock1 = new ReentrantLock(true);
    private final Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        LiveLockExample liveLock = new LiveLockExample();
        new Thread(liveLock::worker1, "Worker1").start();
        new Thread(liveLock::worker2, "Worker2").start();
    }

    @SuppressWarnings({"ResultOfMethodCallIgnored", "CallToPrintStackTrace"})
    public void worker1() {
        while (true) {
            try {
                lock1.tryLock(50, TimeUnit.MILLISECONDS);
                System.out.println(getThreadName() + " acquired lock1...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getThreadName() + " tries to get lock2...");
            if (lock2.tryLock()) {
                System.out.println(getThreadName() + " acquired lock2...");
                lock2.unlock();
            } else {
                System.out.println(getThreadName() + " cannot acquire lock2...");
                continue;
            }
            break;
        }
        lock1.unlock();
        lock2.unlock();
    }

    private String getThreadName() {
        return Thread.currentThread().getName();
    }

    @SuppressWarnings({"ResultOfMethodCallIgnored", "CallToPrintStackTrace"})
    public void worker2() {
        while (true) {
            try {
                lock2.tryLock(50, TimeUnit.MILLISECONDS);
                System.out.println(getThreadName() + " acquired lock2...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getThreadName() + " tries to get lock1...");
            if (lock1.tryLock()) {
                System.out.println(getThreadName() + " acquired lock1...");
                lock1.unlock();
            } else {
                System.out.println(getThreadName() + " cannot acquire lock1...");
                continue;
            }
            break;
        }
        lock2.unlock();
        lock1.unlock();
    }

}
