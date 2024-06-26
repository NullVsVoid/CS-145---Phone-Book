//
// Author: Caiden Sanders
// Date: February 14, 2024
// Class: CS145 Assignment 2
// Purpose: This file serves as the interactive interface for a console-based
// phone book application. It allows users to add, delete, and search
// for contacts in a phone book. Utilizing the PhonebookManager class,
// it manages contacts stored in a singly linked list, providing
// functionalities such as viewing all contacts, searching contacts by
// name, address, or phone number, and editing contact details. This
// interface is designed to be user-friendly, offering clear prompts
// and feedback for all operations to ensure a seamless user experience.
//

import java.util.Scanner;
import java.util.List;

public class Main {
  private static Scanner scanner = new Scanner(System.in);
  private static PhonebookManager phonebookManager = new PhonebookManager();

  /**
   * Entry point of the application. Displays a menu and handles user input.
   */
  public static void main(String[] args) {
    while (true) {
      System.out.println("\n1 - Add\n2 - Delete\n3 - View Contacts\n" +
          "4 - Name Search\n5 - Address Search\n6 - Phone Number Search\n" +
          "7 - Edit Name\n8 - Edit Address\n9 - Edit Phone Number\n0 - Quit\n");
      System.out.print("Enter a command: ");
      int command = scanner.nextInt();
      scanner.nextLine(); // Consume newline.
      switch (command) {
        case 1:
          addContact();
          break;
        case 2:
          deleteContact();
          break;
        case 3:
          if (phonebookManager.toString().trim().isEmpty()) {
            System.out.println("No contacts in the phonebook yet.");
          } else {
            System.out.println(phonebookManager);
          }
          break;
        case 4:
          searchByName();
          break;
        case 5:
          searchByAddress();
          break;
        case 6:
          searchByPhoneNumber();
          break;
        case 7:
          editName();
          break;
        case 8:
          editAddress();
          break;
        case 9:
          editPhoneNumber();
          break;
        case 0:
          System.out.println("Quitting...");
          System.exit(0);
        default:
          System.out.println("Invalid command. Please try again.");
      }
    }
  }

  /**
   * Adds a new contact to the phone book. Prompts the user for contact details.
   */
  private static void addContact() {
    System.out.println("Add a new contact to the phonebook.\n");
    System.out.print("Enter first name: ");
    String firstName = scanner.nextLine();
    System.out.print("Enter last name: ");
    String lastName = scanner.nextLine();
    System.out.print("Enter address: ");
    String address = scanner.nextLine();
    System.out.print("Enter city: ");
    String city = scanner.nextLine();
    System.out.print("Enter phone number: ");
    String phoneNumber = scanner.nextLine();
    phonebookManager.addEntryAtEnd(firstName, lastName, address, city,
        phoneNumber);
    System.out.println("Contact added.");
  }

  /**
   * Deletes a contact from the phone book based on user-specified criteria and
   * value.
   */
  private static void deleteContact() {
    System.out.println("Enter criteria for deletion (name, address, "
        + "phoneNumber): ");
    String criteria = scanner.nextLine().trim();
    // Validate criteria input by the user.
    if (!criteria.equals("name") && !criteria.equals("address")
        && !criteria.equals("phoneNumber")) {
      System.out.println("Invalid criteria. Please enter 'name', 'address', "
          + "or 'phoneNumber'.");
      return;
    }
    System.out.println("Enter the value for " + criteria + " to search and "
        + "delete: ");
    String value = scanner.nextLine();
    List<ListNode> results = phonebookManager.searchByCriteria(criteria, value);
    if (results.isEmpty()) {
      System.out.println("No contact found with the given criteria.");
      return;
    }
    System.out.println("Matching contacts found:\n");
    int index = 1;
    for (ListNode node : results) {
      System.out.println(index++ + ": " + node + "\n");
    }
    System.out.println("Enter the number of the contact you wish to delete: ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume the newline.
    if (choice < 1 || choice > results.size()) {
      System.out.println("Invalid selection.");
      return;
    }
    // Adjusted to directly access the ListNode to be deleted.
    ListNode toDelete = results.get(choice - 1);
    boolean deleted = phonebookManager.deleteNode(toDelete);
    if (deleted) {
      System.out.println("Contact deleted successfully.");
    } else {
      System.out.println("Error deleting contact.");
    }
  }

  /**
   * Searches for and displays contacts matching a given name.
   */
  private static void searchByName() {
    System.out.print("Enter the name to search for: ");
    String fullName = scanner.nextLine();
    List<ListNode> results = phonebookManager.searchByCriteria("name", fullName);
    displaySearchResults(results, "name");
  }

  /**
   * Searches for and displays contacts matching a given address.
   */
  private static void searchByAddress() {
    System.out.print("Enter the address to search for: ");
    String address = scanner.nextLine();
    List<ListNode> results = phonebookManager.searchByCriteria("address",
        address);
    displaySearchResults(results, "address");
  }

  /**
   * Searches for and displays contacts matching a given phone number.
   */
  private static void searchByPhoneNumber() {
    System.out.print("Enter the phone number to search for: ");
    String phoneNumber = scanner.nextLine();
    List<ListNode> results = phonebookManager.searchByCriteria("phoneNumber",
        phoneNumber);
    displaySearchResults(results, "phone number");
  }

  /**
   * Allows the user to edit the name of a contact after searching for it by
   * name.
   */
  private static void editName() {
    System.out.print("Enter the name to search for: ");
    String name = scanner.nextLine();
    List<ListNode> results = phonebookManager.searchByCriteria("name", name);
    if (!results.isEmpty()) {
      System.out.println("Matching contacts found:\n");
      int index = 1;
      for (ListNode node : results) {
        System.out.println(index++ + ": " + node + "\n");
      }
      System.out.print("Enter the number of the contact you wish to edit: ");
      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume the newline.
      if (choice > 0 && choice <= results.size()) {
        ListNode selectedNode = results.get(choice - 1);
        System.out.print("Enter new first name: ");
        String newFirstName = scanner.nextLine();
        System.out.print("Enter new last name: ");
        String newLastName = scanner.nextLine();
        selectedNode.setFirstName(newFirstName);
        selectedNode.setLastName(newLastName);
        System.out.println("Contact updated successfully.");
      } else {
        System.out.println("Invalid selection.");
      }
    } else {
      System.out.println("No contacts found with the given name.");
    }
  }

  /**
   * Allows the user to edit the address of a contact after searching for it by
   * address.
   */
  private static void editAddress() {
    System.out.print("Enter the address to search for: ");
    String address = scanner.nextLine();
    List<ListNode> results = phonebookManager.searchByCriteria("address",
        address);
    if (!results.isEmpty()) {
      System.out.println("Matching contacts found:\n");
      int index = 1;
      for (ListNode node : results) {
        System.out.println(index++ + ": " + node + "\n");
      }
      System.out.print("Enter the number of the contact you wish to edit: ");
      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume the newline.
      if (choice > 0 && choice <= results.size()) {
        ListNode selectedNode = results.get(choice - 1);
        System.out.print("Enter new address: ");
        String newAddress = scanner.nextLine();
        selectedNode.setAddress(newAddress);
        System.out.println("Contact updated successfully.");
      } else {
        System.out.println("Invalid selection.");
      }
    } else {
      System.out.println("No contacts found with the given address.");
    }
  }

  /**
   * Allows the user to edit the phone number of a contact after searching for
   * it by phone number.
   */
  private static void editPhoneNumber() {
    System.out.print("Enter the phone number to search for: ");
    String phoneNumber = scanner.nextLine();
    List<ListNode> results = phonebookManager.searchByCriteria("phoneNumber",
        phoneNumber);
    if (!results.isEmpty()) {
      System.out.println("Matching contacts found:\n");
      int index = 1;
      for (ListNode node : results) {
        System.out.println(index++ + ": " + node + "\n");
      }
      System.out.print("Enter the number of the contact you wish to edit: ");
      int choice = scanner.nextInt();
      scanner.nextLine(); // Consume the newline.
      if (choice > 0 && choice <= results.size()) {
        ListNode selectedNode = results.get(choice - 1);
        System.out.print("Enter new phone number: ");
        String newPhoneNumber = scanner.nextLine();
        selectedNode.setPhoneNumber(newPhoneNumber);
        System.out.println("Contact updated successfully.");
      } else {
        System.out.println("Invalid selection.");
      }
    } else {
      System.out.println("No contacts found with the given phone number.");
    }
  }

  /**
   * Utility method to display search results and handle no results found
   * scenario.
   *
   * @param results List of contacts found.
   */
  private static void displaySearchResults(List<ListNode> results,
      String criteria) {
    if (!results.isEmpty()) {
      System.out.println("Matching contact(s) found:\n");
      for (ListNode node : results) {
        System.out.println(node + "\n");
      }
    } else {
      System.out.println("No contacts found with the given " + criteria + ".");
    }
  }
}