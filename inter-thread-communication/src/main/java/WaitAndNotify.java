class WaitAndNotifyProcess{
    public void consume()throws InterruptedException{
        synchronized (this){
            System.out.println("Running producer method...");
            wait();
            System.out.println("Again in producer method...");
        }
    }
    public void produce()throws InterruptedException{
        Thread.sleep(1000);
        synchronized (this){
            System.out.println("Running consumer method");
            notify();
            Thread.sleep(5000);
        }
    }
}

public class WaitAndNotify {
    public static void main(String[] args) {
        WaitAndNotifyProcess process = new WaitAndNotifyProcess();
        Thread t1 = new Thread(()->{
            try {
                process.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(()->{
            try {
                process.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
    }
}
