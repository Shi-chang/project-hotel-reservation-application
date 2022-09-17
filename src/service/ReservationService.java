package service;

import java.util.Collection;
import java.util.Date;
import model.Customer;
import model.IRoom;
import java.util.List;
import java.util.ArrayList;
import model.Reservation;

public class ReservationService {

    private static ReservationService reservationService = new ReservationService();
    private List<IRoom> rooms = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    // Sets the constructor private to ensure singleton design for the class
    private ReservationService() {
    }

    /**
     * Gets the singleton object of the ReservationService class.
     *
     * @return the singleton object of CustomerService class
     */
    public static ReservationService getInstance() {
        return reservationService;
    }

    /**
     * Adds a room to the rooms list.
     *
     * @param room the room to be added
     */
    public void addRoom(IRoom room) {
        rooms.add(room);
    }

    /**
     * Gets the room with the specified room Id.
     *
     * @param roomId the ID of the room
     * @return the room with the specified ID or null if no room can be found
     */
    public IRoom getARoom(String roomId) {
        for (IRoom room : rooms) {
            if (room.getRoomNumber().equals(roomId)) {
                return room;
            }
        }
        // If no room can be found, returns null
        return null;
    }
    /**
     * Gets all the rooms.
     *
     * @return all the rooms
     */
    public List<IRoom> getAllRooms(){
        return rooms;
    }

    /**
     * Reserves a room.
     *
     * @param customer     the customer for the reservation
     * @param room         the room for the reservation
     * @param checkInDate  the date for check-in
     * @param checkOutDate the date for check out
     * @return the reservation
     */
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate,
        Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }

    /**
     * Finds rooms that are available for the specified check-in date and checkout date.
     *
     * @param checkInDate  the check-in date
     * @param checkOutDate the checkout date
     * @return rooms that are available for the specified check-in date and checkout date
     */
    public List<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> availableRooms = new ArrayList<>();
        List<IRoom> unavailableRooms = getUnavailableRooms(checkInDate, checkOutDate);

        for (IRoom room : rooms) {
            if (unavailableRooms.contains(room)) {
                continue;
            }
            availableRooms.add(room);
        }

        return availableRooms;
    }

    /**
     * Gets reserved rooms whose availability conflicts with the specified check-in and check out
     * dates.
     *
     * @param checkInDate  the check-in date
     * @param checkOutDate the checkout date
     * @return reserved rooms whose availability conflicts with the specified check-in and check out
     * dates
     */
    private List<IRoom> getUnavailableRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> unavailableRooms = new ArrayList<>();

        for (Reservation reservation : reservations) {
            // Reserved rooms with a checkout date later than the specified check-in date or with a
            // check-in date earlier than the specified checkout date are not available.
            if (reservation.getCheckOutDate().getTime() > checkInDate.getTime()
                || reservation.getCheckInDate().getTime() < checkOutDate.getTime()) {
                unavailableRooms.add(reservation.getRoom());
            }
        }

        return unavailableRooms;
    }

    /**
     * Gets the specified customer's reservations.
     *
     * @param customer the customer for reservation searching
     * @return the specified customer's reservations
     */
    public List<Reservation> getCustomerReservation(Customer customer) {
        List<Reservation> customerReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }

        return customerReservations;
    }

    /**
     * Prints all reservations.
     */
    public void printAllReservations() {
        for (Reservation reservation : reservations) {
            System.out.println("Reservations: ");
            System.out.println(reservation);
        }
    }
}
