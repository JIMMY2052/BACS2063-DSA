package Control;

/**
 *
 * @author JIAQIAN
 */
import adt.SortedListInterface;
import boundary.DoneeUI;
import entity.DonatedItem;
import entity.Donation;
import entity.DonationDistribution;
import entity.Donee;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import utility.MessageUI;

public class DoneeController {

    private SortedListInterface<Donee> doneeList;
    private SortedListInterface<DonationDistribution> doneeDDistributionList;
    private final DoneeUI doneeUI = new DoneeUI();

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
                    sortByIdAscending();
                    doneeUI.listAllDonee(getAllDonees());
                    break;
                case 2:
                    sortByIdAscending();
                    doneeUI.listAllDonee(getAllDonees());
                    removeDonee();
                    doneeUI.listAllDonee(getAllDonees());
                    break;
                case 3:
                    findDonee();
                    break;
                case 4:
                    updateDetails();
                    sortByIdAscending();
                    doneeUI.listAllDonee(getAllDonees());
                    break;
                case 5:
                    sortingAllDonee();
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
        if (newDonee == null) {
            doneeUI.displayMessage("\u001B[32mAdding new Donee was canceled. Returning to main menu...\u001B[0m");
            return; // Exit the method or return to main menu logic
        }
        doneeUI.displayDoneeDetails(newDonee);
        // Confirm adding the new Donee
        boolean confirmed = doneeUI.confirmAdd(newDonee.getDoneeId()); // Assume getDoneeID() returns the ID

        if (confirmed) {
            doneeList.add(newDonee);
            doneeUI.displayMessage("\u001B[32mNew Donee added.\u001B[0m");
        } else {
            doneeUI.displayMessage("\u001B[32mAdding new Donee was canceled.Returning to main menu...\u001B[0m");
        }
    }

    public void removeDonee() {
        String doneeID = doneeUI.inputDoneeID();
        // Check if the input was canceled
        if (doneeID == null) {
            return; // Exit the method or return to main menu logic
        }
        Donee donee = findDoneeByID(doneeID);

        if (donee != null) {
            // Display the details of the Donee
            doneeUI.displayDoneeDetails(donee);

            // Ask for confirmation to remove the Donee
            boolean confirmed = doneeUI.confirmRemoval(doneeID); // Method to confirm removal

            if (confirmed) {
                int index = doneeList.search(donee);
                if (index != -1) {
                    doneeList.remove(index);
                    MessageUI.removeDoneeSuccessMessage();
                } else {
                    MessageUI.DoneeNotFoundMessage(); // In case of an unexpected situation
                }
            } else {
                MessageUI.displayCancellationMessage(); // Message for cancellation
            }
        } else {
            MessageUI.DoneeNotFoundMessage();
        }
    }

    public void findDonee() {
        Donee donee = null;
        boolean doneeFound = false;

        while (!doneeFound) {
            int searchOption = -1;

            // Try-catch block to handle non-integer inputs
            try {
                searchOption = doneeUI.inputSearchOption(); // Prompt user to choose search by ID, name, or contact number
            } catch (InputMismatchException e) {
                MessageUI.WrongInputMessage(); // Inform the user of the invalid choice
                doneeUI.clearScanner(); // Clear the scanner buffer
                continue; // Go back to the start of the loop
            }

            switch (searchOption) {
                case 1: // Search by ID
                    doneeUI.clearScanner(); // Clear the scanner buffer
                    String doneeID = doneeUI.inputDoneeID();
                    // Check if the input was canceled
                    if (doneeID == null) {
                        return; // Exit the method or return to main menu logic
                    }
                    donee = findDoneeByID(doneeID);
                    break;
                case 2: // Search by Name
                    doneeUI.clearScanner(); // Clear the scanner buffer
                    String doneeName = doneeUI.inputDoneeName();
                    // Check if the input was canceled
                    if (doneeName == null) {
                        return; // Exit the method or return to main menu logic
                    }
                    donee = findDoneeByName(doneeName);
                    break;
                case 3: // Search by Contact Number
                    doneeUI.clearScanner(); // Clear the scanner buffer
                    String doneeContactNo = doneeUI.inputDoneeContactNo();
                    if (doneeContactNo == null) {
                        return; // Exit the method or return to main menu logic
                    }
                    donee = findDoneeByContactNo(doneeContactNo);
                    break;
                case 0:
                    MessageUI.displayExitPreviousMessage();
                    doneeFound = true;
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage(); // Inform the user of the invalid choice
                    continue; // Go back to the start of the loop
            }

            if (donee != null) {
                doneeUI.displayDoneeDetails(donee);
                doneeFound = true; // Exit the loop if the Donee is found
            } else if (searchOption != 0) {
                MessageUI.DoneeNotFoundMessage(); // Inform the user that the Donee was not found
            }
        }
    }

    public void updateDetails() {
        String doneeID = doneeUI.inputDoneeID();
        // Check if the input was canceled
        if (doneeID == null) {
            return; // Exit the method or return to main menu logic
        }
        Donee donee = findDoneeByID(doneeID);

        if (donee != null) {
            doneeUI.displayMessage("Please enter new details >> ");
            Donee newDoneeDetails = doneeUI.inputDoneeDetails();
            if (newDoneeDetails == null) {
                doneeUI.displayMessage("\u001B[32mAdding new Donee was canceled. Returning to main menu...\u001B[0m");
                return; // Exit the method or return to main menu logic
            }
            // Call the editDoneeDetails method to update the donee
            if (editDoneeDetails(doneeID, newDoneeDetails)) {
                MessageUI.updateDoneeSuccessMessage();
            } else {
                MessageUI.DoneeNotFoundMessage();
            }
        } else {
            MessageUI.DoneeNotFoundMessage();
        }
    }

// New method to edit Donee details and update the list
    private boolean editDoneeDetails(String doneeID, Donee newDetails) {
        int position = searchById(doneeID); // Search for the donee by ID
        if (position != -1) {
            // Update the existing donee object with new details
            Donee existingDonee = doneeList.getEntry(position);
            existingDonee.setDoneeName(newDetails.getDoneeName());
            existingDonee.setDoneeContactNo(newDetails.getDoneeContactNo());
            // Update other fields as necessary

            // Replace the entry in the list with the updated donee
            doneeList.replace(position, existingDonee);
            return true; // Indicate success
        }
        return false; // Indicate failure
    }

    public int searchById(String doneeID) {
        for (int i = 0; i < doneeList.getNumberOfEntries(); i++) {
            Donee donee = doneeList.getEntry(i);
            if (donee.getDoneeId().equals(doneeID)) {
                return i; // Return the index if found
            }
        }
        return -1; // Not found
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

    public Donee findDoneeByName(String doneeName) {
        for (int i = 0; i < doneeList.getNumberOfEntries(); i++) {
            Donee donee = doneeList.getEntry(i);
            if (donee.getDoneeName().equalsIgnoreCase(doneeName)) {
                return donee;
            }
        }
        return null;
    }

    public Donee findDoneeByContactNo(String doneeContactNo) {
        for (int i = 0; i < doneeList.getNumberOfEntries(); i++) {
            Donee donee = doneeList.getEntry(i);
            if (donee.getDoneeContactNo().equals(doneeContactNo)) {
                return donee;
            }
        }
        return null;
    }

    public void sortingAllDonee() {
        boolean sortingDone = false;

        while (!sortingDone) {
            int sortOption = -1;

            // Try-catch block to handle non-integer inputs
            try {
                sortOption = doneeUI.displaySortingOptions(); // Prompt user to choose sorting option
            } catch (InputMismatchException e) {
                MessageUI.WrongInputMessage(); // Inform the user of the invalid choice
                doneeUI.clearScanner(); // Clear the scanner buffer
                continue; // Go back to the start of the loop
            }

            // Perform sorting based on the selected option
            switch (sortOption) {
                case 1: // Sort by ID Ascending
                    sortByIdAscending();
                    break;
                case 2: // Sort by ID Descending
                    sortByIdDescending();
                    break;
                case 3: // Sort by Name Ascending
                    sortByNameAscending();
                    break;
                case 4: // Sort by Name Descending
                    sortByNameDescending();
                    break;
                case 5: // Sort by Contact Number Ascending
                    sortByContactNoAscending();
                    break;
                case 6: // Sort by Contact Number Descending
                    sortByContactNoDescending();
                    break;
                case 0: // Exit option
                    MessageUI.displayExitPreviousMessage();
                    sortingDone = true; // Exit the loop if the user wants to go back
                    continue; // Skip to the next iteration
                default:
                    MessageUI.displayInvalidChoiceMessage(); // Inform the user of the invalid choice
                    continue; // Go back to the start of the loop
            }

            // Retrieve and display the sorted Donees
            String result = getAllDonees();
            if (result != null && !result.isEmpty()) {
                doneeUI.listAllDonee(result); // Display the sorted list
            } else {
                MessageUI.EmptyMessage(); // Inform the user that no Donees are available
            }
        }
    }

// Sorting methods
    private void sortByIdAscending() {
        for (int i = 0; i < doneeList.getNumberOfEntries() - 1; i++) {
            for (int j = 0; j < doneeList.getNumberOfEntries() - 1 - i; j++) {
                Donee d1 = doneeList.getEntry(j);
                Donee d2 = doneeList.getEntry(j + 1);
                if (d1.getDoneeId().compareTo(d2.getDoneeId()) > 0) {
                    swapEntries(j, j + 1);
                }
            }
        }
    }

    private void sortByIdDescending() {
        for (int i = 0; i < doneeList.getNumberOfEntries() - 1; i++) {
            for (int j = 0; j < doneeList.getNumberOfEntries() - 1 - i; j++) {
                Donee d1 = doneeList.getEntry(j);
                Donee d2 = doneeList.getEntry(j + 1);
                if (d1.getDoneeId().compareTo(d2.getDoneeId()) < 0) {
                    swapEntries(j, j + 1);
                }
            }
        }
    }

    private void sortByNameAscending() {
        for (int i = 0; i < doneeList.getNumberOfEntries() - 1; i++) {
            for (int j = 0; j < doneeList.getNumberOfEntries() - 1 - i; j++) {
                Donee d1 = doneeList.getEntry(j);
                Donee d2 = doneeList.getEntry(j + 1);
                if (d1.getDoneeName().compareTo(d2.getDoneeName()) > 0) {
                    swapEntries(j, j + 1);
                }
            }
        }
    }

    private void sortByNameDescending() {
        for (int i = 0; i < doneeList.getNumberOfEntries() - 1; i++) {
            for (int j = 0; j < doneeList.getNumberOfEntries() - 1 - i; j++) {
                Donee d1 = doneeList.getEntry(j);
                Donee d2 = doneeList.getEntry(j + 1);
                if (d1.getDoneeName().compareTo(d2.getDoneeName()) < 0) {
                    swapEntries(j, j + 1);
                }
            }
        }
    }

    private void sortByContactNoAscending() {
        for (int i = 0; i < doneeList.getNumberOfEntries() - 1; i++) {
            for (int j = 0; j < doneeList.getNumberOfEntries() - 1 - i; j++) {
                Donee d1 = doneeList.getEntry(j);
                Donee d2 = doneeList.getEntry(j + 1);
                if (d1.getDoneeContactNo().compareTo(d2.getDoneeContactNo()) > 0) {
                    swapEntries(j, j + 1);
                }
            }
        }
    }

    private void sortByContactNoDescending() {
        for (int i = 0; i < doneeList.getNumberOfEntries() - 1; i++) {
            for (int j = 0; j < doneeList.getNumberOfEntries() - 1 - i; j++) {
                Donee d1 = doneeList.getEntry(j);
                Donee d2 = doneeList.getEntry(j + 1);
                if (d1.getDoneeContactNo().compareTo(d2.getDoneeContactNo()) < 0) {
                    swapEntries(j, j + 1);
                }
            }
        }
    }

// Helper method to swap Donee entries
    private void swapEntries(int index1, int index2) {
        Donee temp = doneeList.getEntry(index1);
        doneeList.replace(index1, doneeList.getEntry(index2));
        doneeList.replace(index2, temp);
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
        // Check if the input was canceled
        if (doneeID == null) {
            return ""; // Exit the method or return to main menu logic
        }
        Donee donee = findDoneeByID(doneeID);

        if (donee == null) {
            return "Donee ID not found!\n";
        }

        // Append donee details
        outputStr.append(String.format("Donee ID: %s\nName: %s\nContact Number: %s\n\n",
                donee.getDoneeId(),
                donee.getDoneeName(),
                donee.getDoneeContactNo()));
        outputStr.append("Items Donated:\n");

        boolean foundDistribution = false;

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
        sortByIdAscending();
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
