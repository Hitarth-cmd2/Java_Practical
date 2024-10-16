import java.util.ArrayList;
import java.util.Scanner;

// Student Class
class Student {
    private String name;
    private int roomNumber;
    private boolean hasPaidFees;
    private double feeAmountPaid;

    public Student(String name, int roomNumber, boolean hasPaidFees, double feeAmountPaid) {
        this.name = name;
        this.roomNumber = roomNumber;
        this.hasPaidFees = hasPaidFees;
        this.feeAmountPaid = feeAmountPaid;
    }

    public String getName() {
        return name;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean hasPaidFees() {
        return hasPaidFees;
    }

    public void setFeesPaid(boolean hasPaidFees, double amount) {
        this.hasPaidFees = hasPaidFees;
        this.feeAmountPaid = amount;
    }

    public double getFeeAmountPaid() {
        return feeAmountPaid;
    }

    public String toString() {
        return "Student: " + name + ", Room: " + roomNumber + ", Fees Paid: " + (hasPaidFees ? "Yes" : "No") + ", Amount: $" + feeAmountPaid;
    }
}

// Room Class
class Room {
    private int roomNumber;
    private boolean isAvailable;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String toString() {
        return "Room " + roomNumber + ": " + (isAvailable ? "Available" : "Occupied");
    }
}

// Hostel Class
class Hostel {
    private ArrayList<Student> students;
    private ArrayList<Room> rooms;

    public Hostel() {
        students = new ArrayList<>();
        rooms = new ArrayList<>();

        // Initialize rooms
        for (int i = 1; i <= 10; i++) {
            rooms.add(new Room(i));
        }
    }

    public void addStudent(String name, int roomNumber, double feeAmount) {
        if (roomNumber <= rooms.size() && rooms.get(roomNumber - 1).isAvailable()) {
            Student student = new Student(name, roomNumber, feeAmount == 1000, feeAmount);
            students.add(student);
            rooms.get(roomNumber - 1).setAvailable(false);
            System.out.println("Student added and assigned to Room " + roomNumber);
        } else {
            System.out.println("Room not available.");
        }
    }

    public void removeStudent(String name) {
        Student studentToRemove = null;
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                studentToRemove = student;
                rooms.get(student.getRoomNumber() - 1).setAvailable(true);
                break;
            }
        }

        if (studentToRemove != null) {
            students.remove(studentToRemove);
            System.out.println("Student " + name + " removed.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public void viewRooms() {
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public void updateFeeStatus(String name, double amountPaid) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                boolean fullPayment = amountPaid == 1000;
                student.setFeesPaid(fullPayment, amountPaid);
                System.out.println("Updated fee status for " + name);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void searchStudent(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                System.out.println("Student Found: " + student);
                return;
            }
        }
        System.out.println("Student not found.");
    }
}

// Menu Class
class Menu {
    private Hostel hostel;

    public Menu(Hostel hostel) {
        this.hostel = hostel;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n-----SHREEDEEP RESIDENCY-----");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. View Students");
            System.out.println("4. View Rooms");
            System.out.println("5. Update Fees");
            System.out.println("6. Search Student");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    hostel.viewStudents();
                    break;
                case 4:
                    hostel.viewRooms();
                    break;
                case 5:
                    updateFees();
                    break;
                case 6:
                    searchStudent();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    private void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter room number (1-10): ");
        int roomNumber = scanner.nextInt();
        System.out.print("Enter fee amount paid (max 1000): ");
        double feeAmount = scanner.nextDouble();

        hostel.addStudent(name, roomNumber, feeAmount);
    }

    private void removeStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student name to remove: ");
        String name = scanner.nextLine();

        hostel.removeStudent(name);
    }

    private void updateFees() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter amount paid (max 1000): ");
        double amountPaid = scanner.nextDouble();

        hostel.updateFeeStatus(name, amountPaid);
    }

    private void searchStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        hostel.searchStudent(name);
    }
}

// Main Class
public class mini {
    public static void main(String[] args) {
        Hostel hostel = new Hostel();
        Menu menu = new Menu(hostel);
        menu.showMenu();
    }
}
