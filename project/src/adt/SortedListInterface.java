package adt;

import java.util.Iterator;

/**
 *
 * @author JIMMY
 */
public interface SortedListInterface<T extends Comparable<T>> {

    public boolean add(T newEntry); //JIMMY

    public int search(T entry);

    public T remove(int givenPosition);

    public boolean clear(); //JIMMY

    public boolean replace(int givenPosition, T newEntry);

    public T getEntry(int givenPosition);

    public int getNumberOfEntries();

    public boolean isEmpty();

    public boolean isFull();

    public Iterator<T> getIterator(); //JIMMY

    public boolean remove(T entry);

}
