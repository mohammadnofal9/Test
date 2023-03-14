import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private ContactList contactList;
    private Scanner scanner;

    public UserInterface() {
        contactList = new ContactList();
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add a new contact");
        System.out.println("2. Update an existing contact");
        System.out.println("3. Delete a contact");
        System.out.println("4. View all contacts");
        System.out.println("5. View details of a contact");
        System.out.println("0. Exit");
    }

    public void addContact() {
        System.out.println("Enter name:");
        String name = scanner.nextLine();

        System.out.println("Enter phone number:");
        String phoneNumber = scanner.nextLine();

        System.out.println("Enter email address:");
        String emailAddress = scanner.nextLine();

        Contact contact = new Contact(name, phoneNumber, emailAddress);
        contactList.addContact(contact);
    }

    public void updateContact() {
        System.out.println("Enter name of contact to update:");
        String name = scanner.nextLine();

        Contact contact = contactList.getContactByName(name);
        if (contact == null) {
            System.out.println("Contact not found: " + name);
            return;
        }

        System.out.println("Enter new phone number:");
        String phoneNumber = scanner.nextLine();

        System.out.println("Enter new email address:");
        String emailAddress = scanner.nextLine();

        contact.setPhoneNumber(phoneNumber);
        contact.setEmailAddress(emailAddress);
        contactList.updateContact(contact);
    }

    public void deleteContact() {
        System.out.println("Enter name of contact to delete:");
        String name = scanner.nextLine();

        Contact contact = contactList.getContactByName(name);
        if (contact == null) {
            System.out.println("Contact not found: " + name);
            return;
        }

        contactList.deleteContact(contact);
    }

    public void displayAllContacts() {
        List<Contact> contacts = contactList.getAllContacts();
        for (Contact c : contacts) {
            System.out.println(c);
        }
    }

    public void displayContactDetails() {
        System.out.println("Enter name of contact to view details:");
        String name = scanner.nextLine();

        Contact contact = contactList.getContactByName(name);
        if (contact == null) {
            System.out.println("Contact not found: " + name);
            return;
        }

        System.out.println("Name: " + contact.getName());
        System.out.println("Phone number: " + contact.getPhoneNumber());
        System.out.println("Email address: " + contact.getEmailAddress());
    }

    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.println("Enter option:");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    addContact();
                    break;
                case "2":
                    updateContact();
                    break;
                case "3":
                    deleteContact();
                    break;
                case "4":
                    displayAllContacts();
                    break;
                case "5":
                    displayContactDetails();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.run();
    }
}

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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNumber + ", Email Address: " + emailAddress;
    }
}

class ContactList {
    private List<Contact> contacts;

    public ContactList() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public Contact getContactByName(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public void updateContact(Contact contact) {
        // no need to do anything, as the contact object is already updated in the list
    }

    public void deleteContact(Contact contact) {
        contacts.remove(contact);
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }
}
