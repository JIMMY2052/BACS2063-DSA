package entity;
/**
 *
 * @author KK
 */
import adt.SortedArrayList;
import adt.SortedListInterface;

public class Event implements Comparable<Event> {

    private String eventID;
    private String eventName;
    private String eventDetail;
    private SortedListInterface<Volunteer> volunteerList;

    public Event(String eventID, String eventName, String eventDetail) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventDetail = eventDetail;
        this.volunteerList = new SortedArrayList<>();
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDetail() {
        return eventDetail;
    }

    public void setEventDetail(String eventDetail) {
        this.eventDetail = eventDetail;
    }
    
    // To Add Volunteer Into Event
    public boolean addVolunteer(Volunteer vlounteer) {
        return this.volunteerList.add(vlounteer);
    }
    
    // Check The Volunteer Is Already In Event
    public boolean addVolunteers(Volunteer volunteer) {
    if (volunteerList.search(volunteer) == -1) { // If Not Found Volunteer In The Event Add Into Event
        return volunteerList.add(volunteer);
    }
    return false;
}

    public SortedListInterface<Volunteer> getVolunteerList() {
        return volunteerList;
    }

    @Override
    public int compareTo(Event o) {
        return eventID.compareTo(o.eventID);
    }

}
