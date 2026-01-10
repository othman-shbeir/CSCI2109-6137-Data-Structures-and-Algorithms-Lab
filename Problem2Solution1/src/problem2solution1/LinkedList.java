package problem2solution1;

import java.util.Iterator;

/**
 * LinkedList<E>
 * -------------
 * A custom generic implementation of a Doubly Linked List that supports:
 * - Adding elements at the beginning, end, or a specific index
 * - Removing elements from the beginning, end, or a specific index
 * - Accessing elements by index (linear traversal)
 * - Iteration using Java's enhanced for-loop (Iterable + Iterator)
 *
 * Educational goals:
 * - Understand node-based dynamic data structures
 * - Practice pointer manipulation (next/prev)
 * - Learn how Iterable enables: for(E x : list)
 * - Apply linked lists to solve the Josephus elimination problem (Duck method)
 *
 * @param <E> the type of elements stored in this linked list
 */
public class LinkedList<E> implements Iterable<E> {

    /** Number of elements currently stored in the list */
    private int size;

    /** Reference to the first node in the list */
    private Node<E> head;

    /** Reference to the last node in the list */
    private Node<E> tail;

    /**
     * Helper reference used by the iterator to walk through the list.
     * (This approach works for teaching, but in production we usually keep
     * iterator state inside the Iterator object itself.)
     */
    private Node<E> start;

    /**
     * Constructs an empty LinkedList.
     * Initially, head and tail are null and size is 0.
     */
    public LinkedList() {
        this.size = 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return current list size
     */
    public int size() {
        return size;
    }

    /**
     * Checks whether the list is empty.
     *
     * @return true if the list has no nodes, false otherwise
     */
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    /**
     * Inserts a new element at the beginning of the list.
     *
     * Cases:
     * - If the list is empty, head and tail will both point to the new node.
     * - Otherwise, we link the new node before the current head.
     *
     * Time Complexity: O(1)
     *
     * @param data element to be added
     */
    public void addFirst(E data) {
        Node<E> newNode = new Node<E>(data);

        if (isEmpty()) {
            head = newNode;
            tail = head;
        } else {
            newNode.next = head;
            newNode.setPrev(null);
            head.setPrev(newNode);
            head = newNode;
        }

        size++;
    }

    /**
     * Inserts a new element at the end of the list.
     *
     * Cases:
     * - If the list is empty, head and tail will both point to the new node.
     * - Otherwise, we link the new node after the current tail.
     *
     * Time Complexity: O(1)
     *
     * @param data element to be added
     */
    public void addLast(E data) {
        Node<E> newNode = new Node<E>(data);

        if (isEmpty()) {
            head = newNode;
            tail = head;
        } else {
            tail.next = newNode;
            newNode.setPrev(tail);
            tail = newNode;
        }
        size++;
    }

    /**
     * Adds an element to the end of the list.
     * This is a convenience method equivalent to addLast(data).
     *
     * @param data element to be added
     */
    public void add(E data) {
        addLast(data);
    }

    /**
     * Inserts an element at a specified index.
     *
     * Notes:
     * - index = 0      -> addFirst
     * - index = size-1 -> addLast (as written in this implementation)
     * - otherwise, we traverse until reaching the insertion position
     *
     * Time Complexity: O(n) due to traversal
     *
     * @param index position where the element should be inserted
     * @param data  element to insert
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public void add(int index, E data) {
        if (index == 0) {
            addFirst(data);
        } else if (index == size - 1) {
            addLast(data);
        } else if (index > 0 && index < size - 1) {
            Node<E> newNode = new Node<E>(data);

            Node<E> current = head;
            Node<E> previous = null;

            int i = 0;
            while (i <= index) {
                if (i == index - 1) {
                    previous = current;
                }
                if (i == index) {

                    // Link: previous -> newNode -> current
                    previous.next = newNode;
                    current.setPrev(newNode);
                    newNode.next = current;
                    newNode.setPrev(previous);
                    size++;
                    break;
                }

                current = current.getNext();
                i++;
            }

        } else {
            throw new IndexOutOfBoundsException("invalid index");
        }
    }

    /**
     * Removes the first element (head) from the list.
     *
     * Cases:
     * - If there is only one node, head and tail become null.
     * - Otherwise, move head to the next node and clean old links.
     *
     * Time Complexity: O(1)
     */
    public void removeFirst() {
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.prev.next = null; // disconnect old head (teaching approach)
            head.setPrev(null);
        }
        size--;
    }

    /**
     * Removes the last element (tail) from the list.
     *
     * Cases:
     * - If there is only one node, head and tail become null.
     * - Otherwise, move tail to the previous node and clean old links.
     *
     * Time Complexity: O(1)
     */
    public void removeLast() {
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrev();
            tail.next.setPrev(null); // disconnect old tail
            tail.next = null;
        }
        size--;
    }

    /**
     * Removes the last element from the list.
     * This is a convenience method equivalent to removeLast().
     */
    public void remove() {
        removeLast();
    }

    /**
     * Removes the element at a specified index.
     *
     * Cases:
     * - index = 0        -> removeFirst
     * - index = size - 1 -> removeLast
     * - otherwise, traverse to the node at index and unlink it
     *
     * Time Complexity: O(n) due to traversal
     *
     * @param index position of the element to remove
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public void remove(int index) {

        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else if (index < size && index > 0) {

            Node<E> current = head;
            Node<E> prevoius = null;

            for (int i = 0; i < size; i++) {
                if (index - 1 == i) {
                    prevoius = current;
                }
                if (i == index) {
                    // Unlink current: previous -> current -> next
                    prevoius.next = current.getNext();
                    current.next.setPrev(prevoius);

                    // Disconnect current from the list
                    current.next = null;
                    current.setPrev(null);
                    break;
                }
                current = current.getNext();
            }

            size--;
        } else {
            throw new IndexOutOfBoundsException("invalid index");
        }
    }

    /**
     * Returns the element at a specified index.
     *
     * Notes:
     * - index = 0        -> head
     * - index = size - 1 -> tail
     * - otherwise, traverse from head until reaching the index
     *
     * Time Complexity: O(n) due to traversal
     *
     * @param index element position
     * @return the element at the given index, or null if not found
     */
    public E get(int index) {
        if (index == 0) {
            return head.data;
        } else if (index == size - 1) {
            return tail.data;
        } else if (index > 0 && index < size) {
            Node<E> current = head;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    return current.data;
                }
                current = current.getNext();
            }
        }
        return null;
    }

    /**
     * Returns an iterator that allows this linked list to be used in a foreach loop:
     * for (E item : list) { ... }
     *
     * Iteration starts from the head and moves forward using next references.
     *
     * @return an Iterator over the elements of the linked list (from head to tail)
     */
    @Override
    public Iterator<E> iterator() {
        start = head;

        return new Iterator<E>() {

            /**
             * Checks if there are more nodes to visit.
             *
             * @return true if current iterator position is not null
             */
            @Override
            public boolean hasNext() {
                return start != null;
            }

            /**
             * Returns the current element and moves the iterator forward.
             *
             * @return the next element in the iteration order
             */
            @Override
            public E next() {
                E temp = start.getData();
                start = start.getNext();
                return temp;
            }
        };
    }

    /**
     * @return the head node (mainly for debugging / teaching)
     */
    public Node<E> getHead() {
        return head;
    }

    /**
     * Sets the head node reference.
     * (This is usually avoided in production, but useful for controlled exercises.)
     *
     * @param head new head node reference
     */
    public void setHead(Node<E> head) {
        this.head = head;
    }

    /**
     * @return the tail node (mainly for debugging / teaching)
     */
    public Node<E> getTail() {
        return tail;
    }

    /**
     * Sets the tail node reference.
     * (This is usually avoided in production, but useful for controlled exercises.)
     *
     * @param tail new tail node reference
     */
    public void setTail(Node<E> tail) {
        this.tail = tail;
    }

    /**
     * Node<T>
     * -------
     * Represents a single node in a doubly linked list.
     *
     * Each node stores:
     * - data: the value
     * - next: reference to the next node
     * - prev: reference to the previous node
     */
    class Node<T> {

        private T data;
        private Node<T> next;
        private Node<T> prev;

        /**
         * Constructs a node holding the given data.
         *
         * @param data value stored in this node
         */
        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public Node<T> getNext() {
            return next;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }
    }

    /**
     * Duck (Josephus Problem Solver)
     * ------------------------------
     * Solves a classic elimination problem (Josephus problem) using a circular linked list.
     *
     * How it works:
     * - First, the method temporarily makes the list circular by linking:
     *   tail.next -> head and head.prev -> tail
     * - Then it repeatedly counts k steps and removes the k-th node
     * - The process continues until only one node remains
     *
     * Notes for students:
     * - This method demonstrates a powerful application of linked lists.
     * - It shows how we can "wrap around" the list using circular connections.
     *
     * Time Complexity: O(n * k) in the worst case, because we move k steps for each removal.
     *
     * @param str the linked list containing the elements to eliminate
     * @param k   the step size (every k-th element is removed)
     * @return the last remaining element after eliminations
     */
    E Duck(LinkedList<E> str, int k) {
        // Convert the list into a circular linked list
        str.tail.setNext(str.head);
        str.head.setPrev(str.tail);

        Node<E> current = str.head;

        // Keep eliminating until one element remains
        while (str.size() != 1) {

            // Move (k-1) steps forward
            int i = 1;
            while (i < k) {
                current = current.getNext();
                i++;
            }

            // Remove the current node from the circular structure
            Node<E> prev = current.getPrev();
            Node<E> succ = current.getNext();

            prev.setNext(succ);
            succ.setPrev(prev);

            // If we removed head or tail, update references
            if (current == str.head) {
                str.head = succ;
            }
            if (current == str.tail) {
                str.tail = prev;
            }

            // Decrease size after removal
            str.size--;
        }

        // The remaining node is the winner
        return str.getHead().data;
    }
}
