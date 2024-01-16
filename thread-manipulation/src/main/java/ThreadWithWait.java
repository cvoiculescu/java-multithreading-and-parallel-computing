public class ThreadWithWait {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new RunnerThread1();
        t1.start();
        Thread t2 = new RunnerThread2();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Finished with the threads");
    }
}
