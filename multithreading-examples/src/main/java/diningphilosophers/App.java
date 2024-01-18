package diningphilosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
    The aim of this simulation is to show that is possible to avoid thread starvation
    - all the threads will be executed by executor service
    - we are able to avoid deadlocks, because we used TryLock
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = null;
        Philosopher[] philosophers = null;
        Chopstick[] chopsticks = null;
        try {
            philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];
            chopsticks = new Chopstick[Constants.NUMBER_OF_CHOPSTICKS];
            for (int i = 0; i < Constants.NUMBER_OF_CHOPSTICKS; i++) {
                chopsticks[i] = new Chopstick(i);
            }
            executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHERS);
            for (int i = 0; i < Constants.NUMBER_OF_PHILOSOPHERS; i++) {
                philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % Constants.NUMBER_OF_CHOPSTICKS]);
                executorService.execute(philosophers[i]);
            }
            Thread.sleep(Constants.SIMULATION_RUNNING_TIME);

            for (Philosopher philosopher : philosophers) {
                philosopher.setFull(true);
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
                while (!executorService.isTerminated()) {
                    Thread.sleep(1000);
                }
                for (Philosopher philosopher : philosophers) {
                    System.out.printf("%s eat %d times.%n", philosopher, philosopher.getEatingCounter());
                }
            }

        }
    }
}
