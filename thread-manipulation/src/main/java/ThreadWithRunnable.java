class RunnerRunnable1 implements Runnable {
    public void execute() {
        for (int i = 0; i < 10; ++i) {
            System.out.println("Runner1: " + i);
        }
    }

    @Override
    public void run() {
        execute();
    }
}

class RunnerRunnable2 {
    public void execute() {
        for (int i = 0; i < 10; ++i) {
            System.out.println("Runner2: " + i);
        }
    }

}

public class ThreadWithRunnable {
    public static void main(String[] args) {
        // Runnable Class
        Thread t1 = new Thread(new RunnerRunnable1());
        // Anonymous Runnable
        Thread t2 = new Thread(() -> (new RunnerRunnable2()).execute());
        t1.start();
        t2.start();
    }


}
