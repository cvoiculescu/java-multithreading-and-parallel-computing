import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayedWorker implements Delayed {

    private long duration;
    private String message;

    public DelayedWorker(long duration, String message) {
        this.duration = System.currentTimeMillis() + duration;
        this.message = message;
    }

    public long getDuration() {
        return duration;
    }

    public DelayedWorker setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public DelayedWorker setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        return Long.compare(duration, ((DelayedWorker) other).duration);
    }
}

public class DelayQueueExample {
    public static void main(String[] args) {
        BlockingQueue<DelayedWorker> queue = new DelayQueue<>();
        try {
            queue.put(new DelayedWorker(2_000, "This is the first message"));
            queue.put(new DelayedWorker(10_000, "This is the second message"));
            queue.put(new DelayedWorker(4_500, "This is the third message"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // we can get messages
        while (!queue.isEmpty()) {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
