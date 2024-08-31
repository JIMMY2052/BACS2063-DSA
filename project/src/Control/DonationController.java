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
import dao.Initializer;
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
    private final DonationUI donationUI;
    private Initializer init = new Initializer();

    public DonationController() {
        allDonors = init.donors;
        allDonations = init.donations;
        donationUI = new DonationUI();
        int choice;
        do {
            clearScreen();
            choice = donationUI.displayDonationMenu();

            switch (choice) {
                case 1:
                    makeDonation();
                    break;
                case 2:
                    viewDonation();
                    break;
                case 3:
                    updateDonation();
                    break;
                case 4:
                    deleteDonation();
                    break;
                case 5:
                    deleteDonatedItem();
                    break;
                case 6:
                    addDonatedItem();
                    break;
                case 7:
                    searchDonation();
                    break;
                case 8:
                    generateReport();
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
                        itemDonation(donor);
                    }
                    break;
                case 2:
                    clearScreen();
                    donationUI.displayDonateCashHeader();
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

    private void itemDonation(Donor donor) {
        Donation donation = new Donation(donor, "F");
        int choice;

        do {
            String foodName = donationUI.inputFoodName();
            double qty = donationUI.inputQuantity();
            String unit = donationUI.inputUnit();
            DonatedItem donatedItem = new DonatedItem(foodName.toUpperCase(), qty, unit.toUpperCase());
            donation.addDonatedItem(donatedItem);
            choice = donationUI.askToContinue();
        } while (choice == 1);
        donor.addDonation(donation);
        allDonations.add(donation);
        System.out.printf("%s (%s) succesful make a item donation.\n", donor.getName(), donor.getDonorId());
        System.out.println(donation);
        displayDonatedItems(donation);
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
        System.out.println(donation);
        displayDonatedItems(donation);
        pressEnterContinue();
    }

//------------- View Donation ------------------------------
    private void viewDonation() {
        clearScreen();
        int choice;
        do {
            choice = donationUI.viewDonationMenu();
            switch (choice) {
                case 1:
                    clearScreen();
                    listDonation();
                    break;
                case 2:
                    clearScreen();
                    listFoodDonation();
                    break;
                case 3:
                    clearScreen();
                    listCashDonation();
                    break;
            }

        } while (choice != 0);
    }

    private void listDonation() {
        clearScreen();
        donationUI.listDonationHeader();
        SortedListInterface<DonatedItem> donatedItem;
        Iterator<Donation> donationIterator = allDonations.getIterator();
        boolean firstTime = true;

        while (donationIterator.hasNext()) {
            Donation donation = donationIterator.next();
            donatedItem = donation.getDonatedItems();

            if (donatedItem.isEmpty()) {
                printOnlyDonationDetails(donation);
            }

            if (donatedItem.getNumberOfEntries() == 1) {
                printOneDonatedItem(donation, donatedItem);
            }
            if (donatedItem.getNumberOfEntries() > 1) {
                firstTime = printMoreThanOneDonatedItem(donation, donatedItem, firstTime);
            }

            donationUI.printLine(1, 99);
            firstTime = true;
        }
        pressEnterContinue();
    }

    private void listFoodDonation() {
        donationUI.listFoodDonationHeader();
        SortedListInterface<DonatedItem> donatedItem;
        Iterator<Donation> donationIterator = allDonations.getIterator();
        boolean firstTime = true;

        while (donationIterator.hasNext()) {
            Donation donation = donationIterator.next();
            if (donation.getCategory().equals("F")) {
                donatedItem = donation.getDonatedItems();

                if (donatedItem.isEmpty()) {
                    printOnlyDonationDetails(donation);
                }
                if (donatedItem.getNumberOfEntries() == 1) {
                    printOneDonatedItem(donation, donatedItem);
                }
                if (donatedItem.getNumberOfEntries() > 1) {
                    firstTime = printMoreThanOneDonatedItem(donation, donatedItem, firstTime);
                }

                donationUI.printLine(1, 99);
                firstTime = true;
            }
        }
        pressEnterContinue();
    }

    private void listCashDonation() {
        donationUI.listCashDonationHeader();
        SortedListInterface<DonatedItem> donatedItem;
        Iterator<Donation> donationIterator = allDonations.getIterator();

        while (donationIterator.hasNext()) {
            Donation donation = donationIterator.next();
            if (donation.getCategory().equals("C")) {
                donatedItem = donation.getDonatedItems();
                if (donatedItem.getNumberOfEntries() == 1) {
                    printOneDonatedItem(donation, donatedItem);
                }
                donationUI.printLine(1, 99);
            }
        }
        pressEnterContinue();
    }

    private void printOneDonatedItem(Donation donation, SortedListInterface<DonatedItem> donatedItem) {
        System.out.printf("|%-18s| %-18s| %-18s|",
                donation.getDonationId(),
                donation.getFormattedDate(),
                donation.getDonor().getName());
        System.out.printf(" %-18s|%-18s| \n", donatedItem.getEntry(0).getItemName(), donatedItem.getEntry(0).toString());
    }

    private boolean printMoreThanOneDonatedItem(Donation donation, SortedListInterface<DonatedItem> donatedItem, boolean firstTime) {
        System.out.printf("|%-18s  %-18s  %-18s|",
                donation.getDonationId(),
                donation.getFormattedDate(),
                donation.getDonor().getName());
        Iterator<DonatedItem> itemIterator = donatedItem.getIterator();

        while (itemIterator.hasNext()) {
            DonatedItem item = itemIterator.next();

            if (firstTime == true) {
                System.out.printf(" %-18s|%-18s| \n", item.getItemName(), item.toString());
                firstTime = false;
            } else {
                System.out.printf("|%-18s %-18s  %-18s | %-17s |%-17s | \n", "", "", "", item.getItemName(), item.toString());
            }

        }
        return firstTime;
    }

    private void printOnlyDonationDetails(Donation donation) {
        System.out.printf("|%-18s| %-18s| %-18s|",
                donation.getDonationId(),
                donation.getFormattedDate(),
                donation.getDonor().getName());
        System.out.printf(" %-18s|%-18s| \n", "", "");
    }
//------------- Update Donation ------------------------------

    private void updateDonation() {
        clearScreen();
        int optType;

        donationUI.displayEditDonationHeader();
        optType = donationUI.displayUpdateMenu();

        if (optType == 1) {
            updateItemDonation();
        }

        if (optType == 2) {
            updateCashDonation();
        }

        pressEnterContinue();
    }

    private void displayDonatedItems(Donation donation) {
        SortedListInterface<DonatedItem> donatedItemList = donation.getDonatedItems();
        for (int i = 0; i < donatedItemList.getNumberOfEntries(); i++) {
            DonatedItem donatedItems = donatedItemList.getEntry(i);
            if (donatedItems.getUnit().equals("RM")) {
                System.out.printf("%d) %-13s: %s %.2f \n", i + 1, donatedItems.getItemName(), donatedItems.getUnit(), donatedItems.getQuantity());
            } else {
                System.out.printf("%d) %-13s: %11.2f %s\n", i + 1, donatedItems.getItemName(), donatedItems.getQuantity(), donatedItems.getUnit());
            }
        }
        donationUI.printLine(1, 54);
    }

    private void updateItemName(int itemNo, Donation donation) {
        int opt;
        boolean opt2 = true;
        String newItemName;
        String string = "Item Number " + itemNo;
        do {
            do {
                opt2 = true;
                newItemName = donationUI.inputNewItemName(string);
                if (newItemName.equals("0")) {
                    return;
                }

                if (newItemName.toLowerCase().equals("cash")) {
                    System.out.println("This is item donation cannot enter \"CASH\" as item name.");
                    System.out.println("Please re-enter the item name. ");
                    opt2 = false;
                }
            } while (opt2 == false);
            opt = donationUI.areYouSure("item name.");

        } while (opt == 0);

        DonatedItem donatedItem = searchDonatedItemByIndex(itemNo - 1, donation);
        donatedItem.setItemName(newItemName);
        System.out.printf("Sucessfully Updated Item Name for Item No. [%d]\n", itemNo);

    }

    private void updateItemQuantiy(int itemNo, Donation donation) {
        int opt;
        double qty;
        String newUnit;
        String string = "Item Number " + itemNo;
        do {
            qty = donationUI.inputNewItemQty(string);
            if (qty == 0) {
                return;
            }
            opt = donationUI.areYouSure("quantity.");

        } while (opt == 0);

        do {
            newUnit = donationUI.inputNewItemUnit(string);
            if (newUnit.equals("0")) {
                return;
            }
            opt = donationUI.areYouSure("item name.");

        } while (opt == 0);

        DonatedItem donatedItem = searchDonatedItemByIndex(itemNo - 1, donation);
        donatedItem.setQuantity(qty);
        donatedItem.setUnit(newUnit);
        System.out.printf("Sucessfully Updated Item Quantity & Unit for Item No. [%d]\n", itemNo);
    }

    private void updateItemDonation() {
        SortedListInterface<DonatedItem> donatedItemList;
        int itemNo;
        int opt;
        Donation donation = searchDonationByCategory("F", "ITEM");
        if (donation == null) {
            return;
        }
        clearScreen();
        donationUI.displayHeader("ITEM DONATION");
        System.out.println(donation);
        displayDonatedItems(donation);
        donatedItemList = donation.getDonatedItems();
        itemNo = donationUI.inputChoice(donatedItemList.getNumberOfEntries());
        if (itemNo == 0) {
            return;
        }

        opt = donationUI.inputUpdateType();
        if (opt == 0) {
            return;
        }

        switch (opt) {
            case 1:
                clearScreen();
                donationUI.displayHeader("UPDATE ITEM NAME");
                System.out.println(donation);
                displayDonatedItems(donation);
                updateItemName(itemNo, donation);
                break;
            case 2:
                clearScreen();
                donationUI.displayHeader("UPDATE ITEM QUANTITY");
                System.out.println(donation);
                displayDonatedItems(donation);
                updateItemQuantiy(itemNo, donation);
                break;
        }
    }

    private void updateCashDonation() {
        SortedListInterface<DonatedItem> donatedItemList;
        double amount;

        Donation donation = searchDonationByCategory("C", "CASH");
        if (donation == null) {
            return;
        }
        clearScreen();
        donationUI.displayHeader("CASH DONATION");
        System.out.println(donation);
        displayDonatedItems(donation);
        donatedItemList = donation.getDonatedItems();

        amount = donationUI.inputNewCash();
        donatedItemList.getEntry(0).setQuantity(amount);
        System.out.println("Successfully Update the amount.");

    }

//------------- Delete Donation ------------------------------
    private void deleteDonation() {
        clearScreen();
        donationUI.displayHeader("DELETE DONATION BY ID");
        Donation donation = searchDonationByID();
        if (donation == null) {
            return;
        }
        System.out.println(donation);
        displayDonatedItems(donation);
        int opt = donationUI.askToDeleteDonation(donation.getDonationId());
        if (opt == 0) {
            System.out.printf("Unsuccessful Remove Donation [%s].\n", donation.getDonationId());
            return;
        }
        allDonations.remove(donation);
        System.out.printf("Successfully Deleted Donation [%s]\n", donation.getDonationId());
        pressEnterContinue();

    }

//------------- Delete Donated Item ------------------------------
    private void deleteDonatedItem() {
        SortedListInterface<DonatedItem> donatedItemList;
        int itemNo;
        int opt;
        int choice;
        clearScreen();
        donationUI.displayHeader("REMOVE DONATED ITEM");
        Donation donation = searchDonationByID();
        if (donation == null) {
            return;
        }
        System.out.println(donation);
        displayDonatedItems(donation);
        donatedItemList = donation.getDonatedItems();
        choice = donationUI.inputToRemoveAllItems();
        if (choice == 0) {
            return;
        }
        if (choice == 1) {
            int option;
            clearScreen();
            System.out.println(donation);
            displayDonatedItems(donation);
            System.out.println("WARNING: REMOVE ALL ITEMS !!!");
            option = donationUI.askToContinue();
            if (option == 0) {
                System.out.println("Exit To Main Page...");
            }
            donatedItemList.clear();
            System.out.println("Successfully Removed ALL items.");
        }

        if (choice == 2) {
            do {
                clearScreen();
                System.out.println(donation);
                displayDonatedItems(donation);
                itemNo = donationUI.inputChoiceDonatedItem(donatedItemList.getNumberOfEntries());
                if (itemNo == 0) {
                    return;
                }
                opt = donationUI.areYouSureDonatedItem(itemNo);

            } while (opt == 0);
            donatedItemList.remove(opt - 1);
            System.out.printf("Successfully removed item [%d]\n", itemNo);
        }

        pressEnterContinue();
    }

//------------- Search Donated Item ------------------------------
    private void searchDonation() {
        clearScreen();
        donationUI.displayHeader("SEARCH DONATION");
        Donation donation = searchDonationByID();
        System.out.println(donation);
        displayDonatedItems(donation);
        pressEnterContinue();
    }

//------------- Add Donated Item ------------------------------ 
    private void addDonatedItem() {
        int opt;
        clearScreen();
        opt = donationUI.addDoantedItemMenu();

        if (opt == 0) {
            return;
        }

        if (opt == 1) {
            addItem();
        }

        if (opt == 2) {
            addCash();
        }

    }

    private void addItem() {
        int choice;
        Donation donation = searchDonationByCategory("F", "ITEM");
        if (donation == null) {
            return;
        }

        do {
            clearScreen();
            donationUI.displayHeader("ITEM DONATION");
            System.out.println(donation);
            displayDonatedItems(donation);
            String foodName = donationUI.inputFoodName();
            double qty = donationUI.inputQuantity();
            String unit = donationUI.inputUnit();
            DonatedItem donatedItem = new DonatedItem(foodName.toUpperCase(), qty, unit.toUpperCase());
            donation.addDonatedItem(donatedItem);
            choice = donationUI.askToContinue();
        } while (choice == 1);
    }

    private void addCash() {

    }

//------------- Generate Report ------------------------------ 
    private void generateReport() {

    }

//Sub Function
    private Donation searchDonationByCategory(String string, String string2) {
        Donation donation = null;
        boolean found = false;
        String donationId = donationUI.inputItemDonationId(string2);
        if (donationId.equals("0")) {
            return null;
        }
        Iterator<Donation> iterator = allDonations.getIterator();
        while (iterator.hasNext()) {
            donation = iterator.next();
            if (donation.getDonationId().equals(donationId.toUpperCase())) {
                if (donation.getCategory().equals(string)) {
                    found = true;
                    break;
                }

            }

        }

        if (found == false) {
            System.out.println("Donation ID does not exists.");
            return null;
        }
        return donation;
    }

    private Donor searchDonorByID() {
        Donor donor = null;
        boolean found = false;
        String donorId = donationUI.inputDonorId();
        Iterator<Donor> iterator = allDonors.getIterator();
        while (iterator.hasNext()) {
            donor = iterator.next();
            if (donor.getDonorId().equals(donorId.toUpperCase())) {
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
        if (donationId.equals("0")) {
            return null;
        }
        Iterator<Donation> iterator = allDonations.getIterator();
        while (iterator.hasNext()) {
            donation = iterator.next();
            if (donation.getDonationId().equals(donationId.toUpperCase())) {
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

    private DonatedItem searchDonatedItemByIndex(int itemNo, Donation donation) {
        SortedListInterface<DonatedItem> donatedItems = donation.getDonatedItems();
        DonatedItem donatedItem = donatedItems.getEntry(itemNo);
        return donatedItem;
    }

    private static void clearScreen() {
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

    public void pressEnterContinue() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Press [Enter] key to continue...");
        sc.nextLine();
        clearScreen();
    }

    public static void main(String[] args) {
        DonationController dc = new DonationController();
    }
}
