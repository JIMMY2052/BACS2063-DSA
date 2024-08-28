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

    public void updateDonorDetails() {

    }

    public void menu() {
        int option, exit;
        do {
            exit = 0;
            option = donorUI.donorMenu();
            switch (option) {
                case 1:
                    exit = addDonor();
                    break;

                case 2:
                    removeDonor();
                    break;
                case 3:
                    updateDonorDetails();
                    break;
                case 4:
                    exit = searchDonor();
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
