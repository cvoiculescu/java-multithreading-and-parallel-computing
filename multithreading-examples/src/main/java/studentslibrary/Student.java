package studentslibrary;

import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class Student implements Runnable {
    private final int id;
    private final Book[] books;
    private final Random random = new Random();

    @Override
    public String toString() {
        return "Student #" + id;
    }

    @Override
    public void run() {
        while (true) {
            int bookId = random.nextInt(Constants.NUMBER_OF_BOOKS);
            try {
                books[bookId].read(this);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
