package simpleatmsystem;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * MyArrayList is a simplified implementation of a dynamic array, similar in
 * behavior to Java's built-in ArrayList.
 *
 * This class is designed for educational purposes to help students understand:
 * - Dynamic resizing
 * - Generic types
 * - Element insertion and removal
 * - Sorting
 * - Iteration using Iterator and for-each loop
 *
 * @param <E> the type of elements stored in this list
 */
public class MyArrayList<E> implements Iterable<E> {

    /**
     * Number of elements currently stored in the list.
     */
    private int size;

    /**
     * Internal array used to store elements.
     */
    private Object[] elements;

    /**
     * Constructs an empty MyArrayList with initial capacity 10.
     */
    public MyArrayList() {
        size = 0;
        elements = new Object[10];
    }

    /**
     * @return number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Returns element at a given index.
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) elements[index];
    }

    /**
     * Replaces element at a given index.
     */
    public void set(int index, E data) {
        elements[index] = data;
    }

    /**
     * Adds an element to the end of the list.
     */
    public void add(E data) {

        // Resize array if full
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, size * 2);
        }

        elements[size] = data;
        size++;
    }

    /**
     * Removes element at a given index and shifts remaining elements.
     */
    public void remove(int index) {

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[size - 1] = null;
        size--;
    }

    /**
     * Checks if the list contains a specific element.
     */
    public boolean contains(E data) {

        if (data instanceof Comparable) {
            Comparable c = (Comparable) data;
            for (int i = 0; i < size; i++) {
                if (c.compareTo(elements[i]) == 0) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (data.equals(elements[i])) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Sorts the list using natural ordering (Comparable).
     */
    public void sort() {

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {

                if (((Comparable) elements[i]).compareTo(elements[j]) > 0) {
                    Object temp = elements[i];
                    elements[i] = elements[j];
                    elements[j] = temp;
                }
            }
        }
    }

    /**
     * Sorts the list using a custom Comparator.
     */
    public void sort(Comparator<E> comparator) {

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {

                if (comparator.compare((E) elements[i], (E) elements[j]) > 0) {
                    Object temp = elements[i];
                    elements[i] = elements[j];
                    elements[j] = temp;
                }
            }
        }
    }

    /**
     * Returns an iterator so this list can be used in a for-each loop.
     *
     * @return Iterator over the elements of the list
     */
    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    /**
     * Inner class that implements Iterator for MyArrayList.
     *
     * This iterator walks through the list from index 0 to size - 1.
     */
    private class MyArrayListIterator implements Iterator<E> {

        /**
         * Current position of the iterator.
         */
        private int currentIndex = 0;

        /**
         * Checks if there are more elements to iterate over.
         */
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        /**
         * Returns the next element in the list.
         */
        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            return (E) elements[currentIndex++];
        }
    }

    /**
     * Returns a string representation of the list.
     */
    @Override
    public String toString() {

        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < size; i++) {
            sb.append(elements[i]).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");

        return sb.toString();
    }
}
