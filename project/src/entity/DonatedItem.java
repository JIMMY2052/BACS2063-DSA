/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author JIMMY
 */
public class DonatedItem implements Comparable<DonatedItem>{
    private String itemName;
    private String unit;
    private double quantity;

    public DonatedItem(String itemName, double quantity, String unit) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    

    @Override
    public String toString() {
        if(itemName.equals("CASH")){
            return unit + " " + String.format("%.2f", quantity);
        }
        return quantity + " "+ unit;
        
    }

    @Override
    public int compareTo(DonatedItem o) {
        return itemName.compareTo(o.itemName);
    }
}

