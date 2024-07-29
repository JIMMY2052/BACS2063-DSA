package client;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class CharityManagementSystem {

    public static void main(String[] args) {
        displayMainMenu();

    }

    public static void displayMainMenu() {
        int opt;
        do {
            clearScreen();
            System.out.printf("\n\n");
            System.out.printf("\t    Charity Donation System\n");
            System.out.println("-----------------------------------------------");
            System.out.println("|\t     1. Donor Management \t\t|");
            System.out.println("|\t     2. Donee Management \t\t|");
            System.out.println("|\t     3. Donation Management \t\t|");
            System.out.println("------------------------------------------------");
            System.out.println("Choose an option from the above by entering number (1-4): ");
            Scanner sc = new Scanner(System.in);
            opt = sc.nextInt();

            switch (opt) {
                case 1:
                    System.out.println("Donor");
                    break;
                case 2:
                    System.out.println("Donee");
                    break;
                case 3:
                    donationSubSystem();
                    break;
                default:
                    System.out.println("Error");
            }
        } while (opt <= 3 && opt >= 1);
    }

    public static void donationSubSystem() {
        int opt;
        do {
            System.out.printf("\n\n");
            System.out.printf("\t   Donation Management\n");
            System.out.println("-----------------------------------------------");
            System.out.println("|\t     1. Donate Food \t\t|");
            System.out.println("|\t     2. Donate Cash \t\t|");
            System.out.println("|\t     3. Update Donation Detials \t\t|");
            System.out.println("------------------------------------------------");
            System.out.println("Choose an option from the above by entering number (1-4): ");
            Scanner sc = new Scanner(System.in);
            opt = sc.nextInt();
            switch (opt) {
                case 1:
                    System.out.println("Donate FOOD");
                    break;
                case 2:
                    System.out.println("Donate CASH");
                    break;
                case 3:
                    donationSubSystem();
                    break;
                default:
                    System.out.println("Error");
            }
        } while (opt <= 3 && opt >= 1);
    }

    public static void clearScreen() {
        try {
            Robot rob = new Robot();
            try {
                rob.keyPress(KeyEvent.VK_CONTROL); // press "CTRL"
                rob.keyPress(KeyEvent.VK_L); // press "L"
                rob.keyRelease(KeyEvent.VK_L); // unpress "L"
                rob.keyRelease(KeyEvent.VK_CONTROL); // unpress "CTRL"
                Thread.sleep(50); // add delay in milisecond, if not there will automatically stop after clear
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
