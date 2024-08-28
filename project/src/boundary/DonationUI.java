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

    public int displayDonationMenu() {
        int opt;
        System.out.println("\t\tDONATION MANAGEMENT");
        System.out.println("======================================================");
        System.out.println("\t\t1. Make A Donation");
        System.out.println("\t\t2. View Donations");
        System.out.println("\t\t3. Search Donation");
        System.out.println("\t\t4. Delete Donation");
        System.out.println("\t\t5. Update Donation");
        System.out.println("\t\t6. Generate Summary Report");
        System.out.println("\t\t0. Return Back to Previous Page");
        System.out.println("======================================================");

        System.out.print("Enter your choice -> ");
        opt = sc.nextInt();
        while (opt < 0 || opt > 6) {
            System.out.print("Invalid option! Please select a number between 0 and 6 : ");
            opt = sc.nextInt();
        }
        return opt;
    }

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

    //---------MAKE DONATION---------------------
    public String inputDonorId() {
        System.out.print("Enter Donor ID: ");
        String donorId = sc.nextLine().trim();
        return donorId;
    }

    public String inputFoodName() {
        System.out.println("Enter Food Name: ");
        String foodName = sc.nextLine().trim();
        return foodName;
    }

    public double inputQuantity() {
        System.out.println("Enter Quantity: ");
        double qty = sc.nextDouble();
        return qty;
    }

    public String inputUnit() {
        System.out.print("Enter the Unit(cm,m,kg,g): ");
        String unit = sc.nextLine().trim();
        return unit;
    }

    public double inputCash() {
        System.out.println("Enter amount cash: ");
        double amount = sc.nextDouble();
        return amount;
    }
    //---------MAKE DONATION---------------------

    public static void main(String[] args) {
        DonationUI ui = new DonationUI();
        ui.addDonationMenu();
    }

}
