/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

/**
 *
 * @author JIMMY
 */
public interface SortedListInterface<T extends Comparable <T>> {

  public boolean add(T newEntry);
  public int search(T entry);
  public T remove(int givenPosition);
  public boolean clear();
  public boolean replace(int givenPosition, T newEntry);
  public T getEntry(int givenPosition);
  public int getNumberOfEntries();
  public boolean isEmpty();
  public boolean isFull();

  
}
