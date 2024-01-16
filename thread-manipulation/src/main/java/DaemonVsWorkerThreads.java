class Worker implements Runnable {
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("Hello world from %s thread...%n", name);
    }
}

class DaemonWorker implements Runnable {

    @SuppressWarnings({"InfiniteLoopStatement", "BusyWait"})
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Daemon thread is running...");
        }
    }
}

class NormalWorker implements Runnable {

    @Override
    public void run() {
        System.out.println("Normal Thread started...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Normal Thread finished!");
    }
}

public class DaemonVsWorkerThreads {
    public static void main(String[] args) {
        System.out.println("Main Thread: " + Thread.currentThread().getName());
        // daemon thread
        Thread t1 = new Thread(new DaemonWorker());
        t1.setDaemon(true);
        t1.start();
        // not daemon thread
        Thread t2 = new Thread(new NormalWorker());
        t2.start();
    }
}
