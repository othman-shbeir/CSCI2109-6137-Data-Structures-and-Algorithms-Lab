package problem3solution;

/**
 * Stack<E>
 * --------
 * A Stack is a linear data structure that follows the LIFO principle:
 * Last In, First Out.
 *
 * Common operations:
 * - push: add an element to the top
 * - pop : remove and return the top element
 * - top : view the top element without removing it
 *
 * @param <E> the type of elements stored in the stack
 */
public interface Stack<E> {

    int size();

    boolean isEmpty();

    E top();

    void push(E element);

    E pop();
}
