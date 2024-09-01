package Control;
/**
 *
 * @author JIAQIAN
 */
import adt.SortedListInterface;
import boundary.DoneeManagementUI;
import entity.DonatedItem;
import entity.Donation;
import entity.DonationDistribution;
import entity.Donee;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import utility.MessageUI;

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

    private Donee findDoneeByID(String doneeID) {
        for (int i = 0; i < doneeList.getNumberOfEntries(); i++) {
            Donee donee = doneeList.getEntry(i);
            if (doneeID.equals(donee.getDoneeId())) {
                return donee;
            }
        }
        return null;
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

    // Get the Donee ID from user input
    String doneeID = doneeUI.inputDoneeID();
    Donee donee = findDoneeByID(doneeID);

    if (donee == null) {
        return "Donee ID not found!";
    }

    // Append donee details
    outputStr.append(String.format("Donee ID: %s\nName: %s\nContact Number: %s\n\n",
            donee.getDoneeId(),
            donee.getDoneeName(),
            donee.getDoneeContactNo()));
    outputStr.append("Items Donated:\n");

    boolean foundDistribution = false;

    // Debugging: Check entries in doneeDDistributionList
    System.out.println("Current Donation Distributions:");
    for (int a = 0; a < doneeDDistributionList.getNumberOfEntries(); a++) {
        DonationDistribution distribution = doneeDDistributionList.getEntry(a);
        System.out.println("Donee ID in distribution: " + distribution.getDonee().getDoneeId());
    }

    // Iterate over the doneeDDistributionList to find the distributions matching the Donee
    for (int b = 0; b < doneeDDistributionList.getNumberOfEntries(); b++) {
        DonationDistribution distribution = doneeDDistributionList.getEntry(b);

        if (distribution.getDonee().getDoneeId().equals(doneeID)) {
            foundDistribution = true;
            Donation donation = distribution.getDonation();

            // Iterate over the donated items in the found donation
            SortedListInterface<DonatedItem> donatedItems = donation.getDonatedItems();
            for (int j = 0; j < donatedItems.getNumberOfEntries(); j++) {
                DonatedItem item = donatedItems.getEntry(j);
                outputStr.append(String.format("%s, %.2f %s\n",
                        item.getItemName(),
                        item.getQuantity(),
                        item.getUnit()));
            }
        }
    }

    // If no distributions are found
    if (!foundDistribution) {
        outputStr.append("No items donated by this Donee.\n");
    }

    return outputStr.toString();
}



    public void displayReports() {
        doneeUI.listAllDonee(getAllDonees());
        int totalDonee = doneeList.getNumberOfEntries();
        Donee recentAddedDonee = getRecentAddedDonee();
        doneeUI.displayReport(totalDonee, recentAddedDonee);
    }

    public Donee getRecentAddedDonee() {
        if (doneeList.isEmpty()) {
            return null;
        }

        Donee recentDonee = null;
        LocalDateTime maxDateTime = LocalDateTime.MIN;

        for (int i = 0; i < doneeList.getNumberOfEntries(); i++) {
            Donee donee = doneeList.getEntry(i);
            LocalDateTime addedDateTime = donee.getDateAdded();

            if (addedDateTime.isAfter(maxDateTime)) {
                maxDateTime = addedDateTime;
                recentDonee = donee;
            }
        }
        return recentDonee;
    }

}
