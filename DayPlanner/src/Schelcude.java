import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Schelcude {
    private List<Task> tasks;
    private static final String FILE_PATH = "tasks.txt"; // Path to the text file

    public Schelcude() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        writeTasksToFile();
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
            writeTasksToFile();
        } else {
            System.out.println("Nuh uh");
        }
    }

    public void deleteTask(Task task) {
        if (tasks.remove(task)) {
            System.out.println("Deleted");
            writeTasksToFile();
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

    // Write the list of tasks to a file
    private void writeTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
