/**
 * Represents a staff user.
 */
public class Staff extends User {
    public Staff(String id, String name) {
        super(id, name);
    }

    @Override
    public void showMenu() {
        System.out.println("\n=== Staff Menu ===");
        System.out.println("1. Create Event");
        System.out.println("2. Update Event");
        System.out.println("3. Cancel Event");
        System.out.println("4. View Event Participants & Waitlist");
        System.out.println("5. View All Events");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }
}