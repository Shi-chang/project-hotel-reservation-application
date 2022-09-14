package model;

import java.util.Date;

public class Tester {

    public static void main(String[] args) {
        // Tests the Room class
        Room room = new Room("000", 130.00, RoomType.SINGLE);
        System.out.println(room);

        // Tests the FreeRoom class
        FreeRoom freeRoom = new FreeRoom("001", RoomType.DOUBLE);
        System.out.println(freeRoom);

        // Tests the Customer class
        Customer customer1 = new Customer("Alan", "Turing", "alanturing@gmail.com");
        System.out.println(customer1);

        // Tests the Customer class when the email address is invalid
        try {
            Customer customer2 = new Customer("Alan", "Turing", "alanturinggmail.com");
        } catch (IllegalArgumentException e) {
            System.out.println("This is expected.");
        }

        // Tests the Reservation class
        Date checkInDate = new Date();
        Date checkOutDate = new Date();
        Reservation reservation = new Reservation(customer1, room, checkInDate, checkOutDate);
        System.out.println(reservation);
    }
}
