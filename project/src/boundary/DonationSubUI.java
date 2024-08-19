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
public class DonationSubUI {
    Scanner sc = new Scanner(System.in);
    
    public int displayMenu(){
        System.out.println("1. Make A Donation");
        System.out.println("2. View Donations");
        System.out.println("3. Search Donation");
        System.out.println("4. Delete Donation");
        System.out.println("5. Update Donation");
        System.out.println("6. Generate Summary Report");
        System.out.println("0. Return Back to Previous Page");
        
        System.out.println("Enter your choice -> ");
        int opt = sc.nextInt();
        return opt;
    }
    
    public int addDonationMenu(){
        System.out.println("1. Donate Food");
        System.out.println("2. Donate Cash");
        System.out.println("0. Return Back to Previous Page");
        
        System.out.println("Enter your choice -> ");
        int opt = sc.nextInt();
        return opt;
    }
    
    public void donateFoodUI(){
        String donorId;
        String foodName;
        int qty;
        
        System.out.print("Enter Donor ID: ");
        donorId = sc.nextLine();
        System.out.println("Enter Food Name: ");
        foodName = sc.nextLine();
        System.out.println("Enter Quatinty: ");
        qty = sc.nextInt();
        
        
        
    }
    
    
}
