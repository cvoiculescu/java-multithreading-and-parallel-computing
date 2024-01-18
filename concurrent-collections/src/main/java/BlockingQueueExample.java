import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        BlockingFirstWorker blockingFirstWorker = new BlockingFirstWorker(queue);
        BlockingSecondWorker secondWorker = new BlockingSecondWorker(queue);
        new Thread(blockingFirstWorker).start();
        new Thread(secondWorker).start();
    }
}

class BlockingFirstWorker implements Runnable {

    private final BlockingQueue<Integer> queue;

    public BlockingFirstWorker(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            try {
                queue.put(counter);
                System.out.println("Putting item into queue: " + counter);
                counter++;
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class BlockingSecondWorker implements Runnable {

    private final BlockingQueue<Integer> queue;

    public BlockingSecondWorker(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer take = queue.take();
                System.out.println("Got item from queue: " + take);
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}


