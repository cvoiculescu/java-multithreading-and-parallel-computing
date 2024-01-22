import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamWithObjects {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Being and time","Heidegger",560,Type.PHILOSOPHY));
        books.add(new Book("The trial","Frantz Kafka",240,Type.NOVEL));
        books.add(new Book("Death on the Nile","Agatha Christie",370,Type.THRILLER));
        books.add(new Book("Ancient Greece","Robert F.",435,Type.HISTORY));
        books.add(new Book("Ancient Rome","Robert F.",860,Type.HISTORY));
        books.add(new Book("Death of Virgil","Herman Broch",590,Type.NOVEL));
        books.add(new Book("The Stranger","Albert Camus",560,Type.NOVEL));

        List<Book> result = books.stream().filter(b -> b.getType() == Type.NOVEL).toList();
        result.forEach(System.out::println);
        System.out.println();
        result.stream().sorted(Comparator.comparing(Book::getTitle)).forEach(System.out::println);
        Map<Type, List<Book>> booksByType = books.stream().collect(Collectors.groupingBy(Book::getType));
        System.out.println();
        booksByType.entrySet().forEach(System.out::println);
        System.out.println();
        books.stream().filter(book->book.getTitle().split(" ").length==2).forEach(System.out::println);
    }
}
