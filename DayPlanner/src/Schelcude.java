import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Schelcude {
    private List<Task> tasks;

    public Schelcude() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task findTaskByTitle(String title) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                return task;
            }
        }
        return null;
    }

    public void updateTask(Task oldTask, Task newTaskDetails) {
        int index = tasks.indexOf(oldTask);
        if (index != -1) {
            tasks.set(index, newTaskDetails);
        } else {
            System.out.println("Nuh uh");
        }
    }

    public void deleteTask(Task task) {
        if (tasks.remove(task)) {
            System.out.println("Deleted");
        } else {
            System.out.println("Nuh uh");
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        return "Schelcude{" +
                "tasks=" + tasks +
                '}';
    }
}
