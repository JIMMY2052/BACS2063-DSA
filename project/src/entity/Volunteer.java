package entity;

/**
 *
 * @author KK
 */
import adt.SortedArrayList;
import adt.SortedListInterface;

public class Volunteer implements Comparable<Volunteer> {

    private int volunteerID;
    private String volunteerName;
    private char gender;
    private String phoneNumber;
    private SortedListInterface<Event> eventList;

    public Volunteer(int volunteerID, String volunteerName, char gender, String phoneNumber) {
        this.volunteerID = volunteerID;
        this.volunteerName = volunteerName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.eventList = new SortedArrayList<>();
    }

    public int getVolunteerID() {
        return volunteerID;
    }

    public void setVolunteerID(int volunteerID) {
        this.volunteerID = volunteerID;
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    // Get The Event
    public SortedListInterface<Event> getEventList(){
        return eventList;
    }

    @Override
    public int compareTo(Volunteer o) {
        return volunteerName.compareTo(o.volunteerName);
    }

}
