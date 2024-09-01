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
import boundary.DonationUI;
import boundary.EventUI;
import entity.Donation;
import entity.DonatedItem;
import entity.Donor;
import entity.Event;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
    private SortedListInterface<Event> eventList = new SortedArrayList<>();
    private EventUI eventUI;
    private DonationUI donationUI;

    public DonationController(SortedListInterface<Donor> allDonors, SortedListInterface<Donation> allDonations, SortedListInterface<Event> event) {
        this.allDonations = allDonations;
        this.allDonors = allDonors;
        this.eventList = event;
    }

    public void DonationManagement() {
        donationUI = new DonationUI();
        eventUI = new EventUI();
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
                case 9:
                    generateTopDonatedItemsReport();
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
        boolean loop = false;
        String inputEventID = "";
        System.out.printf("\n");
        displayAllEvents();
        System.out.printf("\n");
        do {
            boolean found = false;
            inputEventID = eventUI.inputEditEventID();
            for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
                Event event = eventList.getEntry(i);
                if (inputEventID.equals(event.getEventID())) {
                    found = true;
                    loop = true;
                    break;
                }
            }
            if (!found) {
                System.err.println("Event ID Not Found. Please Try Again.");
            }
        } while (!loop);
        Donation donation = new Donation(donor, "F", inputEventID);
        int choice;

        do {
            String foodName = donationUI.inputFoodName();
            double qty = donationUI.inputQuantity();
            String unit = donationUI.inputUnit();
            DonatedItem donatedItem = new DonatedItem(foodName.toUpperCase(), qty, unit);
            donation.addDonatedItem(donatedItem);
            choice = donationUI.askToContinue();
        } while (choice == 1);
        clearScreen();
        donationUI.displayDonateFoodHeader();
        donor.addDonation(donation);
        allDonations.add(donation);
        System.out.printf("%s (%s) succesful make a item donation.\n", donor.getName(), donor.getDonorId());
        System.out.println(donation);
        displayDonatedItems(donation);
        pressEnterContinue();
    }

    private void cashDonation(Donor donor) {
        String inputEventID = "";
        boolean loop = false;
        System.out.printf("\n");
        displayAllEvents();
        System.out.printf("\n");
        do {
            boolean found = false;
            inputEventID = eventUI.inputEditEventID();
            for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
                Event event = eventList.getEntry(i);
                if (inputEventID.equals(event.getEventID())) {
                    found = true;
                    loop = true;
                    break;
                }
            }
            if (!found) {
                System.err.println("Event ID Not Found. Please Try Again.");
            }
        } while (!loop);

        Donation donation = new Donation(donor, "C", inputEventID);
        DonatedItem donatedItem;
        int choice;
        double amount = donationUI.inputCash();
        donatedItem = new DonatedItem("CASH", amount, "RM");
        choice = donationUI.areYouSure1();

        if (choice == 0) {
            System.out.printf("%s (%s) unsuccesful make a cash donation.\n", donor.getName(), donor.getDonorId());
            pressEnterContinue();
            return;
        }

        clearScreen();
        donationUI.displayDonateCashHeader();
        donation.addDonatedItem(donatedItem);
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

            donationUI.printLine(1, 121);
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

                donationUI.printLine(1, 121);
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
                donationUI.printLine(1, 121);
            }
        }
        pressEnterContinue();
    }

    private void printOneDonatedItem(Donation donation, SortedListInterface<DonatedItem> donatedItem) {
        System.out.printf("|%-18s| %-18s| %-18s| %-20s|",
                donation.getDonationId(),
                donation.getFormattedDate(),
                donation.getDonor().getName(),
                donation.getEvent());
        System.out.printf(" %-18s|%-18s| \n", donatedItem.getEntry(0).getItemName(), donatedItem.getEntry(0).toString());
    }

    private boolean printMoreThanOneDonatedItem(Donation donation, SortedListInterface<DonatedItem> donatedItem, boolean firstTime) {
        System.out.printf("|%-18s  %-18s  %-18s  %-20s|",
                donation.getDonationId(),
                donation.getFormattedDate(),
                donation.getDonor().getName(),
                donation.getEvent());
        Iterator<DonatedItem> itemIterator = donatedItem.getIterator();

        while (itemIterator.hasNext()) {
            DonatedItem item = itemIterator.next();

            if (firstTime == true) {
                System.out.printf(" %-18s|%-18s| \n", item.getItemName(), item.toString());
                firstTime = false;
            } else {
                System.out.printf("|%-18s %-18s  %-18s  %-21s| %-17s |%-17s | \n", "", "", "", "", item.getItemName(), item.toString());
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
        donatedItem.setItemName(newItemName.toUpperCase());
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
        donatedItem.setUnit(newUnit.toUpperCase());
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

        String donorId = donation.getDonor().getDonorId();
        Donor donor = searchDonorByID(donorId);
        donor.getDonorDonationList().remove(donation);
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

        pressEnterContinue();
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

        clearScreen();
        donationUI.displayHeader("ITEM DONATION");
        System.out.println(donation);
        displayDonatedItems(donation);
        System.out.printf("Succesfully Added Donated Item to %s\n", donation.getDonationId());

    }

    private void addCash() {
        Donation donation = searchDonationByCategory("C", "CASH");
        if (donation == null) {
            return;
        }
        clearScreen();
        donationUI.displayHeader("CASH DONATION");
        System.out.println(donation);
        displayDonatedItems(donation);
        double amount = donationUI.inputCash();
        DonatedItem donatedItem = new DonatedItem("CASH", amount, "RM");
        if (donation.getDonatedItems().isEmpty()) {
            donation.addDonatedItem(donatedItem);
        } else {
            donation.getDonatedItems().replace(0, donatedItem);
        }
        clearScreen();
        donationUI.displayHeader("CASH DONATION");
        System.out.println(donation);
        displayDonatedItems(donation);
        System.out.printf("Succesfully Added Cash to %s\n", donation.getDonationId());
    }

//------------- Generate Report ------------------------------ 
    private void generateReport() {
        clearScreen();

        donationUI.displayHeader("GENERATE MONTHLY REPORT");

        int month = donationUI.inputMonth();
        int year = donationUI.inputYear();

        if (month < 1 || month > 12 || year < 0) {
            System.out.println("Invalid month or year.");
            pressEnterContinue();
            return;
        }

        double totalCash = 0;
        int totalFoodItems = 0;
        int totalDonations = 0;

        Iterator<Donation> donationIterator = allDonations.getIterator();
        SortedListInterface<Donation> donationForReport = new SortedArrayList<>();
        while (donationIterator.hasNext()) {
            Donation donation = donationIterator.next();
            Calendar cal = Calendar.getInstance();
            cal.setTime(donation.getDate());

            int donationMonth = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH is 0-based, so add 1
            int donationYear = cal.get(Calendar.YEAR);

            // Filter donations by the given month and year
            if (donationMonth == month && donationYear == year) {
                totalDonations++;

                // Aggregate cash donations
                if (donation.getCategory().equals("C")) {
                    SortedListInterface<DonatedItem> donatedItems = donation.getDonatedItems();
                    for (int i = 0; i < donatedItems.getNumberOfEntries(); i++) {
                        DonatedItem item = donatedItems.getEntry(i);
                        totalCash += item.getQuantity();
                    }
                }

                // Aggregate food item donations
                if (donation.getCategory().equals("F")) {
                    totalFoodItems += donation.getDonatedItems().getNumberOfEntries();
                }

                donationForReport.add(donation);
            }
        }

        clearScreen();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd yyyy, hh:mm a");
        String generatedDate = now.format(formatter);
        donationUI.displayReportHeader(generatedDate);

        System.out.printf("Monthly Report for %02d-%d\n", month, year);
        System.out.println("==========================================================================");
        for (int i = 0; i < donationForReport.getNumberOfEntries(); i++) {
            Donation donationReport = donationForReport.getEntry(i);
            System.out.printf("%d)", i + 1);
            System.out.println(donationReport);
        }
        donationUI.printLine(1, 55);
        System.out.printf("Total Donations: %d\n", totalDonations);
        System.out.printf("Total Cash Donations: RM %.2f\n", totalCash);
        System.out.printf("Total Food Items Donated: %d\n", totalFoodItems);
        donationUI.displayReportFooter();

        pressEnterContinue();
    }

// ------------- Generate Top Donated Items Report ------------------------------
    private void generateTopDonatedItemsReport() {
        clearScreen();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd yyyy, hh:mm a");
        String generatedDate = now.format(formatter);
        donationUI.displayReportHeader(generatedDate);
        System.out.println("\t\t\t  Top Donated Items Report");
        System.out.println("==========================================================================");

        SortedListInterface<DonatedItem> aggregatedItems = new SortedArrayList<>();

        Iterator<Donation> donationIterator = allDonations.getIterator();
        while (donationIterator.hasNext()) {
            Donation donation = donationIterator.next();

            SortedListInterface<DonatedItem> donatedItems = donation.getDonatedItems();

            for (int i = 0; i < donatedItems.getNumberOfEntries(); i++) {
                DonatedItem currentItem = donatedItems.getEntry(i);
                String currentItemName = currentItem.getItemName();
                double currentQuantity = currentItem.getQuantity();
                String currentItemUnit = currentItem.getUnit();

                boolean itemFound = false;
                for (int j = 0; j < aggregatedItems.getNumberOfEntries(); j++) {
                    DonatedItem aggregatedItem = aggregatedItems.getEntry(j);
                    if (aggregatedItem.getItemName().equalsIgnoreCase(currentItemName)) {

                        aggregatedItem.setQuantity(aggregatedItem.getQuantity() + currentQuantity);
                        itemFound = true;
                        break;
                    }
                }

                if (!itemFound) {
                    DonatedItem newItem = new DonatedItem(currentItemName, currentQuantity, currentItemUnit);
                    aggregatedItems.add(newItem);
                }
            }

        }

        for (int i = 0; i < aggregatedItems.getNumberOfEntries() - 1; i++) {
            for (int j = 0; j < aggregatedItems.getNumberOfEntries() - 1 - i; j++) {
                DonatedItem currentItem = aggregatedItems.getEntry(j);
                DonatedItem nextItem = aggregatedItems.getEntry(j + 1);

                if (currentItem.getQuantity() < nextItem.getQuantity()) {
                    aggregatedItems.replace(j, nextItem);
                    aggregatedItems.replace(j + 1, currentItem);
                }
            }
        }

        // Display the sorted top donated items
        System.out.println("\tItem Name\t\tTotal Quantity\t\tUnit");
        donationUI.printLine(1, 74);
        for (int i = 0; i < aggregatedItems.getNumberOfEntries(); i++) {
            DonatedItem item = aggregatedItems.getEntry(i);
            System.out.printf("\t%-21s\t%-10.2f\t\t%-12s\n", item.getItemName(), item.getQuantity(), item.getUnit());
        }
        donationUI.displayReportFooter();
        pressEnterContinue();
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

    private Donor searchDonorByID(String donorId) {
        Donor donor = null;
        boolean found = false;
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

    public void displayAllEvents() {
        eventUI.displayAllEventsHeader();
        for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
            Event event = eventList.getEntry(i);
            eventUI.displayAllEventsDetail(event);
        }
    }

}
