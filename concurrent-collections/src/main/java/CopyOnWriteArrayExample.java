import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class CopyOnWriteArrayExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        executor.submit(new ReadTask(list));
        IntStream.rangeClosed(1, 3).forEach((item) -> executor.submit(new WriteTask(list)));
    }
}

class ReadTask implements Runnable {

    private List<Integer> list;

    public ReadTask(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
                System.out.println(list);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class WriteTask implements Runnable {

    private List<Integer> list;

    private final Random random = new Random();

    public WriteTask(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
                list.set(random.nextInt(list.size()), random.nextInt(10));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}


