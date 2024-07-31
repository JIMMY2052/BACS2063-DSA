package client;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import entity.*;
import java.util.Date;
import adt.ListInterface;
import adt.ArrayList;

public class CharityManagementSystem {

    public static void main(String[] args) {
        //displayMainMenu();
        Donor g1 = new Donor("JIMMY");
        Donee r1 = new Donee("Chan");
        Donation d1 = new Donation(1,g1,r1,new Date());
        FoodDonation f1 = new FoodDonation(2,g1,r1,new Date(),"RICE",1);
        FoodDonation f2 = new FoodDonation(3,g1,r1,new Date(),"RICEasdf",2);
        ListInterface<FoodDonation> foodArray = new ArrayList<>();
        foodArray.add(f1);
        foodArray.add(f2);
        System.out.println(foodArray.getEntry(2).getFoodName());
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
                    int itemNo = donateFoodFunction();
                    if (itemNo != 'x' || itemNo != 'x') {

                    }
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
