
package entity;
import adt.ListInterface;
import java.util.Date;

/**
 *
 * @author JIMMY
 */
public class Donation {
   protected int donationId;
   protected Date donationDate;
   protected Donor donor;

    public Donation(int donationId, Donor donor) {
        this.donationId = donationId;
        this.donationDate = new Date();
        this.donor = donor;
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

   

   
}
