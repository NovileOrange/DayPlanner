package Model;

import java.time.LocalDateTime;

public class RecurringTask extends Task {
    private String recurrence;
    private int occurrenceCount;

    public RecurringTask(String title, LocalDateTime start, LocalDateTime end, String recurrence, int occurrenceCount) {
        super(title, start, end);
        this.recurrence = recurrence;
        this.occurrenceCount = occurrenceCount;
    }

    public String getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }

    public int getOccurrenceCount() {
        return occurrenceCount;
    }

    public void setOccurrenceCount(int occurrenceCount) {
        this.occurrenceCount = occurrenceCount;
    }

    @Override
    public String toString() {
        return "Model.RecurringTask{" +
                "title='" + getTitle() + '\'' +
                ", start=" + getStart() +
                ", end=" + getEnd() +
                ", recurrence='" + recurrence + '\'' +
                ", occurrenceCount=" + occurrenceCount +
                '}';
    }
}
