package entity;

import java.text.SimpleDateFormat;
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
    private String category;
    private Date date;
    private static int numberOfDonation = 0;
    private String event;

    public Donation(Donor donor, String category, String event) {
        numberOfDonation++;
        this.donor = donor;
        this.date = new Date();
        this.category = category;
        this.donatedItems = new SortedArrayList<>();
        if (category.equals("F")) {
            this.donationId = String.format("F%03d", numberOfDonation);
        } else {
            this.donationId = String.format("C%03d", numberOfDonation);
        }
        this.event = event;
    }

    public Donation(Donor donor, String category, Date date) {
        numberOfDonation++;
        this.donor = donor;
        this.date = date;
        this.category = category;
        this.donatedItems = new SortedArrayList<>();
        if (category.equals("F")) {
            this.donationId = String.format("F%03d", numberOfDonation);
        } else {
            this.donationId = String.format("C%03d", numberOfDonation);
        }
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
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

    // Method to return formatted date as a String
    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        return sdf.format(date);
    }

    @Override
    public String toString() {
        return "Donation ID: " + donationId + ", Donor: " + donor.getName() + ", Date: " + getFormattedDate();
    }

    @Override
    public int compareTo(Donation o) {
        return this.donationId.substring(1).compareTo(o.donationId.substring(1));

    }
}
