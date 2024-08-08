/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ListInterface;
import java.util.Date;

/**
 *
 * @author JIMMY
 */
public class DonationDistribution {
    private int distributionId;
    private Date date;
    private ListInterface<Donation> donations;
    private ListInterface<Donee> donees;

    public DonationDistribution(int distributionId, Date date, ListInterface<Donation> donations, ListInterface<Donee> donees) {
        this.distributionId = distributionId;
        this.date = date;
        this.donations = donations;
        this.donees = donees;
    }

    public int getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(int distributionId) {
        this.distributionId = distributionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ListInterface<Donation> getDonations() {
        return donations;
    }

    public void setDonations(ListInterface<Donation> donations) {
        this.donations = donations;
    }

    public ListInterface<Donee> getDonees() {
        return donees;
    }

    public void setDonees(ListInterface<Donee> donees) {
        this.donees = donees;
    }
    
    
    
}
