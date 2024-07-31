/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author JIMMY
 */
public class FoodDonation extends Donation {

    private String foodName;
    private int qty;

    public FoodDonation(int id, Donor donor, Donee donee, Date donationDate, String foodName, int qty) {
        super(id, donor, donee, donationDate);
        this.foodName = foodName;
        this.qty = qty;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}
