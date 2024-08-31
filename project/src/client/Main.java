/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

/**
 *
 * @author JIMMY
 */
import Control.DonationController;
import adt.*;
import boundary.*;
import control.EventCtrl;
import entity.*;
import dao.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import utility.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainSystemUI mainSystemUI = new MainSystemUI(scanner);
        Initializer initializer = new Initializer();

        String choice;
        do {
            clearScreen();
            choice = mainSystemUI.getMainMenu();
            switch (choice) {
                case "1": {
                    clearScreen();
                    SortedListInterface<Volunteer> volunteerList = initializer.getVolunteerList();
                    SortedListInterface<Event> eventList = initializer.getEventList();

                    EventCtrl eventCtrl = new EventCtrl(volunteerList, eventList);
                    eventCtrl.runEventCtrl();
                    break;
                }
                case "2": {
                    clearScreen();
                    DonationController donationCrt = new DonationController();
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