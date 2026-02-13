package service;

import model.Contact;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ContactService class handles all CRUD operations for contacts
 * Manages file persistence using serialization
 */
public class ContactService {
    private ArrayList<Contact> contacts;
    private int nextId;
    private static final String FILE_NAME = "contacts.dat";

    /**
     * Constructor initializes the service and loads existing contacts
     */
    public ContactService() {
        this.contacts = new ArrayList<>();
        this.nextId = 1;
        loadContactsFromFile();
    }

    /**
     * Adds a new contact to the system
     * 
     * @param name        Contact's name
     * @param phoneNumber Contact's phone number
     * @param email       Contact's email
     * @param address     Contact's address
     * @return true if contact was added successfully
     * @throws IllegalArgumentException if validation fails
     */
    public boolean addContact(String name, String phoneNumber, String email, String address) {
        try {
            validateContactDetails(name, phoneNumber, email);

            Contact newContact = new Contact(nextId++, name, phoneNumber, email, address);
            contacts.add(newContact);
            saveContactsToFile();
            return true;
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    /**
     * Updates an existing contact
     * 
     * @param id          Contact ID to update
     * @param name        New name
     * @param phoneNumber New phone number
     * @param email       New email
     * @param address     New address
     * @return true if contact was updated successfully
     * @throws IllegalArgumentException if validation fails or contact not found
     */
    public boolean updateContact(int id, String name, String phoneNumber, String email, String address) {
        try {
            validateContactDetails(name, phoneNumber, email);

            Contact contact = findContactById(id);
            if (contact == null) {
                throw new IllegalArgumentException("Contact with ID " + id + " not found.");
            }

            contact.setName(name);
            contact.setPhoneNumber(phoneNumber);
            contact.setEmail(email);
            contact.setAddress(address);
            saveContactsToFile();
            return true;
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    /**
     * Deletes a contact by ID
     * 
     * @param id Contact ID to delete
     * @return true if contact was deleted successfully
     * @throws IllegalArgumentException if contact not found
     */
    public boolean deleteContact(int id) {
        Contact contact = findContactById(id);
        if (contact == null) {
            throw new IllegalArgumentException("Contact with ID " + id + " not found.");
        }

        contacts.remove(contact);
        saveContactsToFile();
        return true;
    }

    /**
     * Searches for contacts by name (case-insensitive, partial match)
     * 
     * @param name Name to search for
     * @return List of matching contacts
     */
    public List<Contact> searchByName(String name) {
        List<Contact> results = new ArrayList<>();
        String searchTerm = name.toLowerCase().trim();

        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(searchTerm)) {
                results.add(contact);
            }
        }

        return results;
    }

    /**
     * Searches for contacts by phone number (partial match)
     * 
     * @param phoneNumber Phone number to search for
     * @return List of matching contacts
     */
    public List<Contact> searchByPhone(String phoneNumber) {
        List<Contact> results = new ArrayList<>();
        String searchTerm = phoneNumber.trim();

        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().contains(searchTerm)) {
                results.add(contact);
            }
        }

        return results;
    }

    /**
     * Returns all contacts
     * 
     * @return List of all contacts
     */
    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }

    /**
     * Finds a contact by ID
     * 
     * @param id Contact ID
     * @return Contact object or null if not found
     */
    private Contact findContactById(int id) {
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;
    }

    /**
     * Validates contact details
     * 
     * @param name        Contact name
     * @param phoneNumber Phone number
     * @param email       Email address
     * @throws IllegalArgumentException if validation fails
     */
    private void validateContactDetails(String name, String phoneNumber, String email) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty.");
        }

        // Basic phone number validation (digits, spaces, hyphens, parentheses, plus)
        if (!phoneNumber.matches("[0-9\\s\\-()+]+")) {
            throw new IllegalArgumentException("Phone number contains invalid characters.");
        }

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }

        // Basic email validation
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
    }

    /**
     * Saves contacts to file using serialization
     */
    private void saveContactsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(contacts);
            oos.writeInt(nextId);
        } catch (IOException e) {
            System.err.println("Error saving contacts to file: " + e.getMessage());
        }
    }

    /**
     * Loads contacts from file using serialization
     */
    @SuppressWarnings("unchecked")
    private void loadContactsFromFile() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return; // No file exists yet, start with empty list
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            contacts = (ArrayList<Contact>) ois.readObject();
            nextId = ois.readInt();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading contacts from file: " + e.getMessage());
            System.err.println("Starting with empty contact list.");
            contacts = new ArrayList<>();
            nextId = 1;
        }
    }

    /**
     * Returns the total number of contacts
     * 
     * @return Number of contacts
     */
    public int getContactCount() {
        return contacts.size();
    }
}