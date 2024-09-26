import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskCreator taskCreator = new TaskCreator();
        Schelcude schelcude = new Schelcude();

        createTestData(schelcude);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("1) Create task");
                System.out.println("2) Update task");
                System.out.println("3) Delete task");
                System.out.println("4) View dashboard");
                System.out.println("5) Exit \n");
                System.out.print("Choose option: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        try {
                            Task task = taskCreator.createTask();
                            schelcude.addTask(task);
                        } catch (Exception e) {
                            System.out.println("Error creating task: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("Name task to update: ");
                        String oldTitle = scanner.nextLine();
                        try {
                            Task taskToUpdate = schelcude.findTaskByTitle(oldTitle);
                            if (taskToUpdate != null) {
                                Task newTaskDetails = taskCreator.createTask();
                                schelcude.updateTask(taskToUpdate, newTaskDetails);
                                System.out.println("Task updated: " + newTaskDetails);
                            } else {
                                System.out.println("Task not found.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error updating task: " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("Name task to delete: ");
                        String titleToDelete = scanner.nextLine();
                        try {
                            Task taskToDelete = schelcude.findTaskByTitle(titleToDelete);
                            if (taskToDelete != null) {
                                schelcude.deleteTask(taskToDelete);
                            } else {
                                System.out.println("Task not found.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error deleting task: " + e.getMessage());
                        }
                        break;
                    case 4:
                        try {
                            Dashboard dashboard = new Dashboard(schelcude);
                            dashboard.displayStatistics();
                        } catch (Exception e) {
                            System.out.println("Error displaying dashboard: " + e.getMessage());
                        }
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Please choose a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear the invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private static void createTestData(Schelcude schelcude) {
        try {
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

            RecurringTask recurringTask = new RecurringTask(
                    "ew",
                    LocalDateTime.of(2024, 1, 1, 12, 0),
                    LocalDateTime.of(2024, 1, 1, 13, 0),
                    "DAILY",
                    2
            );

            schelcude.addTask(task1);
            schelcude.addTask(task2);
            schelcude.addTask(task3);
            schelcude.addTask(recurringTask);
        } catch (Exception e) {
            System.out.println("Error creating test data: " + e.getMessage());
        }
    }
}
