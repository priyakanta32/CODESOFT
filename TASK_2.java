package intern_java_development;
import java.io.*;
import java.util.*;

class Student {
    private String name;
    private int rollno;
    private String grade;

    public Student(String name, int rollno, String grade) {
        this.name = name;
        this.rollno = rollno;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollno;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", rollNumber=" + rollno +
                ", grade='" + grade + '\'' +
                '}';
    }
}

class StudentManagement {
    private List<Student> students;

    public StudentManagement() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(students);
            System.out.println("Data saved to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            students = (List<Student>) ois.readObject();
            System.out.println("Data loaded from file: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data from file: " + e.getMessage());
        }
    }
}

public class TASK_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagement studentManagement = new StudentManagement();

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add a new student");
            System.out.println("2. Remove a student");
            System.out.println("3. Search for a student");
            System.out.println("4. Display all students");
            System.out.println("5. Save data to file");
            System.out.println("6. Load data from file");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent(scanner, studentManagement);
                    break;
                case 2:
                    removeStudent(scanner, studentManagement);
                    break;
                case 3:
                    searchStudent(scanner, studentManagement);
                    break;
                case 4:
                    displayAllStudents(studentManagement);
                    break;
                case 5:
                    saveDataToFile(scanner, studentManagement);
                    break;
                case 6:
                    loadDataFromFile(scanner, studentManagement);
                    break;
                case 7:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
    }

    private static void addStudent(Scanner scanner, StudentManagement studentManagementSystem) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student roll number: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter student grade: ");
        String grade = scanner.nextLine();

        Student newStudent = new Student(name, rollNumber, grade);
        studentManagementSystem.addStudent(newStudent);

        System.out.println("Student added successfully.");
    }

    private static void removeStudent(Scanner sc, StudentManagement student) {
        System.out.print("Enter the roll number of the student to remove: ");
        int rollNumber = sc.nextInt();
        sc.nextLine();

        student.removeStudent(rollNumber);

        System.out.println("Student removed successfully.");
    }

    private static void searchStudent(Scanner sc, StudentManagement student) {
        System.out.print("Enter the roll number of the student to search: ");
        int rollNumber = sc.nextInt();
        sc.nextLine();

        Student foundStudent = student.searchStudent(rollNumber);

        if (foundStudent != null) {
            System.out.println("Student found:\n" + foundStudent);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void displayAllStudents(StudentManagement student) {
        System.out.println("All Students:");
        student.displayAllStudents();
    }

    private static void saveDataToFile(Scanner sc, StudentManagement student) {
        System.out.print("Enter the file name to save data: ");
        String fileName = sc.nextLine();

        student.saveToFile(fileName);
    }

    private static void loadDataFromFile(Scanner sc, StudentManagement student) {
        System.out.print("Enter the file name to load data from: ");
        String fileName = sc.nextLine();

        student.loadFromFile(fileName);
    }
}
