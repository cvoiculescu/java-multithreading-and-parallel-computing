import java.util.stream.LongStream;

public class ParallelizationExample1 {
    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        System.out.println(sum(2_000_000_000));
        System.out.println("Sequential: " + (System.currentTimeMillis() - now));
        now = System.currentTimeMillis();
        System.out.println(parallelSum(2_000_000_000));
        System.out.println("Parallel: " + (System.currentTimeMillis() - now));
    }

    private static long sum(long n) {
        return LongStream.rangeClosed(1, n).reduce(0, Long::sum);
    }

    private static long parallelSum(long n) {
        // 1 2 ...n
        return LongStream.rangeClosed(1, n).parallel().reduce(0, Long::sum);
    }
}
