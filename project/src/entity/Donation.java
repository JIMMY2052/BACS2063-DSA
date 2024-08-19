package entity;

import java.util.Date;
import adt.SortedArrayList;
import adt.SortedListInterface;

/**
 *
 * @author JIMMY
 */
public class Donation {

    private int donationId; 
    private Donor donor;
    private SortedListInterface<DonatedItem> donatedItems;
    private Date date;
    private static int numberOfDonation = 0;

    // Constructors, Getters, Setters
    public Donation(Donor donor) {
        numberOfDonation++;
        this.donationId = numberOfDonation;
        this.donor = donor;
        this.date = new Date();
        this.donatedItems = new SortedArrayList<>();
    }

    public void addDonatedItem(DonatedItem item) {
        this.donatedItems.add(item);
    }

    public SortedListInterface<DonatedItem> getDonatedItems() {
        return donatedItems;
    }

    @Override
    public String toString() {
        return "Donation ID: " + donationId + ", Donor: " + donor.getName() + ", Date: " + date + ", Items: " + donatedItems;
    }
    
}
