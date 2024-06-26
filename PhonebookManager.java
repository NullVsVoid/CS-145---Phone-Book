//
// Author: Caiden Sanders
// Date: February 14, 2024
// Class: CS145 Assignment 2
// Purpose: Manages a phone book as a singly linked list. ALlows adding,
// deleting, modifying, and searching for entries based on
// various criteria.
//

import java.util.ArrayList;
import java.util.List;

public class PhonebookManager {
    private ListNode head; // Head of the list.

    public PhonebookManager() {
        this.head = null; // Initially, the list is empty.
    }

    /**
     * Adds a new entry at the beginning of the phone book.
     *
     * @param firstName   The first name of the contact.
     * @param lastName    The last name of the contact.
     * @param address     The address of the contact.
     * @param city        The city of the contact.
     * @param phoneNumber The phone number of the contact.
     */
    public void addEntryAtFront(String firstName, String lastName,
            String address, String city, String phoneNumber) {
        ListNode newNode = new ListNode(firstName, lastName, address, city,
                phoneNumber);
        newNode.setNext(head);
        head = newNode;
    }

    /**
     * Adds a new entry at the end of the phone book.
     *
     * @param firstName   The first name of the contact.
     * @param lastName    The last name of the contact.
     * @param address     The address of the contact.
     * @param city        The city of the contact.
     * @param phoneNumber The phone number of the contact.
     */
    public void addEntryAtEnd(String firstName, String lastName,
            String address, String city, String phoneNumber) {
        ListNode newNode = new ListNode(firstName, lastName, address, city,
                phoneNumber);
        if (head == null) {
            head = newNode; // If list is empty, new node becomes the head.
        } else {
            ListNode current = head;
            while (current.getNext() != null) { // Traverse to the end of the list.
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    /**
     * Adds a new entry at a specified index in the phone book.
     *
     * @param index       The index at which the entry should be added.
     * @param firstName   The first name of the contact.
     * @param lastName    The last name of the contact.
     * @param address     The address of the contact.
     * @param city        The city of the contact.
     * @param phoneNumber The phone number of the contact.
     */
    public void addAtIndex(int index, String firstName,
            String lastName, String address, String city, String phoneNumber) {
        if (index == 0) {
            // Add at the front if index is 0.
            addEntryAtFront(firstName, lastName, address, city, phoneNumber);
            return;
        }
        ListNode newNode = new ListNode(firstName, lastName, address, city,
                phoneNumber);
        ListNode current = head;
        for (int i = 0; current != null && i < index - 1; i++) {
            current = current.getNext(); // Traverse to the node before the desired position.
        }
        if (current != null) {
            newNode.setNext(current.getNext()); // Insert the new node after the current node.
            current.setNext(newNode); // Current node points to the new node.
        }
    }

    /**
     * Deletes an entry at a specified index in the phone book.
     *
     * @param index The index of the entry to be deleted.
     */
    public void deleteAtIndex(int index) {
        if (head == null)
            return; // List is empty.
        if (index == 0) {
            head = head.getNext(); // Remove head.
            return;
        }
        ListNode current = head;
        for (int i = 0; current != null && i < index - 1; i++) {
            current = current.getNext(); // Traverse to the node before the desired position.
        }
        if (current != null && current.getNext() != null) {
            current.setNext(current.getNext().getNext()); // Remove the node at the desired position.
        }
    }

    /**
     * Modifies an existing entry's information by index.
     *
     * @param index       The index of the entry to modify.
     * @param firstName   The new first name.
     * @param lastName    The new last name.
     * @param address     The new address.
     * @param city        The new city.
     * @param phoneNumber The new phone number.
     */
    public void modifyEntryAtIndex(int index, String firstName, String lastName,
            String address, String city, String phoneNumber) {
        ListNode current = head;
        for (int i = 0; current != null && i < index; i++) {
            current = current.getNext(); // Traverse to the desired position.
        }
        if (current != null) {
            // Update the details of the nodes at the index.
            current.setFirstName(firstName);
            current.setLastName(lastName);
            current.setAddress(address);
            current.setCity(city);
            current.setPhoneNumber(phoneNumber);
        }
    }

    /**
     * Searches the phone book for all entries matching the given criteria and
     * value.
     *
     * @param criteria The criteria to search for ("name", "address", or
     *                 "phoneNumber").
     * @param value    The value to match against the criteria.
     * @return The first ListNode matching the criteria, or null if no
     *         match is found.
     */
    public List<ListNode> searchByCriteria(String criteria, String value) {
        List<ListNode> matches = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            if ("name".equals(criteria)) {
                // Split the input value into parts to handle first name, last name, or both.
                String[] nameParts = value.trim().split("\\s+"); // Splits by whitespace.
                for (String part : nameParts) {
                    if (current.getFirstName().equalsIgnoreCase(part)
                            || current.getLastName().equalsIgnoreCase(part)) {
                        matches.add(current);
                        break; // Found a match, no need to check further for this node.
                    }
                }
                // Check for full name match if there are multiple parts (assumes 2 parts max
                // for simplicity).
                if (nameParts.length > 1) {
                    String fullName = current.getFirstName() + " " + current.getLastName();
                    if (fullName.equalsIgnoreCase(value.trim())) {
                        // To avoid adding the same node twice if it matches both on individual
                        // and full name.
                        if (!matches.contains(current)) {
                            matches.add(current);
                        }
                    }
                }
            } else if ("address".equals(criteria)) {
                if (current.getAddress().equalsIgnoreCase(value)) {
                    matches.add(current);
                }
            } else if ("phoneNumber".equals(criteria)) {
                if (current.getPhoneNumber().equalsIgnoreCase(value)) {
                    matches.add(current);
                }
            }
            current = current.getNext();
        }
        return matches;
    }

    /**
     * Deletes a node from the phone book directly.
     *
     * @param nodeToDelete The node to delete.
     * @return true if the node was deleted, false otherwise.
     */
    public boolean deleteNode(ListNode nodeToDelete) {
        if (head == null || nodeToDelete == null)
            return false;
        // Special case: deleting the head node.
        if (head == nodeToDelete) {
            head = head.getNext();
            return true;
        }
        ListNode current = head;
        while (current != null && current.getNext() != null) {
            if (current.getNext() == nodeToDelete) {
                current.setNext(current.getNext().getNext());
                return true;
            }
            current = current.getNext();
        }
        return false; // Node not found.
    }

    /**
     * Returns a string representation of the entire phone book.
     * Each contact is enumerated as "Contact 1", "Contact 2", etc.
     *
     * @return A formatted string containing all entries in the phone book.
     */
    @Override
    public String toString() {
        String result = "";
        ListNode current = head;
        int contactNumber = 1; // Start numbering contacts from 1.
        while (current != null) {
            result += "\nContact " + contactNumber + ":\n\n" + current.toString() + "\n";
            current = current.getNext(); // Move to the next node.
            contactNumber++; // Increment contact number.
        }
        return result;
    }
}