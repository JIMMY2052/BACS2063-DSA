package adt;


import java.util.Iterator;

/**
 *
 * @author JIMMY
 */
public class SortedArrayList<T extends Comparable<T>> implements SortedListInterface<T> {

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 10;

    public SortedArrayList() {
        array = (T[]) new Comparable[DEFAULT_CAPACITY];
        numberOfEntries = 0;
    }

    @Override
    public boolean add(T newEntry) {
        if (newEntry == null) {
            return false;
        }

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

    @Override
    public boolean remove(int givenPosition) {
        if (givenPosition >= 0 && givenPosition < numberOfEntries) {
            // Shift the elements to the left to fill the gap
            for (int i = givenPosition; i < numberOfEntries - 1; i++) {
                array[i] = array[i + 1];
            }
            array[numberOfEntries - 1] = null;
            numberOfEntries--;
            return true;
        } else {
            throw new IndexOutOfBoundsException("Invalid position given.");
        }
       
    }

    @Override
    public boolean remove(T entry) {
        int index = search(entry);
        if (index >= 0) {
            // Shift elements to fill the gap
            for (int i = index; i < numberOfEntries - 1; i++) {
                array[i] = array[i + 1];
            }
            array[numberOfEntries - 1] = null;
            numberOfEntries--;
            return true;
        } else {
            return false;
        }

    }

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

    @Override
    public boolean replace(int givenPosition, T newEntry) {
        if (givenPosition >= 0 && givenPosition < numberOfEntries) {
            array[givenPosition] = newEntry;
            return true;
        } else {
            throw new IndexOutOfBoundsException("Invalid position given.");
        }
    }

    @Override
    public T getEntry(int givenPosition) {
        if (givenPosition >= 0 && givenPosition < numberOfEntries) {
            return array[givenPosition];
        } else {
            throw new IndexOutOfBoundsException("Invalid position given.");
        }
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    private boolean isFull() {
        return numberOfEntries >= array.length;
    }

    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = lastIndex; index >= newIndex; index--) {
            array[index + 1] = array[index];
        }
    }

    private void doubleCapacity() {
        T[] oldArray = array;

        array = (T[]) new Comparable[2 * oldArray.length];
        for (int index = 0; index < oldArray.length; index++) {
            array[index] = oldArray[index];
        }

    }
}
