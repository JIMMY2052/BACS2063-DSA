package control;

/**
 *
 * @author KK
 */
import adt.*;
import boundary.*;
import entity.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import utility.MessageUI;

public class EventCtrl {

    Scanner scanner = new Scanner(System.in);
    EventUI eventUI;
    private SortedListInterface<Event> eventList;
    private SortedListInterface<Volunteer> volunteerList;

    public EventCtrl(SortedListInterface<Volunteer> volunteer, SortedListInterface<Event> event) {
        this.volunteerList = volunteer;
        this.eventList = event;

    }

    public void runEventCtrl() {
        boolean running = true;
        eventUI = new EventUI();
        while (running) {
            String choice = eventUI.getEventMenu();

            switch (choice) {
                case "1":
                    displayAllVolunteersByEvents();
                    break;
                case "2":
                    listAllVolunteersByID();
                    break;
                case "3":
                    displayAllFilterGender();
                    break;
                case "4":
                    searchVolunteerByID();
                    break;
                case "5":
                    deleteVolunteerByID();
                    break;
                case "6":
                    addVolunteer();
                    break;
                case "7":
                    displayAllEvents();
                    break;
                case "8":
                    displayAllEventofVolunteerNumber();
                    break;
                case "9":
                    addEvent();
                    break;
                case "10":
                    addVolunteerToEvent();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        }
        clearScreen();
    }

    public void displayAllVolunteersByEvents() {
        for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
            Event event = eventList.getEntry(i);
            System.out.println("\nEvent ID: " + event.getEventID() + ", Event Name: " + event.getEventName());
            eventUI.listAllVolunteerHeader();

            SortedListInterface<Volunteer> eventVolunteerList = event.getVolunteerList();
            int numberOfVolunteers = eventVolunteerList.getNumberOfEntries();

            if (numberOfVolunteers == 0) {
                System.out.println("This Event Has No Volunteer.");
            } else {
                for (int j = 0; j < numberOfVolunteers; j++) {
                    Volunteer volunteer = eventVolunteerList.getEntry(j);
                    eventUI.listAllVolunteerDetail(volunteer);
                }
            }
        }
    }

    public void displayAllEventofVolunteerNumber() {
        int totalVolunteer = volunteerList.getNumberOfEntries();
        int totalJoinedVolunteer = 0;

        eventUI.displayAllEventofVolunteerNumberHeader();
        boolean[] findAllJoinedVolunteer = new boolean[totalVolunteer];

        for (int i = 0; i < totalVolunteer; i++) {
            findAllJoinedVolunteer[i] = false;
        }

        for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
            Event event = eventList.getEntry(i);
            int numberOfVolunteers = event.getVolunteerList().getNumberOfEntries();

            System.out.printf("|%-10s|%-20s|%-22d|\n", event.getEventID(), event.getEventName(), numberOfVolunteers);

            for (int j = 0; j < numberOfVolunteers; j++) {
                Volunteer volunteer = event.getVolunteerList().getEntry(j);
                int volunteerID = volunteer.getVolunteerID();

                int index = findVolunteerIndex(volunteerID);
                if (index != -1) {
                    findAllJoinedVolunteer[index] = true;
                }
            }
        }

        for (boolean hasJoined : findAllJoinedVolunteer) {
            if (hasJoined) {
                totalJoinedVolunteer++;
            }
        }

        int totalNotJoinVolunteer = totalVolunteer - totalJoinedVolunteer;

        System.out.println("--------------------------------------------------------");
        System.out.printf("Number of Not Join Event Volunteer: %-3d\n", totalNotJoinVolunteer);
    }

    private int findVolunteerIndex(int volunteerID) {
        for (int i = 0; i < volunteerList.getNumberOfEntries(); i++) {
            Volunteer volunteer = volunteerList.getEntry(i);
            if (volunteer.getVolunteerID() == volunteerID) {
                return i;
            }
        }
        return -1;
    }

    public void addVolunteer() {
        listAllVolunteersByID();
        Volunteer newVolunteer = inputAddVolunteer();
        volunteerList.add(newVolunteer);
        System.out.println("Add Successful.");
    }

    public Volunteer inputAddVolunteer() {
        int id = generateVolunteerID();
        String name = eventUI.inputName();
        char gender = eventUI.inputGender();
        String phoneNumber = eventUI.inputPhoneNumber();
        Volunteer volunteer = new Volunteer(id, name, gender, phoneNumber);
        char choice = eventUI.messageEventChoice();

        if (choice == 'Y') {
            boolean continueLoop = false;
            boolean messagePop = true;
            while (!continueLoop) {
                displayAllEventofVolunteerNumber();
                String eventID = eventUI.inputEventID();
                boolean found = false;

                for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
                    Event event = eventList.getEntry(i);

                    if (eventID.equals(event.getEventID())) {
                        boolean existedAdd = false;
                        SortedListInterface<Volunteer> findvolunteers = event.getVolunteerList();
                        for (int j = 0; j < findvolunteers.getNumberOfEntries(); j++) {
                            Volunteer existingVolunteer = findvolunteers.getEntry(j);
                            if (existingVolunteer.getVolunteerID() == volunteer.getVolunteerID()) {
                                existedAdd = true;
                                break;
                            }
                        }
                        if (!existedAdd) {
                            event.addVolunteer(volunteer);
                            found = true;
                            messagePop = true;
                            break;
                        } else {
                            System.out.println("\nVolunteer is Already Registered For This Event.");
                            found = true;
                            messagePop = false;
                            break;
                        }
                    }
                }
                if (!found) {
                    System.out.println("Event ID Not Found. Please try again.");
                    messagePop = false;
                }

                if (messagePop) {
                    continueLoop = eventUI.joinMoreEvent();
                }
            }
        }

        clearScreen();
        return volunteer;
    }

    public int generateVolunteerID() {
        int nextVolunteerID = 10001;

        int latestVolunteerID = nextVolunteerID;
        for (int i = 0; i < volunteerList.getNumberOfEntries(); i++) {
            Volunteer volunteer = volunteerList.getEntry(i);
            int volunterID = volunteer.getVolunteerID();
            if (volunterID > latestVolunteerID) {
                latestVolunteerID = volunterID;
            }
        }
        int volunterID = latestVolunteerID + 1;
        return volunterID;
    }

    public void listAllVolunteersByID() {
        eventUI.listAllVolunteerHeader();
        int id = 10001;
        while (true) {
            boolean found = false;
            for (int i = 0; i < volunteerList.getNumberOfEntries(); i++) {
                Volunteer volunteer = volunteerList.getEntry(i);
                if (volunteer.getVolunteerID() == id) {
                    eventUI.listAllVolunteerDetail(volunteer);
                    found = true;
                }
            }
            if (!found) {
                break;
            }
            id++;
        }
    }

    public void displayAllEvents() {
        eventUI.displayAllEventsHeader();
        for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
            Event event = eventList.getEntry(i);
            eventUI.displayAllEventsDetail(event);
        }
    }

    public void displayAllFilterGender() {
        char gender = eventUI.inputGender();
        eventUI.listAllVolunteerHeader();
        for (int i = 0; i < volunteerList.getNumberOfEntries(); i++) {
            Volunteer volunteer = volunteerList.getEntry(i);
            if (volunteer.getGender() == gender) {
                eventUI.listAllVolunteerDetail(volunteer);
            }
        }
    }

    public void searchVolunteerByID() {
        boolean done = false;
        do {
            int searchID = eventUI.inputSearch();
            Volunteer foundVolunteer = null;

            for (int i = 0; i < volunteerList.getNumberOfEntries(); i++) {
                Volunteer searchVolunteer = volunteerList.getEntry(i);
                if (searchVolunteer.getVolunteerID() == searchID) {
                    foundVolunteer = searchVolunteer;
                    break;
                }
            }

            if (foundVolunteer != null) {
                eventUI.detailofVolunteerHeader();
                eventUI.listAllVolunteerDetail(foundVolunteer);

                boolean join = false;
                System.out.println("\nEvent Joined:");

                for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
                    Event event = eventList.getEntry(i);
                    if (event.getVolunteerList().search(foundVolunteer) != -1) {
                        System.out.println("Event ID: " + event.getEventID() + ", Event Name: " + event.getEventName());
                        join = true;
                    }
                }

                if (!join) {
                    System.out.println("This Volunteer Not Joined Any Event.");
                }

                done = true;
            } else {
                System.out.println("Volunteer ID Not found. Please Try Again.");
            }

        } while (!done);
    }

    public void deleteVolunteerByID() {
        boolean done = false;
        int deleteID = -1;
        Volunteer foundVolunteer = null;

        do {
            int removeID = eventUI.inputDelete();

            for (int i = 0; i < volunteerList.getNumberOfEntries(); i++) {
                Volunteer removeVolunteer = volunteerList.getEntry(i);
                if (removeVolunteer.getVolunteerID() == removeID) {
                    foundVolunteer = removeVolunteer;
                    deleteID = i;
                    break;
                }
            }

            if (foundVolunteer != null) {
                eventUI.detailofVolunteerHeader();
                eventUI.listAllVolunteerDetail(foundVolunteer);

                boolean join = false;
                System.out.println("\nEvent Joined:");

                for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
                    Event event = eventList.getEntry(i);
                    if (event.getVolunteerList().search(foundVolunteer) != -1) {
                        System.out.println("Event ID: " + event.getEventID() + ", Event Name: " + event.getEventName());
                        join = true;
                    }
                }

                if (!join) {
                    System.out.println("This Volunteer Not Joined Any Event.");
                }

                done = true;
            } else {
                System.out.println("Volunteer ID Not found. Please Try Again.");
            }

        } while (!done);

        boolean confirm = eventUI.confirmDelete();
        if (confirm == true) {

            if (deleteID != -1) {
                volunteerList.remove(deleteID);
            }

            for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
                Event event = eventList.getEntry(i);
                int position = event.getVolunteerList().search(foundVolunteer);
                if (position != -1) {
                    event.getVolunteerList().remove(position);
                }
            }

            System.out.println("Delete Successful.");
        } else {
            System.out.println("Delete Cancel.");
        }
    }

    public void addEvent() {
        displayAllEvents();
        Event newEvent = inputAddEvent();
        eventList.add(newEvent);
        System.out.println("Add Successful.");
    }

    public Event inputAddEvent() {
        boolean found = false;
        String eventID = " ";
        do {

            found = false;
            eventID = eventUI.inputEventID();
            for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
                Event event = eventList.getEntry(i);
                if (eventID.equals(event.getEventID())) {
                    found = true;
                    break;
                }
            }
            if (found == true) {
                System.out.println("Event ID Already Exists. Please Try Again.");
            }
        } while (found == true);

        String eventName = eventUI.inputName();
        String eventDetail = eventUI.inputEventDetail();
        Event event = new Event(eventID, eventName, eventDetail);
        clearScreen();
        return event;
    }

    public void addVolunteerToEvent() {
        displayAllEvents();

        boolean found = false;
        String eventID = " ";
        Event selectedEvent = null;

        do {
            eventID = eventUI.inputEventID();
            for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
                Event event = eventList.getEntry(i);
                if (eventID.equals(event.getEventID())) {
                    selectedEvent = event;
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Event ID Not Found. Please Try Again.");
            }
        } while (!found);
        displayAllEventofVolunteerNumber();
        int quantity = eventUI.inputQuantity(volunteerList.getNumberOfEntries());
        int addedCount = 0;

        for (int j = 0; j < quantity; j++) {
            boolean added = false;

            for (int i = 0; i < volunteerList.getNumberOfEntries(); i++) {
                Volunteer volunteer = volunteerList.getEntry(i);
                if (volunteer == null) {
                    continue;
                }
                if (volunteer.getEventList() != null && volunteer.getEventList().search(selectedEvent) == -1) {
                    if (selectedEvent.addVolunteers(volunteer)) {
                        volunteer.getEventList().add(selectedEvent);
                        addedCount++;
                        added = true;
                        break;
                    }
                }
            }

            if (!added) {
                System.out.println("Not Enough Available Volunteers To Add.");
                break;
            }
        }

        System.out.println("Total Volunteers Added: " + addedCount);
    }

    public void clearScreen() {
        try {
            Robot rob = new Robot();
            try {
                rob.keyPress(KeyEvent.VK_CONTROL); // press "CTRL"
                rob.keyPress(KeyEvent.VK_L); // press "L"
                rob.keyRelease(KeyEvent.VK_L); // unpress "L"
                rob.keyRelease(KeyEvent.VK_CONTROL); // unpress "CTRL"
                Thread.sleep(50); // add delay in milisecond, if not there will automatically stop after clear
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
