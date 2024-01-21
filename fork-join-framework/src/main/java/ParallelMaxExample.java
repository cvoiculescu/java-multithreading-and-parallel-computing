import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

public class ParallelMaxExample {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ParallelMaxTask task = new ParallelMaxTask(LongStream.rangeClosed(1,10_000_000).toArray());
        System.out.println(pool.invoke(task));
    }
}
