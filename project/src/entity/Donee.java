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
public class Donee {
    private String name;
    private ListInterface foodDoantionList;
    private ListInterface casdDonationList;

    public Donee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ListInterface getFoodDoantionList() {
        return foodDoantionList;
    }

    public void addFoodDoantionList(FoodDonation food) {
        this.foodDoantionList.add(food);
    }

    public ListInterface getCasdDonationList() {
        return casdDonationList;
    }

    public void setCasdDonationList(ListInterface casdDonationList) {
        this.casdDonationList = casdDonationList;
    }
    
    
   
    
}
