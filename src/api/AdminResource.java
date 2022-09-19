package api;

import java.util.List;
import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

/**
 * A class that provides resources for the admin.
 */
public class AdminResource {

    private static AdminResource adminResource = new AdminResource();
    private CustomerService customerService = CustomerService.getInstance();
    private ReservationService reservationService = ReservationService.getInstance();

    // Sets the constructor private to ensure singleton design for the class
    private AdminResource() {
    }

    /**
     * Gets the singleton object of the AdminResource class.
     *
     * @return the singleton object of AdminResource class
     */
    public static AdminResource getInstance() {
        return adminResource;
    }

    /**
     * Gets the specified customer based on the customer's email address.
     *
     * @param email the customer's email address
     * @return the customer that has the specified email address, or null if no such customer can be
     * found
     */
    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    /**
     * Adds rooms to the rooms list.
     *
     * @param rooms the rooms to be added
     */
    public void addRooms(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            reservationService.addRoom(room);
        }
    }

    /**
     * Returns all the rooms.
     *
     * @return a list of all the rooms
     */
    public List<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    /**
     * Gets all the customers.
     *
     * @return all the customers
     */
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    /**
     * Displays all reservations.
     */
    public void displayAllReservations() {
        reservationService.printAllReservations();
    }
}
