package sum;

import java.util.stream.IntStream;

@SuppressWarnings({"InstantiationOfUtilityClass", "AccessStaticViaInstance"})
public class App {
    public static void main(String[] args) throws InterruptedException {
        int[] nums = generate(1_000_000_000);
        Parallel parallelSum = new Parallel();
        Sequential sequentialSum = new Sequential();

        long start = getTime();
        parallelSum.sum(nums);
        System.out.println("Parallel: " + (getTime() - start));

        start = getTime();
        sequentialSum.sum(nums);
        System.out.println("Sequential: " + (getTime() - start));
    }

    private static long getTime() {
        return System.currentTimeMillis();
    }

    public static int[] generate(int n) {
        return IntStream.rangeClosed(1, n).toArray();
    }
}
