package problem3solution;

/**
 * LinkedStack<E>
 * --------------
 * A Stack implementation using a LinkedList as the internal storage.
 *
 * Design idea:
 * - The "top" of the stack is the "head" of the linked list.
 * - push(element)  -> addFirst(element)
 * - pop()          -> deleteFirst()
 * - top()          -> head.data
 *
 * Why this is efficient:
 * - All stack operations happen at the head of the list, so they are O(1).
 *
 * @param <E> the type of elements stored in the stack
 */
public class LinkedStack<E> implements Stack<E> {

    /** Internal storage for the stack elements */
    private LinkedList<E> linkedStack;

    /**
     * Constructs an empty stack.
     */
    public LinkedStack() {
        linkedStack = new LinkedList<>();
    }

    /**
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return linkedStack.size();
    }

    /**
     * @return true if stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return linkedStack.isEmpty();
    }

    /**
     * Returns the top element without removing it.
     *
     * @return top element, or null if stack is empty
     */
    @Override
    public E top() {
        if (this.linkedStack.getHead() == null) {
            return null;
        }
        return linkedStack.getHead().getData();
    }

    /**
     * Pushes a new element onto the stack.
     *
     * Implementation detail:
     * We add the element to the beginning of the linked list (head).
     *
     * @param element element to be pushed
     */
    @Override
    public void push(E element) {
        linkedStack.addFirst(element);
    }

    /**
     * Removes and returns the top element from the stack.
     *
     * @return removed top element, or null if stack is empty
     */
    @Override
    public E pop() {
        if (this.linkedStack.getHead() == null) {
            return null;
        }

        E temp = linkedStack.getHead().getData(); // store top value
        linkedStack.deleteFirst();                // remove top node
        return temp;
    }
}
