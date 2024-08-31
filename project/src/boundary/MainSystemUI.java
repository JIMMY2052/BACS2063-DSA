package boundary;

/**
 *
 * @author KK
 */

import java.util.Scanner;

public class MainSystemUI {

    Scanner scanner = new Scanner(System.in);

    public MainSystemUI(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getMainMenu() {
        System.out.println("Main System");
        System.out.println("1. Donor Management");
        System.out.println("2. Donation Management");
        System.out.println("3. Event & Volunteer Management");
        System.out.println("0. Quit");
        System.out.print("Enter Choice: ");
        String choice = scanner.nextLine();
        System.out.println();
        return choice;
    }

}
