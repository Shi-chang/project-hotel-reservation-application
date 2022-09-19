package api;

import java.util.Date;
import java.util.List;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

/**
 * A class that provides hotel resource for users and the admin.
 */
public class HotelResource {

    private static HotelResource hotelResource = new HotelResource();
    private CustomerService customerService = CustomerService.getInstance();
    private ReservationService reservationService = ReservationService.getInstance();

    // Sets the constructor private to ensure singleton design for the class
    private HotelResource() {
    }

    /**
     * Gets the singleton object of the HotelResource class.
     *
     * @return the singleton object of HotelResource class
     */
    public static HotelResource getInstance() {
        return hotelResource;
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
     * Adds a customer to the customer list.
     *
     * @param firstName the first name of the customer
     * @param lastName  the last name of the customer
     * @param email     the email address of the customer
     */
    public void createACustomer(String firstName, String lastName, String email) {
        customerService.addCustomer(firstName, lastName, email);
    }

    /**
     * Gets the room with the specified room Id.
     *
     * @param roomNumber the ID of the room
     * @return the room with the specified ID or null if no room can be found
     */
    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }

    /**
     * Books a room.
     *
     * @param customerEmail the customer's email for the reservation
     * @param room          the room for the reservation
     * @param checkInDate   the date for check-in
     * @param checkOutDate  the date for check out
     * @return the reservation
     */
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate,
        Date checkOutDate) {
        Customer customer = getCustomer(customerEmail);
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
    }

    /**
     * Gets the specified customer's reservations.
     *
     * @param customerEmail the customer for reservation searching
     * @return the specified customer's reservations
     */
    public List<Reservation> getCustomerReservations(String customerEmail) {
        Customer customer = getCustomer(customerEmail);
        return reservationService.getCustomerReservation(customer);
    }

    /**
     * Finds rooms that are available for the specified check-in date and checkout date.
     *
     * @param checkInDate  the check-in date
     * @param checkOutDate the checkout date
     * @return rooms that are available for the specified check-in date and checkout date
     */
    public List<IRoom> findARoom(Date checkInDate, Date checkOutDate) {
        return reservationService.findRooms(checkInDate, checkOutDate);
    }

}
