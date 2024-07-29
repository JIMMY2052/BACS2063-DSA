
package entity;

import java.util.Date;

/**
 *
 * @author JIMMY
 */
public class Donation {

    private int numberOfEntries = 1;
    private String donationID;
    private String donationType;
    private Date donateDate;

    public Donation(String donationType, Date donateDate) {
        this.donationID = "D" + numberOfEntries;
        numberOfEntries++;
        this.donationType = donationType;
        this.donateDate = donateDate;
    }

    public String getDonationID() {
        return donationID;
    }

    public void setDonationID(String donationID) {
        this.donationID = donationID;
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public Date getDonateDate() {
        return donateDate;
    }

    public void setDonateDate(Date donateDate) {
        this.donateDate = donateDate;
    }

}
