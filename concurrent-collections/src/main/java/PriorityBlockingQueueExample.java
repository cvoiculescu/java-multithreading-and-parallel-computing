import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueExample {

    public static void main(String[] args) {
        BlockingQueue<String> q = new PriorityBlockingQueue<>();
        new Thread(new PriorityFirstWorker(q)).start();
        new Thread(new PrioritySecondWorker(q)).start();
    }
}

class PriorityFirstWorker implements Runnable {

    private final BlockingQueue<String> queue;

    public PriorityFirstWorker(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {

            queue.put("B");
            queue.put("H");
            queue.put("F");
            Thread.sleep(2000);
            queue.put("A");
            Thread.sleep(2000);
            queue.put("Z");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

class PrioritySecondWorker implements Runnable {

    private final BlockingQueue<String> queue;

    public PrioritySecondWorker(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5_000);
            System.out.println(queue.take());
            Thread.sleep(1_000);
            System.out.println(queue.take());
            Thread.sleep(2_000);
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


