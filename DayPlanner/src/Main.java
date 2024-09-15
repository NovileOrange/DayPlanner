import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskCreator taskCreator = new TaskCreator();
        Schelcude schelcude = new Schelcude();

        createTestData(schelcude);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1) Create task");
            System.out.println("2) Update task");
            System.out.println("3) Delete task");
            System.out.println("4) View dashboard \n");
            System.out.print("Choose option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    Task task = taskCreator.createTask();
                    schelcude.addTask(task);
                    break;
                case 2:
                    System.out.println("Name task to update: ");
                    String oldTitle = scanner.nextLine();
                    Task taskToUpdate = schelcude.findTaskByTitle(oldTitle);
                    if (taskToUpdate != null) {
                        Task newTaskDetails = taskCreator.createTask();
                        schelcude.updateTask(taskToUpdate, newTaskDetails);
                        System.out.println("Task updated: " + newTaskDetails);
                    } else {
                        System.out.println("Nuh uh");
                    }
                    break;
                case 3:
                    System.out.println("Name task to delete: ");
                    String titleToDelete = scanner.nextLine();
                    Task taskToDelete = schelcude.findTaskByTitle(titleToDelete);
                    if (taskToDelete != null) {
                        schelcude.deleteTask(taskToDelete);
                    } else {
                        System.out.println("Nuh uh");
                    }
                    break;
                case 4:
                    Dashboard dashboard = new Dashboard(schelcude);
                    dashboard.displayStatistics();
                    break;
                default:
                    System.out.println("Can you type something that makes sense?");
            }
        }
    }

    private static void createTestData(Schelcude schelcude) {
        Task task1 = new Task(
                "DO JAVA",
                LocalDateTime.of(2024, 9, 13, 10, 0),
                LocalDateTime.of(2024, 9, 13, 11, 0)
        );

        Task task2 = new Task(
                "DO JAVA AGAIN",
                LocalDateTime.of(2024, 9, 14, 12, 0),
                LocalDateTime.of(2024, 9, 14, 13, 0)
        );

        Task task3 = new Task(
                "DO JAVA AGAIN AND AGAIN",
                LocalDateTime.of(2024, 9, 15, 14, 0),
                LocalDateTime.of(2024, 9, 15, 15, 0)
        );

        schelcude.addTask(task1);
        schelcude.addTask(task2);
        schelcude.addTask(task3);
    }
}
