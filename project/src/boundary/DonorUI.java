/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import java.util.Scanner;
/**
 *
 * @author User
 */
public class DonorUI {
    Scanner scanner = new Scanner(System.in);
    
    public int donorMenu() {
        int option = 0;
        boolean isValid = false;
        
        while(!isValid){
            do {
                System.out.println("+---------------------------------------------------------+\n"
                                 + "|                     Donor Management                    |\n"
                                 + "+---------------------------------------------------------+\n"
                                 + "|          1. Add Donor                                   |\n"
                                 + "|          2. Remove Donor                                |\n"
                                 + "|          3. Update Donor Details                        |\n"
                                 + "|          4. Search Donor Details                        |\n"
                                 + "|          5. List Donor                                  |\n"
                                 + "|          6. Donor Summary Report                        |\n"
                                 + "|          0. Exit                                        |\n"
                                 + "+---------------------------------------------------------+");
                        
                System.out.print("Enter your option (0 - 6): ");
                
                if(scanner.hasNextInt()){
                    option = scanner.nextInt();
                    scanner.nextLine();
                    isValid = true;
                }else{
                    System.out.println("You entered an invalid input!! Please enter only from 0 to 6.");
                    scanner.next();
                }
                
                if (option < 0 || option > 6) {
                    System.out.println("You entered an invalid input!! Please enter only from 0 to 6.");
                }
            } while (option < 0 || option > 6);
        }
        return option;
    }
    
    public int listMenu() {
        int option;
        
        do {
            System.out.println("+----------------------------------------------+\n" +
                               "|  1. List Donors With All the Donations made  |\n" +
                               "|  2. Categories Donors                        |\n" +
                               "|  0. Exit                                     |\n" +
                               "+----------------------------------------------+\n\n");

            System.out.print("Enter your option (1 - 2): ");
            option = scanner.nextInt();
            scanner.nextLine();
            if(option < 0 || option > 2) {
                System.out.println("You entered an invalid input!! Please enter only from 0 to 2.");
            }
        }while(option < 0 || option > 2);
        
        return option;
    }
    
    public void addDonorMenu() {
        System.out.println("+----------------------------------------------+\n" +
                           "|                                              |\n" +
                           "|                Add a New Donor               |\n" +
                           "|                                              |\n" +
                           "+----------------------------------------------+\n");
    }
    
    public void removeDonorMenu() {
        System.out.println("+----------------------------------------------+\n" +
                           "|                                              |\n" +
                           "|                Remove a Donor                |\n" +
                           "|                                              |\n" +
                           "+----------------------------------------------+\n");
    }
    
    public void updateDonorMenu() {
        System.out.println("+----------------------------------------------+\n" +
                           "|                                              |\n" +
                           "|             Update Donor Details             |\n" +
                           "|                                              |\n" +
                           "+----------------------------------------------+\n");
    }
    
    public void searchDonorMenu() {
        System.out.println("+----------------------------------------------+\n" + 
                           "|                                              |\n" +
                           "|               Search a New Donor             |\n" +
                           "|                                              |\n" +
                           "+----------------------------------------------+\n");
    }
    
    public void listDonorMenu() {
        System.out.println("+----------------------------------------------+\n" +
                           "|                                              |\n" +
                           "|   List Donors With All the Donations made    |\n" +
                           "|                                              |\n" +
                           "+----------------------------------------------+\n");
    }
    
    public void filterDonorMenu() {
        System.out.println("+----------------------------------------------+\n" +
                           "|                                              |\n" +
                           "|        Filter Donor based On Criteria        |\n" +
                           "|                                              |\n" +
                           "+----------------------------------------------+\n");
    }
    
    
    
    public void categoriesDonorMenu() {
        System.out.println("+----------------------------------------------+\n" +
                           "|                                              |\n" +
                           "|              Categories Donor                |\n" +
                           "|                                              |\n" +
                           "+----------------------------------------------+\n");
    }
    
    public void reportDonorMenu() {
        System.out.println("+----------------------------------------------+\n" +
                           "|                                              |\n" +
                           "|             Donor Summary Report             |\n" +
                           "|                                              |\n" +
                           "+----------------------------------------------+\n");
    }
    
    public void monthlyRegisterMenu(String months[], int counts[]) {
        System.out.println("No. of Donor");
        int[] count = counts;
        int maxNum = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] >= maxNum) {
                maxNum = count[i];
            }
        }
        
        for (int i = maxNum; i >= 1; i--) {
            System.out.printf("%d |", i);
            for (int j = 0; j < count.length; j++) {
                if (count[j] == i) {
                    System.out.print("     *      ");
                    count[j]--;
                } else {
                    System.out.print("            ");
                }
            }
            System.out.println();
        }
        System.out.println("--|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|--> Month");

        System.out.printf("%-5s%s%-4s%s%-6s%s%-7s%s%-8s%s%-8s%s%-8s%s%-7s%s%-5s%s%-4s%s%-4s%s%-4s%s", 
                "     ", "January","    ", "February", "      ", "March","    ", "April","    ", "May","    ", "June","    ", "July","    ", "August","    ", "September" ,"    ", "October" ,"    ", "November","    ", "December");
    }
    
//    public void monthlyRegisterMenu(String months[], int counts[]){
//        String[] month = months;
//        int[] count = counts, highest;
//        System.out.println("+--------------------------------------------------------------------------+\n" +
//                           "|                   2024 Donor Monthly Registration Report                 |\n" +
//                           "+--------------------------------------------------------------------------+\n" +
//                           "|    Month    |   Chart                                                    |\n" +
//                           "+--------------------------------------------------------------------------+");
//        
//        for (int i = 0; i < counts.length; i++) {
//            System.out.printf("%-3s%-9s%-5s", "|  ", months[i], "  |  ");
//            String output = "";
//            for(int j = 0; j < counts[i]; j++){
//                output += " *";
//            }
//            output += " (" + counts[i] + ")";
//            System.out.printf("%-57s |\n", output);
//        }
//        System.out.println("+--------------------------------------------------------------------------+");
//        
//    }
    
    public int updateMenu() {
        int option;
        do{
            System.out.println();
            System.out.println("+----------------------------------------------+\n" +
                               "|  1. Donor Name                               |\n" +
                               "|  2. Donor Contact Number                     |\n" +
                               "|  3. Donor Category                           |\n" +
                               "|  0. Exit                                     |\n" +
                               "+----------------------------------------------+\n");
            System.out.print("Choose one to update (1-3): ");
            option = scanner.nextInt();
            scanner.nextLine();
            if (option < 0 || option > 3) {
                System.out.print("Invalid option! Please select a number between 1 and 3 : ");
                option = scanner.nextInt();
                scanner.nextLine();
            }

        }while(option < 0 || option > 3);
        
        return option;
    }
    
    public int filterMenu() {
        int option;
        
        do{
            System.out.println();
            System.out.println("+----------------------------------------------+\n" +
                               "|  1. Donor Category                           |\n" +
                               "|  2. Donor Type                             |\n" +
                               "|  0. Exit                                     |\n" +
                               "+----------------------------------------------+\n");
            System.out.print("Choose one to list (1-2): ");
            option = scanner.nextInt();
            scanner.nextLine();
            if (option < 1 || option > 3) {
                System.out.print("Invalid option! Please select 1 or 2 : ");
                option = scanner.nextInt();
                scanner.nextLine();
            }

        }while(option < 1 || option > 4);
        
        return option;
    }
    
    public void displayDonorDetail(){
        System.out.println("+--------------------------------------------------------------------------------+\n" +
                           "| Donor Id  |  Name              | Contact Number  | Category    | Type          |\n" +
                           "+--------------------------------------------------------------------------------+");
        
        
    }
    
    public String inputDonorId() {
        String donorId;
        
        do{
            System.out.print("Enter donor id (Exit = 0): ");
            donorId = scanner.nextLine();
            if(donorId.isEmpty()){
                System.out.println("Opps!! Please do not leave it blank.");
            }
        }while(donorId.isEmpty());
        
        return donorId;
    }
    
    public String inputDonorName() {
        String name;
        
        do{
            System.out.print("Enter a new donor name (Exit = 0): ");
            name = scanner.nextLine();
            if(name.equals("0")) {
                return name;
            } else if(name.length() < 4) {
                System.out.println("Opps!! The donor name must be equal or over 4 letters.");
            }
        }while(name.length() < 4);
         
        return name;
    }
    
    public String inputDonorContactNo() {
        String contactNo;
        
        do{
            System.out.print("Enter new donor's contact number (enter without - and space) (Exit = 0): ");
            contactNo = scanner.nextLine();
            if(contactNo.equals("0")) {
                return contactNo;
            } else if(contactNo.length() < 10 || contactNo.length() > 11) {
                System.out.println("Opps!! The contact number must be 10 or 11 digits.");
            }
        }while(contactNo.length() < 10 || contactNo.length() > 11);
        
        return contactNo;
    }
    
    public String inputDonorCategory() {
        String category;
        
        do{
            System.out.print("Enter new donor in what category (public, government, private) (Exit = 0): ");
            category = scanner.nextLine().toLowerCase();
            if(category.equals("0")) {
                return category;
            } else if(!category.equals("public") && !category.equals("private") && !category.equals("government")) {
                System.out.println("Opps!! The category must enter public, government or private only.");
            }
        }while(!category.equals("public") && !category.equals("private") && !category.equals("government"));
        
        return category;
    }
    
    public String inputDonorType() {
        String type;
        
        do{
            System.out.print("Enter new donor in what type (individual, organization) (Exit = 0): ");
            type = scanner.nextLine().toLowerCase();
            if(type.equals("0")) {
                return type;
            } else if(!type.equals("individual") && !type.equals("organization")) {
                System.out.println("Opps!! The category must enter individual or organization only.");
            }
        }while(!type.equals("individual") && !type.equals("organization"));
        
        return type;
    }
    
    public boolean inputConfirmation(String str) {
        String confirm;

        System.out.println();
        do {
            System.out.printf("Confirm to " + str + "? (Y/N): ");
            confirm = scanner.nextLine();
            if (!confirm.toLowerCase().equals("y") && !confirm.toUpperCase().equals("Y")
                    && !confirm.toLowerCase().equals("n") && !confirm.toUpperCase().equals("N")) {
                System.out.println("Your entered an invalid Input !! Please try agian enter Y = Yes or N = No only.");
            }
        } while (!confirm.toLowerCase().equals("y") && !confirm.toUpperCase().equals("Y")
                && !confirm.toLowerCase().equals("n") && !confirm.toUpperCase().equals("N"));
        System.out.println();

        if (confirm.toLowerCase().equals("y") || confirm.toUpperCase().equals("Y")) {
            return true;
        } else {
            return false;
        }
    }
    
    public int inputExitPage() {
        int exit;

        System.out.println();
        System.out.print("Do you want to EXIT this page? (Yes = 1 / No = 0) : ");
        exit = scanner.nextInt();
        scanner.nextLine();
        while (exit < 0 || exit > 1) {
            System.out.println("Invalid Input! Please enter 0 or 1 (Yes=1/No=0) : ");
            exit = scanner.nextInt();
        }
        System.out.println();
        return exit;
    }
}
