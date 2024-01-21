import java.util.concurrent.ForkJoinPool;

public class ForkJoinExample {
    public static void main(String[] args) {
        System.out.println("CPUs: " + Runtime.getRuntime().availableProcessors());
        ForkJoinPool pool = new ForkJoinPool();
        SimpleRecursiveAction action = new SimpleRecursiveAction(2000);
        pool.invoke(action);
    }
}
