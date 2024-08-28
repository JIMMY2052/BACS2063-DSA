package entity;

import java.util.Date;
import adt.SortedArrayList;
import adt.SortedListInterface;

/**
 *
 * @author JIMMY
 */
public class Donation implements Comparable<Donation> {

    private String donationId;
    private Donor donor;
    private SortedListInterface<DonatedItem> donatedItems;
    private Date date;
    private static int numberOfDonation = 0;

    public Donation(Donor donor) {
        numberOfDonation++;
        this.donor = donor;
        this.date = new Date();
        this.donatedItems = new SortedArrayList<>();
        this.donationId = String.format("K%03d", numberOfDonation);
    }

    public String getDonationId() {
        return donationId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static int getNumberOfDonation() {
        return numberOfDonation;
    }

    public static void setNumberOfDonation(int numberOfDonation) {
        Donation.numberOfDonation = numberOfDonation;
    }

    public void addDonatedItem(DonatedItem item) {
        this.donatedItems.add(item);
    }

    public void deleteDonatedItem(int index) {
        this.donatedItems.remove(index);
    }

    public SortedListInterface<DonatedItem> getDonatedItems() {
        return donatedItems;
    }

    @Override
    public String toString() {
        return "Donation ID: " + donationId + ", Donor: " + donor.getName() + ", Date: " + date + ", Items: " + donatedItems;
    }

    @Override
    public int compareTo(Donation o) {
        return this.donationId.compareTo(o.donationId);
    }

}
