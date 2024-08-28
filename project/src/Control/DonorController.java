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
import java.util.Scanner;
/**
 *
 * @author User
 */
public class DonorController {
    private DonorUI donorUI = new DonorUI();
    private SortedListInterface<Donation> donationList = new SortedArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    
    private String name = "";
    private int contactNo = 0;
    private String category = "";
    private String gender = "";
    
    
    public void addDonor() {
        donorUI.addDonorMenu();
        System.out.print("Enter a new donor name: ");
        name = scanner.nextLine();
        System.out.println("Enter new donor's contact number: ");
        contactNo = scanner.nextInt();
        System.out.println("Enter new donor in what category: ");
        category = scanner.nextLine();
        System.out.println("Enter new donor's gender: ");
        gender = scanner.nextLine();
        
        Donor donor = new Donor(name, contactNo, category, gender);
        arrayList.add(donor);
    }
    
    public void menu() {
        int option = 0;
          
        do{
            donorUI.donorMenu();
            System.out.print("Enter your option (0 - 8): ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch(option) {
                case 1:
                    addDonor();
                    break;
                default:
                    System.out.println("Please enter a valid input!!\n");
            }
        }while(option >= 0 && option <= 8);
    }
    
    public static void main(String[] args) {
        DonorController d = new DonorController();
        
        d.menu();
    }
}
