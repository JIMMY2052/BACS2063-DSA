/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;
import adt.SortedListInterface;

/**
 *
 * @author JIMMY
 */
public class DonationDistribution {

    private int distributionId;
    private Date date;
    private SortedListInterface<Donation> donations;
    private SortedListInterface<Donee> donees;

    public DonationDistribution(int distributionId, Date date, SortedListInterface<Donation> donations, SortedListInterface<Donee> donees) {
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

    public SortedListInterface<Donation> getDonations() {
        return donations;
    }

    public void setDonations(SortedListInterface<Donation> donations) {
        this.donations = donations;
    }

    public SortedListInterface<Donee> getDonees() {
        return donees;
    }

    public void setDonees(SortedListInterface<Donee> donees) {
        this.donees = donees;
    }

}
