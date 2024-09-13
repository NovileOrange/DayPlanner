import java.time.LocalDateTime;

public class Task {
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Task(String title, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    @Override
    public String toString() {
        return "Task: " + title + ", Starts: " + startTime.getHour() + ", Ends: " + endTime.getHour();
    }
}
