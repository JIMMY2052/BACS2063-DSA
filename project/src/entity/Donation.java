
package entity;
import java.util.Date;

/**
 *
 * @author JIMMY
 */
public class Donation {
    
   private int donationId;
   private Donor donor;
   private Donee donee;
   private Date donationDate;
   
    public Donation(int donationId, Donor donor, Donee donee, Date donationDate) {
        this.donationId = donationId;
        this.donor = donor;
        this.donee = donee;
        this.donationDate = donationDate;
    }

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public Donee getDonee() {
        return donee;
    }

    public void setDonee(Donee donee) {
        this.donee = donee;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }
   
   
   
   
}
