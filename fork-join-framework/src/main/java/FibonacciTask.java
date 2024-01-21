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
        // current thread computes the n1
        // create another thread and insert into the pool just for n2
        return n1.compute() + n2.join();
    }
}