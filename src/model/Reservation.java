package model;

import java.util.Date;

public class Reservation {

    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    /**
     * Gets the customer for the reservation.
     *
     * @return the customer for the reservation
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Gets the room for the reservation.
     *
     * @return the room for the reservation
     */
    public IRoom getRoom() {
        return room;
    }

    /**
     * Gets the check in date for the reservation.
     *
     * @return the check in date for the reservation
     */
    public Date getCheckInDate() {
        return checkInDate;
    }

    /**
     * Gets the check out date for the reservation.
     *
     * @return the check out date for the reservation
     */
    public Date getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * A string representation of the reservation.
     *
     * @return a string representation of the reservation
     */
    @Override
    public String toString() {
        return customer.toString() + "\n" + room.toString() + "\nCheck in date: "
            + checkInDate.toString() + "\nCheck out date: " + checkOutDate;
    }
}
