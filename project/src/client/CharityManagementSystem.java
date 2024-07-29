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
            System.out.println("Choose an option from the above by entering number (1-3): ");
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
            clearScreen();
            switch (opt) {
                case 1:
                    donateFoodFunction();
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

    public static void donateFoodFunction() {

        
        char itemNo;
        char confirm = 'n';
        int optAscii;

        do{
        clearScreen();
        System.out.println("FOOD DONATION");
        System.out.println("Donate Food");
        System.out.println("1. Rice");
        System.out.println("2. Oil");
        System.out.println("3. Milo");
        System.out.println("4. Chicken");
        System.out.println("5. Others...");
        System.out.println("Press (Y) : Proceed to next step");
        System.out.println("Press (X) : Back to previous step");
        System.out.printf("Enter the item no. that want to donate \nHere --> ");
        
        itemNo = captureItemNo();
        optAscii = itemNo;
        
        Scanner sc = new Scanner(System.in);

        
//        if (optAscii >= 49 && optAscii <= 53) {
//            confirm = confirmationFood();
//        }
        }while(confirm == 'n' || itemNo != 'y');

    }

    public static char captureItemNo() {
        String itemNo;
        int ascii;
        Boolean check;
        do {
            check = false;
            Scanner sc = new Scanner(System.in);
            itemNo = sc.next();
            ascii = itemNo.charAt(0);

            if (itemNo.length() > 1) {
                System.out.printf("Please enter correct number (1-5) or character(Y/N) only. \n");
                check = true;
            }
            if (ascii != 49 && ascii !=50 && ascii != 51 && ascii != 52 && ascii !=53 &&
                    itemNo.toUpperCase().charAt(0) != 'Y' && itemNo.toUpperCase().charAt(0) != 'X'){
                System.out.printf("Please enter correct number (1-5) or character(Y/N) only. \n");
                check = true;
            }

        } while (check);
        return itemNo.charAt(0);
    }
    public static char confirmationFood() {
        String confirm;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.printf("**Are you sure to donate this item ?(Y/N)**\nHere -->");
            confirm = sc.next();
            
            if (confirm.length() > 1) {
                System.out.printf("Please enter correct character (Y/N) only.\n");
            }
            
        } while (confirm.length() > 1);

        return confirm.charAt(0);
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
