package boundary;
/**
 *
 * @author KK
 */
import adt.*;
import control.*;
import entity.*;
import java.util.Scanner;

public class MainSystemUI {

    Scanner scanner = new Scanner(System.in);

    public MainSystemUI(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getMainMenu() {
        System.out.println("Main System");
        System.out.println("1. Event & Volunteer Management");
        System.out.println("0. Quit");
        System.out.print("Enter Choice: ");
        String choice = scanner.next();
        System.out.println();
        return choice;
    }

    public static void clearScreen() {
    for (int i = 0; i < 50; i++) {
        System.out.println();
    }
}

}
