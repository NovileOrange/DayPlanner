package Service;

import Model.Schelcude;
import Model.Task;
import Model.RecurringTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Dashboard {
    private final Schelcude schelcude;

    public Dashboard(Schelcude schelcude) {
        this.schelcude = schelcude;
    }

    public void displayStatistics() {
        List<Task> tasks = schelcude.getTasks();
        List<DateTasks> dateTasksList = new ArrayList<>();

        tasks.forEach(task -> {
            if (task instanceof RecurringTask) {
                addRecurringTaskToList(dateTasksList, (RecurringTask) task);
            } else {
                addTaskToList(dateTasksList, task);
            }
        });

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");

        for (DateTasks dateTasks : dateTasksList) {
            System.out.println(dateFormatter.format(dateTasks.getDate()));
            System.out.println("-----------------------------------");
            for (Task task : dateTasks.getTasks()) {
                String startTime = timeFormatter.format(task.getStart().toLocalTime());
                String endTime = timeFormatter.format(task.getEnd().toLocalTime());
                System.out.printf("%s-%s | %s%n", startTime, endTime, task.getTitle());
            }
            System.out.println();
        }
    }

    private void addTaskToList(List<DateTasks> dateTasksList, Task task) {
        LocalDateTime dateKey = task.getStart().toLocalDate().atStartOfDay();
        DateTasks dateTasks = findDateTasks(dateTasksList, dateKey);
        if (dateTasks == null) {
            dateTasks = new DateTasks(dateKey);
            dateTasksList.add(dateTasks);
        }
        dateTasks.addTask(task);
    }

    private void addRecurringTaskToList(List<DateTasks> dateTasksList, RecurringTask recurringTask) {
        LocalDateTime startDate = recurringTask.getStart();
        LocalDateTime endDate = recurringTask.getEnd();
        int occurrenceCount = recurringTask.getOccurrenceCount();

        for (int i = 0; i < occurrenceCount; i++) {
            LocalDateTime occurrenceDate = getNextOccurrence(startDate, recurringTask.getRecurrence(), i);
            LocalDateTime occurrenceEnd = occurrenceDate.plusHours(endDate.getHour() - startDate.getHour())
                    .plusMinutes(endDate.getMinute() - startDate.getMinute());
            addTaskToList(dateTasksList, new Task(recurringTask.getTitle(), occurrenceDate, occurrenceEnd));
        }
    }

    private LocalDateTime getNextOccurrence(LocalDateTime startDate, String recurrence, int occurrenceIndex) {
        return switch (recurrence.toUpperCase()) {
            case "DAILY" -> startDate.plusDays(occurrenceIndex);
            case "WEEKLY" -> startDate.plusWeeks(occurrenceIndex);
            case "MONTHLY" -> startDate.plusMonths(occurrenceIndex);
            default -> startDate;
        };
    }

    private DateTasks findDateTasks(List<DateTasks> dateTasksList, LocalDateTime date) {
        for (DateTasks dt : dateTasksList) {
            if (dt.getDate().isEqual(date)) {
                return dt;
            }
        }
        return null;
    }
}
