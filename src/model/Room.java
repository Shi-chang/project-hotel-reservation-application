package model;

/**
 * The room class that implements the IRoom interface. A room has a room number, price and type.
 */
public class Room implements IRoom {

    private String roomNumber;
    private Double price;
    private RoomType roomType;

    /**
     * The constructor for the class.
     *
     * @param roomNumber number of the room
     * @param price      price of the room
     * @param roomType   type of the room
     */
    public Room(String roomNumber, double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    /**
     * Gets the room number.
     *
     * @return the room number
     */
    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Gets the room price.
     *
     * @return the room price
     */
    @Override
    public Double getRoomPrice() {
        return price;
    }

    /**
     * Gets the room type.
     *
     * @return the room type
     */
    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    /**
     * Returns if the room is free.
     *
     * @return true if the room is free, false otherwise
     */
    @Override
    public boolean isFree() {
        return false;
    }

    /**
     * A string representation of the room.
     *
     * @return a string representation of the room
     */
    @Override
    public String toString() {
        return "Room - Number: " + this.getRoomNumber() + " / Price: " + this.getRoomPrice()
            + " / Type: " + this.getRoomType();
    }
}
