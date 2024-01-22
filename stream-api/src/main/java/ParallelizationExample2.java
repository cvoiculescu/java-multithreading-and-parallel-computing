import java.util.stream.IntStream;

public class ParallelizationExample2 {

    public static void main(String[] args) {
        long currentTime = System.currentTimeMillis();
        long numOfPrimes = IntStream.rangeClosed(2, Integer.MAX_VALUE / 100).filter(ParallelizationExample2::isPrime).count();
        System.out.println("Sequential: " + (System.currentTimeMillis() - currentTime));
        currentTime = System.currentTimeMillis();
        numOfPrimes = IntStream.rangeClosed(2, Integer.MAX_VALUE / 100).parallel().filter(ParallelizationExample2::isPrime).count();
        System.out.println("Parallel: " + (System.currentTimeMillis() - currentTime));
    }

    public static boolean isPrime(int num) {
        if (num == 2) {
            return true;
        }
        if (num < 2 || num % 2 == 0) {
            return false;
        }
        return IntStream
                .iterate(3, i -> i <= (long) Math.sqrt(num), i -> i + 2)
                .noneMatch(i -> num % i == 0);
    }

}
