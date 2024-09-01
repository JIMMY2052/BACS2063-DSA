/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

/**
 *
 * @author JIMMY
 */
import adt.SortedListInterface;
import dao.Initializer;
import boundary.*;
import entity.*;
import Control.DonorController;
import Control.DonationController;
import Control.EventCtrl;
import Control.DoneeController;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import utility.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainSystemUI mainSystemUI = new MainSystemUI(scanner);
        Initializer init = new Initializer();
        SortedListInterface<Volunteer> volunteerList = init.getVolunteerList();
        SortedListInterface<Event> eventList = init.getEventList();
        SortedListInterface<Donation> donationList = init.getDonation();
        SortedListInterface<Donor> donorList = init.getDonor();
        SortedListInterface<Donee> doneeList = init.getDonees();
        SortedListInterface<DonationDistribution> donationdistributionList = init.getinitializeDonationDistributions();

        String choice;
        do {
            clearScreen();
            choice = mainSystemUI.getMainMenu();
            switch (choice) {
                case "1": {
                    clearScreen();
                    DonorController donorCrt = new DonorController(donorList, donationList);
                    donorCrt.menu();
                    break;
                }
                case "2": {
                    clearScreen();
                    DonationController donationCrt = new DonationController(donorList, donationList);
                    donationCrt.DonationManagement();
                    break;
                }
                case "3": {
                    clearScreen();
                }
                case "4": {
                    clearScreen();
                    DoneeController doneeCtr = new DoneeController(doneeList, donationdistributionList);
                    doneeCtr.runDoneeManagement();
                    break;
                }
                case "5": {
                    clearScreen();
                    EventCtrl eventCtrl = new EventCtrl(volunteerList, eventList);
                    eventCtrl.runEventCtrl();
                    break;
                }
                default: {
                    MessageUI.displayInvalidChoiceMessage();
                }
            }
        } while (!choice.equals("0"));
    }

    public static void clearScreen() {
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
