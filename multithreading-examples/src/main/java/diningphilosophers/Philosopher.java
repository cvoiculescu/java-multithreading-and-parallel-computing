package diningphilosophers;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

public class Philosopher implements Runnable {
    private final int id;
    @Getter
    @Setter
    private volatile boolean full;
    private final Chopstick left;
    private final Chopstick right;
    private final Random random = new Random();
    @Getter
    private int eatingCounter;

    public Philosopher(int id, Chopstick left, Chopstick right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    private void think() throws InterruptedException {
        System.out.printf("%s is thinking...%n", this);
        // the philosopher thinks for a random time
        Thread.sleep(random.nextInt(1000));
    }

    private void eat() throws InterruptedException {
        System.out.printf("%s is eating...%n", this);
        eatingCounter++;
        Thread.sleep(random.nextInt(1000));
    }

    @SuppressWarnings("CallToPrintStackTrace")
    @Override
    public void run() {
        try {
            //after eating a lot we will terminate
            while (!full) {
                think();
                if (left.pickUp(this, State.LEFT)) {
                    if (right.pickUp(this, State.RIGHT)) {
                        eat();
                        right.putDown(this, State.RIGHT);
                    }
                    left.putDown(this, State.LEFT);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Philosopher " + id;
    }
}
