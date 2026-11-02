/**
 * Helper class to represent a student's event registration record.
 */
public class Registration {
    private String studentId;
    private int eventId;
    private String status; // "REGISTERED" or "WAITLISTED"

    public Registration(String studentId, int eventId, String status) {
        this.studentId = studentId;
        this.eventId = eventId;
        this.status = status;
    }

    public String getStudentId() { return studentId; }
    public int getEventId() { return eventId; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Student ID: " + studentId + " | Event ID: " + eventId + " | Status: " + status;
    }
}