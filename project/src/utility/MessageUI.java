package utility;

/**
 *
 * @author All
 */
public class MessageUI {

    public static void displayInvalidChoiceMessage() {
        System.err.println("Invalid choice. Please try again.");
    }

    public static void displayExitMessage() {
        System.out.println("\u001B[32mExiting SubSystem\u001B[0m");
    }

    public static void displayExitPreviousMessage() {
        System.out.println("\u001B[32mBack to previous page.\u001B[0m");
    }

    public static void removeDoneeSuccessMessage() {
        System.out.println("\n\u001B[32mDonee removed successfully.\u001B[0m");
    }

    public static void DoneeNotFoundMessage() {
        System.err.println("\nDonee not found.");
    }

    public static void EmptyMessage() {
        System.err.println("\nDonee is empty.");
    }

    public static void updateDoneeSuccessMessage() {
        System.out.println("\n\u001B[32mDonee details amended successfully.\u001B[0m");
    }

    public static void WrongInputMessage() {
        System.err.println("Please type Decimal Number. Please try again.");
    }

    public static void displayCancellationMessage() {
        System.out.println("\n\u001B[32mRemoval of Donee has been cancelled.\u001B[0m");
    }

}
