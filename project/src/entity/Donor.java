/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.SortedArrayList;
import adt.SortedListInterface;

/**
 *
 * @author JIMMY
 */
public class Donor {
    private String name;
    private String DonorId;
    private int contactNo;
    private String category;
    private String gender;
    private SortedListInterface<Donation> donorDonationList;
    private static int numberOfDonor = 1;

    public Donor(String name, int contactNo, String category, String gender) {
        this.name = name;
        this.DonorId = "DR" + numberOfDonor;
        this.contactNo = contactNo;
        this.category = category;
        this.gender = gender;
        this.donorDonationList = new SortedArrayList<Donation>();
        numberOfDonor++;
    }

    public Donor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getName() {
        return name;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getContactNo() {
        return contactNo;
    }

    public String getCategory() {
        return category;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDonorId(String DonorId) {
        this.DonorId = DonorId;
    }

    public String getDonorId() {
        return DonorId;
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
    
    
}
