import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TaskCreator {
    private Scanner scanner;
    private DateManager dateManager;
    private TimeManager timeManager;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public TaskCreator() {
        this.scanner = new Scanner(System.in);
        this.dateManager = new DateManager(scanner);
        this.timeManager = new TimeManager(scanner);
    }

    public Task createTask() {
        LocalDateTime taskDate = dateManager.getDateInput();

        LocalTime startLocalTime = getTimeFromUser("Start time (HH:mm): ");
        LocalTime endLocalTime = getTimeFromUser("End time (HH:mm): ");

        if (endLocalTime.isBefore(startLocalTime)) {
            System.out.println("Why are end is earlier than start? \n");
            return null;
        }

        LocalDateTime start = LocalDateTime.of(taskDate.toLocalDate(), startLocalTime);
        LocalDateTime end = LocalDateTime.of(taskDate.toLocalDate(), endLocalTime);

        System.out.println("Enter the title of the task: ");
        String title = scanner.nextLine();

        return new Task(title, start, end);
    }

    private LocalTime getTimeFromUser(String prompt) {
        while (true) {
            System.out.println(prompt);
            String timeInput = scanner.nextLine();
            try {
                return LocalTime.parse(timeInput, TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format");
            }
        }
    }
}
