import model.Contact;
import service.ContactService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the Contact Management System
 * Provides a console-based menu-driven interface
 */
public class Main {
    private ContactService contactService;
    private Scanner scanner;

    /**
     * Constructor initializes the contact service and scanner
     */
    public Main() {
        this.contactService = new ContactService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main method to start the application
     */
    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    /**
     * Main application loop
     */
    public void run() {
        displayWelcomeMessage();

        boolean running = true;
        while (running) {
            try {
                displayMenu();
                int choice = getUserChoice();

                switch (choice) {
                    case 1:
                        addNewContact();
                        break;
                    case 2:
                        updateContact();
                        break;
                    case 3:
                        deleteContact();
                        break;
                    case 4:
                        searchContact();
                        break;
                    case 5:
                        displayAllContacts();
                        break;
                    case 6:
                        running = false;
                        displayExitMessage();
                        break;
                    default:
                        System.out.println("❌ Invalid choice. Please select 1-6.");
                }

                if (running) {
                    pressEnterToContinue();
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
                pressEnterToContinue();
            } catch (Exception e) {
                System.out.println("❌ An error occurred: " + e.getMessage());
                pressEnterToContinue();
            }
        }

        scanner.close();
    }

    /**
     * Displays welcome message
     */
    private void displayWelcomeMessage() {
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║                                                           ║");
        System.out.println("║        📇 CONTACT MANAGEMENT SYSTEM 📇                    ║");
        System.out.println("║                                                           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
    }

    /**
     * Displays the main menu
     */
    private void displayMenu() {
        System.out.println("\n┌───────────────────────────────────────────────────────────┐");
        System.out.println("│                      MAIN MENU                            │");
        System.out.println("├───────────────────────────────────────────────────────────┤");
        System.out.println("│  1. ➕ Add New Contact                                    │");
        System.out.println("│  2. ✏️  Update Contact                                     │");
        System.out.println("│  3. 🗑️  Delete Contact                                     │");
        System.out.println("│  4. 🔍 Search Contact                                     │");
        System.out.println("│  5. 📋 Display All Contacts                               │");
        System.out.println("│  6. 🚪 Exit                                               │");
        System.out.println("└───────────────────────────────────────────────────────────┘");
        System.out.printf("   Total Contacts: %d\n", contactService.getContactCount());
        System.out.print("\nEnter your choice (1-6): ");
    }

    /**
     * Gets user's menu choice
     * 
     * @return User's choice as integer
     */
    private int getUserChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return choice;
    }

    /**
     * Adds a new contact
     */
    private void addNewContact() {
        System.out.println("\n" + repeat("=", 60));
        System.out.println("                    ➕ ADD NEW CONTACT");
        System.out.println(repeat("=", 60));

        try {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter Phone Number: ");
            String phoneNumber = scanner.nextLine().trim();

            System.out.print("Enter Email: ");
            String email = scanner.nextLine().trim();

            System.out.print("Enter Address: ");
            String address = scanner.nextLine().trim();

            contactService.addContact(name, phoneNumber, email, address);
            System.out.println("\n✅ Contact added successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

    /**
     * Updates an existing contact
     */
    private void updateContact() {
        System.out.println("\n" + repeat("=", 60));
        System.out.println("                    ✏️  UPDATE CONTACT");
        System.out.println(repeat("=", 60));

        try {
            System.out.print("Enter Contact ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter New Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter New Phone Number: ");
            String phoneNumber = scanner.nextLine().trim();

            System.out.print("Enter New Email: ");
            String email = scanner.nextLine().trim();

            System.out.print("Enter New Address: ");
            String address = scanner.nextLine().trim();

            contactService.updateContact(id, name, phoneNumber, email, address);
            System.out.println("\n✅ Contact updated successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\n❌ Error: Invalid ID format. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    /**
     * Deletes a contact
     */
    private void deleteContact() {
        System.out.println("\n" + repeat("=", 60));
        System.out.println("                    🗑️  DELETE CONTACT");
        System.out.println(repeat("=", 60));

        try {
            System.out.print("Enter Contact ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Are you sure you want to delete this contact? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("yes") || confirmation.equals("y")) {
                contactService.deleteContact(id);
                System.out.println("\n✅ Contact deleted successfully!");
            } else {
                System.out.println("\n❌ Deletion cancelled.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\n❌ Error: Invalid ID format. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    /**
     * Searches for contacts
     */
    private void searchContact() {
        System.out.println("\n" + repeat("=", 60));
        System.out.println("                    🔍 SEARCH CONTACT");
        System.out.println(repeat("=", 60));
        System.out.println("1. Search by Name");
        System.out.println("2. Search by Phone Number");
        System.out.print("\nEnter search type (1-2): ");

        try {
            int searchType = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            List<Contact> results;

            switch (searchType) {
                case 1:
                    System.out.print("Enter name to search: ");
                    String name = scanner.nextLine().trim();
                    results = contactService.searchByName(name);
                    displaySearchResults(results, "Name: " + name);
                    break;
                case 2:
                    System.out.print("Enter phone number to search: ");
                    String phone = scanner.nextLine().trim();
                    results = contactService.searchByPhone(phone);
                    displaySearchResults(results, "Phone: " + phone);
                    break;
                default:
                    System.out.println("❌ Invalid search type.");
            }

        } catch (InputMismatchException e) {
            System.out.println("\n❌ Error: Invalid input.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    /**
     * Displays search results
     * 
     * @param results        List of contacts found
     * @param searchCriteria Search criteria used
     */
    private void displaySearchResults(List<Contact> results, String searchCriteria) {
        System.out.println("\n" + repeat("-", 60));
        System.out.println("Search Results for: " + searchCriteria);
        System.out.println(repeat("-", 60));

        if (results.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("Found " + results.size() + " contact(s):\n");
            for (Contact contact : results) {
                System.out.println(contact);
            }
        }
    }

    /**
     * Displays all contacts
     */
    private void displayAllContacts() {
        System.out.println("\n" + repeat("=", 60));
        System.out.println("                    📋 ALL CONTACTS");
        System.out.println(repeat("=", 60));

        List<Contact> contacts = contactService.getAllContacts();

        if (contacts.isEmpty()) {
            System.out.println("\n📭 No contacts available. Add some contacts to get started!");
        } else {
            System.out.println("\nTotal Contacts: " + contacts.size() + "\n");
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    /**
     * Displays exit message
     */
    private void displayExitMessage() {
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║                                                           ║");
        System.out.println("║   Thank you for using Contact Management System! 👋       ║");
        System.out.println("║                                                           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
    }

    /**
     * Pauses execution until user presses Enter
     */
    private void pressEnterToContinue() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    /**
     * Replaces String.repeat(int) for compatibility with Java versions older than
     * 11
     * 
     * @param str   String to repeat
     * @param count Number of times to repeat
     * @return Repeated string
     */
    private String repeat(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}