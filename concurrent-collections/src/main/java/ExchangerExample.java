import java.util.concurrent.Exchanger;

class FirstExchangeThread implements Runnable {
    private int counter;
    private Exchanger<Integer> exchanger;

    public FirstExchangeThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            try {
                counter++;
                System.out.println("First thread incremented the counter: " + counter);
                counter = exchanger.exchange(counter);
                System.out.println("First thread got the counter: " + counter);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class SecondExchangeThread implements Runnable {
    private int counter;
    private Exchanger<Integer> exchanger;

    public SecondExchangeThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            try {
                counter--;
                System.out.println("Second thread decremented the counter: " + counter);
                counter = exchanger.exchange(counter);
                System.out.println("Second thread got the counter: " + counter);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        new Thread(new FirstExchangeThread(exchanger)).start();
        new Thread(new SecondExchangeThread(exchanger)).start();
    }
}
