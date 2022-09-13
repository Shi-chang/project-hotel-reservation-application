package model;

/**
 * An interface for rooms.
 */
public interface IRoom {

    /**
     * Gets the room number.
     *
     * @return the room number
     */
    public String getRoomNumber();

    /**
     * Gets the room price.
     *
     * @return the room price
     */
    public Double getRoomPrice();

    /**
     * Gets the room type.
     *
     * @return the room type
     */
    public RoomType getRoomType();

    /**
     * Returns if the room is free.
     *
     * @return true if the room is free, false otherwise
     */
    public boolean isFree();
}
