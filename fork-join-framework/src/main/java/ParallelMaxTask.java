import java.util.concurrent.RecursiveTask;

public class ParallelMaxTask extends RecursiveTask<Long> {

    private long[] nums;
    private int lowIndex;
    private int highIndex;

    public ParallelMaxTask(long[] nums) {
        this.nums = nums;
        lowIndex = 0;
        highIndex = nums.length - 1;
    }

    public ParallelMaxTask(long[] nums, int lowIndex, int highIndex) {
        this.nums = nums;
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
    }

    @Override
    protected Long compute() {
        if (highIndex - lowIndex < 1000) {
            return sequentialMaxFinding();
        } else {
            System.out.println("low,max:(" + lowIndex + "," + highIndex + ")");
            int middle = (lowIndex + highIndex) / 2;
            ParallelMaxTask max1 = new ParallelMaxTask(nums, lowIndex, middle);
            ParallelMaxTask max2 = new ParallelMaxTask(nums, middle + 1, highIndex);
            invokeAll(max1, max2);
            return Math.max(max1.join(), max2.join());
        }
    }

    private Long sequentialMaxFinding() {
        long max = nums[lowIndex];
        for (int i = lowIndex; i <= highIndex; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }
}
