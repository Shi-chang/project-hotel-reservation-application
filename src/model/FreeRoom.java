package model;

/**
 * The FreeRoom class that extends the Room class. The price of a free room is 0.
 */
public class FreeRoom extends Room {

    /**
     * The constructor for the class.
     */
    public FreeRoom(String roomNumber, RoomType roomType) {
        super(roomNumber, 0, roomType);
    }

    /**
     * A string representation of the room.
     *
     * @return a string representation of the room
     */
    @Override
    public String toString() {
        return "Free room - Number: " + this.getRoomNumber() + " / Price: 0 / Type: "
            + this.getRoomType();
    }
}
