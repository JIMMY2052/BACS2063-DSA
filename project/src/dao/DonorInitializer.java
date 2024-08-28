/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.SortedListInterface;
import adt.SortedArrayList;
import entity.Donor;

/**
 *
 * @author JIMMY
 */
public class DonorInitializer {

    public SortedListInterface<Donor> initializeStudent() {

        SortedListInterface<Donor> donors = new SortedArrayList<>();

        donors.add(new Donor("JIMMY", 010, "c", "m"));
        donors.add(new Donor("JIE YANG", 010, "c", "m"));

        return donors;
    }

}
