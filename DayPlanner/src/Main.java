import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) {
        Task myTask = new Task(
                "Go do Java lab",
                LocalDateTime.of(2024, 9, 13, 10, 0),
                LocalDateTime.of(2024, 9, 13, 12, 0)
        );

        Task myTask2 = new Task(
                "Go do Java lab 2",
                LocalDateTime.of(2024, 9, 13, 10, 0),
                LocalDateTime.of(2024, 9, 13, 12, 0)
        );

        Schelcude schelcude = new Schelcude();
        schelcude.addTask(myTask);
        schelcude.addTask(myTask2);
        System.out.println(myTask);
        System.out.println(schelcude);
    }
}