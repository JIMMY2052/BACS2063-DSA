/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;


import entity.*;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author jiaqian
 */
public class DoneeManagementUI {
        Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n");
        printLine(35);
        System.out.println("Donee MANAGEMENT MENU");
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
        int choice = -1;
        try {
            choice = scanner.nextInt();
            if (choice < 0 || choice > 7) {
                System.out.println("Invalid choice. Please enter a number between 0 and 6.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
        }

        scanner.nextLine();
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
        System.out.printf("%-10s %-40s %-15s %-15s\n", "Donee ID", "Donee Name", "Contact No","Date Added");
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
            System.out.print("Enter Donee name: ");
            doneeName = scanner.nextLine().trim().toUpperCase();
            if (!doneeName.isEmpty()) {
                break;
            } else {
                System.out.println("Name cannot be empty!");
            }
        }

        String doneeContactNo;
        while (true) {
            System.out.print("Enter Contact No (e.g., 0123456789): ");
            doneeContactNo = scanner.nextLine();
            if (isValidDoneeContactNo(doneeContactNo)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid Contact No.");
            }
        }

        return new Donee(doneeName,doneeContactNo);
    }

    
public String inputDoneeID() {
    String doneeId;
    while (true) {
        System.out.print("Enter Donee ID (e.g. E001, E234): ");
        doneeId = scanner.nextLine(); // Changed to nextLine() to read the entire input
        if (isValidDoneeId(doneeId)) {
            break;
        } else {
            System.out.println("Invalid format. Please enter a valid Donee ID.");
        }
    }
    return doneeId;
}

    
    public String inputDoneeName() {
        String doneeName;
        while (true) {
            System.out.print("Enter Donee name: ");
            doneeName = scanner.nextLine().trim().toUpperCase();
            if (!doneeName.isEmpty()) {
                break;
            } else {
                System.out.println("Name cannot be empty!");
            }
        }
        return doneeName;
    }

    public String inputDoneeContactNo() {
      String doneeContactNo;
        while (true) {
            System.out.print("Enter Contact No (e.g. 0123456789): ");
            doneeContactNo = scanner.nextLine();
            if (isValidDoneeContactNo(doneeContactNo)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid Contact No.");
            }
        }
        return doneeContactNo;
    }

public void displayDoneeDetails(Donee donee) {
    System.out.println("Donee Details");
    String formattedId = donee.getDoneeId();
    System.out.println("Donee ID: " + formattedId);
    System.out.println("Donee Name: " + donee.getDoneeName());
    System.out.println("Donee Contact No: " + donee.getDoneeContactNo());
}


    public void displayMessage(String message) {
        System.out.println(message);
    }
    public void displayReport(int totalDonee, Donee recentAddedDonee) {
        printLine(70);
        System.out.println("\t\tDonee Management Report");
        printLine(70);
        System.out.println("Total Number of Donees             >> " + totalDonee);
        System.out.println("The recent added Donee             >> " + recentAddedDonee.getDoneeId() + "\t" + recentAddedDonee.getDoneeName()+ "\t" + recentAddedDonee.getDoneeContactNo());
        printLine(70);

        // Wait for user input before proceeding
        System.out.print("Enter any value to proceed -> ");
        scanner.next();
    }

}
