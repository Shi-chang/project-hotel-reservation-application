import api.AdminResource;
import api.HotelResource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Customer;
import model.IRoom;
import model.Reservation;

/**
 * The main menu class that handles command line interactions with users and the admin.
 */
public class MainMenu {

    private static HotelResource hotelResource = HotelResource.getInstance();
    private static AdminResource adminResource = AdminResource.getInstance();
    private static Scanner scanner;

    /**
     * The entry point for the program.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        try {
            boolean exit = false;
            while (!exit) {
                String choice = displayMainMenu();
                switch (choice) {
                    case "1":
                        findAndReserveARoom();
                        break;
                    case "2":
                        seeMyReservations();
                        break;
                    case "3":
                        createAccount();
                        break;
                    case "4":
                        AdminMenu.startAdminMenu();
                        break;
                    case "5":
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid input. Please enter a valid number: \n");
                }
            }
            System.exit(0);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * Finds and reserves a room.
     */
    private static void findAndReserveARoom() {
        // Deals with getting check-in and checkout dates
        Date checkInDate = getDateFromUser("Enter check-in date (YYYY/MM/DD): ",
            "Invalid check-in date.");
        Date checkOutDate = getDateFromUser("Enter checkout date (YYYY/MM/DD): ",
            "Invalid checkout date.");

        // Gets all available rooms
        List<IRoom> availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);
        if (availableRooms.isEmpty()) {
            System.out.println("Sorry, no available rooms found for the period.\n");
            return;
        }

        // Deals with booking a room
        char bookARoom = getCharFromUser("Would you like to book a room? y/n",
            "Invalid character.");
        if (bookARoom == 'n') {
            return;
        }

        // Authentication of the user
        char hasAnAccount = getCharFromUser("Do you have an account? y/n", "Invalid character.");
        if (hasAnAccount == 'n') {
            createAccount();
        }
        Customer customer = getCustomer();
        if (customer == null) {
            System.out.println("Customer not found.\n");
            return;
        }

        // Books a room
        boolean isRoomAvailable = false;
        while (!isRoomAvailable) {
            System.out.println("Available rooms: ");
            System.out.println(availableRooms);
            System.out.println("Select a room by inputting room number: ");
            String roomNumber = scanner.next();
            IRoom selectedRoom = hotelResource.getRoom(roomNumber);

            if (!availableRooms.contains(selectedRoom)) {
                System.out.println("Sorry, Room" + roomNumber + "is not available.");
                continue;
            }
            hotelResource.bookARoom(customer.getEmail(), selectedRoom, checkInDate, checkOutDate);
            System.out.println("Reservation is successful. Thank you!");
        }
    }

    /**
     * Gets a customer.
     *
     * @return the customer with the input email
     */
    private static Customer getCustomer() {
        System.out.println("Enter your email address: ");
        String email = scanner.next();

        return hotelResource.getCustomer(email);
    }

    /**
     * Sees my reservations.
     */
    private static void seeMyReservations() {
        System.out.println("Enter your email: ");
        String email = scanner.next();
        List<Reservation> reservations = hotelResource.getCustomerReservations(email);
        if (reservations.isEmpty()) {
            System.out.println("Hi, you don't have any reservations.\n");
            return;
        }
        System.out.println(reservations);
        System.out.println();
    }

    /**
     * Creates an account.
     */
    private static void createAccount() {
        String email = getEmailFromUser("Enter your email (example@domain.com): ",
            "Email format should be example@domain.com");

        System.out.println("Enter your first name: ");
        String firstName = scanner.next();

        System.out.println("Enter your last name: ");
        String lastName = scanner.next();

        try {
            hotelResource.createACustomer(email, firstName, lastName);
            System.out.println("Account created successfully.\n");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * Displays the main menu.
     */
    private static String displayMainMenu() {
        System.out.println("----------Main Menu----------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("-----------------------------");
        System.out.println("Please enter your choice: ");
        String choice = scanner.next();
        return choice;
    }

    /**
     * Gets the input email from the user.
     *
     * @param prompt  the prompt for the input
     * @param warning the warning for when the email is invalid
     * @return the input email string
     */
    private static String getEmailFromUser(String prompt, String warning) {
        // The source of this regular expression comes from "https://regexlib.com/REDetails.aspx?regexp_id=1012"
        String emailRegex = "^([0-9a-zA-Z]+[-._+&amp;])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        String email = "";

        boolean isValid = false;
        while (!isValid) {
            System.out.println(prompt);
            email = scanner.next();
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isValid = true;
            } else {
                System.out.println(warning);
            }
        }

        return email;
    }

    /**
     * Gets the input date from the user.
     *
     * @param prompt  the prompt for the input
     * @param warning the warning for when the date is invalid
     * @return the input date
     */
    private static Date getDateFromUser(String prompt, String warning) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM/DD");
        Date inputDate = null;

        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.println(prompt);
                inputDate = simpleDateFormat.parse(scanner.next());
                isValid = true;
            } catch (ParseException e) {
                System.out.println(warning);
            }
        }

        return inputDate;
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
            if (inputChar == 'y' || inputChar == 'n') {
                isValid = true;
            } else {
                System.out.println(warning);
            }
        }

        return inputChar;
    }
}
