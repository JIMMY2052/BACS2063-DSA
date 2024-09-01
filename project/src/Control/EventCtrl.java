package Control;

/**
 *
 * @author KK
 */
import adt.*;
import boundary.*;
import entity.*;
import java.util.Scanner;
import utility.MessageUI;

public class EventCtrl {

    Scanner scanner = new Scanner(System.in);
    private EventUI eventUI;
    private SortedListInterface<Event> eventList = new SortedArrayList<>();
    private SortedListInterface<Volunteer> volunteerList = new SortedArrayList<>();

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
                    updateVolunteer();
                    break;
                case "8":
                    displayAllEvents();
                    break;
                case "9":
                    displayAllEventofVolunteerNumber();
                    break;
                case "10":
                    addEvent();
                    break;
                case "11":
                    addVolunteerToEvent();
                    break;
                case "12":
                    searchEvent();
                    break;
                case "13":
                    editEvent();
                    break;
                case "14":
                    deleteEvent();
                    break;
                case "15":
                    removeEventFromVolunteer();
                    break;
                case "16":
                    displayUnjoinVolunteer();
                    break;
                case "17":
                    volunteerReport();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        }
        MainSystemUI.clearScreen();
    }

    //-------------------- Display --------------------------
    public void displayAllVolunteersByEvents() {
        for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
            Event event = eventList.getEntry(i);
            System.out.println("\nEvent ID: " + event.getEventID() + ", Event Name: " + event.getEventName());
            eventUI.listAllVolunteerHeader();

            SortedListInterface<Volunteer> eventVolunteerList = event.getVolunteerList();
            int numberOfVolunteers = eventVolunteerList.getNumberOfEntries();

            if (numberOfVolunteers == 0) {
                System.out.println("This Event Has No Volunteer.\n");
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
        int totalJoinVolunteer = 0;

        eventUI.displayAllEventofVolunteerNumberHeader();
        boolean[] findAllJoinVolunteer = new boolean[totalVolunteer];

        for (int i = 0; i < totalVolunteer; i++) {
            findAllJoinVolunteer[i] = false;
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
                    findAllJoinVolunteer[index] = true;
                }
            }
        }

        for (boolean join : findAllJoinVolunteer) {
            if (join) {
                totalJoinVolunteer++;
            }
        }

        int totalNotJoinVolunteer = totalVolunteer - totalJoinVolunteer;

        System.out.println("--------------------------------------------------------");
        System.out.printf("Number of Not Join Event Volunteer: %-3d\n", totalNotJoinVolunteer);
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

    public void displayAllEvents() {
        eventUI.displayAllEventsHeader();
        for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
            Event event = eventList.getEntry(i);
            eventUI.displayAllEventsDetail(event);
        }
    }

    public void displayUnjoinVolunteer() {
        int l = 0;
        boolean message = false;
        eventUI.listAllVolunteerHeader();

        for (int i = 0; i < volunteerList.getNumberOfEntries(); i++) {
            Volunteer volunteer = volunteerList.getEntry(i);

            boolean hasJoin = false;

            for (int j = 0; j < eventList.getNumberOfEntries(); j++) {
                Event event = eventList.getEntry(j);
                if (event.getVolunteerList().search(volunteer) != -1) {
                    hasJoin = true;
                    break;
                }
            }

            if (!hasJoin) {
                eventUI.listAllVolunteerDetail(volunteer);
                l++;
                message = true;
            }
        }

        if (message == true) {
            System.out.printf("\nNumber of Not Join Event Volunteer: %-3d", l);
        }

        if (!message) {
            System.out.println("\nAll Volunteer Have Join At Least One Event.");
        }

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

    public void searchVolunteerByID() {
        boolean done = false;

        // Do While For Find Exist Volunteer
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

            // If Found The Volunteer
            if (foundVolunteer != null) {
                eventUI.detailofVolunteerHeader();
                eventUI.listAllVolunteerDetail(foundVolunteer);

                boolean join = false;
                System.out.println("\nEvent Join:");

                // For Loop For Find The Volunteer Have Join What Event
                for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
                    Event event = eventList.getEntry(i);
                    if (event.getVolunteerList().search(foundVolunteer) != -1) {
                        System.out.println("Event ID: " + event.getEventID() + ", Event Name: " + event.getEventName());
                        join = true;
                    }
                }

                if (!join) {
                    System.out.println("This Volunteer Not Join Any Event.");
                }

                done = true;
            } else {
                System.err.println("Volunteer ID Not found. Please Try Again.");
            }

        } while (!done);
    }

    public void searchEvent() {
        boolean found = false;
        do {
            String inputEventID = eventUI.inputSearchEvent();
            for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
                Event event = eventList.getEntry(i);
                if (inputEventID.equals(event.getEventID())) {
                    int numberOfVolunteers = event.getVolunteerList().getNumberOfEntries();
                    eventUI.displayAllEventofDetail();
                    System.out.printf("%-10s %-20s %-21d %-100s\n", event.getEventID(), event.getEventName(), numberOfVolunteers, event.getEventDetail());
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.err.println("Event ID Not Found. Please try again.");
            }
        } while (!found);
    }

    //---------------------- Add Volunteer -----------------------
    public void addVolunteer() {
        listAllVolunteersByID();
        Volunteer newVolunteer = inputAddVolunteer();

        if (newVolunteer != null) {
            volunteerList.add(newVolunteer);
            System.out.println("Add Successful.");
        } else {
            System.out.println("Add Cancel.");
        }

    }

    public Volunteer inputAddVolunteer() {
        // Input The Volunteer Detail
        int id = generateVolunteerID();
        String name = eventUI.inputName();
        char gender = eventUI.inputGender();
        String phoneNumber = eventUI.inputPhoneNumber();

        // Confirmation To Add
        boolean addconfirm = eventUI.addConfirm();
        if (addconfirm == true) {
            Volunteer volunteer = new Volunteer(id, name, gender, phoneNumber);

            // Add Volunteer Into Event
            char choice = eventUI.messageEventChoice();
            if (choice == 'Y') {
                boolean continueLoop = false;
                boolean messagePop = true;

                // While Loop For Join More Event & Find Exist Event
                while (!continueLoop) {
                    displayAllEventofVolunteerNumber();
                    String eventID = eventUI.inputEventID();
                    boolean found = false;

                    // For loop For Add Volunteer Into Event
                    for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
                        Event event = eventList.getEntry(i);

                        // If Found The Event
                        if (eventID.equals(event.getEventID())) {
                            boolean existAdd = false;
                            SortedListInterface<Volunteer> findvolunteers = event.getVolunteerList();

                            // For Loop For Find The Volunteer Is Already Join The Event
                            for (int j = 0; j < findvolunteers.getNumberOfEntries(); j++) {
                                Volunteer existingVolunteer = findvolunteers.getEntry(j);
                                if (existingVolunteer.getVolunteerID() == volunteer.getVolunteerID()) {
                                    existAdd = true;
                                    break;
                                }
                            }

                            // If The Volunteer Not In The Event Add Into The Event
                            if (!existAdd) {
                                event.addVolunteer(volunteer);
                                found = true;
                                messagePop = true;
                                break;
                            } else {
                                System.err.println("\nVolunteer is Already Register For This Event.");
                                found = true;
                                messagePop = false;
                                break;
                            }
                        }
                    }
                    if (!found) {
                        System.err.println("Event ID Not Found. Please try again.");
                        messagePop = false;
                    }

                    if (messagePop) {
                        continueLoop = eventUI.joinMoreEvent();
                    }
                }
            }
            MainSystemUI.clearScreen();
            return volunteer;
        } else {
            MainSystemUI.clearScreen();
            Volunteer volunteer = null;
            return volunteer;
        }

    }

    //------------------- Delete Volunteer -------------------
    public void deleteVolunteerByID() {
        boolean join = false;
        boolean done = false;
        int deleteID = -1;
        Volunteer foundVolunteer = null;
        listAllVolunteersByID();

        // Find Exist Volunteer
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

            // If Volunteer Found
            if (foundVolunteer != null) {
                eventUI.detailofVolunteerHeader();
                eventUI.listAllVolunteerDetail(foundVolunteer);

                join = false;
                System.out.println("\nEvent Join:");

                // Show The Volunteer Have Join What Event
                for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
                    Event event = eventList.getEntry(i);
                    if (event.getVolunteerList().search(foundVolunteer) != -1) {
                        System.out.println("Event ID: " + event.getEventID() + ", Event Name: " + event.getEventName());
                        join = true;
                    }
                }

                if (!join) {
                    System.out.println("This Volunteer Not Join Any Event.");
                }

                done = true;
            } else {
                System.err.println("Volunteer ID Not found. Please Try Again.");
            }

        } while (!done);
        // If Found The Volunteer Have Join Event
        if (join == true) {
            // Confirmation To Delete
            boolean confirm = eventUI.confirmDelete();

            if (confirm == true) {

                if (deleteID != -1) {
                    volunteerList.remove(deleteID);
                }

                //Delete The Volunteer In The Event
                for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
                    Event event = eventList.getEntry(i);
                    int position = event.getVolunteerList().search(foundVolunteer);
                    if (position != -1) {
                        event.getVolunteerList().remove(position);
                    }
                }
                MainSystemUI.clearScreen();
                System.out.println("Delete Successful.");

            } else {
                MainSystemUI.clearScreen();
                System.out.println("Delete Cancel.");
            }

        } // If The Volunteer Not Join Event
        else {
            // Confirmation To Delete
            boolean confirm = eventUI.confirmDelete();

            if (confirm == true) {

                if (deleteID != -1) {
                    volunteerList.remove(deleteID);
                    MainSystemUI.clearScreen();
                    System.out.println("Delete Successful.");
                }

            } else {
                MainSystemUI.clearScreen();
                System.out.println("Delete Cancel.");
            }
        }

    }

    //------------------- Update Volunteer --------------------
    public void updateVolunteer() {
        boolean loop = false;
        boolean confirm = false;

        listAllVolunteersByID();
        do {
            boolean found = false;
            int inputVolunteerID = eventUI.inputSearch();
            for (int i = 0; i < volunteerList.getNumberOfEntries(); i++) {
                Volunteer volunteer = volunteerList.getEntry(i);
                if (inputVolunteerID == volunteer.getVolunteerID()) {
                    eventUI.detailofVolunteerHeader();
                    eventUI.listAllVolunteerDetail(volunteer);
                    String name = eventUI.inputName();
                    char gender = eventUI.inputGender();
                    String phoneNumber = eventUI.inputPhoneNumber();
                    found = true;
                    loop = true;
                    confirm = eventUI.addConfirm();
                    if (confirm == true) {
                        volunteer.setVolunteerName(name);
                        volunteer.setGender(gender);
                        volunteer.setPhoneNumber(phoneNumber);
                    } else if (!confirm) {
                        loop = true;
                    }
                    break;
                }
            }
            if (!found) {
                System.err.println("Volunteer ID Not Found. Please Try Again.");
            }
        } while (!loop);

        MainSystemUI.clearScreen();

        if (confirm == true) {
            System.out.println("\nUpdate Successful");
        }

        if (!confirm) {
            System.out.println("\nUpdate Cancel");
        }

    }

    //-------------- Add Event ---------------------
    public void addEvent() {
        displayAllEvents();
        Event newEvent = inputAddEvent();
        if (newEvent != null) {
            eventList.add(newEvent);
            MainSystemUI.clearScreen();
            System.out.println("Add Successful.");
        } else {
            MainSystemUI.clearScreen();
            System.out.println("Add Cancel.");
        }

    }

    public Event inputAddEvent() {
        boolean found = false;
        String eventID = " ";
        boolean confirm = false;

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
                System.err.println("Event ID Already Exists. Please Try Again.");
            }
        } while (found == true);

        String eventName = eventUI.inputName();
        String eventDetail = eventUI.inputEventDetail();

        confirm = eventUI.addConfirm();
        if (confirm == true) {
            Event event = new Event(eventID, eventName, eventDetail);
            System.out.println("Add Successful.");
            MainSystemUI.clearScreen();
            return event;
        }

        Event event = null;
        MainSystemUI.clearScreen();
        return event;

    }

    //-------------- Update Event ---------------------
    public void editEvent() {
        boolean loop = false;
        boolean confirm = false;

        displayAllEvents();
        do {
            boolean found = false;
            String inputEventID = eventUI.inputEditEventID();
            for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
                Event event = eventList.getEntry(i);
                if (inputEventID.equals(event.getEventID())) {
                    String eventName = eventUI.inputName();
                    String description = eventUI.inputEventDetail();
                    found = true;
                    loop = true;
                    confirm = eventUI.addConfirm();
                    if (confirm == true) {
                        event.setEventName(eventName);
                        event.setEventDetail(description);
                    } else if (!confirm) {
                        loop = true;
                    }
                    break;
                }
            }
            if (!found) {
                System.err.println("Event ID Not Found. Please Try Again.");
            }
        } while (!loop);

        if (confirm == true) {
            MainSystemUI.clearScreen();
            System.out.println("\nUpdate Successful");
        }

        if (!confirm) {
            MainSystemUI.clearScreen();
            System.out.println("\nUpdate Cancel");
        }

    }

    //------------ Delete Event Also Will Delete The Volunteer In The Event --------------------------
    public void deleteEvent() {
        boolean eventFound = false;
        boolean done = false;
        int deleteID = -1;
        Event foundEvent = null;
        displayAllEvents();

        // Do While For Find Exist Event
        do {
            String removeID = eventUI.inputEventID();

            for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
                Event removeEvent = eventList.getEntry(i);
                if (removeEvent.getEventID().equals(removeID)) {
                    foundEvent = removeEvent;
                    deleteID = i;
                    break;
                }
            }

            // If Found The Event
            if (foundEvent != null) {
                eventUI.displayDetailEventHeader();
                eventUI.displayAllEventsDetail(foundEvent);

                System.out.println("\nVolunteers In This Event:");

                boolean has = false;
                SortedListInterface<Volunteer> hasVolunteer = foundEvent.getVolunteerList();

                // Show The Volunteer Detail In This Event
                for (int i = 0; i < hasVolunteer.getNumberOfEntries(); i++) {
                    Volunteer volunteer = hasVolunteer.getEntry(i);
                    eventUI.listAllVolunteerDetail(volunteer);
                    has = true;
                }

                if (!has) {
                    System.out.println("No Volunteers In This Event.");
                }

                done = true;
            } else {
                System.err.println("Event ID Not Found. Please Try Again.");
            }

        } while (!done);

        // If The Event Has Found
        if (foundEvent != null) {

            // Confirmation To Delete
            boolean confirm = eventUI.addConfirm();

            if (confirm) {

                // If The deleteID Not Equals -1
                if (deleteID != -1) {
                    eventList.remove(deleteID); // Delete The Event
                }

                // For Loop To Delete The Volunteer In This Event
                for (int i = 0; i < volunteerList.getNumberOfEntries(); i++) {
                    Volunteer volunteer = volunteerList.getEntry(i);
                    int position = volunteer.getEventList().search(foundEvent);
                    if (position != -1) {
                        volunteer.getEventList().remove(position);
                    }
                }
                MainSystemUI.clearScreen();
                System.out.println("Event Delete Successful.");
            } else {
                MainSystemUI.clearScreen();
                System.out.println("Delete Cancel.");
            }
        }
    }

    //------------ Delete The Exist Volunteer In The Event ---------------------
    public void removeEventFromVolunteer() {
        boolean confirm = false;
        boolean join = false;
        boolean done = false;
        Volunteer foundVolunteer = null;
        Event foundEvent = null;

        int totalVolunteer = volunteerList.getNumberOfEntries();
        boolean[] findAllJoinVolunteer = new boolean[totalVolunteer];

        displayAllVolunteersByEvents();

        // Do While For Find The Volunteer
        do {
            int findID = eventUI.inputDelete();

            for (int i = 0; i < volunteerList.getNumberOfEntries(); i++) {
                Volunteer findVolunteerID = volunteerList.getEntry(i);
                if (findVolunteerID.getVolunteerID() == findID) {
                    foundVolunteer = findVolunteerID;
                    break;
                }
            }

            // If The Volunteer Has Found
            if (foundVolunteer != null) {
                MainSystemUI.clearScreen();
                eventUI.detailofVolunteerHeader();
                eventUI.listAllVolunteerDetail(foundVolunteer);

                join = false;
                System.out.println("\nEvents Join:");

                // For Loop For Show The Volunteer Have Join What Event
                for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
                    Event event = eventList.getEntry(i);
                    int volunteerIndex = event.getVolunteerList().search(foundVolunteer);

                    if (volunteerIndex != -1) {
                        findAllJoinVolunteer[volunteerIndex] = true;

                        System.out.println("Event ID: " + event.getEventID() + ", Event Name: " + event.getEventName());
                        join = true;
                    }
                }

                if (!join) {
                    System.out.println("This Volunteer Has Not Join Any Event.");
                }

                done = true;
            } else {
                System.err.println("Volunteer ID Not Found. Please Try Again.");
            }

        } while (!done);

        // If The Volunteer Have Join Any Event
        if (join) {
            do {
                done = false;
                String deleteEventID = eventUI.inputRemoveEventID();

                // For Loop For Find The Volunteer In The Event
                for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
                    Event event = eventList.getEntry(i);

                    // To Find The Volunteer Have Exist In The Event Or Not
                    if (deleteEventID.equals(event.getEventID())) {
                        int volunteerIndex = event.getVolunteerList().search(foundVolunteer);
                        if (volunteerIndex != -1 && findAllJoinVolunteer[volunteerIndex]) {
                            foundEvent = event;
                            confirm = eventUI.confirmDelete();
                            done = true;
                            break;
                        }
                    }
                }

                if (!done) {
                    System.err.println("Event ID Not Found. Please Try Again.");
                }

            } while (!done);

            // If The Volunteer Found In The Event & Confirm to Delete Volunteer In The Event
            if (confirm && foundEvent != null) {

                // Find The Volunteer In The Event & Delete Volunteer In The Event
                int position = foundEvent.getVolunteerList().search(foundVolunteer);
                if (position != -1) {
                    foundEvent.getVolunteerList().remove(position);
                    MainSystemUI.clearScreen();
                    System.out.println("\nEvent Successful Remove From Volunteer.");
                }
            } else {
                MainSystemUI.clearScreen();
                System.out.println("\nDelete Cancel.");
            }
        } else {
            MainSystemUI.clearScreen();
            System.out.println("\nDelete Cancel.");
        }
    }

    //----------- Add The Exist Volunteer Into Event Based On How Many Quantity The User Want --------------
    public void addVolunteerToEvent() {
        displayAllEvents();

        boolean found = false;
        String eventID = " ";
        Event selectEvent = null;

        // Find Exist Event
        do {
            eventID = eventUI.inputEventID();
            for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
                Event event = eventList.getEntry(i);
                if (eventID.equals(event.getEventID())) {
                    selectEvent = event;
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.err.println("Event ID Not Found. Please Try Again.");
            }
        } while (!found);
        displayAllEventofVolunteerNumber();

        // Enter How Many Volunteer Into Event
        int quantity = eventUI.inputQuantity(volunteerList.getNumberOfEntries());
        int addCount = 0;

        // For Loop To Add Quantity of Volunteer Into Event
        for (int j = 0; j < quantity; j++) {
            boolean add = false;

            for (int i = 0; i < volunteerList.getNumberOfEntries(); i++) {
                Volunteer volunteer = volunteerList.getEntry(i);

                // Check The Volunteer Is Not In The Event
                if (volunteer.getEventList() != null && volunteer.getEventList().search(selectEvent) == -1) {
                    if (selectEvent.addVolunteers(volunteer)) {
                        // If Volunteer Is Not In The Event Add The Volunteer Into Event
                        volunteer.getEventList().add(selectEvent);
                        addCount++;
                        add = true;
                        break;
                    }
                }
            }

            if (!add) {
                System.err.println("Not Enough Available Volunteers To Add.");
                break;
            }
        }
        MainSystemUI.clearScreen();
        System.out.println("Total Volunteers Add: " + addCount);
    }

    //-------------------- Find Volunteer Index ----------------
    private int findVolunteerIndex(int volunteerID) {
        for (int i = 0; i < volunteerList.getNumberOfEntries(); i++) {
            Volunteer volunteer = volunteerList.getEntry(i);
            if (volunteer.getVolunteerID() == volunteerID) {
                return i;
            }
        }
        return -1;
    }

    //-------------------- Auto Generate Volunteer ID --------------
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

    //-------------------- Report ---------------------
    public void volunteerReport() {
        int totalVolunteer = volunteerList.getNumberOfEntries();
        int totalJoinVolunteer = 0;
        boolean[] findAllJoinVolunteer = new boolean[totalVolunteer];

        for (int i = 0; i < totalVolunteer; i++) {
            findAllJoinVolunteer[i] = false;
        }

        for (int i = 0; i < eventList.getNumberOfEntries(); i++) {
            Event event = eventList.getEntry(i);
            SortedListInterface<Volunteer> eventVolunteerList = event.getVolunteerList();
            int numberOfVolunteers = eventVolunteerList.getNumberOfEntries();

            for (int j = 0; j < numberOfVolunteers; j++) {
                Volunteer volunteer = event.getVolunteerList().getEntry(j);
                int volunteerID = volunteer.getVolunteerID();

                int index = findVolunteerIndex(volunteerID);
                if (index != -1) {
                    findAllJoinVolunteer[index] = true;
                }
            }

            if (numberOfVolunteers > 0) {
                System.out.println("\nEvent ID: " + event.getEventID() + ", Event Name: " + event.getEventName() + ", \nDeception: " + event.getEventDetail());
                eventUI.listAllVolunteerHeader();
                for (int j = 0; j < numberOfVolunteers; j++) {
                    Volunteer volunteer = eventVolunteerList.getEntry(j);
                    eventUI.listAllVolunteerDetail(volunteer);
                }
                System.out.printf("Number of Volunteer: %-3d\n", numberOfVolunteers);
            }
        }

        for (boolean join : findAllJoinVolunteer) {
            if (join) {
                totalJoinVolunteer++;
            }
        }

        int totalNotJoinVolunteer = totalVolunteer - totalJoinVolunteer;

        System.out.printf("\nNumber of Volunteer Have Join Event: %-3d\n", totalJoinVolunteer);
        System.out.printf("Number of Volunteer Not Join Event (At Least One Event): %-3d\n", totalNotJoinVolunteer);
    }
}
