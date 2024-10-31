import java.util.ArrayList;
import java.util.Scanner;

// Class representing a Contact
class Contact {
    private String name;
    private String phone;
    private String email;

    // Constructor
    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Method to display contact details
    public void displayContact() {
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
    }
}

// Main class for the Contact Management System
public class ContactManagementSystem {
    private static ArrayList<Contact> contactList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Contact Management System ---");
            System.out.println("1. Add Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    viewAllContacts();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    System.out.println("Exiting the system...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to add a new contact
    private static void addContact() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Contact contact = new Contact(name, phone, email);
        contactList.add(contact);
        System.out.println("Contact added successfully!");
    }

    // Method to view all contacts
    private static void viewAllContacts() {
        if (contactList.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("\n--- Contact List ---");
            for (int i = 0; i < contactList.size(); i++) {
                System.out.println("Contact " + (i + 1) + ":");
                contactList.get(i).displayContact();
                System.out.println();
            }
        }
    }

    // Method to update a contact
    private static void updateContact() {
        System.out.print("Enter the contact number to update (1, 2, 3...): ");
        int contactIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume newline

        if (contactIndex >= 0 && contactIndex < contactList.size()) {
            Contact contact = contactList.get(contactIndex);
            System.out.print("Enter new name (leave empty to keep current): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                contact.setName(name);
            }

            System.out.print("Enter new phone (leave empty to keep current): ");
            String phone = scanner.nextLine();
            if (!phone.isEmpty()) {
                contact.setPhone(phone);
            }

            System.out.print("Enter new email (leave empty to keep current): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                contact.setEmail(email);
            }

            System.out.println("Contact updated successfully!");
        } else {
            System.out.println("Invalid contact number.");
        }
    }

    // Method to delete a contact
    private static void deleteContact() {
        System.out.print("Enter the contact number to delete (1, 2, 3...): ");
        int contactIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume newline

        if (contactIndex >= 0 && contactIndex < contactList.size()) {
            contactList.remove(contactIndex);
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Invalid contact number.");
        }
    }
}
