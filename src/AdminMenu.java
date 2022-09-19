import api.AdminResource;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.IRoom;
import model.Room;
import model.RoomType;

/**
 * The admin menu class that handles command line interactions with the admin.
 */
public class AdminMenu {

    private static AdminResource adminResource = AdminResource.getInstance();
    private static Scanner scanner;

    /**
     * Starts the admin menu.
     */
    public static void startAdminMenu() {
        scanner = new Scanner(System.in);

        try {
            boolean exit = false;
            while (!exit) {
                String choice = displayAdminMenu();
                switch (choice) {
                    case "1":
                        System.out.println(adminResource.getAllCustomers());
                        break;
                    case "2":
                        System.out.println(adminResource.getAllRooms());
                        break;
                    case "3":
                        adminResource.displayAllReservations();
                        break;
                    case "4":
                        addARoom();
                        break;
                    case "5":
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid input. Please enter a valid number: \n");
                }
            }
            String[] arguments = new String[]{""};
            MainMenu.main(arguments);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * Adds a room.
     */
    private static void addARoom() {
        System.out.println("Enter room number: ");
        String roomNumber = scanner.next();

        double price = getFloatNumberFromUser("Enter price per day for the room:",
            "Invalid price. Price should be a number greater than 0.");

        char roomTypeNumber = getCharFromUser(
            "Enter room type: type in 1 for single or 2 for double",
            "Invalid input. Type in 1 for single or 2 for double");
        RoomType roomType = roomTypeNumber == '1' ? RoomType.SINGLE : RoomType.DOUBLE;

        IRoom room = new Room(roomNumber, price, roomType);
        adminResource.addRooms(Arrays.asList(room));
        System.out.println("Room added successfully.\n");
    }

    /**
     * Displays the admin menu.
     */
    private static String displayAdminMenu() {
        System.out.println("----------Admin Menu----------");
        System.out.println("1. See all customers");
        System.out.println("2. See all rooms");
        System.out.println("3. See all reservations");
        System.out.println("4. Add a room");
        System.out.println("5. Back to Main Menu");
        System.out.println("-----------------------------");
        System.out.println("Please enter your choice: ");
        String choice = scanner.next();
        return choice;
    }

    /**
     * Gets a float number from the user.
     *
     * @param prompt  the prompt for the input
     * @param warning the warning for when the input is invalid
     * @return the input number
     */
    private static double getFloatNumberFromUser(String prompt, String warning) {
        double price = 0.0;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(prompt);
                price = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println(warning);
            }

            if (price > 0) {
                isValid = true;
            } else {
                System.out.println(warning);
            }
        }

        return price;
    }

    /**
     * Gets the input character from the user.
     *
     * @param prompt  the prompt for the input
     * @param warning the warning for when the input is invalid
     * @return the input character
     */
    private static char getCharFromUser(String prompt, String warning) {
        char inputChar = ' ';
        boolean isValid = false;
        while (!isValid) {
            System.out.println(prompt);
            inputChar = scanner.next().trim().charAt(0);
            if (inputChar == '1' || inputChar == '2') {
                isValid = true;
            } else {
                System.out.println(warning);
            }
        }

        return inputChar;
    }
}
