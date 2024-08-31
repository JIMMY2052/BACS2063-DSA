/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.SortedArrayList;
import adt.SortedListInterface;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author JIMMY
 */
public class Donor implements Comparable<Donor> {

    private String name;
    private String contactNo;
    private String category;
    private String type;
    private String gender;
    private String donorId;
    private String date;

    private SortedListInterface<Donation> donorDonationList;
    private static int numberOfDonor = 0;
    LocalDate currentDate = LocalDate.now();
    
    public Donor(String name, String contactNo, String category, String type) {
        Month date = currentDate.getMonth();
        
        this.name = name;
        this.contactNo = contactNo;
        this.category = category;
        this.type = type;
        this.date = date.toString();
        this.donorDonationList = new SortedArrayList<Donation>();
        numberOfDonor++;
        this.donorId = String.format("D%03d", numberOfDonor);
    }
    
    public Donor(String name, String contactNo, String category, String type, String date) {
        this.name = name;
        this.contactNo = contactNo;
        this.category = category;
        this.type = type;
        this.date = date;
        this.donorDonationList = new SortedArrayList<Donation>();
        numberOfDonor++;
        this.donorId = String.format("D%03d", numberOfDonor);
    }

    public Donor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public static int getNumberOfDonor() {
        return numberOfDonor;
    }

    public static void setNumberOfDonor(int numberOfDonor) {
        Donor.numberOfDonor = numberOfDonor;
    }

    public SortedListInterface<Donation> getDonorDonationList() {
        return donorDonationList;
    }

    public void setDonorDonationList(SortedListInterface<Donation> donorDonationList) {
        this.donorDonationList = donorDonationList;
    }
    
    public void addDonation(Donation donation){
        donorDonationList.add(donation);
    }

    @Override
    public int compareTo(Donor o) {
        return donorId.compareTo(o.donorId);
    }

    @Override
    public String toString() {
        return "\ndonorId = " + donorId + "\nname = " + name + "\ncontactNo = " + contactNo + "\ncategory = " + category + "\ntype = " + type + "\ndate = " + date;
    }

}
