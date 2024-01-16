class Runner1 implements Runnable {
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

class Runner2 {
    public void execute() {
        for (int i = 0; i < 10; ++i) {
            System.out.println("Runner2: " + i);
        }
    }

}

public class ThreadWithRunnable {
    public static void main(String[] args) {
        // Runnable Class
        Thread t1 = new Thread(new Runner1());
        // Anonymous Runnable
        Thread t2 = new Thread(() -> (new Runner2()).execute());
        t1.start();
        t2.start();
    }


}
