import java.util.Scanner;

/**
 * Console UI driver application with outer loop for role switching.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventManager manager = new EventManager();
        
        // Load existing state from file
        manager.setEvents(FileManager.loadEvents());

        boolean systemRunning = true;

        while (systemRunning) {
            System.out.println("\n==================================================");
            System.out.println("   Welcome to Campus Event Management System      ");
            System.out.println("==================================================");
            System.out.println("Select User Role:");
            System.out.println("1. Student");
            System.out.println("2. Staff");
            System.out.println("3. Exit System");
            System.out.print("Enter your choice (1-3");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            int roleChoice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            if (roleChoice == 3) {
                systemRunning = false;
                break;
            }

            User currentUser = null;

            if (roleChoice == 1) {
                System.out.print("Enter Student ID: ");
                String id = scanner.nextLine();
                System.out.print("Enter Student Name: ");
                String name = scanner.nextLine();
                currentUser = new Student(id, name);
            } else if (roleChoice == 2) {
                System.out.print("Enter Staff ID: ");
                String id = scanner.nextLine();
                System.out.print("Enter Staff Name: ");
                String name = scanner.nextLine();
                currentUser = new Staff(id, name);
            } else {
                System.out.println("Invalid option. Please select 1, 2, or 3.");
                continue;
            }

            // Inner loop for the logged-in user session
            boolean sessionActive = true;
            while (sessionActive) {
                currentUser.showMenu();

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a menu number.");
                    scanner.nextLine();
                    continue;
                }

                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                if (currentUser instanceof Student) {
                    switch (choice) {
                        case 1:
                            manager.viewEvents();
                            break;
                        case 2:
                            System.out.print("Enter Event ID to Register: ");
                            int regId = scanner.nextInt();
                            Event regEvent = manager.findEventById(regId);
                            if (regEvent != null) {
                                regEvent.registerStudent(currentUser.getId());
                            } else {
                                System.out.println("Event not found.");
                            }
                            break;
                        case 3:
                            System.out.print("Enter Event ID to Cancel Registration: ");
                            int cancelId = scanner.nextInt();
                            Event cancelEvent = manager.findEventById(cancelId);
                            if (cancelEvent != null) {
                                cancelEvent.cancelRegistration(currentUser.getId());
                            } else {
                                System.out.println("Event not found.");
                            }
                            break;
                        case 4:
                            manager.viewStudentStatus(currentUser.getId());
                            break;
                        case 5:
                            System.out.println("Logging out of Student session...");
                            sessionActive = false;
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                } else if (currentUser instanceof Staff) {
                    switch (choice) {
                        case 1:
                            System.out.print("Enter Event ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Enter Event Name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter Event Date (YYYY-MM-DD): ");
                            String date = scanner.nextLine();
                            System.out.print("Enter Event Time: ");
                            String time = scanner.nextLine();
                            System.out.print("Enter Location: ");
                            String loc = scanner.nextLine();
                            System.out.print("Enter Max Participants: ");
                            int max = scanner.nextInt();
                            manager.addEvent(new Event(id, name, date, time, loc, max));
                            break;
                        case 2:
                            System.out.print("Enter Event ID to Update: ");
                            int upId = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("New Name (leave blank to keep current): ");
                            String uName = scanner.nextLine();
                            System.out.print("New Date (leave blank to keep current): ");
                            String uDate = scanner.nextLine();
                            System.out.print("New Time (leave blank to keep current): ");
                            String uTime = scanner.nextLine();
                            System.out.print("New Location (leave blank to keep current): ");
                            String uLoc = scanner.nextLine();
                            System.out.print("New Max Capacity (enter 0 to keep current): ");
                            int uMax = scanner.nextInt();
                            manager.updateEvent(upId, uName, uDate, uTime, uLoc, uMax);
                            break;
                        case 3:
                            System.out.print("Enter Event ID to Cancel: ");
                            int cId = scanner.nextInt();
                            manager.cancelEvent(cId);
                            break;
                        case 4:
                            System.out.print("Enter Event ID: ");
                            int vId = scanner.nextInt();
                            manager.viewParticipants(vId);
                            break;
                        case 5:
                            manager.viewEvents();
                            break;
                        case 6:
                            System.out.println("Logging out of Staff session...");
                            sessionActive = false;
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                }
            }
        }

        // Save data when system completely exits
        FileManager.saveEvents(manager.getEvents());
        System.out.println("Data saved successfully. Application closed.");
        scanner.close();
    }
}