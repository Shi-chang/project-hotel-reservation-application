import api.AdminResource;
import api.HotelResource;
import java.util.Scanner;

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
                displayMainMenu();
                String choice = scanner.nextLine();
                switch (choice){
                    case "1":
                        findAndReserveARoom();
                    case "2":
                        seeMyReservations();
                    case "3":
                        createAccount();
                    case "4":
                        return;//todo
                    case "5":
                        exit = true;
                    default:
                        continue;
                }
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
        } finally {
            scanner.close();
        }
    }

    /**
     * Displays the main menu.
     */
    private static void displayMainMenu() {
        System.out.println("*********Main Menu*********");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("Enter your choice: ");
    }
}
