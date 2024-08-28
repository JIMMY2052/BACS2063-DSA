/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import adt.SortedListInterface;
import adt.SortedArrayList;
import dao.DonorInitializer;
import boundary.DonationUI;
import entity.Donation;
import entity.Donor;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author JIMMY
 */
public class DonationController {
    private Scanner sc = new Scanner(System.in);
    private SortedListInterface<Donor> donors = new SortedArrayList<>();
    private SortedListInterface<Donation> donations = new SortedArrayList<>();
    
    private DonationUI donationUI = new DonationUI();
    private DonorInitializer donorinitializer = new DonorInitializer();

    public DonationController() {
        donors = donorinitializer.initializeStudent();
        int choice;
        do {
            clearScreen();
            choice = donationUI.displayDonationMenu();

            switch (choice) {
                case 1:
                    makeDonation();
                    break;
                case 2:
            }
        } while (choice != 0);

    }

    private void makeDonation() {
        int choice;
        do {
            clearScreen();
            choice = donationUI.addDonationMenu();

            switch (choice) {
                case 1:
                    foodDonation();
                    break;
                case 2:
                        
                    cashDonation();
                    break;
            }
        } while (choice != 0);
    }

    private void foodDonation() {
        Donor donor;
        boolean found = false;
        String donorId = donationUI.inputDonorId();
        Iterator<Donor> iterator = donors.getIterator();
        while (iterator.hasNext()) {
            donor = iterator.next();
            if (donor.getDonorId().equals(donorId)) {
                found = true;
                break;
            }
        
        }
        
                System.out.println("Donor ID does not exists.");

        
    }
    
    private void cashDonation(){
        
    }

    public static void main(String[] args) {
        DonationController dc = new DonationController();
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
    
        public static void pressEnterContinue() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Press [Enter] key to continue...");
        sc.nextLine();
        clearScreen();
    }
}
