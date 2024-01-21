import java.util.concurrent.ForkJoinPool;

public class PrintIntegersExample {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        PrintIntegersRecursiveAction action = new PrintIntegersRecursiveAction(array);
        pool.invoke(action);
    }
}
