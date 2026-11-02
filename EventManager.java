import java.util.ArrayList;

/**
 * Business logic manager for events.
 */
public class EventManager {
    private ArrayList<Event> events;

    public EventManager() {
        events = new ArrayList<>();
    }

    public void setEvents(ArrayList<Event> events) { this.events = events; }
    public ArrayList<Event> getEvents() { return events; }

    public void addEvent(Event e) {
        if (findEventById(e.getEventId()) != null) {
            System.out.println("Error: Event ID already exists.");
            return;
        }
        events.add(e);
        System.out.println("Event added successfully.");
    }

    public Event findEventById(int id) {
        for (Event e : events) {
            if (e.getEventId() == id) return e;
        }
        return null;
    }

    public void updateEvent(int id, String newName, String newDate, String newTime, String newLocation, int newMax) {
        Event e = findEventById(id);
        if (e != null) {
            if (!newName.isEmpty()) e.setName(newName);
            if (!newDate.isEmpty()) e.setDate(newDate);
            if (!newTime.isEmpty()) e.setTime(newTime);
            if (!newLocation.isEmpty()) e.setLocation(newLocation);
            if (newMax > 0) e.setMaxParticipants(newMax);
            System.out.println("Event updated successfully.");
        } else {
            System.out.println("Event not found.");
        }
    }

    public void cancelEvent(int id) {
        Event e = findEventById(id);
        if (e != null) {
            events.remove(e);
            System.out.println("Event cancelled successfully.");
        } else {
            System.out.println("Event not found.");
        }
    }

    public void viewParticipants(int id) {
        Event e = findEventById(id);
        if (e != null) {
            System.out.println("Registered Participants: " + e.getParticipants());
            System.out.println("Waitlist: " + e.getWaitlist());
        } else {
            System.out.println("Event not found.");
        }
    }

    public void viewStudentStatus(String studentId) {
        boolean found = false;
        for (Event e : events) {
            if (e.getParticipants().contains(studentId)) {
                System.out.println("Event: " + e.getName() + " (ID: " + e.getEventId() + ") - Status: REGISTERED");
                found = true;
            } else if (e.getWaitlist().contains(studentId)) {
                System.out.println("Event: " + e.getName() + " (ID: " + e.getEventId() + ") - Status: WAITLISTED");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No registrations or waitlist records found for student ID: " + studentId);
        }
    }

    public void viewEvents() {
        if (events.isEmpty()) {
            System.out.println("No events available.");
            return;
        }
        System.out.println("=== Event List ===");
        for (Event e : events) {
            System.out.println(e);
        }
    }
}