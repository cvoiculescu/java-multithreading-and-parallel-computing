package diningphilosophers;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@AllArgsConstructor
@Getter
public class Chopstick {
    private int id;
    private final Lock lock = new ReentrantLock();

    public boolean pickUp(Philosopher philosopher, State state) throws InterruptedException {
        if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
            System.out.printf("%s picked up %s %s%n", philosopher, state, this);
            return true;
        }
        return false;
    }

    public void putDown(Philosopher philosopher, State state) {
        lock.unlock();
        System.out.printf("%s puts down %s %s%n", philosopher, state, this);
    }

    @Override
    public String toString() {
        return String.format("chopstick %d", id);
    }
}
