
package entity;
import adt.ListInterface;
import java.util.Date;

/**
 *
 * @author JIMMY
 */
public class Donation {
   private int donationId;
   private Date donationDate;
   private Donor donor;
   private Donee donee;

    public Donation(int donationId, Donor donor, Donee donee) {
        this.donationId = donationId;
        this.donationDate = new Date();
        this.donor = donor;
        this.donee = donee;
    }

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
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
   

   
}
