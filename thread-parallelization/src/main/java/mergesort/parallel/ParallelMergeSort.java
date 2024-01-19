package mergesort.parallel;

import java.util.Arrays;

public class ParallelMergeSort {

    private final int[] nums;
    // not in place;
    private final int[] tempArray;

    public ParallelMergeSort(int[] nums) {
        this.nums = nums;
        this.tempArray = new int[nums.length];
    }

    public void showArray() {
        System.out.println(Arrays.toString(nums));
    }

    public void sort() {
        int threads = Runtime.getRuntime().availableProcessors();
        parallelMergeSort(0, nums.length - 1,threads);
    }

    private Thread createThread(int lowIndex, int highIndex, int numOfThreads) {
        return new Thread() {
            @Override
            public void run() {
                parallelMergeSort(lowIndex, highIndex, numOfThreads / 2);
            }
        };
    }

    private void parallelMergeSort(int low, int high, int numOfThreads) {
        if (numOfThreads <= 1) {
            mergeSort(low, high);
            return;
        }
        int middle = (low + high) / 2;
        Thread leftSort = createThread(low, middle, numOfThreads);
        Thread rightSort = createThread(middle + 1, high, numOfThreads);
        leftSort.start();
        rightSort.start();
        try {
            leftSort.join();
            rightSort.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        merge(low, middle, high);
    }

    private void mergeSort(int low, int high) {
        if (low >= high) {
            return;
        }
        int middleIndex = (low + high) / 2;
        // keep splitting in smaller sub-problems
        mergeSort(low, middleIndex);
        mergeSort(middleIndex + 1, high);
        // we combine the sub-solutions
        merge(low, middleIndex, high);
    }

    private void merge(int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            tempArray[i] = nums[i];
        }
        int i = low;
        int j = middle + 1;
        int k = low;
        // we consider temp, and we copy to original
        while (i <= middle && j <= high) {
            if (tempArray[i] < tempArray[j]) {
                nums[k] = tempArray[i];
                ++i;
            } else {
                nums[k] = tempArray[j];
                ++j;
            }
            ++k;
        }
        while (i <= middle) {
            nums[k] = tempArray[i];
            ++i;
            ++k;
        }
        while (j <= high) {
            nums[k] = tempArray[j];
            ++j;
            ++k;
        }
    }

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
