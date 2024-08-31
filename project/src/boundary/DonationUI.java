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
        System.out.println("\t\t2. View Donation");
        System.out.println("\t\t3. Update Donation");
        System.out.println("\t\t4. Delete Donation");
        System.out.println("\t\t5. Delete Donated Item");
        System.out.println("\t\t6. Add Donated Item to Donation");
        System.out.println("\t\t7. Search Donation");
        System.out.println("\t\t8. Generate Summary Report");
        System.out.println("\t\t0. Return Back to Previous Page");
        System.out.println("======================================================");

        System.out.print("Enter your choice -> ");
        opt = sc.nextInt();
        sc.nextLine();
        while (opt < 0 || opt > 8) {
            System.out.print("Invalid option! Please select a number between 0 and 8 : ");
            opt = sc.nextInt();
        }
        return opt;
    }
    //---------View Donation---------------------

    public void displayDonateFoodHeader() {
        System.out.println("\t\tDONATE FOOD");
        System.out.println("======================================================");
    }

    public void displayDonateCashHeader() {
        System.out.println("\t\tDONATE CASH");
        System.out.println("======================================================");
    }

    public void listDonationHeader() {
        System.out.println("\t\t\t\t\tALL DONATION LIST");
        printLine(1, 99);
        System.out.printf("|%-18s| %-18s| %-18s| %-18s| %-16s |\n", "Donation ID", "Donation Date", "Donor Name", "Donated Item", "Quantity");
        printLine(1, 99);
    }

    public void listFoodDonationHeader() {
        System.out.println("\t\t\t\t\tALL FOOD DONATION LIST");
        printLine(1, 99);
        System.out.printf("|%-18s| %-18s| %-18s| %-18s| %-16s |\n", "Donation ID", "Donation Date", "Donor Name", "Donated Item", "Quantity");
        printLine(1, 99);
    }

    public void listCashDonationHeader() {
        System.out.println("\t\t\t\t\tALL CASH DONATION LIST");
        printLine(1, 99);
        System.out.printf("|%-18s| %-18s| %-18s| %-18s| %-16s |\n", "Donation ID", "Donation Date", "Donor Name", "Donated Item", "Quantity");
        printLine(1, 99);
    }

    public int viewDonationMenu() {
        int opt;
        System.out.println("\t\tVIEW DONATION");
        System.out.println("======================================================");
        System.out.println("\t\t1. View All Donation");
        System.out.println("\t\t2. View Food Donation");
        System.out.println("\t\t3. View Cash Donation");
        System.out.println("\t\t0. Return Back to Previous Page");
        System.out.println("======================================================");

        System.out.print("Enter your choice -> ");
        opt = sc.nextInt();
        sc.nextLine();
        while (opt < 0 || opt > 3) {
            System.out.print("Invalid option! Please select a number between 0 and 3 : ");
            opt = sc.nextInt();
        }
        return opt;
    }

    //---------MAKE DONATION---------------------
    public int addDonationMenu() {
        int opt;
        System.out.println("\t\tMAKE DONATION");
        System.out.println("======================================================");
        System.out.println("\t\t1. Donate Food or Goods");
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

    public String inputDonorId() {
        System.out.print("Enter Donor ID: ");
        String donorId = sc.nextLine().trim();
        return donorId;
    }

    public String inputFoodName() {
        System.out.print("Enter item Name: ");
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

    //---------UPDATE DONATION---------------------
    public void displayEditDonationHeader() {
        System.out.println("\t\tUpdate DONATION");
        System.out.println("======================================================");
    }

    public int displayUpdateMenu() {
        int opt;
        System.out.println("\t\t1. Update Item Donation");
        System.out.println("\t\t2. Update Cash Donation");
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

    public String inputItemDonationId(String string) {
        System.out.printf("Enter %s DONATION ID (Exit = 0) -> ", string);
        String donationId = sc.nextLine();
        return donationId;
    }
    
    public String inputDonationId() {
        System.out.print("Enter DONATION ID (Exit = 0) -> ");
        String donationId = sc.nextLine();
        return donationId;
    }
    
    public double inputNewCash() {
        System.out.print("Enter new amount cash: RM ");
        double amount = sc.nextDouble();
        sc.nextLine();
        return amount;
    }
    
    public int inputChoice(int noOfItems) {
        int opt;
        System.out.print("Choose the item No. to edit (Exit = 0): ");
        opt = sc.nextInt();
        sc.nextLine();
        while (opt < 0 || opt > noOfItems) {
            System.out.printf("Invalid option! Please select a number between 0 and %d : ", noOfItems);
            opt = sc.nextInt();
        }
        return opt;
    }

    public int inputUpdateType() {
        int opt;
        System.out.print("1)Change Item Name\n2)Change Item Quantity\nPlease enter your choice (Exit = 0) -> ");
        opt = sc.nextInt();
        sc.nextLine();
        while (opt < 0 || opt > 2) {
            System.out.print("Invalid option! Please select a number between 0 and 2 : ");
            opt = sc.nextInt();
        }
        return opt;
    }

    public String inputNewItemName(String string) {
        String newItemName;
        System.out.printf("Enter new item name for [%s](Exit = 0) -> ", string);
        newItemName = sc.nextLine().trim();
        return newItemName;
    }

    public double inputNewItemQty(String string) {
        System.out.printf("Enter new quantity for [%s](Exit = 0) -> ", string);
        double qty = sc.nextDouble();
        sc.nextLine();
        return qty;
    }

    public String inputNewItemUnit(String string) {
        String newItemUnit;
        System.out.printf("Enter new item unit for [%s](Exit = 0) -> ", string);
        newItemUnit = sc.nextLine().trim();
        return newItemUnit;
    }

    public int areYouSure(String string) {
        System.out.printf("Are you sure to update %s (Y = 1/N = 0) -> ", string);
        int choice = sc.nextInt();
        sc.nextLine();
        while (choice < 0 || choice > 1) {
            System.out.printf("Invalid option! Please select a number range (0 to 1) : ");
            choice = sc.nextInt();
        }
        return choice;
    }

    //---------DELETE DONATION---------------------
    public int askToDeleteDonation(String string) {
        System.out.printf("Are you sure to delete this donation [%s]? (Yes = 1 / N = 0): ", string);
        int choice = sc.nextInt();
        sc.nextLine();
        while (choice < 0 || choice > 1) {
            System.out.print("Invalid option! Please select a number (0 OR 1) : ");
            choice = sc.nextInt();
            sc.nextLine();
        }
        return choice;
    }

    //---------DELETE DONATED ITEM--------------------  
    public int inputToRemoveAllItems() {
        int opt;
        System.out.printf("1. Remove All Items\n2. Remove One Item\n0. Exit\nEnter your choice: ");
        opt = sc.nextInt();
        sc.nextLine();
        while (opt < 0 || opt > 2) {
            System.out.print("Invalid option! Please select a number between 0 and 2 : ");
            opt = sc.nextInt();
        }
        return opt;
    }

    public int inputChoiceDonatedItem(int noOfItems) {
        int opt;
        System.out.print("Choose the item No. to remove (Exit = 0): ");
        opt = sc.nextInt();
        sc.nextLine();
        while (opt < 0 || opt > noOfItems) {
            System.out.printf("Invalid option! Please select a number between 0 and %d : ", noOfItems);
            opt = sc.nextInt();
        }
        return opt;
    }

    public int areYouSureDonatedItem(int itemNo) {
        System.out.printf("Are you sure to remove Item %d (Yes = 1/No = 0) -> ", itemNo);
        int choice = sc.nextInt();
        sc.nextLine();
        while (choice < 0 || choice > 1) {
            System.out.printf("Invalid option! Please select a number range (0 to 1) : ");
            choice = sc.nextInt();
        }
        return choice;
    }
    
    //---------ADD DONATED ITEM--------------------  
    public int addDoantedItemMenu(){
        int opt;
        System.out.println("\t\tADD DONATED ITEM / CASH");
        System.out.println("======================================================");
        System.out.println("\t\t1. Add Donated Item to Donation");
        System.out.println("\t\t2. Add Cash to Donation");
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

    // sub function
    public int askToContinue() {
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

    public void displayHeader(String string) {
        System.out.printf("\t\t%s\n", string);
        System.out.println("======================================================");
    }
    
    public int inputMonth() {
        int month = 0;
        while (month < 1 || month > 12) {
            System.out.print("Enter the month (1-12): ");
            if (sc.hasNextInt()) {
                month = sc.nextInt();
                if (month < 1 || month > 12) {
                    System.out.println("Invalid month. Please enter a number between 1 and 12.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // Clear the invalid input
            }
        }
        return month;
    }
    
    public int inputYear() {
        int year = 0;
        while (year <= 0) {
            System.out.print("Enter the year: ");
            if (sc.hasNextInt()) {
                year = sc.nextInt();
                if (year <= 0) {
                    System.out.println("Invalid year. Please enter a positive number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next(); // Clear the invalid input
            }
        }
        return year;
    }

    public static void main(String[] args) {
        DonationUI ui = new DonationUI();
        ui.addDonationMenu();
    }

}
