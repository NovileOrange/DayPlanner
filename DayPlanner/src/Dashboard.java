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

        Map<LocalDateTime, List<Task>> tasksByDate = new TreeMap<>();

        tasks.stream()
                .filter(task -> !(task instanceof RecurringTask))
                .forEach(task -> addTaskToMap(tasksByDate, task));

        tasks.stream()
                .filter(task -> task instanceof RecurringTask)
                .forEach(task -> addRecurringTaskToMap(tasksByDate, (RecurringTask) task));

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

    private void addTaskToMap(Map<LocalDateTime, List<Task>> tasksByDate, Task task) {
        LocalDateTime dateKey = LocalDateTime.of(task.getStart().getYear(),
                task.getStart().getMonth(),
                task.getStart().getDayOfMonth(),
                0, 0);
        tasksByDate.computeIfAbsent(dateKey, k -> new java.util.ArrayList<>()).add(task);
    }

    private void addRecurringTaskToMap(Map<LocalDateTime, List<Task>> tasksByDate, RecurringTask recurringTask) {
        LocalDateTime startDate = recurringTask.getStart();
        LocalDateTime endDate = recurringTask.getEnd();
        int occurrenceCount = recurringTask.getOccurrenceCount();

        for (int i = 0; i < occurrenceCount; i++) {
            LocalDateTime occurrenceDate = getNextOccurrence(startDate, recurringTask.getRecurrence(), i);
            LocalDateTime occurrenceEnd = occurrenceDate.plusHours(endDate.getHour() - startDate.getHour())
                    .plusMinutes(endDate.getMinute() - startDate.getMinute());
            Task occurrenceTask = new Task(recurringTask.getTitle(), occurrenceDate, occurrenceEnd);
            addTaskToMap(tasksByDate, occurrenceTask);
        }
    }

    private LocalDateTime getNextOccurrence(LocalDateTime startDate, String recurrence, int occurrenceIndex) {
        switch (recurrence.toUpperCase()) {
            case "DAILY":
                return startDate.plusDays(occurrenceIndex);
            case "WEEKLY":
                return startDate.plusWeeks(occurrenceIndex);
            case "MONTHLY":
                return startDate.plusMonths(occurrenceIndex);
            default:
                return startDate;
        }
    }
}
