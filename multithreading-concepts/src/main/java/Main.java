class Worker implements Runnable {

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }

    private volatile boolean terminated;

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        while (!terminated) {
            System.out.println("Working class is running");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Working class is terminated");
    }

}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Worker w = new Worker();
        Thread t = new Thread(w);
        t.start();
        Thread.sleep(3000);
        w.setTerminated(true);
    }
}