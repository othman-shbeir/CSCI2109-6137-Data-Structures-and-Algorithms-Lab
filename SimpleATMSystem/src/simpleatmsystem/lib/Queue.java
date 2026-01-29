package simpleatmsystem.lib;

/**
 * Queue<E>
 * --------
 * Interface representing a Queue data structure (FIFO).
 *
 * FIFO = First In, First Out
 *
 * @param <E> type of elements stored in the queue
 */
public interface Queue<E> extends Iterable<E> {

    /** Adds an element to the back of the queue */
    void enqueue(E element);

    /** Removes and returns the front element */
    E dequeue();

    /** Returns the front element without removing it */
    E first();

    /** @return number of elements in the queue */
    int size();

    /** @return true if queue is empty */
    boolean isEmpty();
}
