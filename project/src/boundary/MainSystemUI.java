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
        
         System.out.println("\t\t\t\tCHARITY DONATION MANAGEMENT SYSTEM");
        System.out.println("\t\t=============================================================================");
        System.out.println("\t\t\t\t1. Donor Management");
        System.out.println("\t\t\t\t2. Donation Management");
        System.out.println("\t\t\t\t3. Donation Distribution Management");
        System.out.println("\t\t\t\t4. Donee Management");
        System.out.println("\t\t\t\t5. Event & Volunteer Management");
        System.out.println("\t\t\t\t0. Quit");
        System.out.println("\t\t=============================================================================");
        System.out.print("\t\t\tEnter your choice -> ");
        String choice = scanner.nextLine();
        System.out.println();
        return choice;
    }

}
