import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Dashboard {
    private Schelcude schelcude;

    public Dashboard(Schelcude schelcude) {
        this.schelcude = schelcude;
    }

    public void displayStatistics() {
        List<Task> tasks = schelcude.getTasks();

        Map<LocalDateTime, List<Task>> tasksByDate = tasks.stream()
                .collect(Collectors.groupingBy(task -> LocalDateTime.of(
                        task.getStart().getYear(),
                        task.getStart().getMonth(),
                        task.getStart().getDayOfMonth(),
                        0, 0
                ), TreeMap::new, Collectors.toList()));

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");

        for (Map.Entry<LocalDateTime, List<Task>> entry : tasksByDate.entrySet()) {
            LocalDateTime date = entry.getKey();
            List<Task> tasksForDate = entry.getValue();

            System.out.println(dateFormatter.format(date));
            System.out.println("-----------------------------------");

            for (Task task : tasksForDate) {
                String startTime = timeFormatter.format(task.getStart().toLocalTime());
                String endTime = timeFormatter.format(task.getEnd().toLocalTime());
                System.out.printf("%s-%s | %s%n", startTime, endTime, task.getTitle());
            }
            System.out.println();
        }
    }
}
