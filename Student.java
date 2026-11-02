/**
 * Represents a student user.
 */
public class Student extends User {
    public Student(String id, String name) {
        super(id, name);
    }

    @Override
    public void showMenu() {
        System.out.println("\n=== Student Menu ===");
        System.out.println("1. View Available Events");
        System.out.println("2. Register for Event");
        System.out.println("3. Cancel Registration");
        System.out.println("4. View My Registration Status");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }
}