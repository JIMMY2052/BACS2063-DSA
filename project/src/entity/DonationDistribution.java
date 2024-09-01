package entity;

import java.util.Date;
import adt.SortedListInterface;

public class DonationDistribution implements Comparable<DonationDistribution> {

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


    @Override
    public int compareTo(DonationDistribution other) {
        return Integer.compare(this.distributionId, other.distributionId);
    }

}
