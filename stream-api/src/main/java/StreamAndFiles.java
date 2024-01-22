import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StreamAndFiles {
    public static void main(String[] args) throws IOException {
        String path = "/home/cvoicule/Projects/training/udemy/java-multithreading-and-parallel-computing/stream-api/src/main/resources/names";
        Files.lines(Paths.get(path)).forEach(System.out::println);
    }
}
