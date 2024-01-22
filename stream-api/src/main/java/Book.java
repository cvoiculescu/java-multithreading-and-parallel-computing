import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {
    private String title;
    private String author;
    private int numberOfPages;
    private Type type;
}
