import java.util.concurrent.ForkJoinPool;

public class FibonacciTaskApp {
    public static void main(String[] args) {
        long n = 10;
        FibonacciTask task = new FibonacciTask(n);
        ForkJoinPool pool = new ForkJoinPool();
        System.out.printf("%dth fibonacci element is %d%n",n, pool.invoke(task));

    }
}
