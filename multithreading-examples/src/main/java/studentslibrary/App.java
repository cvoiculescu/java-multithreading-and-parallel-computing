package studentslibrary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        Student[] students = null;
        Book[] books = null;
        ExecutorService executorService = null;
        try {
            books = new Book[Constants.NUMBER_OF_BOOKS];
            students = new Student[Constants.NUMBER_OF_STUDENTS];
            for (int i = 0; i < Constants.NUMBER_OF_BOOKS; i++) {
                books[i] = new Book(i+1);
            }
            executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_STUDENTS);
            for (int i = 0; i < Constants.NUMBER_OF_STUDENTS; i++) {
                students[i] = new Student(i+1, books);
            }
            for (Student student : students) {
                executorService.execute(student);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }
}
