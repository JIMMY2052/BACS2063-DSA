/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ListInterface;

/**
 *
 * @author JIMMY
 */
public class Donor {
    private String name;
    private ListInterface foodDoantionList;
    private ListInterface casdDonationList;
   

    public Donor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ListInterface<FoodDonation> getFoodDoantionList() {
        return this.foodDoantionList;
    }

    public void setFoodDoantionList(ListInterface<FoodDonation> foodDoantionList) {
        this.foodDoantionList = foodDoantionList;
    }

    public ListInterface getCasdDonationList() {
        return casdDonationList;
    }

    public void setCasdDonationList(ListInterface casdDonationList) {
        this.casdDonationList = casdDonationList;
    }


    
    
    
}
