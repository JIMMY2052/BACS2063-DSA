package control;

import adt.SortedListInterface;
import adt.SortedArrayList;
import boundary.DonationDistributionUI;
import entity.DonationDistribution;
import entity.Donation;
import entity.Donor;
import entity.Donee;
import java.util.Iterator;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import entity.DonatedItem;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class DonationDistributionController {

    private Scanner sc = new Scanner(System.in);
    private SortedListInterface<DonationDistribution> allDistributions = new SortedArrayList<>();
    private DonationDistributionUI distributionUI = new DonationDistributionUI();
    private SortedListInterface<DonatedItem> allDonatedItems = new SortedArrayList<>();
    private SortedListInterface<Donation> allDonations = new SortedArrayList<>();
    private SortedListInterface<Donee> allDonees = new SortedArrayList<>();

    public DonationDistributionController(SortedListInterface<Donee> doneeList, SortedListInterface<Donation> donationList, SortedListInterface<DonationDistribution> distributionList) {
        this.allDistributions = distributionList;
        this.allDonees = doneeList;
        this.allDonations = donationList;

    }

    public void DonationDistributionManagement() {


        int choice;
        do {
            choice = distributionUI.displayDistributionMenu();

            switch (choice) {
                case 1:
                    clearScreen();
                    addNewDistribution();
                    break;
                case 2:
                     clearScreen();
                    updateDistribution();
                    break;
                case 3:
                     clearScreen();
                    removeDistribution();
                    break;
                case 4:
                     clearScreen();
                    monitorDistributedItems();
                    break;
                case 5:
                     clearScreen();
                    generateSummaryReports();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    //------------- Add New Distribution ------------------------------
    private void addNewDistribution() {
        String address = distributionUI.inputDistributionAddress();

        Donation donation;
        while (true) {
            String donationId = distributionUI.inputDonationId();
            donation = searchDonationByID(donationId);
            if (donation != null) {
                break;
            } else {
                System.out.println("Donation ID does not exist. Please enter a valid Donation ID.");
            }
        }

        Donee donee;
        while (true) {
            String doneeId = distributionUI.inputDoneeId();
            donee = searchDoneeByID(doneeId);
            if (donee != null) {
                break;
            } else {
                System.out.println("Donee ID does not exist. Please enter a valid Donee ID.");
            }
        }

        System.out.println("You have entered the following information:");
        System.out.printf("Address: %s\n", address);
        System.out.printf("Donation ID: %s\n", donation.getDonationId());
        System.out.printf("Donee ID: %s\n", donee.getDoneeId());

        if (!distributionUI.confirmSaveDistribution()) {
            System.out.println("Distribution not saved. Returning to the previous menu.");
            pressEnterContinue();
            return;
        }

        DonationDistribution distribution = new DonationDistribution(donation, address, donee);
        allDistributions.add(distribution);

        System.out.printf("Successfully added new distribution with ID %s.\n", distribution.getDistributionId());
        pressEnterContinue();
    }

    private Donee searchDoneeByID(String doneeId) {
        Donee donee = null;
        Iterator<Donee> iterator = allDonees.getIterator();
        while (iterator.hasNext()) {
            donee = iterator.next();
            if (donee.getDoneeId().equals(doneeId)) {
                return donee;
            }
        }

        return null;
    }

    private Donation searchDonationByID(String donationId) {
        Donation donation = null;
        Iterator<Donation> iterator = allDonations.getIterator();
        while (iterator.hasNext()) {
            donation = iterator.next();
            if (donation.getDonationId().equals(donationId)) {
                return donation;
            }
        }

        return null;
    }

    //------------- Update Distribution ------------------------------
    private void updateDistribution() {

        if (allDistributions.isEmpty()) {
            System.out.println("No distribution records found.");
            pressEnterContinue();
            return;
        }

        String distributionId;
        DonationDistribution distribution;

        while (true) {
            distributionId = distributionUI.inputDistributionId();
            distribution = searchDistributionByID(distributionId);

            if (distribution == null) {
                System.out.println("Distribution ID does not exist. Please try again.");
            } else {
                break;
            }
        }

        String newAddress;

        while (true) {
            newAddress = distributionUI.inputDistributionAddress();
            if (newAddress == null || newAddress.trim().isEmpty()) {
                System.out.println("Address cannot be empty. Please try again.");
            } else {
                break;
            }
        }

        String newDoneeId;
        Donee newDonee;

        while (true) {
            newDoneeId = distributionUI.inputDoneeId();
            newDonee = searchDoneeByID(newDoneeId);

            if (newDonee == null) {
                System.out.println("Donee ID does not exist. Please try again.");
            } else {
                break;
            }
        }

        String newDonationId;
        Donation newDonation;

        while (true) {
            newDonationId = distributionUI.inputDonationId();
            newDonation = searchDonationByID(newDonationId);

            if (newDonation == null) {
                System.out.println("Donation ID does not exist. Please try again.");
            } else {
                break;
            }
        }

        System.out.println("You have entered the following new information:");
        System.out.printf("New Address: %s\n", newAddress);
        System.out.printf("New Donee ID: %s\n", newDoneeId);
        System.out.printf("New Donation ID: %s\n", newDonationId);

        if (!distributionUI.confirmSaveDistribution()) {
            System.out.println("Update canceled. Returning to the previous menu.");
            pressEnterContinue();
            return;
        }

        distribution.setAddress(newAddress);
        distribution.setDonee(newDonee);
        distribution.setDonation(newDonation);

        System.out.println("Distribution updated successfully.");
        pressEnterContinue();
    }

    //------------- Remove Distribution ------------------------------
    private void removeDistribution() {

        if (allDistributions.isEmpty()) {
            System.out.println("No distribution records found.");
            pressEnterContinue();
            return;
        }

        String distributionId;
        DonationDistribution distribution;

        while (true) {
            distributionId = distributionUI.inputDistributionId();
            distribution = searchDistributionByID(distributionId);

            if (distribution == null) {
                System.out.println("Distribution ID does not exist. Please try again.");
            } else {
                break;
            }
        }

        System.out.println("You are about to delete the following distribution:");
        System.out.println("\n\n\n");
        System.out.println(distribution);
        System.out.println("\n\n\n");

        if (!distributionUI.confirmSaveDistribution()) {
            System.out.println("Deletion canceled. Returning to the previous menu.");
            pressEnterContinue();
            return;
        }

        allDistributions.remove(distribution);
        System.out.println("Distribution removed successfully.");
        pressEnterContinue();
    }

    //------------- Monitor/Track Distributed Items ------------------------------
    private void monitorDistributedItems() {
        listAllDistributions();
    }

    private void listAllDistributions() {
        System.out.println("List of all distributions:\n");
        Iterator<DonationDistribution> iterator = allDistributions.getIterator();
        if (!iterator.hasNext()) {
            System.out.println("No distributions found.");
            pressEnterContinue();
            return;
        }
        while (iterator.hasNext()) {
            DonationDistribution distribution = iterator.next();

            String distributionInfo = getDistributionInfo(distribution);
            System.out.println(distributionInfo);

            System.out.println("------------------------------------------------------------------------------------------------");

            String donatedItemsInfo = getDonatedItemsInfo(distribution);
            System.out.println(donatedItemsInfo);
        }
        pressEnterContinue();
    }

    private String getDistributionInfo(DonationDistribution distribution) {
        String formattedDate = distribution.getFormattedDate();
        String donorName = distribution.getDonation() != null && distribution.getDonation().getDonor() != null
                ? distribution.getDonation().getDonor().getName() : "N/A";
        String donorPhone = distribution.getDonation() != null && distribution.getDonation().getDonor() != null
                ? distribution.getDonation().getDonor().getContactNo() : "N/A";
        String donationId = distribution.getDonation() != null ? distribution.getDonation().getDonationId() : "N/A";
        String addressDisplay = (distribution.getAddress() != null && !distribution.getAddress().isEmpty())
                ? distribution.getAddress() : "N/A";
        String doneeName = distribution.getDonee() != null ? distribution.getDonee().getDoneeName() : "N/A";

        String doneePhone = distribution.getDonee() != null ? distribution.getDonee().getDoneeContactNo() : "N/A";

        // Create a StringBuilder to build strings
        StringBuilder sb = new StringBuilder();

        // Add the primary information for the distribution record
        sb.append(String.format(
                "%-20s | %-20s | %-20s | %-20s\n",
                "Distribution ID: " + distribution.getDistributionId(),
                "Address: " + addressDisplay,
                "Donation ID: " + donationId,
                "Date: " + formattedDate
        ));

        return sb.toString();
    }

    private String getDonatedItemsInfo(DonationDistribution distribution) {
        StringBuilder sb = new StringBuilder();

        if (distribution.getDonation() != null && distribution.getDonation().getDonatedItems() != null) {
            // Donor information is displayed
            sb.append(String.format(
                    "Donor: %-20s | Donor Phone: %-15s\n",
                    distribution.getDonation().getDonor() != null ? distribution.getDonation().getDonor().getName() : "N/A",
                    distribution.getDonation().getDonor() != null ? distribution.getDonation().getDonor().getContactNo() : "N/A"
            ));
            sb.append("------------------------------------------------------------------------------------------------\n");

            // Displays the donation item information
            sb.append("Donated Items:\n");
            Iterator<DonatedItem> itemIterator = distribution.getDonation().getDonatedItems().getIterator();
            while (itemIterator.hasNext()) {
                DonatedItem item = itemIterator.next();
                sb.append(String.format(" - Item: %-20s | Quantity: %-10.2f | Unit: %-5s\n",
                        item.getItemName(),
                        item.getQuantity(),
                        item.getUnit()));
            }

            // Add line separator after donated items
            sb.append("\n\n\n\n\n\n\n\n\n------------------------------------------------------------------------------------------------\n");

            // Donee information
            sb.append(String.format(
                    "Donee: %-20s | Donee Phone: %-15s\n",
                    distribution.getDonee() != null ? distribution.getDonee().getDoneeName() : "N/A",
                    distribution.getDonee() != null ? distribution.getDonee().getDoneeContactNo() : "N/A"
            ));

            // Add a line separator after donee information
            sb.append("------------------------------------------------------------------------------------------------\n\n\n\n\n\n\n");
        }

        return sb.toString();
    }

    //------------- Generate Summary Reports ------------------------------
    private void generateSummaryReports() {
        System.out.println("Generating Summary Report...\n");

        int totalDistributions = 0;
        Set<String> donationIds = new HashSet<>();
        int totalItemsDistributed = 0;

        System.out.println(new String(new char[55]).replace('\0', '-') + " Summary Report " + new String(new char[59]).replace('\0', '-'));
        System.out.printf("%-20s | %-20s | %-20s | %-30s | %-20s\n",
                "Distribution ID", "Donor", "Donee", "Address", "Date");
        System.out.println(new String(new char[130]).replace('\0', '-'));

        Iterator<DonationDistribution> distributionIterator = allDistributions.getIterator();
        while (distributionIterator.hasNext()) {
            DonationDistribution distribution = distributionIterator.next();
            totalDistributions++;

            Donation donation = distribution.getDonation();
            donationIds.add(donation.getDonationId());

            Iterator<DonatedItem> itemIterator = donation.getDonatedItems().getIterator();
            while (itemIterator.hasNext()) {
                itemIterator.next();
                totalItemsDistributed++;
            }

            Donor donor = donation.getDonor();
            Donee donee = distribution.getDonee();
            String donorName = donor != null ? donor.getName() : "N/A";
            String doneeName = donee != null ? donee.getDoneeName() : "N/A";
            String address = distribution.getAddress() != null ? distribution.getAddress() : "N/A";
            String date = distribution.getDate() != null ? distribution.getDate().toString() : "N/A";

            System.out.printf("%-20s | %-20s | %-20s | %-30s | %-20s\n",
                    distribution.getDistributionId(), donorName, doneeName, address, date);
        }

        System.out.printf("\n\n\n\n\n\n\n\n\n\n");
        System.out.println(new String(new char[57]).replace('\0', '-') + new String(new char[73]).replace('\0', '-'));
        System.out.printf("Total Number of Distributions: %-20d\n", totalDistributions);
        System.out.printf("Total Number of Unique Donations: %-20d\n", donationIds.size());
        System.out.printf("Total Number of Items Distributed: %-20d\n", totalItemsDistributed);

        pressEnterContinue();
    }

    //------------- Helper Methods ------------------------------
    private DonationDistribution searchDistributionByID(String distributionId) {
        Iterator<DonationDistribution> iterator = allDistributions.getIterator();
        while (iterator.hasNext()) {
            DonationDistribution distribution = iterator.next();
            if (distribution.getDistributionId().equals(distributionId)) {
                return distribution;
            }
        }
        return null;
    }

    private void pressEnterContinue() {
         System.out.print("Press [Enter] key to continue...");
        sc.nextLine();
         clearScreen();
   
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

     public static void main(String[] args) {
        // Create sample lists for testing
        SortedListInterface<Donee> doneeList = new SortedArrayList<>();
        SortedListInterface<Donation> donationList = new SortedArrayList<>();
        SortedListInterface<DonationDistribution> distributionList = new SortedArrayList<>();

        // Initialize the controller with the sample lists
        DonationDistributionController controller = new DonationDistributionController(doneeList, donationList, distributionList);

        // Start the management process
        controller.DonationDistributionManagement();
    }
}
