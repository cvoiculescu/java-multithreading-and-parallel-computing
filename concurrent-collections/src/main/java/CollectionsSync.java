import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class CollectionsSync {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> nums = new ArrayList<>();
        //not synced
        Thread t1 = new Thread(() -> IntStream.range(1, 1001).forEach(nums::add));
        Thread t2 = new Thread(() -> IntStream.range(1, 1001).forEach(nums::add));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(nums.size());
        // Synchronized
        List<Integer> syncNums = Collections.synchronizedList(new ArrayList<>());
        Thread t3 = new Thread(() -> IntStream.range(1, 1001).forEach(syncNums::add));
        Thread t4 = new Thread(() -> IntStream.range(1, 1001).forEach(syncNums::add));
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.println(syncNums.size());


    }
}
