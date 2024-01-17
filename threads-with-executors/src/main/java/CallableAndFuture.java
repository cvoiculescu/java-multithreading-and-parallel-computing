import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

class Processor implements Callable<String> {

    private final int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        return "Id: " + id;
    }
}

public class CallableAndFuture {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<String>> list =
                IntStream.range(1, 6)
                        .mapToObj(i -> executorService.submit(new Processor(i)))
                        .toList();

        for (var value : list) {
            try {
                System.out.println(value.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        executorService.shutdownNow();
    }
}
