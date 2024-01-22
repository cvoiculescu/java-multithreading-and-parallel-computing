import lombok.Getter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Getter
class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final int id;

    public Person(int id) {
        this.id = id;
    }

}

public class ParallelizationExample3 {

    public static final String DIRECTOR = System.getProperty("HOME") + "/test";

    public static void main(String[] args) throws IOException {
        Files.createDirectories(Paths.get(DIRECTOR));
        ParallelizationExample3 example = new ParallelizationExample3();
        List<Person> people = example.generatePeople(100000);
        long start = System.currentTimeMillis();
        people.stream().forEach(ParallelizationExample3::save);
        System.out.println("Sequential: " + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        people.parallelStream().forEach(ParallelizationExample3::save);
        System.out.println("Parallel: " + (System.currentTimeMillis() - start));
    }

    private static void save(Person person) {
        try (FileOutputStream fos = new FileOutputStream(new File(DIRECTOR + person.getId() + ".txt"))) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Person> generatePeople(int num) {
        return Stream.iterate(0, n -> n + 1).limit(num)
                .map(Person::new)
                .toList();
    }


}
