package mergesort;

import mergesort.parallel.ParallelMergeSort;
import mergesort.sequential.SequentialMergeSort;

import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) {
        int n = 50_000_000;

        int[] nums = generate(n);
        ParallelMergeSort pms = new ParallelMergeSort(nums);
        long start = getTime();
        pms.sort();
        System.out.println("Parallel: " + (getTime() - start));

        nums = generate(n);
        SequentialMergeSort sms = new SequentialMergeSort(nums);
        start = getTime();
        sms.sort();
        System.out.println("Sequential: " + (getTime() - start));
    }

    private static long getTime() {
        return System.currentTimeMillis();
    }

    public static int[] generate(int n) {
        return IntStream.rangeClosed(-n, -1).map(x -> -x).toArray();
    }
}
