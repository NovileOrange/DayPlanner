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
        try {
            LocalDateTime taskDate = dateManager.getDateInput();
            LocalTime startLocalTime = getTimeFromUser("Start time (HH:mm): ");
            LocalTime endLocalTime = getTimeFromUser("End time (HH:mm): ");

            if (endLocalTime.isBefore(startLocalTime)) {
                System.out.println("Why is the end earlier than the start? \n");
                return null;
            }

            LocalDateTime start = LocalDateTime.of(taskDate.toLocalDate(), startLocalTime);
            LocalDateTime end = LocalDateTime.of(taskDate.toLocalDate(), endLocalTime);

            System.out.println("Enter the title of the task: ");
            String title = scanner.nextLine();

            System.out.println("Is this a recurring task? (yes/no): ");
            String isRecurring = scanner.nextLine().trim().toLowerCase();

            if (isRecurring.equals("yes")) {
                System.out.println("Enter recurrence pattern (e.g., DAILY, WEEKLY, MONTHLY): ");
                String recurrence = scanner.nextLine();

                System.out.println("Enter the number of occurrences: ");
                int occurrenceCount;
                try {
                    occurrenceCount = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number for occurrences. Please enter a valid integer.");
                    return null;
                }

                System.out.printf("Creating a recurring task: %s, Start: %s, End: %s, Recurrence: %s, Occurrences: %d%n",
                        title, start, end, recurrence, occurrenceCount);

                return new RecurringTask(title, start, end, recurrence, occurrenceCount);
            }

            return new Task(title, start, end);
        } catch (Exception e) {
            System.out.println("An error occurred while creating the task: " + e.getMessage());
            return null;
        }
    }

    private LocalTime getTimeFromUser(String prompt) {
        while (true) {
            System.out.println(prompt);
            String timeInput = scanner.nextLine();
            try {
                return LocalTime.parse(timeInput, TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please use HH:mm.");
            }
        }
    }
}
