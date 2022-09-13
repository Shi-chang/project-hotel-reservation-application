package model;

import java.util.Date;

public class Tester {

    public static void main(String[] args) {
        Room room = new Room("000", 130.00, RoomType.SINGLE);
        System.out.println(room);

        FreeRoom freeRoom = new FreeRoom("001", RoomType.DOUBLE);
        System.out.println(freeRoom);

        Customer customer = new Customer("Alan", "Turing", "alanturing@gmail.com");
        System.out.println(customer);

        String checkInDate = "2022/12/12";
        String checkOutDate = "2022/12/15";
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        System.out.println(reservation);
    }
}
