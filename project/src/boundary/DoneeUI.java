/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

/**
 *
 * @author JIAQIAN
 */
import entity.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author jiaqian
 */
public class DoneeUI {

    Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        int choice = -1;

        do {
            System.out.println("\n");
            printLine(35);
            System.out.println("DONEE MANAGEMENT MENU");
            printLine(35);
            System.out.println("1. Add Donee");
            System.out.println("2. Remove Donee");
            System.out.println("3. Find Donee");
            System.out.println("4. Update Donee Details");
            System.out.println("5. List All Donee");
            System.out.println("6. List All Donee with the donations made");
            System.out.println("7. Generate Report");
            System.out.println("0. Return Back to Previous Page");
            printLine(35);
            System.out.print("Enter your choice -> ");

            try {
                choice = scanner.nextInt(); // Attempt to read the user's choice
                if (choice < 0 || choice > 7) {
                    System.out.println("\u001B[31mInvalid choice. Please enter a number between 0 and 7.\u001B[0m");
                }
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mInvalid input. Please enter a valid number.\u001B[0m");
                scanner.nextLine(); // Clear the invalid input
            }

        } while (choice < 0 || choice > 7); // Continue looping until a valid choice is entered

        scanner.nextLine(); // Consume the newline character
        System.out.println();

        return choice;
    }

    public void printLine(int length) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < length; i++) {
            line.append("=");
        }
        System.out.println(line.toString());
    }

    public void listAllDonee(String outputStr) {
        printLine(90); // Adjusted to match the length of the columns
        System.out.printf("%-10s %-40s %-15s %-15s\n", "Donee ID", "Donee Name", "Contact No", "Date Added");
        printLine(90);
        System.out.print(outputStr);
        printLine(90);
    }

    public void listAllDoneebyDonationDistribution(String outputStr) {
        printLine(150);
        System.out.print(outputStr);
        printLine(150);
    }

    private boolean isValidDoneeId(String doneeId) {
        // Check if doneeId matches the format "E" followed by three digits
        return doneeId.matches("^E\\d{3}$");
    }

    private boolean isValidDoneeContactNo(String contactNo) {
        return contactNo.matches("^\\d{10,11}$");
    }

public Donee inputDoneeDetails() {
    String doneeName;
    while (true) {
        System.out.print("Enter Donee name (or enter '0' to cancel): ");
        doneeName = scanner.nextLine().trim().toUpperCase();
        if (doneeName.equals("0")) {
            return null; // Return null to indicate cancellation
        } else if (!doneeName.isEmpty()) {
            break;
        } else {
            System.out.println("\u001B[31mName cannot be empty!\u001B[0m");
        }
    }

    String doneeContactNo;
    while (true) {
        System.out.print("Enter Contact No (e.g., 0123456789), or enter '0' to cancel): ");
        doneeContactNo = scanner.nextLine();
        if (doneeContactNo.equals("0")) {
            return null; // Return null to indicate cancellation
        } else if (isValidDoneeContactNo(doneeContactNo)) {
            break;
        } else {
            System.out.println("\u001B[31mInvalid format. Please enter a valid Contact No.\u001B[0m");
        }
    }

    return new Donee(doneeName, doneeContactNo);
}


public String inputDoneeID() {
    String doneeId;
    while (true) {
        System.out.print("Enter Donee ID (e.g. E001, E234) or '0' to cancel: ");
        doneeId = scanner.nextLine(); // Read the entire input

        // Check if user wants to cancel
        if (doneeId.equals("0")) {
            System.out.println("\u001B[32mInput canceled. Returning to main menu...\u001B[0m");
            return null; // Return null to indicate cancellation
        }

        // Convert input to uppercase to standardize Donee ID format
        doneeId = doneeId.toUpperCase();

        // Validate the Donee ID format
        if (isValidDoneeId(doneeId)) {
            break; // Exit the loop if the ID is valid
        } else {
            System.out.println("\u001B[31mInvalid format. Please enter a valid Donee ID.\u001B[0m");
        }
    }
    return doneeId; // Return the valid Donee ID
}



public String inputDoneeName() {
    String doneeName;
    while (true) {
        System.out.print("Enter Donee name (or enter '0' to cancel): ");
        doneeName = scanner.nextLine().trim().toUpperCase(); // Read the entire input

        // Check if user wants to cancel
        if (doneeName.equals("0")) {
            System.out.println("\u001B[32mInput canceled. Returning to main menu...\u001B[0m");
            return null; // Return null to indicate cancellation
        }

        // Validate that the name is not empty
        if (!doneeName.isEmpty()) {
            break; // Exit the loop if the name is valid
        } else {
            System.out.println("\u001B[31mName cannot be empty!\u001B[0m");
        }
    }
    return doneeName; // Return the valid Donee name
}


public String inputDoneeContactNo() {
    String doneeContactNo;
    while (true) {
        System.out.print("Enter Contact No (e.g. 0123456789) or enter '0' to cancel: ");
        doneeContactNo = scanner.nextLine(); // Read the entire input

        // Check if user wants to cancel
        if (doneeContactNo.equals("0")) {
            System.out.println("\u001B[32mInput canceled. Returning to main menu...\u001B[0m");
            return null; // Return null to indicate cancellation
        }

        // Validate the contact number
        if (isValidDoneeContactNo(doneeContactNo)) {
            break; // Exit the loop if the contact number is valid
        } else {
            System.out.println("\u001B[31mInvalid format. Please enter a valid Contact No.\u001B[0m");
        }
    }
    return doneeContactNo; // Return the valid Donee contact number
}


public boolean confirmRemoval(String doneeID) {
    String response;
    while (true) {
        System.out.printf("Are you sure you want to remove Donee with ID %s? (Yes = 1, No = 0): ", doneeID);
        response = scanner.nextLine().trim();

        switch (response) {
            case "1":
                return true; // User confirmed removal
            case "0":
                return false; // User cancelled removal
            default:
                System.err.println("Invalid input. Please enter '1' for Yes or '0' for No."); // Error message for invalid input
                break;
        }
    }
}
public boolean confirmAdd(String doneeID) {
    String response;
    while (true) {
        System.out.printf("Are you sure you want to add Donee with ID %s? (Yes = 1, No = 0): ", doneeID);
        response = scanner.nextLine().trim();

        switch (response) {
            case "1":
                return true; // User confirmed addition
            case "0":
                return false; // User cancelled addition
            default:
                System.err.println("Invalid input. Please enter '1' for Yes or '0' for No."); // Error message for invalid input
                break;
        }
    }
}




    public int inputSearchOption() {
        printLine(70);
        System.out.println("Please select a search option:");
        printLine(70);
        System.out.println("1. Search by Donee ID");
        System.out.println("2. Search by Donee Name");
        System.out.println("3. Search by Donee Contact Number");
        System.out.println("0. Return Back to Previous Page");
        printLine(70);
        System.out.print("Enter your choice (1/2/3) -> ");

        int choice = scanner.nextInt(); // Read the user's input

        return choice; // Return the valid choice
    }

    public void displayDoneeDetails(Donee donee) {
        System.out.println("\n");
        printLine(70);
        System.out.println("Donee Details");
        printLine(70);
        String formattedId = donee.getDoneeId();
        System.out.println("Donee ID: " + formattedId);
        System.out.println("Donee Name: " + donee.getDoneeName());
        System.out.println("Donee Contact No: " + donee.getDoneeContactNo());
        printLine(70);
    }

    public void clearScanner() {
        scanner.nextLine(); // Clear the buffer by reading the leftover input
    }

    public int displaySortingOptions() {
        printLine(70);
        System.out.println("Sort Donees by:");
        printLine(70);
        System.out.println("1. ID (Ascending)");
        System.out.println("2. ID (Descending)");
        System.out.println("3. Name (Ascending)");
        System.out.println("4. Name (Descending)");
        System.out.println("5. Contact Number (Ascending)");
        System.out.println("6. Contact Number (Descending)");
        System.out.println("0. Back to Previous Page");
        printLine(70);
        System.out.print("Enter your choice  -> ");

        int choice = scanner.nextInt(); // Read the user's input

        return choice; // Return the valid choice
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayReport(int totalDonee, Donee recentAddedDonee) {
        printLine(70);
        System.out.println("\t\tDonee Management Report");
        printLine(70);
        System.out.println("Total Number of Donees             >> " + totalDonee);
        System.out.println("The recent added Donee             >> " + recentAddedDonee.getDoneeId() + "\t" + recentAddedDonee.getDoneeName() + "\t" + recentAddedDonee.getDoneeContactNo());
        printLine(70);

        // Wait for user input before proceeding
        System.out.print("Enter any value to proceed -> ");
        scanner.next();
    }

}
