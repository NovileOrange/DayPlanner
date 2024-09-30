package Service;

import Model.Task;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DateTasks {
    private LocalDateTime date;
    private List<Task> tasks;

    public DateTasks(LocalDateTime date) {
        this.date = date;
        this.tasks = new ArrayList<>();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
