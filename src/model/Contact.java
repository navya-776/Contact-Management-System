package model;

import java.io.Serializable; // Serialization: It allows you to "freeze" an object  into a format that can be saved to a file on your hard drive or sent over a network.

/**
 * Contact class represents a single contact with all necessary details
 * Implements Serializable for file persistence
 */
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;

    /**
     * Constructor to create a new Contact
     * 
     * @param id          Unique identifier for the contact
     * @param name        Contact's name
     * @param phoneNumber Contact's phone number
     * @param email       Contact's email address
     * @param address     Contact's physical address
     */
    public Contact(int id, String name, String phoneNumber, String email, String address) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns a formatted string representation of the contact
     * 
     * @return Formatted contact details in a box
     */
    @Override
    public String toString() {
        return String.format(
                "┌─────────────────────────────────────────────────────────┐\n" +
                        "│ ID: %-52d│\n" +
                        "│ Name: %-50s│\n" +
                        "│ Phone: %-49s│\n" +
                        "│ Email: %-49s│\n" +
                        "│ Address: %-47s│\n" +
                        "└─────────────────────────────────────────────────────────┘",
                id, name, phoneNumber, email, address);
    }

    /**
     * Returns a compact single-line representation of the contact
     * 
     * @return Compact string representation
     */
    public String toCompactString() {
        return String.format("ID: %d | Name: %s | Phone: %s | Email: %s",
                id, name, phoneNumber, email);
    }
}