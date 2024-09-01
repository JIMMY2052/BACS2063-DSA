package entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class DonationDistribution implements Comparable<DonationDistribution> {

    private String distributionId;
    private Donation donation;
    private String address;
    private Donee donee; 
    private Date date;
    private static int distributionCount = 0; 

    
    public DonationDistribution(Donation donation, String address, Donee donee) { 
        this.distributionId = generateDistributionId();
        this.donation = donation;
        this.address = address;
        this.donee = donee; 
        this.date = new Date(); 
    }

  
    private String generateDistributionId() {
     
        distributionCount++;
        return String.format("DS%03d", distributionCount);
    }

    // Getters and setters
    public String getDistributionId() {
        return distributionId;
    }

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Donee getDonee() {
        return donee;
    }

    public void setDonee(Donee donee) {
        this.donee = donee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Method to return formatted date as a String
    public String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");  // Includes time
        return sdf.format(date);
    }

    @Override
public String toString() {
    return String.format(
        "Distribution ID: %s | Donor: %s | Donation ID: %s | Address: %s | Donee: %s | Date: %s",
        distributionId,
        (donation != null && donation.getDonor() != null) ? donation.getDonor().getName() : "N/A",
        (donation != null) ? donation.getDonationId() : "N/A",
        (address != null && !address.isEmpty()) ? address : "N/A",
        (donee != null) ? donee.getDoneeName() : "N/A",
        getFormattedDate()
    );
}








    @Override
    public int compareTo(DonationDistribution o) {
        return this.distributionId.compareTo(o.distributionId);
    }
}
