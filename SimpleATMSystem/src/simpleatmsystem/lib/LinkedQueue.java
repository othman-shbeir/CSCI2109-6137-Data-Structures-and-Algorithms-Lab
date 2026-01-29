package simpleatmsystem.lib;

import java.util.Iterator;

/**
 * LinkedQueue<E>
 * --------------
 * A Queue implementation using a LinkedList as the underlying storage.
 *
 * Mapping:
 * - enqueue -> add element to the end of the list
 * - dequeue -> remove element from the front
 * - first   -> access the front element
 *
 * @param <E> type of elements stored in the queue
 */
public class LinkedQueue<E> implements Queue<E> {

    /** Internal linked list used to store queue elements */
    private LinkedList<E> listedQueue;

    /**
     * Constructs an empty queue.
     */
    public LinkedQueue() {
        listedQueue = new LinkedList<>();
    }

    /**
     * Adds an element to the back of the queue.
     *
     * @param element element to enqueue
     */
    @Override
    public void enqueue(E element) {
        listedQueue.add(element);
    }

    /**
     * Removes and returns the front element of the queue.
     *
     * @return removed element, or null if queue is empty
     */
    @Override
    public E dequeue() {
        if (listedQueue.getHead() == null) {
            return null;
        }
        E temp = listedQueue.getHead().getData();
        listedQueue.removeFirst();
        return temp;
    }

    /**
     * Returns the front element without removing it.
     *
     * @return front element, or null if queue is empty
     */
    @Override
    public E first() {
        if (listedQueue.getHead() == null) {
            return null;
        }
        return listedQueue.getHead().getData();
    }

    @Override
    public int size() {
        return listedQueue.size();
    }

    @Override
    public boolean isEmpty() {
        return listedQueue.isEmpty();
    }

    /**
     * Allows iteration over the queue from front to back.
     *
     * @return iterator for queue elements
     */
    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    /**
     * QueueIterator
     * -------------
     * Iterates over the queue elements starting from the front.
     */
    private class QueueIterator implements Iterator<E> {

        private LinkedList<E>.Node<E> current = listedQueue.getHead();

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E data = current.getData();
            current = current.getNext();
            return data;
        }
    }
}
