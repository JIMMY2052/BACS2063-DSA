package utility;
/**
 *
 * @author KK
 */
public class MessageUI {

  public static void displayInvalidChoiceMessage() {
        System.err.println("Invalid choice. Please try again.");
    }       
     
    public static void displayExitMessage() {
        System.out.println("Exiting System");
    }

    public static void removeDoneeSuccessMessage() {
        System.out.println("\nDonee removed successfully.");
    }

    public static void DoneeNotFoundMessage() {
        System.err.println("\nDonee not found.");
    }

    public static void updateDoneeSuccessMessage() {
        System.out.println("\nDonee details amended successfully.");
    }
    
    
}