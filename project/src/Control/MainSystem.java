package Control;
/**
 *
 * @author KK
 */
import adt.*;
import boundary.*;
import entity.*;
import dao.*;
import utility.*;
import java.util.Scanner;

public class MainSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainSystemUI mainSystemUI = new MainSystemUI(scanner);
        Initializer initializer = new Initializer();
        mainSystemUI.clearScreen();
        
        String choice;
        do {
            
            choice = mainSystemUI.getMainMenu();
            switch (choice) {
                case "1": {
                    mainSystemUI.clearScreen();
                    SortedListInterface<Volunteer> volunteerList = initializer.getVolunteerList();
                    SortedListInterface<Event> eventList = initializer.getEventList();
                    
                    EventCtrl eventCtrl = new EventCtrl(volunteerList,eventList);
                    eventCtrl.runEventCtrl();
                    break;
                }
                case "0":{
                    mainSystemUI.clearScreen();
                    MessageUI.displayExitMessage();
                    break;
                }
                default :{
                    mainSystemUI.clearScreen();
                    MessageUI.displayInvalidChoiceMessage();
                }
            }
        } while (!choice.equals("0"));
    }

}
