package Service;

import java.time.LocalDateTime;
import java.util.Scanner;

public class DateManager {
    private Scanner scanner;
    private LocalDateTime rememberedDate;

    public DateManager(Scanner scanner) {
        this.scanner = scanner;
        this.rememberedDate = null;
    }

    public LocalDateTime getDateInput() {
        if (rememberedDate != null) {
            System.out.println("Remembered date: " + rememberedDate.toLocalDate() + ". Type 'Change' to enter a new date or press Enter to continue with old one:");
            String input = scanner.nextLine();

            if ("Change".equalsIgnoreCase(input)) {
                rememberedDate = null;
                return promptForDate();
            } else {
                return rememberedDate;
            }
        } else {
            return promptForDate();
        }
    }

    private LocalDateTime promptForDate() {
        System.out.println("Enter the month (1-12): ");
        int month = scanner.nextInt();

        System.out.println("Enter the day (1-31): ");
        int day = scanner.nextInt();

        scanner.nextLine();

        rememberedDate = LocalDateTime.of(2024, month, day, 0, 0);
        return rememberedDate;
    }
}
