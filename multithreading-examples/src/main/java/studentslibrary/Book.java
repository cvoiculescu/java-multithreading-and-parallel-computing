package studentslibrary;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@RequiredArgsConstructor
public class Book {

    private final int id;
    private final Lock lock = new ReentrantLock(true);

    public void read(Student student) throws InterruptedException {
        if (lock.tryLock(1, TimeUnit.SECONDS)) {
            System.out.printf("%s started reading %s%n", student, this);
            Thread.sleep(2000);
            System.out.printf("%s finished reading %s%n", student, this);
            lock.unlock();
        } else {
            System.out.printf("%s cannot read %s as is lend to another student!%n", student, this);
        }
    }

    @Override
    public String toString() {
        return "Book " + id;
    }
}
