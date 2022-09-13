package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Customer class. A customer has a first name, last name and an email address.
 */
public class Customer {

    private String firstName;
    private String lastName;
    private String email;

    /**
     * Constructor for the class.
     *
     * @param firstName first name of the customer
     * @param lastName last name of the customer
     * @param email email address of the customer
     */
    public Customer(String firstName, String lastName, String email) {
        // Validates the pattern of the input email address
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid email pattern.");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Gets the first name of the customer.
     *
     * @return the first name of the customer
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the customer.
     *
     * @return the last name of the customer
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the email address of the customer.
     *
     * @return the email address of the customer
     */
    public String getEmail() {
        return email;
    }

    /**
     * A string representation of the customer.
     *
     * @return a string representation of the customer
     */
    @Override
    public String toString() {
        return "Customer - First name: " + getFirstName() + " / Last name: " + getLastName()
            + " / Email: " + getEmail();
    }
}