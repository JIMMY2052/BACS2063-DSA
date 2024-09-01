package entity;

import java.time.LocalDateTime;

public class Donee implements Comparable<Donee> {
    private final String doneeId;
    private String doneeName;
    private String doneeContactNo; 
    private LocalDateTime dateAdded;
    private static int numberOfDonees = 0;


    public Donee(String doneeName, String doneeContactNo) {
        numberOfDonees++;
        this.doneeId = String.format("E%03d", numberOfDonees);
        this.doneeName = doneeName;
        this.doneeContactNo = doneeContactNo; 
        this.dateAdded = LocalDateTime.now();
        
    }

    // Getter and Setter methods
    public String getDoneeId() {
        return doneeId;
    }

    public String getDoneeName() {
        return doneeName;
    }

    public void setDoneeName(String doneeName) {
        this.doneeName = doneeName;
    }

    public String getDoneeContactNo() {
        return doneeContactNo;  
    }

    public void setDoneeContactNo(String doneeContactNo) {  
        this.doneeContactNo = doneeContactNo;
    }

    public int totalNumberOfObject() {
        return numberOfDonees;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public int compareTo(Donee other) {
        return this.doneeId.compareTo(other.doneeId);
    }


    @Override
    public String toString() {
        return String.format("Donee[doneeId=%04d, doneeName=%s, doneeContactNo=%s]", doneeId, doneeName, doneeContactNo);
    }
}
