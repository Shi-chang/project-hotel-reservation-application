package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.Customer;

/**
 * The class for customer service. This class is a singleton class, so the instance can only be
 * obtained by calling the getInstance() method.
 */
public class CustomerService {

    private static CustomerService customerService = new CustomerService();
    private List<Customer> customers = new ArrayList<>();

    // Sets the constructor private to ensure singleton design for the class
    private CustomerService() {
    }

    /**
     * Gets the singleton object of the CustomerService class.
     *
     * @return the singleton object of CustomerService class
     */
    public static CustomerService getInstance() {
        return customerService;
    }

    /**
     * Adds a customer to the customer list.
     *
     * @param firstName the first name of the customer
     * @param lastName  the last name of the customer
     * @param email     the email address of the customer
     */
    public void addCustomer(String firstName, String lastName, String email) {
        Customer customer = new Customer(firstName, lastName, email);
        customers.add(customer);
    }

    /**
     * Gets the specified customer based on the customer's email address.
     *
     * @param customerEmail the customer's email address
     * @return the customer that has the specified email address, or null if no such customer can be
     * found
     */
    public Customer getCustomer(String customerEmail) {
        for (Customer current : customers) {
            if (current.getEmail().equals(customerEmail)) {
                return current;
            }
        }
        // If the customer is not found, returns null
        return null;
    }

    /**
     * Gets all the customers.
     *
     * @return all the customers
     */
    public Collection<Customer> getAllCustomers() {
        return customers;
    }
}
