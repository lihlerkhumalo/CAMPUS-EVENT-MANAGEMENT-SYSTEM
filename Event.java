import java.util.ArrayList;

/**
 * Represents an event with participants and a waitlist.
 */
public class Event {
    private int eventId;
    private String name;
    private String date;
    private String time;
    private String location;
    private int maxParticipants;
    private ArrayList<String> participants;
    private ArrayList<String> waitlist;

    public Event(int eventId, String name, String date, String time, String location, int maxParticipants) {
        if (maxParticipants <= 0) throw new IllegalArgumentException("Max participants must be positive.");
        this.eventId = eventId;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.maxParticipants = maxParticipants;
        this.participants = new ArrayList<>();
        this.waitlist = new ArrayList<>();
    }

    public int getEventId() { return eventId; }
    public String getName() { return name; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getLocation() { return location; }
    public int getMaxParticipants() { return maxParticipants; }
    public ArrayList<String> getParticipants() { return participants; }
    public ArrayList<String> getWaitlist() { return waitlist; }

    public void setName(String name) { this.name = name; }
    public void setDate(String date) { this.date = date; }
    public void setTime(String time) { this.time = time; }
    public void setLocation(String location) { this.location = location; }

    public void setMaxParticipants(int maxParticipants) {
        if (maxParticipants > 0) {
            this.maxParticipants = maxParticipants;
            processWaitlist();
        }
    }

    public void registerStudent(String studentId) {
        if (participants.contains(studentId)) {
            System.out.println("Error: Student already registered.");
            return;
        }
        if (waitlist.contains(studentId)) {
            System.out.println("Error: Student already on waitlist.");
            return;
        }

        if (participants.size() < maxParticipants) {
            participants.add(studentId);
            System.out.println("Successfully registered for " + name + "!");
        } else {
            waitlist.add(studentId);
            System.out.println("Event is full. Added to waitlist for " + name + ".");
        }
    }

    public void cancelRegistration(String studentId) {
        if (participants.remove(studentId)) {
            System.out.println("Registration cancelled.");
            processWaitlist();
        } else if (waitlist.remove(studentId)) {
            System.out.println("Removed from waitlist.");
        } else {
            System.out.println("Error: Student not registered for this event.");
        }
    }

    public void processWaitlist() {
        while (participants.size() < maxParticipants && !waitlist.isEmpty()) {
            String promoted = waitlist.remove(0);
            participants.add(promoted);
            System.out.println("Student " + promoted + " promoted from waitlist to registered!");
        }
    }

    @Override
    public String toString() {
        return "ID: " + eventId + " | Name: " + name + " | Date: " + date +
               " | Time: " + time + " | Location: " + location +
               " | Registered: " + participants.size() + "/" + maxParticipants +
               " | Waitlist: " + waitlist.size();
    }
}