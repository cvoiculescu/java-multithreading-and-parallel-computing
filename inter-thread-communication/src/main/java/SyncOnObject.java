public class SyncOnObject {

    public static Integer counter = 0;

    public static final Object object1 = new Object();

    public static void increment() {
        synchronized (object1) {
            counter++;
        }
    }

    public static void process() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
//                counter++;
                increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
//                counter++;
                increment();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Counter is: " + counter);
    }

    public static void main(String[] args) throws InterruptedException {
        process();
    }
}
