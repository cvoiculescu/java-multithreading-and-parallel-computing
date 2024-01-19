package sum;

import java.util.List;
import java.util.stream.IntStream;

public class Parallel {

    class Worker extends Thread {
        private final int[] nums;
        private final int lowIndex;
        private final int highIndex;

        public Worker(int[] nums, int lowIndex, int highIndex) {
            super();
            this.nums = nums;
            this.lowIndex = lowIndex;
            this.highIndex = Math.min(highIndex, nums.length);
        }

        private int partialSum = 0;

        @Override
        public void run() {
            for (int i = lowIndex; i < highIndex; i++) {
                partialSum += nums[i];
            }
        }

        public int getPartialSum() {
            return partialSum;
        }
    }

    private final int numOfThreads = Runtime.getRuntime().availableProcessors();

    public int sum(int[] nums) throws InterruptedException {
        int size = (int) Math.ceil(nums.length * 1.0 / numOfThreads);
        List<Worker> workers = IntStream.rangeClosed(0, numOfThreads - 1)
                .mapToObj(i -> new Worker(nums, i * size, (i + 1) * size)).toList();
        workers.forEach(Thread::start);
        for (Worker worker : workers) {
            worker.join();
        }
        return workers.stream()
                .map(Worker::getPartialSum)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
