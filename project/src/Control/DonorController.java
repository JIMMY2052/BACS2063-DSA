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
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class DonorController {

    private DonorUI donorUI = new DonorUI();
    SortedListInterface<Donor> donor = new SortedArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public int addDonor() {
        int exit = 0;
        boolean isSuccess = false;

        do {
            donorUI.addDonorMenu();
            String name = donorUI.inputDonorName();
            if (name.equals("0")) {
                return exit;
            }
            String contactNo = donorUI.inputDonorContactNo();
            if (contactNo.equals("0")) {
                return exit;
            }
            String category = donorUI.inputDonorCategory();
            if (category.equals("0")) {
                return exit;
            }
            String gender = donorUI.inputDonorGender();
            if (gender.equals("0")) {
                return exit;
            }

            if (donorUI.inputConfirmation("add a new donor")) {
                Donor dr = new Donor(name, contactNo, category, gender);
                isSuccess = donor.add(dr);
                System.out.println("You have successfully added a new donor!!");
            } else {
                System.out.println("You canceled to add a new donor!!");
            }
        } while (!isSuccess);

        return exit;
    }

    public int searchDonor() {
        int exit, found = 0;
        String id;
        
        do{
            donorUI.searchDonorMenu();
            id = donorUI.inputDonorId();
            if(id.equals("0")){
                return exit = 0;
            }
            Iterator<Donor> getDonor = donor.getIterator();
            while(getDonor.hasNext()){
                Donor donorObject = getDonor.next();
                if(donorObject.getDonorId().equals(id)){
                    ++found;
                    System.out.println(donorObject);
                    break;
                }
            }
            if(found == 0){
                System.out.println("There is no details for this donor.");
            }
            
            exit = donorUI.inputExitPage();
        }while(exit == 0);
        
            
        
        return exit;
    }

    public int removeDonor() {
        int exit = 0;
        String id;
        boolean isSuccess = false;
        
        do{
            donorUI.removeDonorMenu();
            id = donorUI.inputDonorId();
            if(id.equals("0")){
                return exit;
            }
            if(donorUI.inputConfirmation("remove this donor") == true){
                Iterator<Donor> getDonor = donor.getIterator();
                while(getDonor.hasNext()){
                    Donor donorObject = getDonor.next();
                    if(donorObject.getDonorId().equals(id)){
                        donor.remove(donorObject);
                        isSuccess = true;
                        break;
                    }
                }
                if(isSuccess == true){
                    System.out.println("Remove the donor successfully.");
                }else {
                    System.out.println("The donor id entered is not in the list.");
                }
            }else{
                System.out.println("Your canceled to remove the donor.");
            }
            
            exit = donorUI.inputExitPage();
        }while(exit == 0);
        return exit;
    }

    public int updateDonorDetails() {
        int exit, option, count;
        String id, name = "", contactNo = "", category = "", gender = "";
        boolean isSuccess = false;
        
        do{
            donorUI.updateDonorMenu();
            id = donorUI.inputDonorId();
            if(id.equals("0")){
                return exit = 1;
            }
            option = donorUI.updateMenu();
            if(option == 0){
                return exit = 1;
            }
            switch(option){
                case 1: {
                    name = donorUI.inputDonorName();
                    if(name.equals("0")){
                        return exit = 1;
                    }
                    break;
                }
                case 2: {
                    contactNo = donorUI.inputDonorContactNo();
                    if(contactNo.equals("0")){
                        return exit = 1;
                    }
                    break;
                }
                case 3: {
                    category = donorUI.inputDonorCategory();
                    if(category.equals("0")){
                        return exit = 1;
                    }
                    break;
                }
                case 4: {
                    gender = donorUI.inputDonorGender();
                    if(gender.equals("0")){
                        return exit = 1;
                    }
                    break;
                }
                    
            }
            if(donorUI.inputConfirmation("update the donor detail")){
                Iterator<Donor> getDonor = donor.getIterator();
                count = 0;
                while(getDonor.hasNext()){
                    count++;
                    Donor donorObject = getDonor.next();
                    if(donorObject.getDonorId().equals(id)){
                        if(option == 1){
                            donorObject.setName(name);
                            break;
                        }
                        if(option == 2){
                            donorObject.setContactNo(contactNo);
                            break;
                        }
                        if(option == 3){
                            donorObject.setCategory(category);
                            break;
                        }
                        if(option == 4){
                            donorObject.setGender(gender);
                            break;
                        }
                    }
                }
                if(isSuccess){
                    System.out.println("You updated the donor detail successfully!!");
                }else{
                    System.out.println("Opps!! You updated the donor detail unsuccessfully.");
                }
            }else{
                System.out.println("You canceled to update the donor details.");
            }
            
            exit = donorUI.inputExitPage();
        }while(exit == 0);
        
        return exit;
    }
    
    public void filterDonor(){
        int exit, option;
        String category, gender;
        do {
            donorUI.filterDonorMenu();
            option = donorUI.filterMenu();
            
            if(donor.isEmpty()){
                System.out.println("Opps!! There is no donor in the list.");
            }else{
                Iterator<Donor> getDonor = donor.getIterator();
                while(getDonor.hasNext()){
                    Donor donorObject = getDonor.next();
                    if(option == 1){
                        System.out.print("Enter the donor category that wanted to list: ");
                        category = scanner.nextLine();
                        SortedListInterface<Donor> filterByCategory = filterByCategory(category);
                        Iterator<Donor> itDonor = filterByCategory.getIterator();
                        while (itDonor.hasNext()) {
                             System.out.println(itDonor.next().toString());
                        }
                        break;
                    }
                    if(option == 2){
                        System.out.print("Enter the donor gender that wanted to list: ");
                        gender = scanner.nextLine();
                        SortedListInterface<Donor> filterByGender = filterByGender(gender);
                        Iterator<Donor> itDonor = filterByGender.getIterator();
                        while (itDonor.hasNext()) {
                             System.out.println(itDonor.next().toString());
                        }
                        break;
                    }
                }
            }
                
            exit = donorUI.inputExitPage();
        }while(exit == 0);
    }
    
    private SortedListInterface<Donor> filterByCategory(String category){
        SortedListInterface<Donor> result = new SortedArrayList<>();
        
        Iterator<Donor> itDonor = donor.getIterator();
        while (itDonor.hasNext()) {
            Donor tempDonor = itDonor.next();
            if(tempDonor.getCategory().equals(category)) {
                result.add(tempDonor);
            }
        }
        return result;
    }
    
    private SortedListInterface<Donor> filterByGender(String gender){
        SortedListInterface<Donor> result = new SortedArrayList<>();
        
        Iterator<Donor> itDonor = donor.getIterator();
        while (itDonor.hasNext()) {
            Donor tempDonor = itDonor.next();
            if(tempDonor.getGender().equals(gender)) {
                result.add(tempDonor);
            }
        }
        return result;
    }

    public void menu() {
        int option, exit;
        
        donor.add(new Donor("Alice", "9999999999", "public", "female"));
        donor.add(new Donor("Jack", "8888888888", "public", "male"));
        donor.add(new Donor("John", "7777777777", "private", "male"));
        donor.add(new Donor("Janice", "6666666666", "private", "female"));
        do {
            exit = 0;
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
                case 6:
                    filterDonor();
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

    public static void main(String[] args) {
        DonorController d = new DonorController();
        
        d.menu();
        
    }
}
