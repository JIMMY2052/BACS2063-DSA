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
import entity.DonatedItem;
import entity.Donor;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author JIMMY
 */
public class DonationController {

    private Scanner sc = new Scanner(System.in);
    private SortedListInterface<Donor> allDonors = new SortedArrayList<>();
    private SortedListInterface<Donation> allDonations = new SortedArrayList<>();
    private SortedListInterface<DonatedItem> allDonatedItems = new SortedArrayList<>();

    private DonationUI donationUI = new DonationUI();
    private DonorInitializer donorinitializer = new DonorInitializer();

    public DonationController() {
        allDonors = donorinitializer.initializeStudent();
        int choice;
        do {
            clearScreen();
            choice = donationUI.displayDonationMenu();

            switch (choice) {
                case 1:
                    makeDonation();
                    break;
                case 2:
                    listDonation();
                    break;
                case 3:
                    editDonation();
                    break;
            }
        } while (choice != 0);

    }
//------------- Make Donation ------------------------------

    private void makeDonation() {
        int choice;
        do {
            clearScreen();
            choice = donationUI.addDonationMenu();
            Donor donor;
            switch (choice) {
                case 1:
                    clearScreen();
                    donationUI.displayDonateFoodHeader();
                    donor = searchDonorByID();
                    if (donor == null) {
                        break;
                    } else {
                        foodDonation(donor);
                    }
                    break;
                case 2:
                    clearScreen();
                    donationUI.displayDonateFoodHeader();
                    donor = searchDonorByID();
                    if (donor == null) {
                        break;
                    } else {
                        cashDonation(donor);
                    }
                    break;
            }
        } while (choice != 0);
    }

    private void foodDonation(Donor donor) {
        Donation donation = new Donation(donor, "F");
        int choice;

        do {
            String foodName = donationUI.inputFoodName();
            double qty = donationUI.inputQuantity();
            String unit = donationUI.inputUnit();
            DonatedItem donatedItem = new DonatedItem(foodName, qty, unit);
            donation.addDonatedItem(donatedItem);
            choice = donationUI.askToContinue();
        } while (choice == 1);
        donor.addDonation(donation);
        allDonations.add(donation);
        System.out.printf("%s (%s) succesful make a food donation.\n", donor.getName(), donor.getDonorId());
        pressEnterContinue();
    }

    private void cashDonation(Donor donor) {
        Donation donation = new Donation(donor, "C");
        int choice;

        do {
            double amount = donationUI.inputCash();
            DonatedItem donatedItem = new DonatedItem("CASH", amount, "RM");
            donation.addDonatedItem(donatedItem);
            choice = donationUI.askToContinue();
        } while (choice == 1);
        donor.addDonation(donation);
        allDonations.add(donation);
        System.out.printf("%s (%s) succesful make a cash donation.\n", donor.getName(), donor.getDonorId());
        pressEnterContinue();
    }
//------------- Make Donation ------------------------------

//------------- List All Donation ------------------------------
    private void listDonation() {
        clearScreen();
        donationUI.listDonationHeader();
        SortedListInterface<DonatedItem> donatedItem;
        Iterator<Donation> donationIterator = allDonations.getIterator();
        while (donationIterator.hasNext()) {
            Donation donation = donationIterator.next();
            donatedItem = donation.getDonatedItems();
            System.out.printf("|%-18s| %-18s| %-18s|",
                    donation.getDonationId(),
                    donation.getFormattedDate(),
                    donation.getDonor().getName());
            Iterator<DonatedItem> itemIterator = donatedItem.getIterator();
            boolean firstTime = true;
            while (itemIterator.hasNext()) {
                DonatedItem item = itemIterator.next();

                if (firstTime == true) {
                    System.out.printf(" %-18s|%-18s| \n", item.getItemName(), item.toString());
                    firstTime = false;
                } else {
                    System.out.printf("|%-18s %-18s  %-18s | %-17s |%-17s | \n", "", "", "", item.getItemName(), item.toString());
                }
            }
            donationUI.printLine(1, 99);
            firstTime = true;
        }
        pressEnterContinue();
    }
//------------- List All Donation ------------------------------

    private void editDonation() {
        clearScreen();
        Donation donation;
        SortedListInterface<DonatedItem> donatedItemList;
        donationUI.displayEditDonationHeader();
        donation = searchDonationByID();
        System.out.println(donation);
        donatedItemList= donation.getDonatedItems();
        for(int i = 0 ; i < donatedItemList.getNumberOfEntries() ; i++){
            DonatedItem donatedItems = donatedItemList.getEntry(i);
            System.out.printf("%d) %s: %s %s",i+1, donatedItems.getItemName(), donatedItems.getQuantity(),donatedItems.getUnit());
            
        }
        pressEnterContinue();
    }

    private Donor searchDonorByID() {
        Donor donor = null;
        boolean found = false;
        String donorId = donationUI.inputDonorId();
        Iterator<Donor> iterator = allDonors.getIterator();
        while (iterator.hasNext()) {
            donor = iterator.next();
            if (donor.getDonorId().equals(donorId)) {
                found = true;
                break;
            }

        }

        if (found == false) {
            System.out.println("Donor ID does not exists.");
            pressEnterContinue();
            return null;
        }
        return donor;
    }

    private Donation searchDonationByID() {
        Donation donation = null;
        boolean found = false;
        String donationId = donationUI.inputDonationId();
        Iterator<Donation> iterator = allDonations.getIterator();
        while (iterator.hasNext()) {
            donation = iterator.next();
            if (donation.getDonationId().equals(donationId)) {
                found = true;
                break;
            }

        }

        if (found == false) {
            System.out.println("Donation ID does not exists.");
            pressEnterContinue();
            return null;
        }
        return donation;
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

    public static void main(String[] args) {
        DonationController dc = new DonationController();
    }
}