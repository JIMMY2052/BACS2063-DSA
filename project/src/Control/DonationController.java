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

/**
 *
 * @author JIMMY
 */
public class DonationController {
    
    private SortedListInterface<Donor> donors = new SortedArrayList<>();
    private SortedListInterface<Donation> donations = new SortedArrayList<>();
    
    private DonationUI donationUI = new DonationUI();
    private DonorInitializer initializer = new DonorInitializer();

    public DonationController() {
        donors = initializer.
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
        String donorId = donationUI.inputDonorId();
        for (int i = 0; i < 10; i++) {
            
        }
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
}
