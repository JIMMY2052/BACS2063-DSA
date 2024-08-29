/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author JIMMY
 */
public class SortedArrayList<T extends Comparable<T>> implements SortedListInterface<T> {

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAX_CAPACITY = 1000;

    public SortedArrayList() {
        array = (T[]) new Comparable[DEFAULT_CAPACITY];
        numberOfEntries = 0;
    }

    @Override
    public boolean add(T newEntry) {
        if (isFull()) {
            doubleCapacity();
        }

        int i = 0;
        while (i < numberOfEntries && newEntry.compareTo(array[i]) > 0) {
            i++;
        }
        makeRoom(i + 1);
        array[i] = newEntry;
        numberOfEntries++;

        return true;
    }

    @Override
    public int search(T entry) {
        for (int i = 0; i < numberOfEntries; i++) {
            if (array[i].equals(entry)) {
                return i; // Return the index of the found entry
            }
        }
        return -1; // Return -1 if the entry is not found
    }

    // Remove an entry at a given position
    @Override
    public T remove(int givenPosition) {
        if (givenPosition >= 0 && givenPosition < numberOfEntries) {
            T result = array[givenPosition];

            // Shift the elements to the left to fill the gap
            for (int i = givenPosition; i < numberOfEntries - 1; i++) {
                array[i] = array[i + 1];
            }
            array[numberOfEntries - 1] = null;
            numberOfEntries--;
            return result;
        } else {
            throw new IndexOutOfBoundsException("Invalid position given.");
        }
    }

    @Override
    public boolean remove(T entry){
        int index = getElementIndex(entry);
        if (index != -1) {
            // Shift elements to fill the gap
            for (int i = index; i < numberOfEntries; i++) {
                array[i] = array[i + 1];
            }
            numberOfEntries--;
            return true;
        } else {
            return false;
        }
        
    }
    
    private int getElementIndex(T object) {
        for (int i = 0; i < numberOfEntries; i++) {
            if (array[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }
    
    // Clear the list
    @Override
    public boolean clear() {
        if (numberOfEntries == 0) {
            return false;
        }

        for (int i = 0; i < numberOfEntries; i++) {
            array[i] = null;
        }

        numberOfEntries = 0;
        return true;
    }
    
    @Override
    public Iterator<T> getIterator() {
        return new SortedArrayListIterator();
    }

    private class SortedArrayListIterator implements Iterator<T> {

        private int currentIndex;

        public SortedArrayListIterator() {
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < numberOfEntries;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T obj = (T) array[currentIndex];
            currentIndex++;
            return obj;
        }
    }

    // Replace an entry at a given position
    @Override
    public boolean replace(int givenPosition, T newEntry) {
        if (givenPosition >= 0 && givenPosition < numberOfEntries) {
            array[givenPosition] = newEntry;
            return true;
        } else {
            throw new IndexOutOfBoundsException("Invalid position given.");
        }
    }

    // Get an entry at a given position
    @Override
    public T getEntry(int givenPosition) {
        if (givenPosition >= 0 && givenPosition < numberOfEntries) {
            return array[givenPosition];
        } else {
            throw new IndexOutOfBoundsException("Invalid position given.");
        }
    }

    // Get the number of entries in the list
    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    // Check if the list is empty
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    // Check if the list is full
    @Override
    public boolean isFull() {
        return numberOfEntries >= array.length;
    }

    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = lastIndex; index >= newIndex; index--) {
            array[index + 1] = array[index];
        }
    }

    // Double the capacity of the array if it's full
    private void doubleCapacity() {
        int newLength = Math.min(array.length * 2, MAX_CAPACITY);
        if (newLength <= array.length) {
            throw new IllegalStateException("Cannot create a list larger than " + MAX_CAPACITY);
        }
        array = Arrays.copyOf(array, newLength);
    }

}
