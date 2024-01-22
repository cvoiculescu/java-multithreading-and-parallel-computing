import java.util.*;
import java.util.stream.Stream;

public class StreamMapExample {
    public static void main(String[] args) {
        List<Integer> int1 = Arrays.asList(1, 2, 3);
        List<Integer> int2 = Arrays.asList(4, 5);
        List<List<Integer>> list =
                int1.stream()
                .flatMap(x -> int2.stream().map(y -> List.of(x, y)))
                .toList();
        System.out.println(Arrays.toString(list.toArray()));

    }
}
