package intern_java_development;
import java.io.*;
import java.util.*;

class Contact {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + emailAddress;
    }
}

class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(String name) {
        contacts.removeIf(contact -> contact.getName().equals(name));
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                return contact;
            }
        }
        return null;
    }

    public void displayAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("Address book is empty.");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}

public class TASK_4 {
    private static final String FILE_NAME = "contacts.txt";

    public static void main(String[] args) {
        AddressBook addressBook = readDataFromFile();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAddress Book System Menu:");
            System.out.println("1. Add a new contact");
            System.out.println("2. Remove a contact");
            System.out.println("3. Search for a contact");
            System.out.println("4. Display all contacts");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addContact(addressBook, scanner);
                    break;
                case 2:
                    removeContact(addressBook, scanner);
                    break;
                case 3:
                    searchContact(addressBook, scanner);
                    break;
                case 4:
                    addressBook.displayAllContacts();
                    break;
                case 5:
                    writeDataToFile(addressBook);
                    System.out.println("Exiting the Address Book System.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void addContact(AddressBook add, Scanner sc) {
        System.out.println("Enter name:");
        String name = sc.nextLine();

        System.out.println("Enter phone number:");
        String phoneNumber = sc.nextLine();

        System.out.println("Enter email address:");
        String emailAddress = sc.nextLine();

        Contact newContact = new Contact(name, phoneNumber, emailAddress);
        add.addContact(newContact);
        System.out.println("Contact added successfully.");
    }

    private static void removeContact(AddressBook add, Scanner sc) {
        System.out.println("Enter the name of the contact to remove:");
        String name = sc.nextLine();
        add.removeContact(name);
        System.out.println("Contact removed successfully.");
    }

    private static void searchContact(AddressBook add, Scanner sc) {
        System.out.println("Enter the name of the contact to search:");
        String name = sc.nextLine();
        Contact foundContact = add.searchContact(name);
        if (foundContact != null) {
            System.out.println("Contact found:\n" + foundContact);
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static AddressBook readDataFromFile() {
        AddressBook addressBook = new AddressBook();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            addressBook = (AddressBook) ois.readObject();
            System.out.println("Data loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing data found ");
        }
        return addressBook;
    }

    private static void writeDataToFile(AddressBook addressBook) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(addressBook);
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving data to file.");
            e.printStackTrace();
        }
    }
}
