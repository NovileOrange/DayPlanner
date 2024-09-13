import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;  // For filtering tasks using streams

public class Schelcude {
    private List<Task> tasks;

    public Schelcude() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasksForDay(LocalDate date) {
        return tasks.stream()
                .filter(task -> task.getStartTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Planner: " + tasks;
    }
}
