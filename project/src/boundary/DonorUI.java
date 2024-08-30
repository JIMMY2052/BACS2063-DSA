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
        int option;
        
        do {
            System.out.println("+----------------------------------------------+\n" +
                               "|  1. Add Donor                                |\n" +
                               "|  2. Remove Donor                             |\n" +
                               "|  3. Update Donor Details                     |\n" +
                               "|  4. Search Donor Details                     |\n" +
                               "|  5. List Donors With All the Donations made  |\n" +
                               "|  6. Filter Donor based On Criteria           |\n" +
                               "|  7. Categories Donors                        |\n" +
                               "|  8. Generate Donor Summary Report            |\n" +
                               "|  0. Exit                                     |\n" +
                               "+----------------------------------------------+\n\n");

            System.out.print("Enter your option (0 - 8): ");
            option = scanner.nextInt();
            scanner.nextLine();
            if(option < 0 || option > 8) {
                System.out.println("You entered an invalid input!! Please enter only from 0 to 8.");
            }
        }while(option < 0 || option > 8);
        
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
                           "|         Generate Donor Summary Report        |\n" +
                           "|                                              |\n" +
                           "+----------------------------------------------+\n");
    }
    
    public int updateMenu() {
        int option;
        do{
            System.out.println();
            System.out.println("+----------------------------------------------+\n" +
                               "|  1. Donor Name                               |\n" +
                               "|  2. Donor Contact Number                     |\n" +
                               "|  3. Donor Category                           |\n" +
                               "|  4. Donor Gender                             |\n" +
                               "|  0. Exit                                     |\n" +
                               "+----------------------------------------------+\n");
            System.out.print("Choose one to update (1-4): ");
            option = scanner.nextInt();
            scanner.nextLine();
            if (option < 1 || option > 4) {
                System.out.print("Invalid option! Please select a number between 1 and 4 : ");
                option = scanner.nextInt();
                scanner.nextLine();
            }

        }while(option < 1 || option > 4);
        
        return option;
    }
    
    public int filterMenu() {
        int option;
        
        do{
            System.out.println();
            System.out.println("+----------------------------------------------+\n" +
                               "|  1. Donor Category                           |\n" +
                               "|  2. Donor Gender                             |\n" +
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
    
    public String inputDonorGender() {
        String gender;
        
        do{
            System.out.print("Enter new donor's gender (Exit = 0): ");
            gender = scanner.nextLine().toLowerCase();
            if(gender.equals("0")){
                return gender;
            }else if(!gender.equals("male") && !gender.equals("female")){
                System.out.println("Opps!! The gender must enter male or female only.");
            }
        }while(!gender.equals("male") && !gender.equals("female"));
        
        return gender;
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
        while (exit < 0 || exit > 1) {
            System.out.println("Invalid Input! Please enter 0 or 1 (Yes=1/No=0) : ");
            exit = scanner.nextInt();
        }
        System.out.println();
        return exit;
    }
}
