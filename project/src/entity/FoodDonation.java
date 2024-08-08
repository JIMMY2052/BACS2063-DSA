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
public class FoodDonation extends Donation{

    private String foodType;
    private int qty;

    public FoodDonation(int donationId, Donor donor, String foodType, int qty) {
        super(donationId, donor);
        this.foodType = foodType;
        this.qty = qty;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}
