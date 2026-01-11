package problem4solution;

/**
 * LinkedList<E>
 * -------------
 * A custom implementation of a generic singly linked list.
 *
 * This list is used internally by the Queue implementation.
 *
 * Each node contains:
 * - data : stored element
 * - next : reference to the next node
 *
 * @param <E> type of elements stored in the list
 */
public class LinkedList<E> {

    /** Number of elements in the list */
    private int size;

    /** Reference to the first node */
    private Node<E> head;

    /** Reference to the last node */
    private Node<E> tail;

    /**
     * Constructs an empty linked list.
     */
    public LinkedList() {
        this.size = 0;
    }

    /**
     * @return reference to the head node
     */
    public Node<E> getHead() {
        return head;
    }

    /**
     * @return number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Checks whether the list is empty.
     *
     * @return true if list has no elements
     */
    public boolean isEmpty() {
        return this.head == null && this.tail == null;
    }

    /**
     * Inserts an element at the beginning of the list.
     *
     * Time Complexity: O(1)
     *
     * @param data element to insert
     */
    public void addFirst(E data) {
        if (isEmpty()) {
            this.head = new Node<E>(data);
            this.tail = this.head;
        } else {
            Node<E> newNode = new Node<E>(data);
            newNode.next = this.head;
            this.head = newNode;
        }
        size++;
    }

    /**
     * Inserts an element at the end of the list.
     *
     * Time Complexity: O(1)
     *
     * @param data element to insert
     */
    public void addLast(E data) {
        if (isEmpty()) {
            this.head = new Node<E>(data);
            this.tail = head;
        } else {
            tail.next = new Node<E>(data);
            tail = tail.next;
        }
        size++;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param data element to insert
     */
    public void add(E data) {
        addLast(data);
    }

    /**
     * Removes the first element of the list.
     *
     * Time Complexity: O(1)
     */
    public void deleteFirst() {
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
        }
        size--;
    }

    /**
     * Node<T>
     * -------
     * Represents a single node in the linked list.
     */
    class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }

        public Node<T> getNext() {
            return this.next;
        }
    }
}
