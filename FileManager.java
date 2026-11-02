import java.io.*;
import java.util.ArrayList;

/**
 * Handles persistent reading and writing to events.txt.
 */
public class FileManager {
    private static final String FILE_NAME = "events.txt";

    public static void saveEvents(ArrayList<Event> events) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Event e : events) {
                String partsStr = e.getParticipants().isEmpty() ? "NONE" : String.join("~", e.getParticipants());
                String waitStr = e.getWaitlist().isEmpty() ? "NONE" : String.join("~", e.getWaitlist());

                pw.println(e.getEventId() + ";" + e.getName() + ";" + e.getDate() + ";" +
                           e.getTime() + ";" + e.getLocation() + ";" + e.getMaxParticipants() + ";" +
                           partsStr + ";" + waitStr);
            }
        } catch (IOException ex) {
            System.out.println("Error saving events: " + ex.getMessage());
        }
    }

    public static ArrayList<Event> loadEvents() {
        ArrayList<Event> events = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return events;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 8) {
                    int id = Integer.parseInt(parts[0]);
                    int max = Integer.parseInt(parts[5]);
                    Event event = new Event(id, parts[1], parts[2], parts[3], parts[4], max);

                    if (!parts[6].equals("NONE")) {
                        for (String p : parts[6].split("~")) event.getParticipants().add(p);
                    }
                    if (!parts[7].equals("NONE")) {
                        for (String w : parts[7].split("~")) event.getWaitlist().add(w);
                    }
                    events.add(event);
                }
            }
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Error loading events: " + ex.getMessage());
        }
        return events;
    }
}