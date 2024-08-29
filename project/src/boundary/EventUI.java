package boundary;
/**
 *
 * @author KK
 */
import entity.*;
import java.util.Scanner;

public class EventUI {

    Scanner scanner = new Scanner(System.in);

    //Event Menu
    public String getEventMenu() {
        System.out.println("\nEvent & Volunteer Menu");
        System.out.println("1. Display All Volunteer By Event");
        System.out.println("2. Display All Volunteer By ID");
        System.out.println("3. Display All Volunteer Filter By Gender");
        System.out.println("4. Search Volunteer By ID");
        System.out.println("5. Delete Volunteer");
        System.out.println("6. Add Volunteer");
        System.out.println("7. Display All Event");
        System.out.println("8. Display All Event of Volunteer Number");
        System.out.println("9. Add Event");
        System.out.println("10. Add Volunteer to Event");
        System.out.println("0. Exit");
        System.out.print("Enter Choice: ");
        String choice = scanner.nextLine().trim();
        System.out.println();
        return choice;
    }

    public void listAllVolunteerHeader() {
        System.out.println("List of Volunteer:");
        System.out.printf("%-13s %-21s %-20s %-20s\n",
                "Volunteer ID", "Name", "Gender", "Phone Number");
    }

    public void listAllVolunteerDetail(Volunteer volunteer) {
        System.out.printf("%-13s %-21s %-20s %-20s\n",
                volunteer.getVolunteerID(), volunteer.getVolunteerName(), volunteer.getGender(), volunteer.getPhoneNumber());
    }

    public String inputName() {
        String name;
        do {
            System.out.print("Enter Name (Only 20 Characters): ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.err.println("Name Cannot Be Empty.");
            } else if (!name.matches("^[a-zA-Z\\s]+$")) {
                System.err.println("Name Can Only Contain Alphabetic Characters.");
            } else if (name.length() > 20) {
                System.err.println("Name Cannot Be More Than 20 Characters.");
            }
        } while (name.isEmpty() || !name.matches("^[a-zA-Z\\s]+$") || name.length() > 20);

        return name;
    }

    public char inputGender() {
        boolean isValid = false;
        char genderInput = ' ';

        do {
            System.out.print("Enter Gender (M/F): ");
            String gender = scanner.nextLine().trim().toUpperCase();

            if (gender.equals("M") || gender.equals("F")) {
                isValid = true;
                genderInput = gender.charAt(0);
            } else {
                System.err.println("Invalid Gender Input. Please Enter 'M' For Male or 'F' For Female.");
            }
        } while (!isValid);

        return genderInput;
    }

    public String inputPhoneNumber() {
        String phoneNumber;
        do {
            System.out.print("Enter Phone Number (e.g. 0123456789): ");
            phoneNumber = scanner.nextLine().trim();

            if (phoneNumber.isEmpty()) {
                System.err.println("Phone Number Cannot Empty.");
            } else if (phoneNumber.length() > 10) {
                System.err.println("Phone Number Cannot Be More Than 10 Characters.");
            } else if (!phoneNumber.matches("\\d{3}\\d{7}")) {
                System.err.println("Phone Number Must Be Digits (0-9).");
            }
        } while (phoneNumber.isEmpty() || !phoneNumber.matches("\\d{3}\\d{7}") || phoneNumber.length() > 10);

        return phoneNumber;
    }

    public String inputEventID() {
        String eventID;
        do {
            System.out.print("Enter Event ID to Join (e.g. EV001): ");
            eventID = scanner.nextLine().trim().toUpperCase();
            if (eventID.isEmpty()) {
                System.err.println("Event ID Cannot Empty.");
            } else if (!eventID.startsWith("EV")) {
                System.err.println("Event ID Must Start With 'EV'.");
            } else if (eventID.length() > 5) {
                System.err.println("Event ID Cannot Be More Than 5 Characters.");
            }
        } while (eventID.isEmpty() || !eventID.startsWith("EV") || eventID.length() > 5);
        return eventID;
    }

    public void displayAllEventofVolunteerNumberHeader() {
        System.out.println("\n--------------------------------------------------------");
        System.out.printf("|%-10s|%-20s|%-22s|\n", "Event ID", "Event Name", "Number of Volunteers");
        System.out.println("--------------------------------------------------------");
    }

    public char messageEventChoice() {
        boolean isValid = false;
        char choice = ' ';

        do {
            System.out.print("Want Join Event? (Y/N): ");
            String Choice = scanner.nextLine().trim().toUpperCase();

            if (Choice.equals("Y") || Choice.equals("N")) {
                isValid = true;
                choice = Choice.charAt(0);
            } else {
                System.err.println("Invalid Input. Please Enter 'Y' For Yes or 'N' For No.");
            }
        } while (!isValid);

        return choice;
    }

    public void displayAllEventsHeader() {
        System.out.println("List of Event:");
        System.out.printf("%-10s %-20s %-100s\n", "Event ID", "Event Name", "Description");
    }

    public void displayAllEventsDetail(Event event) {
        System.out.printf("%-10s %-20s %-100s\n", event.getEventID(), event.getEventName(), event.getEventDetail());
    }

    public boolean joinMoreEvent() {
        boolean continueLoop = false;
        boolean loop = false;
        do {
            System.out.print("Want Join More Event? (Y/N): ");
            String choice = scanner.nextLine().trim().toUpperCase();

            if (choice.equals("Y") || choice.equals("N")) {

                if (choice.equals("Y")) {
                    continueLoop = false;
                    loop = true;
                }

                if (choice.equals("N")) {
                    continueLoop = true;
                    loop = true;
                }

            } else {
                System.err.println("Invalid Input. Please Enter 'Y' For Yes or 'N' For No.");
            }
        } while (!loop);

        return continueLoop;

    }

    public int inputSearch() {
        boolean done = false;
        String searchID = " ";
        int resultID = 0;
        do {
            System.out.print("Enter Volunteer ID (e.g. 10001): ");
            searchID = scanner.nextLine().trim();
            if (searchID.length() < 5 || !searchID.matches("^[0-9]+$")) {
                System.out.println("Invalid Volunteer ID. Please enter a valid 5-digit ID.");
            } else {
                resultID = Integer.parseInt(searchID);
                if (resultID <= 10000) {
                    System.out.println("Volunteer ID Not found. Please Try Again.");
                } else {
                    done = true;
                }
            }
        } while (!done);

        return resultID;
    }

    public void detailofVolunteerHeader() {
        System.out.println("\nDetail of Volunteer:");
        System.out.printf("%-13s %-21s %-20s %-20s\n",
                "Volunteer ID", "Name", "Gender", "Phone Number");
    }

    public int inputDelete() {
        boolean done = false;
        String searchID = " ";
        int resultID = 0;
        do {
            System.out.print("Enter Volunteer ID to Delete (e.g. 10001): ");
            searchID = scanner.nextLine().trim();
            if (searchID.length() < 5 || !searchID.matches("^[0-9]+$")) {
                System.out.println("Invalid Volunteer ID. Please enter a valid 5-digit ID.");
            } else {
                resultID = Integer.parseInt(searchID);
                if (resultID <= 10000) {
                    System.out.println("Volunteer ID Not found. Please Try Again.");
                } else {
                    done = true;
                }
            }
        } while (!done);

        return resultID;
    }

    public boolean confirmDelete() {
        boolean continueLoop = false;
        boolean loop = false;
        do {
            System.out.print("Confirm Delete Volunteer? (Y/N): ");
            String choice = scanner.nextLine().trim().toUpperCase();

            if (choice.equals("Y") || choice.equals("N")) {

                if (choice.equals("Y")) {
                    continueLoop = true;
                    loop = true;
                }

                if (choice.equals("N")) {
                    continueLoop = false;
                    loop = true;
                }

            } else {
                System.err.println("Invalid Input. Please Enter 'Y' For Yes or 'N' For No.");
            }
        } while (!loop);

        return continueLoop;
    }

    public String inputEventDetail() {
        String detail;
        do {
            System.out.print("Enter Description (Only 100 Characters): ");
            detail = scanner.nextLine().trim();
            if (detail.isEmpty()) {
                System.err.println("Description Cannot Be Empty.");
            } else if (detail.length() > 20) {
                System.err.println("Description Cannot Be More Than 100 Characters.");
            }
        } while (detail.isEmpty() || detail.length() > 100);

        return detail;
    }

    public int inputQuantity(int max) {
        boolean done = false;
        String number = " ";
        int quantity = 0;
        do {
            System.out.print("\nEnter How Many Volunteer to Join: ");
            number = scanner.nextLine().trim();
            if (!number.matches("^[0-9]+$")) {
                System.out.println("Invalid Input. Please Enter Valid Digit.");
            } else {
                quantity = Integer.parseInt(number);
                if (quantity < 0) {
                    System.out.println("Invalid Can't Less Than 0. Please Try Again.");
                } else if (quantity > max) {
                    System.out.println("Invalid Can't More Than Volunteer Number. Please Try Again.");
                } else {
                    done = true;
                }
            }
        } while (!done);

        return quantity;
    }

}
