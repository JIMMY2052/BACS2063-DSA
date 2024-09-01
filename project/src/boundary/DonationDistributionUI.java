package boundary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DonationDistributionUI {

    private Scanner sc = new Scanner(System.in);

    //---------Main Donation Distribution Menu---------------------
    public int displayDistributionMenu() {
        int opt;
        System.out.println("\t\tDONATION DISTRIBUTION SUBSYSTEM");
        System.out.println("==============================================================");
        System.out.println("\t\t1. Add New Donation Distribution");
        System.out.println("\t\t2. Update Donation Distribution Details");
        System.out.println("\t\t3. Remove Donation Distribution");
        System.out.println("\t\t4. Monitor/Track Distributed Items");
        System.out.println("\t\t5. Generate Summary Reports");
        System.out.println("\t\t0. Exit");
        System.out.println("==============================================================");

        System.out.print("Enter your choice -> ");
        opt = sc.nextInt();
        sc.nextLine();
        while (opt < 0 || opt > 5) {
            System.out.print("Invalid option! Please select a number between 0 and 5: ");
            opt = sc.nextInt();
            sc.nextLine();
        }
        return opt;
    }

    public String inputDistributionAddress() {
        System.out.print("Enter Distribution Address: ");
        return sc.nextLine().trim();
    }

    public String inputDoneeId() {
        System.out.print("Enter Donee ID: ");
        return sc.nextLine().trim();
    }

    public String inputDonationId() {
        System.out.print("Enter Donation ID: ");
        return sc.nextLine().trim();
    }

    public String inputDistributionId() {
        System.out.print("Enter Distribution ID(DSXX1): ");
        return sc.nextLine().trim();
    }

    public String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    public String generateDistributionId() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return "DIST-" + currentDateTime.format(formatter);
    }

  
    public boolean confirmSaveDistribution() {
        System.out.print("Do you want to save this distribution? (Y/N): ");
        String choice = sc.nextLine().trim().toUpperCase();
        while (!choice.equals("Y") && !choice.equals("N")) {
            System.out.print("Invalid choice! Please enter 'Y' for Yes or 'N' for No: ");
            choice = sc.nextLine().trim().toUpperCase();
        }
        return choice.equals("Y");
    }
}
