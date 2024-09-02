/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import boundary.DonorUI;
import entity.Donor;
import entity.Donation;
import adt.SortedListInterface;
import adt.SortedArrayList;
import entity.DonatedItem;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Scanner;
import java.time.*;
import java.time.format.*;

/**
 *
 * @author User
 */
public class DonorController {

    private DonorUI donorUI = new DonorUI();
    SortedListInterface<Donor> donor = new SortedArrayList<>();
    SortedListInterface<Donation> donation = new SortedArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public DonorController(SortedListInterface<Donor> donor, SortedListInterface<Donation> donation) {
        this.donation = donation;
        this.donor = donor;
    }

    public void addDonor() {
        int exit = 0;

        do {
            donorUI.addDonorMenu();
            String name = donorUI.inputDonorName();
            if (name.equals("0")) {
                clearScreen();
                break;
            }
            String contactNo = donorUI.inputDonorContactNo();
            if (contactNo.equals("0")) {
                clearScreen();
                break;
            }
            String category = donorUI.inputDonorCategory();
            if (category.equals("0")) {
                clearScreen();
                break;
            }
            String type = donorUI.inputDonorType();
            if (type.equals("0")) {
                clearScreen();
                break;
            }

            if (donorUI.inputConfirmation("add a new donor")) {
                Donor dr = new Donor(name, contactNo, category, type);
                donor.add(dr);
                
                donorUI.displayDonorDetail();
                System.out.printf(" %-12s|  %-28s| %-16s| %-12s| %-14s\n", dr.getDonorId(), dr.getName(), dr.getContactNo(), dr.getCategory(), dr.getType());
                System.out.println("=================================================================================================\n");
                System.out.println("You have successfully added a new donor!!");
            } else {
                System.out.println("You canceled to add a new donor!!");
            }
            
            exit = donorUI.inputExitPage();
            clearScreen();
        } while (exit == 0);

        
    }

    public void searchDonor() {
        int exit, found = 0;
        String id;

        do {
            donorUI.searchDonorMenu();
            id = donorUI.inputDonorId();
            if (id.equals("0")) {
                clearScreen();
                break;
            }
            Iterator<Donor> getDonor = donor.getIterator();
            while (getDonor.hasNext()) {
                Donor donorObject = getDonor.next();
                if (donorObject.getDonorId().equals(id)) {
                    ++found;
                    donorUI.displayDonorDetail();
                    System.out.printf(" %-12s|  %-28s| %-16s| %-12s| %-14s\n", donorObject.getDonorId(), donorObject.getName(), donorObject.getContactNo(), donorObject.getCategory(), donorObject.getType());
                    System.out.println("=================================================================================================\n");
                    break;
                }
            }
            if (found == 0) {
                System.out.println("There is no details for this donor. Please Try Again.");
            }

            exit = donorUI.inputExitPage();
            clearScreen();
        } while (exit == 0);

    }

    public void removeDonor() {
        int exit = 0;
        String id;
        boolean isSuccess = false;

        do {
            donorUI.removeDonorMenu();
            id = donorUI.inputDonorId();
            if (id.equals("0")) {
                clearScreen();
                break;
            }
            
            Iterator<Donor> getDonor = donor.getIterator();
            while (getDonor.hasNext()) {
                Donor donorObject = getDonor.next();
                if(donorObject.getDonorId().equals(id)){
                    donorUI.displayDonorDetail();
                    System.out.printf(" %-12s|  %-28s| %-16s| %-12s| %-14s\n", donorObject.getDonorId(), donorObject.getName(), donorObject.getContactNo(), donorObject.getCategory(), donorObject.getType());
                    System.out.println("=================================================================================================\n");
                    if (donorUI.inputConfirmation("remove this donor") == true) {
                        isSuccess = donor.remove(donorObject);
                        break;
                    } else {
                        System.out.println("Your canceled to remove the donor.");
                    }
                }
                
            }
            if (isSuccess) {
                System.out.println("Remove the donor successfully.");
            }


            exit = donorUI.inputExitPage();
            clearScreen();
        } while (exit == 0);
    }

    public void updateDonorDetails() {
        int exit, option, count;
        String id, name = "", contactNo = "", category = "", type = "";
        boolean isSuccess = false;

        do {
            donorUI.updateDonorMenu();
            id = donorUI.inputDonorId();
            if (id.equals("0")) {
                clearScreen();
                break;
            }
            option = donorUI.updateMenu();
            if (option == 0) {
                clearScreen();
                break;
            }
            switch (option) {
                case 1: {
                    name = donorUI.inputDonorName();
                    if (name.equals("0")) {
                        clearScreen();
                        break;
                    }
                    break;
                }
                case 2: {
                    contactNo = donorUI.inputDonorContactNo();
                    if (contactNo.equals("0")) {
                        clearScreen();
                        break;
                    }
                    break;
                }
                case 3: {
                    category = donorUI.inputDonorCategory();
                    if (category.equals("0")) {
                        clearScreen();
                        break;
                    }
                    break;
                }
                case 4: {
                    type = donorUI.inputDonorType();
                    if (type.equals("0")) {
                        clearScreen();
                        break;
                    }
                    break;
                }
            }
            if (donorUI.inputConfirmation("update the donor detail")) {
                Iterator<Donor> getDonor = donor.getIterator();
                count = 0;
                while (getDonor.hasNext()) {
                    count++;
                    Donor donorObject = getDonor.next();
                    if (donorObject.getDonorId().equals(id)) {
                        if (option == 1) {
                            donorObject.setName(name);
                            donorUI.displayDonorDetail();
                            System.out.printf(" %-12s|  %-28s| %-16s| %-12s| %-14s\n", donorObject.getDonorId(), donorObject.getName(), donorObject.getContactNo(), donorObject.getCategory(), donorObject.getType());
                            System.out.println("=================================================================================================\n");
                            break;
                        }
                        if (option == 2) {
                            donorObject.setContactNo(contactNo);
                            donorUI.displayDonorDetail();
                            System.out.printf(" %-12s|  %-28s| %-16s| %-12s| %-14s\n", donorObject.getDonorId(), donorObject.getName(), donorObject.getContactNo(), donorObject.getCategory(), donorObject.getType());
                            System.out.println("=================================================================================================\n");
                            break;
                        }
                        if (option == 3) {
                            donorObject.setCategory(category);
                            donorUI.displayDonorDetail();
                            System.out.printf(" %-12s|  %-28s| %-16s| %-12s| %-14s\n", donorObject.getDonorId(), donorObject.getName(), donorObject.getContactNo(), donorObject.getCategory(), donorObject.getType());
                            System.out.println("=================================================================================================\n");
                            break;
                        }
                        if (option == 4) {
                            donorObject.setType(type);
                            donorUI.displayDonorDetail();
                            System.out.printf(" %-12s|  %-28s| %-16s| %-12s| %-14s\n", donorObject.getDonorId(), donorObject.getName(), donorObject.getContactNo(), donorObject.getCategory(), donorObject.getType());
                            System.out.println("=================================================================================================\n");
                            break;
                        }
                    }
                }
            } else {
                System.out.println("You canceled to update the donor details.");
            }

            exit = donorUI.inputExitPage();
            clearScreen();
        } while (exit == 0);
    }

    public void listDonor() {
        int option;

        do {
            option = donorUI.listMenu();
            switch (option) {
                case 1:
                    clearScreen();
                    listWithDonation();
                    break;
                case 2:
                    clearScreen();
                    filterDonor();
                    break;
                case 3:
                    clearScreen();
                    listAllDonor();
                case 0:
                    clearScreen();
                    break;
            }
        } while (option != 0);
    }
    
    public void listAllDonor(){
        donorUI.displayDonorDetail();
        Iterator<Donor> getDonor = donor.getIterator();
        while(getDonor.hasNext()){
            Donor donorObject = getDonor.next();
            System.out.printf(" %-12s|  %-28s| %-16s| %-12s| %-14s\n", donorObject.getDonorId(), donorObject.getName(), donorObject.getContactNo(), donorObject.getCategory(), donorObject.getType());
            if(getDonor.hasNext()){
                System.out.println("------------------------------------------------------------------------------------------------");
            }
        }
        System.out.println("=================================================================================================\n");
        pressEnterContinue();
        clearScreen();
    }

    public void listWithDonation() {
        int exit;
        SortedListInterface<Donation> tempDonation = new SortedArrayList<>();

        if (donor.isEmpty()) {
            System.out.println("Opps!! There is no donor in the list.");
        } else {
            donorUI.displayDonorWithDonation();
            Iterator<Donor> getDonor = donor.getIterator();
            while (getDonor.hasNext()) {
                int donationTimes = 0;
                Donor donorObject = getDonor.next();
                tempDonation = donorObject.getDonorDonationList();
                Iterator<Donation> getDonation = tempDonation.getIterator();
                while (getDonation.hasNext()) {
                    Donation donationObject = getDonation.next();
                    if (donationObject == null) {
                        continue;
                    }
                    
                    if(donationTimes == 0){
                        int times = 0;
                        System.out.printf(" %-28s|", donationObject.getDonor().getName());
                        if (donationObject.getCategory().equals("F")) {
                            donationObject.getDonor().getName();
                            System.out.print(" Food Donation     |");
                            Iterator<DonatedItem> itDonatedItem = donationObject.getDonatedItems().getIterator();
                            while (itDonatedItem.hasNext()) {
                                DonatedItem item = itDonatedItem.next();
                                if (times == 0) {
                                    System.out.printf(" %-13s| %-10s| %s\n", item.getItemName(), item.getUnit(), item.getQuantity());

                                    times++;
                                } else {
                                     System.out.printf("%-49s| %-13s| %-10s| %s\n", " ", item.getItemName(), item.getUnit(), item.getQuantity());

                                }

                            }
                        }
                        if (donationObject.getCategory().equals("C")) {
                            donationObject.getDonor().getName();
                            Iterator<DonatedItem> itDonatedItem = donationObject.getDonatedItems().getIterator();
                            System.out.print(" Cash Donation     |");
                            while (itDonatedItem.hasNext()) {
                                DonatedItem item = itDonatedItem.next();
                                if (times == 0) {
                                    System.out.printf(" %-13s| %-10s| %s\n", item.getItemName(), item.getUnit(), item.getQuantity());
                                    times++;
                                } else {
                                    System.out.printf("%-49s| %-13s| %-10s| %s\n", " ", item.getItemName(), item.getUnit(), item.getQuantity());
                                }
                            }
                            
                        }
                        donationTimes++;
                    }else {
                        int times = 0;
                        if (donationObject.getCategory().equals("F")) {
                            donationObject.getDonor().getName();
                            System.out.printf("%-29s| %s", " ", "Food Donation     |");
                            Iterator<DonatedItem> itDonatedItem = donationObject.getDonatedItems().getIterator();
                            while (itDonatedItem.hasNext()) {
                                DonatedItem item = itDonatedItem.next();
                                if (times == 0) {
                                    System.out.printf(" %-13s| %-10s| %s\n", item.getItemName(), item.getUnit(), item.getQuantity());

                                    times++;
                                } else {
                                    System.out.printf("%-49s| %-13s| %-10s| %s\n", " ", item.getItemName(), item.getUnit(), item.getQuantity());

                                }

                            }
                        }
                        if (donationObject.getCategory().equals("C")) {
                            donationObject.getDonor().getName();
                            Iterator<DonatedItem> itDonatedItem = donationObject.getDonatedItems().getIterator();
                            System.out.printf("%-29s| %s", " ", "Cash Donation     |");
                            while (itDonatedItem.hasNext()) {
                                DonatedItem item = itDonatedItem.next();
                                if (times == 0) {
                                    System.out.printf(" %-13s| %-10s| %s\n", item.getItemName(), item.getUnit(), item.getQuantity());
                                    times++;
                                } else {
                                    System.out.printf("%-49s| %-13s| %-10s| %s\n", " ", item.getItemName(), item.getUnit(), item.getQuantity());
                                }
                            }

                        }
                    }
                    
                }
                
            }
            System.out.println("========================================================================================================\n");
            pressEnterContinue();
            clearScreen();
        }
    }

    public void filterDonor() {
        int exit, option;
        String category, type, month;
        do {
            donorUI.filterDonorMenu();
            option = donorUI.filterMenu();
            
            if(option == 0){
                clearScreen();
                break;
            }else if (donor.isEmpty()) {
                System.out.println("Opps!! There is no donor in the list.");
            } else {
                Iterator<Donor> getDonor = donor.getIterator();
                while (getDonor.hasNext()) {
                    Donor donorObject = getDonor.next();
                    if (option == 1) {
                        do{
                            System.out.print("Enter the donor category that wanted to list (Exit = 0): ");
                            category = scanner.nextLine();
                            if(category.equals("0")){
                                clearScreen();
                                break;
                            }else if (!category.equals("public") && !category.equals("private") && !category.equals("government")) {
                                System.out.println("Please only input public, private or government.\n");
                            }else{
                                clearScreen();
                                donorUI.displayDonorDetail();
                                filterByCategory(category);
                                System.out.println("=================================================================================================\n");
                            }
                        }while(!category.equals("public") && !category.equals("private") && !category.equals("government"));
                        
                        break;
                    }
                    if (option == 2) {
                        do{
                            System.out.print("Enter the donor type that wanted to list (Exit = 0): ");
                            type = scanner.nextLine();
                            if(type.equals("0")){
                                clearScreen();
                                break;
                            }else if(!type.equals("individual") && !type.equals("organization")){
                                System.out.println("Please only input individual or organization.\n");
                            }else{
                                clearScreen();
                                donorUI.displayDonorDetail();
                                filterByType(type);
                                System.out.println("=================================================================================================\n");
                            }
                            
                        }while(!type.equals("individual") && !type.equals("organization"));
                        break;
                    }
                    if(option == 3){
                        do{
                            System.out.print("Enter the month that donor registered to list (eg. january) (Exit = 0): ");
                            month = scanner.nextLine().toLowerCase();
                            if(month.equals("0")){
                                clearScreen();
                                break;
                            }else if(!month.equals("january") && !month.equals("february") && !month.equals("march") && !month.equals("april") && !month.equals("may") && !month.equals("june") && !month.equals("july") && !month.equals("august") && !month.equals("september") && !month.equals("october") && !month.equals("november") && !month.equals("december")){
                                System.out.println("Please only input month with required format. (eg. january)\n");
                            }else{
                                clearScreen();
                                donorUI.displayDonorDetail();
                                filterByMonth(month);
                                System.out.println("=================================================================================================\n");
                            }
                        }while(!month.equals("january") && !month.equals("february") && !month.equals("march") && !month.equals("april") && !month.equals("may") && !month.equals("june") && !month.equals("july") && !month.equals("august") && !month.equals("september") && !month.equals("october") && !month.equals("november") && !month.equals("december"));
                        break;
                    }
                }
            }

            exit = donorUI.inputExitPage();
            clearScreen();
        } while (exit == 0);
    }

    private void filterByCategory(String category) {

        
        Iterator<Donor> itDonor = donor.getIterator();
        while (itDonor.hasNext()) {
            Donor tempDonor = itDonor.next();
            if (tempDonor.getCategory().equals(category)) {
                System.out.printf(" %-12s|  %-28s| %-16s| %-12s| %-14s\n", tempDonor.getDonorId(), tempDonor.getName(), tempDonor.getContactNo(), tempDonor.getCategory(), tempDonor.getType());
                
            
            }
        }
    }

    private void filterByType(String type) {

        Iterator<Donor> itDonor = donor.getIterator();
        while (itDonor.hasNext()) {
            Donor tempDonor = itDonor.next();
            if (tempDonor.getType().equals(type)) {
                System.out.printf(" %-12s|  %-28s| %-16s| %-12s| %-14s\n", tempDonor.getDonorId(), tempDonor.getName(), tempDonor.getContactNo(), tempDonor.getCategory(), tempDonor.getType());
            }
        }
    }
    
    private void filterByMonth(String month) {

        Iterator<Donor> itDonor = donor.getIterator();
        while (itDonor.hasNext()) {
            Donor tempDonor = itDonor.next();
            if (tempDonor.getDate().equals(month)) {
                System.out.printf(" %-12s|  %-28s| %-16s| %-12s| %-14s\n", tempDonor.getDonorId(), tempDonor.getName(), tempDonor.getContactNo(), tempDonor.getCategory(), tempDonor.getType());
            }
        }
    }
    
    public void listReport(){
        int option;
        
        do{
            option = donorUI.monthlyDonorReportMenu();
            switch(option){
                case 1:
                    clearScreen();
                    generateMonthlyReport();
                    break;
                case 2:
                    clearScreen();
                    generateAnualReport();
                    break;
                case 0:
                    clearScreen();
                    break;
                default:
                    System.out.println("Please enter a valid input!!\n");
                    break;
            }
        }while(option != 0);
    }
    
    public void generateMonthlyReport(){
        String month;
        int totalDonor = 0, totalPublic = 0, totalPrivate = 0, totalGovernment = 0, totalIndividual = 0, totalOrganization = 0;
        LocalDateTime currentDateTime = LocalDateTime.now();
        
        do{
            System.out.print("Enter the month that wanted to generate (eg. january) (Exit = 0): ");
            month = scanner.nextLine().toLowerCase();
            if(month.equals("0")){
                clearScreen();
                break;
            }else if (!month.equals("january") && !month.equals("february") && !month.equals("march") && !month.equals("april") && !month.equals("may") && !month.equals("june") && !month.equals("july") && !month.equals("august") && !month.equals("september") && !month.equals("october") && !month.equals("november") && !month.equals("december")) {
                System.out.println("Please only input month with required format. (eg. january)\n");
            }else{
                donorUI.monthlyReportMenu(month);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = currentDateTime.format(formatter);
                System.out.println("Generated by: " + formattedDateTime);
                System.out.println("=====================================================================================================================\n");
                
                Iterator<Donor> getDonor = donor.getIterator();
                while (getDonor.hasNext()){
                    Donor donorObject = getDonor.next();
                    if(donorObject.getDate().equals(month)){
                        totalDonor++;
                        if(donorObject.getCategory().equals("public")){
                            totalPublic++;
                        }
                        if(donorObject.getCategory().equals("private")){
                            totalPrivate++;
                        }
                        if(donorObject.getCategory().equals("government")){
                            totalGovernment++;
                        }
                        if(donorObject.getType().equals("individual")){
                            totalIndividual++;
                        }
                        if(donorObject.getType().equals("organization")){
                            totalOrganization++;
                        }
                    }
                }
                
                System.out.printf("Total Number Of Donor Registered: %d \n\n", totalDonor);
                System.out.printf("Total Number Of Donor In Public Category Registered: %d \n", totalPublic);
                System.out.printf("Total Number Of Donor In Private Category Registered: %d \n", totalPrivate);
                System.out.printf("Total Number Of Donor In Government Category Registered: %d \n\n", totalGovernment);
                System.out.printf("Total Number Of Donor In Individual Type Registered: %d \n", totalIndividual);
                System.out.printf("Total Number Of Donor In Organization Type Registered: %d \n\n", totalOrganization);
                System.out.println("=====================================================================================================================\n");
                pressEnterContinue();
                clearScreen();
            }
        }while(!month.equals("january") && !month.equals("february") && !month.equals("march") && !month.equals("april") && !month.equals("may") && !month.equals("june") && !month.equals("july") && !month.equals("august") && !month.equals("september") && !month.equals("october") && !month.equals("november") && !month.equals("december"));
        
        
     
        
        
        
    }

    public void generateAnualReport() {
        int january = 0, february = 0, march = 0, april = 0, may = 0, june = 0, july = 0, august = 0, september = 0, october = 0, november = 0, december = 0;
        
        LocalDateTime currentDateTime = LocalDateTime.now();
        donorUI.reportDonorMenu();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        System.out.println("Generated by: " + formattedDateTime);
        System.out.println("=============================================================================================================================================================\n");

        if (donor.isEmpty()) {
            System.out.println("Opps!! There is no donor in the list.");
        } else {
            Iterator<Donor> getDonor = donor.getIterator();
            while (getDonor.hasNext()) {
                Donor donorObject = getDonor.next();
                if (donorObject.getDate().equals("january")) {
                    january++;
                } else if (donorObject.getDate().equals("february")) {
                    february++;
                } else if (donorObject.getDate().equals("march")) {
                    march++;
                } else if (donorObject.getDate().equals("april")) {
                    april++;
                } else if (donorObject.getDate().equals("may")) {
                    may++;
                } else if (donorObject.getDate().equals("june")) {
                    june++;
                } else if (donorObject.getDate().equals("july")) {
                    july++;
                } else if (donorObject.getDate().equals("august")) {
                    august++;
                } else if (donorObject.getDate().equals("september")) {
                    september++;
                } else if (donorObject.getDate().equals("october")) {
                    october++;
                } else if (donorObject.getDate().equals("november")) {
                    november++;
                } else if (donorObject.getDate().equals("december")) {
                    december++;
                }
            }

            int[] counts = {
                january, february, march, april, may, june, july, august, september, october, november, december
            };
            String[] months = {
                "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
            };

            donorUI.monthlyRegisterMenu(months, counts);
            pressEnterContinue();
            clearScreen();

        }
    }
    
    public static void clearScreen() {
        try {
            Robot rob = new Robot();
            try {
                rob.keyPress(KeyEvent.VK_CONTROL); // press "CTRL"
                rob.keyPress(KeyEvent.VK_L); // press "L"
                rob.keyRelease(KeyEvent.VK_L); // unpress "L"
                rob.keyRelease(KeyEvent.VK_CONTROL); // unpress "CTRL"
                Thread.sleep(50); // add delay in milisecond, if not there will automatically stop after clear
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    
    public void pressEnterContinue() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Press [Enter] key to continue...");
        sc.nextLine();
        clearScreen();
    }

    public void menu() {
        int option;

        do {
            
            option = donorUI.donorMenu();
            clearScreen();
            switch (option) {
                case 1:
                    clearScreen();
                    addDonor();
                    break;
                case 2:
                    clearScreen();
                    removeDonor();
                    break;
                case 3:
                    clearScreen();
                    updateDonorDetails();
                    break;
                case 4:
                    clearScreen();
                    searchDonor();
                    break;
                case 5:
                    clearScreen();
                    listDonor();
                    break;
                case 6:
                    clearScreen();
                    listReport();
                    break;
                case 0:
                    clearScreen();
                    break;
                default:
                    System.out.println("Please enter a valid input!!\n");
                    break;
            }
        } while (option != 0);
    }
}
