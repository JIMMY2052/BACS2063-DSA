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
                break;
            }
            String contactNo = donorUI.inputDonorContactNo();
            if (contactNo.equals("0")) {
                break;
            }
            String category = donorUI.inputDonorCategory();
            if (category.equals("0")) {
                break;
            }
            String type = donorUI.inputDonorType();
            if (type.equals("0")) {
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
        } while (exit == 0);

        
    }

    public int searchDonor() {
        int exit, found = 0;
        String id;

        do {
            donorUI.searchDonorMenu();
            id = donorUI.inputDonorId();
            if (id.equals("0")) {
                return exit = 0;
            }
            Iterator<Donor> getDonor = donor.getIterator();
            while (getDonor.hasNext()) {
                Donor donorObject = getDonor.next();
                if (donorObject.getDonorId().equals(id)) {
                    ++found;
                    donorUI.displayDonorDetail();
                    System.out.printf(" %-12s|  %-28s| %-16s| %-12s| %-14s\n", donorObject.getDonorId(), donorObject.getName(), donorObject.getContactNo(), donorObject.getCategory(), donorObject.getType());
                    System.out.println("============================================================================================\n");
                    break;
                }
            }
            if (found == 0) {
                System.out.println("There is no details for this donor. Please Try Again.");
            }

            exit = donorUI.inputExitPage();
        } while (exit == 0);

        return exit;
    }

    public void removeDonor() {
        int exit = 0;
        String id;
        boolean isSuccess = false;

        do {
            donorUI.removeDonorMenu();
            id = donorUI.inputDonorId();
            if (id.equals("0")) {
                break;
            }
            
            Iterator<Donor> getDonor = donor.getIterator();
            while (getDonor.hasNext()) {
                Donor donorObject = getDonor.next();
                if(donorObject.getDonorId().equals(id)){
                    donorUI.displayDonorDetail();
                    System.out.printf(" %-12s|  %-28s| %-16s| %-12s| %-14s\n", donorObject.getDonorId(), donorObject.getName(), donorObject.getContactNo(), donorObject.getCategory(), donorObject.getType());
                    System.out.println("============================================================================================\n");
                    if (donorUI.inputConfirmation("remove this donor") == true) {
                        isSuccess = donor.remove(donorObject);
                        break;
                    } else {
                        System.out.println("Your canceled to remove the donor.");
                    }
                }
                
            }
            if (isSuccess == true) {
                System.out.println("Remove the donor successfully.");
            }else {
                System.out.println("Opps!! Invalid Input. Please Try Again.");
            }


            exit = donorUI.inputExitPage();
        } while (exit == 0);
    }

    public int updateDonorDetails() {
        int exit, option, count;
        String id, name = "", contactNo = "", category = "", gender = "";
        boolean isSuccess = false;

        do {
            donorUI.updateDonorMenu();
            id = donorUI.inputDonorId();
            if (id.equals("0")) {
                return exit = 1;
            }
            option = donorUI.updateMenu();
            if (option == 0) {
                return exit = 1;
            }
            switch (option) {
                case 1: {
                    name = donorUI.inputDonorName();
                    if (name.equals("0")) {
                        return exit = 1;
                    }
                    break;
                }
                case 2: {
                    contactNo = donorUI.inputDonorContactNo();
                    if (contactNo.equals("0")) {
                        return exit = 1;
                    }
                    break;
                }
                case 3: {
                    category = donorUI.inputDonorCategory();
                    if (category.equals("0")) {
                        return exit = 1;
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
                            System.out.println("============================================================================================\n");
                            break;
                        }
                        if (option == 2) {
                            donorObject.setContactNo(contactNo);
                            donorUI.displayDonorDetail();
                            System.out.printf(" %-12s|  %-28s| %-16s| %-12s| %-14s\n", donorObject.getDonorId(), donorObject.getName(), donorObject.getContactNo(), donorObject.getCategory(), donorObject.getType());
                            System.out.println("============================================================================================\n");
                            break;
                        }
                        if (option == 3) {
                            donorObject.setCategory(category);
                            donorUI.displayDonorDetail();
                            System.out.printf(" %-12s|  %-28s| %-16s| %-12s| %-14s\n", donorObject.getDonorId(), donorObject.getName(), donorObject.getContactNo(), donorObject.getCategory(), donorObject.getType());
                            System.out.println("============================================================================================\n");
                            break;
                        }
                    }
                }
            } else {
                System.out.println("You canceled to update the donor details.");
            }

            exit = donorUI.inputExitPage();
        } while (exit == 0);

        return exit;
    }

    public void listDonor() {
        int option;

        do {
            option = donorUI.listMenu();
            switch (option) {
                case 1:
                    listWithDonation();
                    break;
                case 2:
                    filterDonor();
                    break;
                case 3:
                    listAllDonor();
                case 0:
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
        }
    }

    public void filterDonor() {
        int exit, option;
        String category, type, month;
        do {
            donorUI.filterDonorMenu();
            option = donorUI.filterMenu();
            
            if(option == 0){
                break;
            }else if (donor.isEmpty()) {
                System.out.println("Opps!! There is no donor in the list.");
            } else {
                Iterator<Donor> getDonor = donor.getIterator();
                while (getDonor.hasNext()) {
                    Donor donorObject = getDonor.next();
                    if (option == 1) {
                        do{
                            System.out.print("Enter the donor category that wanted to list: ");
                            category = scanner.nextLine();
                            if (!category.equals("public") && !category.equals("private") && !category.equals("government")) {
                                System.out.println("Please only input public, private or government.\n");
                            }else{
                                donorUI.displayDonorDetail();
                                filterByCategory(category);
                                System.out.println("=================================================================================================\n");
                            }
                        }while(!category.equals("public") && !category.equals("private") && !category.equals("government"));
                        
                        break;
                    }
                    if (option == 2) {
                        do{
                            System.out.print("Enter the donor type that wanted to list: ");
                            type = scanner.nextLine();
                            if(!type.equals("individual") && !type.equals("organization")){
                                System.out.println("Please only input individual or organization.\n");
                            }else{
                                donorUI.displayDonorDetail();
                                filterByType(type);
                                System.out.println("=================================================================================================\n");
                            }
                            
                        }while(!type.equals("individual") && !type.equals("organization"));
                        break;
                    }
                    if(option == 3){
                        do{
                            System.out.print("Enter the month that donor registered to list (eg. january) : ");
                            month = scanner.nextLine().toLowerCase();
                            if(!month.equals("january") && !month.equals("february") && !month.equals("march") && !month.equals("april") && !month.equals("may") && !month.equals("june") && !month.equals("july") && !month.equals("august") && !month.equals("september") && !month.equals("october") && !month.equals("november") && !month.equals("december")){
                                System.out.println("Please only input month with required format. (eg. january)\n");
                            }else{
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

    public void generateReport() {
        int exit;
        int january = 0, february = 0, march = 0, april = 0, may = 0, june = 0, july = 0, august = 0, september = 0, octobor = 0, november = 0, december = 0;
        
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
                } else if (donorObject.getDate().equals("octobor")) {
                    octobor++;
                } else if (donorObject.getDate().equals("november")) {
                    november++;
                } else if (donorObject.getDate().equals("december")) {
                    december++;
                }
            }

            int[] counts = {
                january, february, march, april, may, june, july, august, september, octobor, november, december
            };
            String[] months = {
                "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
            };

            donorUI.monthlyRegisterMenu(months, counts);
//                Iterator<Donor> getDonor = donor.getIterator();
//                Iterator<Donation> getDonation = donation.getIterator();
//                while (getDonor.hasNext()) {
//                    Donor donorObject = getDonor.next();
//
//                    while (getDonation.hasNext()) {
//                        Donation donationObject = getDonation.next();
//                        if (donationObject.getDonor().getCategory().equals("public")) {
//                            Iterator<DonatedItem> itDonatedItem = donationObject.getDonatedItems().getIterator();
//                            while (itDonatedItem.hasNext()) {
//                                DonatedItem item = itDonatedItem.next();
//                                if (item.getUnit().equals("KG")) {
//                                    publicTotalKG += item.getQuantity();
//                                } else if (item.getUnit().equals("L")) {
//                                    publicTotalL += item.getQuantity();
//                                } else if (item.getUnit().equals("RM")) {
//                                    publicTotalRM += item.getQuantity();
//                                }
//                            }
//                        }
//                        if (donationObject.getDonor().getCategory().equals("private")) {
//                            Iterator<DonatedItem> itDonatedItem = donationObject.getDonatedItems().getIterator();
//                            while (itDonatedItem.hasNext()) {
//                                DonatedItem item = itDonatedItem.next();
//                                if (item.getUnit().equals("KG")) {
//                                    privateTotalKG += item.getQuantity();
//                                } else if (item.getUnit().equals("L")) {
//                                    privateTotalL += item.getQuantity();
//                                } else if (item.getUnit().equals("RM")) {
//                                    privateTotalRM += item.getQuantity();
//                                }
//                            }
//                        }
//                        if (donationObject.getDonor().getCategory().equals("government")) {
//                            Iterator<DonatedItem> itDonatedItem = donationObject.getDonatedItems().getIterator();
//                            while (itDonatedItem.hasNext()) {
//                                DonatedItem item = itDonatedItem.next();
//                                if (item.getUnit().equals("KG")) {
//                                    govermentTotalKG += item.getQuantity();
//                                } else if (item.getUnit().equals("L")) {
//                                    governmentTotalL += item.getQuantity();
//                                } else if (item.getUnit().equals("RM")) {
//                                    governmentTotalRM += item.getQuantity();
//                                }
//                            }
//                        }
//                    }
//                    System.out.println("Public:\n" + "KG: " + publicTotalKG + "\n" + "L: " + publicTotalL + "\n" + "RM: " + publicTotalRM + "\n\n");
//                    System.out.println("Private:\n" + "KG: " + privateTotalKG + "\n" + "L: " + privateTotalL + "\n" + "RM: " + privateTotalRM + "\n\n");
//                    System.out.println("Government:\n" + "KG: " + govermentTotalKG + "\n" + "L: " + governmentTotalL + "\n" + "RM: " + governmentTotalRM + "\n\n");
//                    break;
//                }
        }
    }

    public void menu() {
        int option;

        do {
            option = donorUI.donorMenu();
            switch (option) {
                case 1:
                    addDonor();
                    break;

                case 2:
                    removeDonor();
                    break;
                case 3:
                    updateDonorDetails();
                    break;
                case 4:
                    searchDonor();
                    break;
                case 5:
                    listDonor();
                    break;
                case 6:
                    generateReport();
                    break;
                case 0:
                    System.out.println("Bye Bye. ^_^");
                    break;
                default:
                    System.out.println("Please enter a valid input!!\n");
                    break;
            }
        } while (option != 0);
    }
}
