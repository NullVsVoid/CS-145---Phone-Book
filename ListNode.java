//
// Author: Caiden Sanders
// Date: February 14, 2024
// Class: CS145 Assignment 2
// Purpose: This class represents a node in a singly linked list for a phonebook
// 			application. It stores information about a person including their
// 			first name, last name, address, city, and phone number. Each node
// 			contains a reference to the next node in the list.
//
public class ListNode {
	// Data fields for the phone book entry.
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String phoneNumber;
	// Reference to the next node in the list.
	private ListNode next;
  
	/**
	 * Constructor to create a new ListNode with specified information.
	 * Initializes the node with the provided personal details and sets the next
	 * node reference to null.
	 *
	 * @param firstName   The first name of the person.
	 * @param lastName    The last name of the person.
	 * @param address     The address of the person.
	 * @param city        The city where the person lives.
	 * @param phoneNumber The phone number of the person.
	 */
	public ListNode(String firstName, String lastName,
		String address, String city, String phoneNumber) {
	  this.firstName = firstName;
	  this.lastName = lastName;
	  this.address = address;
	  this.city = city;
	  this.phoneNumber = phoneNumber;
	  this.next = null; // Initially, the next node is null.
	}
  
	/**
	 * Returns the first name stored in this node.
	 *
	 * @return The first name.
	 */
	public String getFirstName() {
	  return firstName;
	}
  
	/**
	 * Sets the first name stored in this node.
	 *
	 * @param firstName The new first name.
	 */
	public void setFirstName(String firstName) {
	  this.firstName = firstName;
	}
  
	/**
	 * Returns the last name stored in this node.
	 *
	 * @return The last name.
	 */
	public String getLastName() {
	  return lastName;
	}
  
	/**
	 * Sets the last name stored in this node.
	 *
	 * @param lastName The new last name.
	 */
	public void setLastName(String lastName) {
	  this.lastName = lastName;
	}
  
	/**
	 * Returns the address stored in this node.
	 *
	 * @return The address.
	 */
	public String getAddress() {
	  return address;
	}
  
	/**
	 * Sets the address stored in this node.
	 *
	 * @param address The new address.
	 */
	public void setAddress(String address) {
	  this.address = address;
	}
  
	/**
	 * Returns the city stored in this node.
	 *
	 * @return The city.
	 */
	public String getCity() {
	  return city;
	}
  
	/**
	 * Sets the city stored in this node.
	 *
	 * @param city The new city.
	 */
	public void setCity(String city) {
	  this.city = city;
	}
  
	/**
	 * Returns the phone number stored in this node.
	 *
	 * @return The phone number.
	 */
	public String getPhoneNumber() {
	  return phoneNumber;
	}
  
	/**
	 * Sets the phone number stored in this node.
	 *
	 * @param phoneNumber The new phone number.
	 */
	public void setPhoneNumber(String phoneNumber) {
	  this.phoneNumber = phoneNumber;
	}
  
	/**
	 * Returns the next node in the linked list.
	 *
	 * @return The next node.
	 */
	public ListNode getNext() {
	  return next;
	}
  
	/**
	 * Sets the reference to the next node in the linked list.
	 *
	 * @param next The next node in the list.
	 */
	public void setNext(ListNode next) {
	  this.next = next;
	}
  
	/**
	 * Overrides the toString method to provide a string representation of the
	 * ListNode's data. Formats the personal details into a readable string
	 * format for easy viewing.
	 *
	 * @return A formatted string containing the person's details stored in this
	 *         node.
	 */
	@Override
	public String toString() {
	  return "Name: " + firstName + " " + lastName + "\n" +
		  "Address: " + address + "\n" +
		  "City: " + city + "\n" +
		  "Phone Number: " + phoneNumber;
	}
  }