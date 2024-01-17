import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockExample {

    private final Lock lock1 = new ReentrantLock(true);
    private final Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        DeadLockExample deadLock = new DeadLockExample();
        new Thread(() -> {
            try {
                deadLock.worker1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "worker1").start();
        new Thread(() -> {
            try {
                deadLock.worker2();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "worker2").start();
    }

    public void worker1() throws InterruptedException {
        lock1.lock();
        System.out.println(getThreadName() + " acquired lock1");
        Thread.sleep(300);
        lock2.lock();
        System.out.println(getThreadName() + " acquired lock2");
        lock1.unlock();
        lock2.unlock();
    }

    private String getThreadName() {
        return Thread.currentThread().getName();
    }

    public void worker2() throws InterruptedException {
        lock2.lock();
        System.out.println(getThreadName() + " acquired lock2");
        Thread.sleep(300);
        lock1.lock();
        System.out.println(getThreadName() + " acquired lock1");
        lock2.unlock();
        lock1.unlock();
    }
}
