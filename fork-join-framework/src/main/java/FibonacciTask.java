import java.util.concurrent.RecursiveTask;

public class FibonacciTask extends RecursiveTask<Long> {

    long n;

    public FibonacciTask(long n) {
        this.n = n;
    }

    @Override
    protected Long compute() {
        if (n < 2) {
            return n;
        }
        FibonacciTask n1 = new FibonacciTask(n - 1);
        FibonacciTask n2 = new FibonacciTask(n - 2);
        n1.invoke();
        n2.invoke();
        return n1.join() + n2.join();
    }
}