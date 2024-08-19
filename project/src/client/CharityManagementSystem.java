package client;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import entity.*;
import java.util.Date;
import adt.SortedArrayList;
import adt.SortedListInterface;

public class CharityManagementSystem {

    public static void main(String[] args) {

        Donor g1 = new Donor("JIMMY");
        Donee r1 = new Donee("MR.CHAN");
        Donee r2 = new Donee("MS.OOI");
        Donee r3 = new Donee("MS.OOI");
        Donee r4 = new Donee("MS.OOI");
        Donee r5 = new Donee("MS.OOI");
        Donee r6 = new Donee("MS.OOI");

        DonatedItem di = new DonatedItem("CASH",1000.50,"RM");
        DonatedItem di1 = new DonatedItem("FOOD",1.5,"pack");
        DonatedItem di2 = new DonatedItem("Apple",2,"kg");
        
        Donation d1 = new Donation(g1);
        d1.addDonatedItem(di2);
         d1.addDonatedItem(di1);
          d1.addDonatedItem(di);
          
          
          
          SortedListInterface<DonatedItem> donationList = d1.getDonatedItems();
          donationList.clear();

          
          for (int i = 0; i < donationList.getNumberOfEntries() ; i++) {
              System.out.printf("%s\n",donationList.getEntry(i));
        }

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
