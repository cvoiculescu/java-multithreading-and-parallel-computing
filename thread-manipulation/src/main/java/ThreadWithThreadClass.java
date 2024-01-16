class RunnerThread1 extends Thread {

    public void execute() {
        for (int i = 0; i < 10; ++i) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Runner1: " + i);
        }
    }

    @Override
    public void run() {
        execute();
    }
}

class RunnerThread2 extends Thread {

    @Override
    public void run() {
        execute();
    }

    public void execute() {
        for (int i = 0; i < 10; ++i) {
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Runner2: " + i);
        }
    }

}

public class ThreadWithThreadClass {
    public static void main(String[] args) {
        Thread thread1 = new RunnerThread1();
        Thread thread2 = new RunnerThread2();
        thread1.start();
        thread2.start();
    }
}
