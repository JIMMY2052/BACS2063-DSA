/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import boundary.DonationUI;

/**
 *
 * @author JIMMY
 */
public class DonationController {
    DonationUI donationUI = new DonationUI();
    
    public DonationController(){
        clearScreen();
        int choice = donationUI.displayDonationMenu();
        
    }
    
    public static void main(String[] args) {
        DonationController dc = new DonationController();
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
