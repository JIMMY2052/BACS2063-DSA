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
    private int DonorId;
    private SortedListInterface<Donation> donorDonationList;
    private static int numberOfDonor = 1;

    public Donor(String name) {
        this.name = name;
        this.DonorId = numberOfDonor;
        this.donorDonationList = new SortedArrayList<Donation>();
        numberOfDonor++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDonorId() {
        return DonorId;
    }

    public void setDonorId(int DonorId) {
        this.DonorId = DonorId;
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
