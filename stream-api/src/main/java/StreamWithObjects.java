import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamWithObjects {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Being and time", "Heidegger", 560, Type.PHILOSOPHY));
        books.add(new Book("The trial", "Frantz Kafka", 240, Type.NOVEL));
        books.add(new Book("Death on the Nile", "Agatha Christie", 370, Type.THRILLER));
        books.add(new Book("Ancient Greece", "Robert F.", 435, Type.HISTORY));
        books.add(new Book("Ancient Rome", "Robert F.", 860, Type.HISTORY));
        books.add(new Book("Death of Virgil", "Herman Broch", 590, Type.NOVEL));
        books.add(new Book("The Stranger", "Albert Camus", 560, Type.NOVEL));

        List<Book> result = books.stream().filter(b -> b.getType() == Type.NOVEL).toList();
        result.forEach(System.out::println);
        System.out.println();
        result.stream().sorted(Comparator.comparing(Book::getTitle)).forEach(System.out::println);
        Map<Type, List<Book>> booksByType = books.stream().collect(Collectors.groupingBy(Book::getType));
        System.out.println();
        booksByType.entrySet().forEach(System.out::println);
        System.out.println();
        books.stream().filter(book -> book.getTitle().split(" ").length == 2).forEach(System.out::println);

        // map vs flatmap
        List<String> words = Arrays.asList("Ana", "Adam", "Daniel");
        List<Integer> lengths = words.stream().map(String::length).toList();
        System.out.println(Arrays.toString(lengths.toArray()));

        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squares = nums.stream().map(x -> x * x).toList();
        printList(squares);
        // flatmap
        // "hello","shell" -> get all unique characters (h,e,l,o,s)
        String[] array ={"hello","shell"};
        List<String> list = Arrays.stream(array)
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .toList();
        printList(list);
    }

    private static void printList(List<?> list){
        System.out.println(Arrays.toString(list.toArray()));
    }
}
