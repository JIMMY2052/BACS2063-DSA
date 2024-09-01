/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import adt.SortedListInterface;
import boundary.DoneeManagementUI;
import entity.DonatedItem;
import entity.Donation;
import entity.DonationDistribution;
import entity.Donee;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import utility.MessageUI;


/**
 *
 * @author Jia Qian
 */
public class DoneeController {

    private SortedListInterface<Donee> doneeList;
    private SortedListInterface<DonationDistribution> doneeDDistributionList;
    private final DoneeManagementUI doneeUI = new DoneeManagementUI();
    
    public DoneeController(SortedListInterface<Donee> doneeList, SortedListInterface<DonationDistribution> doneeDDistributionList) {
        this.doneeList = doneeList;
        this.doneeDDistributionList = doneeDDistributionList;
    }


    public void runDoneeManagement() {
        int choice;
        do {
            choice = doneeUI.getMenuChoice();
            switch (choice) {
                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1:
                    addNewDonee();
                    doneeUI.listAllDonee(getAllDonees());
                    doneeUI.displayMessage("New donee added.");
                    break;
                case 2:
                    doneeUI.listAllDonee(getAllDonees());
                    removeDonee();
                    doneeUI.listAllDonee(getAllDonees());
                    break;
                case 3:
                    findDonee();
                    break;
                case 4:
                    updateDetails();
                    doneeUI.listAllDonee(getAllDonees());
                    break;
                case 5:
                    doneeUI.listAllDonee(getAllDonees());
                    break;
                case 6:
                    doneeUI.listAllDoneebyDonationDistribution(getAllDoneesDDistribution());
                    break;
                case 7:
                    displayReports();
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    public void addNewDonee() {
        Donee newDonee = doneeUI.inputDoneeDetails();
        doneeList.add(newDonee);
    }

    public void removeDonee() {
        String doneeID = doneeUI.inputDoneeID();
        Donee donee = findDoneeByID(doneeID);
        if (donee != null) {
            int index = doneeList.search(donee);
            doneeList.remove(index);
            MessageUI.removeDoneeSuccessMessage();
        } else {
            MessageUI.DoneeNotFoundMessage();
        }
    }

    public void findDonee() {
        String doneeID = doneeUI.inputDoneeID();
        Donee donee = findDoneeByID(doneeID);
        if (donee != null) {
            doneeUI.displayDoneeDetails(donee);
        } else {
            MessageUI.DoneeNotFoundMessage();
        }
    }

    public void updateDetails() {
        String doneeID = doneeUI.inputDoneeID();
        Donee donee = findDoneeByID(doneeID);

        if (donee != null) {
            doneeUI.displayMessage("Please enter new details >> ");
            Donee newDoneeDetails = doneeUI.inputDoneeDetails();

            // Find the position of the existing Donee in the list
            int position = doneeList.search(donee);
            if (position != -1) {
                doneeList.replace(position, newDoneeDetails);
                MessageUI.updateDoneeSuccessMessage();
            } else {
                MessageUI.DoneeNotFoundMessage();
            }
        } else {
            MessageUI.DoneeNotFoundMessage();
        }
    }

// Find Donee By ID
private Donee findDoneeByID(String doneeID) {
    for (int i = 0; i < doneeList.getNumberOfEntries(); i++) {
        Donee donee = doneeList.getEntry(i);
        if (doneeID.equals(donee.getDoneeId())) { // Compare as Strings
            return donee;
        }
    }
    return null; // Return null if no matching Donee is found
}

private String getAllDonees() {
    StringBuilder outputStr = new StringBuilder();
    for (int i = 0; i < doneeList.getNumberOfEntries(); i++) {
        Donee donee = doneeList.getEntry(i);
        String formattedDateAdded = donee.getDateAdded().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        
        outputStr.append(String.format("%-10s %-40s %-15s %-20s\n",
                donee.getDoneeId(), 
                donee.getDoneeName(),
                donee.getDoneeContactNo(),
                formattedDateAdded));
    }
    return outputStr.toString();
}

private String getAllDoneesDDistribution() {
    StringBuilder outputStr = new StringBuilder();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    // Get the Donee ID from user input
    String doneeID = doneeUI.inputDoneeID();
    Donee donee = findDoneeByID(doneeID);


    // Append donee details
    outputStr.append(String.format("Donee ID: %-10s\nName: %s\nContact Number: %s\n\n",
            donee.getDoneeId(),
            donee.getDoneeName(),
            donee.getDoneeContactNo()));
    outputStr.append("History: \n\n");
    // Retrieve the Donation Distribution for the specific donee
    DonationDistribution donationDistribution = getDonationDistributionByDoneeID(doneeID); // Add this method to retrieve the distribution

    if (donationDistribution != null) {
        // Append Donation History
        for (int i = 0; i < donationDistribution.getDonations().getNumberOfEntries(); i++) {
            Donation donation = donationDistribution.getDonations().getEntry(i);
            outputStr.append("Donation ID: ").append(donation.getDonationId()).append("\n")
                      .append("Date: ").append(formatter.format(donation.getDate())).append("\n");
                      
            
            SortedListInterface<DonatedItem> donatedItems = donation.getDonatedItems();
            for (int j = 0; j < donatedItems.getNumberOfEntries(); j++) {
                DonatedItem item = donatedItems.getEntry(j);
                outputStr.append("\tItem: ").append(item.getItemName())
                          .append(", Quantity: ").append(item.getQuantity())
                          .append("\n");
            }
            outputStr.append("\n");
        }
    } else {
        outputStr.append("No donations found for this donee.\n");
    }

    return outputStr.toString();
}

private DonationDistribution getDonationDistributionByDoneeID(String doneeID) {
    for (int i = 0; i < doneeDDistributionList.getNumberOfEntries(); i++) {
        DonationDistribution distribution = doneeDDistributionList.getEntry(i);
        
        // Check if the doneeID exists in the distribution's donees
        for (int j = 0; j < distribution.getDonees().getNumberOfEntries(); j++) {
            Donee donee = distribution.getDonees().getEntry(j);
            if (donee.getDoneeId().equals(doneeID)) { // Use .equals() for String comparison
                return distribution; // Return the matching distribution
            }
        }
    }
    
    return null; // Return null if no distribution is found for the doneeID
}



    public void displayReports() {
        doneeUI.listAllDonee(getAllDonees());
        int totalDonee = doneeList.getNumberOfEntries();
        Donee recentAddedDonee = getRecentAddedDonee();
        doneeUI.displayReport(totalDonee, recentAddedDonee);
    }
    
    public Donee getRecentAddedDonee() {
        if (doneeList.isEmpty()) {
            return null; // No courses in the list
        }

        Donee recentDonee = null;
        LocalDateTime maxDateTime = LocalDateTime.MIN; // Initialize maxDateTime to a minimum value

        for (int i = 0; i < doneeList.getNumberOfEntries(); i++) {
            Donee donee = doneeList.getEntry(i);
            LocalDateTime addedDateTime = donee.getDateAdded(); // Assuming you have a method to parse the date string

            if (addedDateTime.isAfter(maxDateTime)) {
                maxDateTime = addedDateTime; // Update maxDateTime if a more recent date is found
                recentDonee = donee; // Clear the list by creating a new one
            }
        }
        return recentDonee;
    }

}
