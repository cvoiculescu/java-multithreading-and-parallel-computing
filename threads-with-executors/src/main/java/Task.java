import java.util.concurrent.TimeUnit;

class Task implements Runnable {
    private final int id;

    public Task(int id) {
        this.id = id;
    }

    private long getThreadId() {
        return Thread.currentThread().getId();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    @Override
    public void run() {
        System.out.printf("Task with id %d is in work - thread id: %d%n", id, getThreadId());
        long duration = (long) (Math.random() * 5);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
