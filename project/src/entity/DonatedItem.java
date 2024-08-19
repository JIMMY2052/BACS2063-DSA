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
    private int quantity;

    public DonatedItem(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item Name: " + itemName  + "\n Quantity: " + quantity;
    }

    @Override
    public int compareTo(DonatedItem o) {
        return itemName.compareTo(o.itemName);
    }
}

