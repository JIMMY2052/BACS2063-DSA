/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author JIMMY
 */
public class Food {
    private String foodName;
    private int qty;
    
    public Food(){
        this.foodName = null;
        this.qty = 0;
    }

    public Food(String foodName, int qty) {
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
