/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import java.util.Scanner;

/**
 *
 * @author JIMMY
 */
public class DonationUI {

    Scanner sc = new Scanner(System.in);

    //---------Main Donation Menu---------------------
    public int displayDonationMenu() {
        int opt;
        System.out.println("\t\tDONATION MANAGEMENT");
        System.out.println("======================================================");
        System.out.println("\t\t1. Make A Donation");
        System.out.println("\t\t2. List All Donations");
        System.out.println("\t\t3. Edit Donation");
        System.out.println("\t\t4. Delete Donation");
        System.out.println("\t\t5. Search Donation");
        System.out.println("\t\t6. Generate Summary Report");
        System.out.println("\t\t0. Return Back to Previous Page");
        System.out.println("======================================================");

        System.out.print("Enter your choice -> ");
        opt = sc.nextInt();
        sc.nextLine();
        while (opt < 0 || opt > 6) {
            System.out.print("Invalid option! Please select a number between 0 and 6 : ");
            opt = sc.nextInt();
        }
        return opt;
    }
    //---------Main Donation Menu---------------------

    public int addDonationMenu() {
        int opt;
        System.out.println("\t\tMAKE DONATION");
        System.out.println("======================================================");
        System.out.println("\t\t1. Donate Food");
        System.out.println("\t\t2. Donate Cash");
        System.out.println("\t\t0. Return Back to Previous Page");
        System.out.println("======================================================");

        System.out.print("Enter your choice -> ");
        opt = sc.nextInt();
        sc.nextLine();
        while (opt < 0 || opt > 2) {
            System.out.print("Invalid option! Please select a number between 0 and 2 : ");
            opt = sc.nextInt();
        }
        return opt;
    }
    
    public void displayDonateFoodHeader(){
        System.out.println("\t\tDONATE FOOD");
        System.out.println("======================================================");
    }
    
    public void displayDonateCashHeader(){
        System.out.println("\t\tDONATE CASH");
        System.out.println("======================================================");
    }
    
    public void listDonationHeader(){
        System.out.println("\t\t\t\t\tALL DONATION LIST");
        printLine(1, 99);
        System.out.printf("|%-18s| %-18s| %-18s| %-18s| %-16s |\n", "Donation ID", "Donation Date", "Donor Name", "Donated Item", "Quantity" );
        printLine(1, 99);
    }
    
    public void displayEditDonationHeader(){
        System.out.println("\t\tEDIT DONATION");
        System.out.println("======================================================");
    }

    //---------MAKE DONATION---------------------
    public String inputDonorId() {
        System.out.print("Enter Donor ID: ");
        String donorId = sc.nextLine().trim();
        return donorId;
    }

    public String inputFoodName() {
        System.out.print("Enter Food Name: ");
        String foodName = sc.nextLine().trim();
        return foodName;
    }

    public double inputQuantity() {
        System.out.print("Enter Quantity: ");
        double qty = sc.nextDouble();
        sc.nextLine();
        return qty;
    }

    public String inputUnit() {
        System.out.print("Enter the Unit(cm,m,kg,g): ");
        String unit = sc.nextLine().trim();
        return unit;
    }

    public double inputCash() {
        System.out.print("Enter amount cash: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        return amount;
    } 
    //---------MAKE DONATION---------------------

    public String inputDonationId(){
        System.out.println("Enter desired DONATION ID(eg: K001) -> ");
        String donationId = sc.nextLine();
        return donationId;
    }
    
    public int askToContinue(){
        System.out.print("Do you want to continue ? (Yes = 1 / N = 0): ");
        int choice = sc.nextInt();
        sc.nextLine();
        while (choice < 0 || choice > 1) {
            System.out.print("Invalid option! Please select a number (0 OR 1) : ");
            choice = sc.nextInt();
            sc.nextLine();
        }
        return choice;
    }
    public void printLine(int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("-");
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        DonationUI ui = new DonationUI();
        ui.addDonationMenu();
    }

}
