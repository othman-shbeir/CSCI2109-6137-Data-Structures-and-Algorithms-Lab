package problem3solution;

/**
 * LinkedList<E>
 * -------------
 * A custom implementation of a generic Singly Linked List.
 *
 * This list stores elements in nodes, where each node contains:
 * - data  : the element value
 * - next  : reference to the next node
 *
 * The list keeps track of:
 * - head : first node
 * - tail : last node
 * - size : number of stored elements
 *
 * Educational purpose:
 * - Understand how linked lists work internally (nodes + pointers).
 * - Learn insertion/deletion at beginning and end.
 * - Provide a base structure for building a Stack using LinkedList.
 *
 * @param <E> the type of elements stored in the list
 */
public class LinkedList<E> {
    /** Number of elements currently stored in the list */
    private int size;

    /** Reference to the first node in the list */
    private Node<E> head;

    /** Reference to the last node in the list */
    private Node<E> tail;

    /**
     * Constructs an empty linked list.
     * Initially: head = null, tail = null, size = 0
     */
    public LinkedList() {
        this.size = 0;
    }

    /**
     * Returns the head node reference.
     * This is mainly useful for other data structures (like Stack) to access the top/front element.
     *
     * @return the head node
     */
    public Node<E> getHead() {
        return head;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return current size
     */
    public int size() {
        return size;
    }

    /**
     * Checks whether the list is empty.
     *
     * @return true if list has no nodes, false otherwise
     */
    public boolean isEmpty() {
        return this.head == null && this.tail == null;
    }

    /**
     * Inserts an element at the beginning of the list.
     *
     * Cases:
     * - If list is empty: head and tail both point to the new node.
     * - Otherwise: new node becomes the head and points to old head.
     *
     * Time Complexity: O(1)
     *
     * @param data element to be inserted
     */
    public void addFirst(E data) {
        if (isEmpty()) {
            this.head = new Node<E>(data);
            this.tail = this.head;
        } else {
            Node<E> newNode = new Node<E>(data);
            newNode.next = this.head;   // newNode points to old head
            this.head = newNode;        // head now becomes newNode
        }

        size++;
    }

    /**
     * Inserts an element at the end of the list.
     *
     * Cases:
     * - If list is empty: head and tail both point to the new node.
     * - Otherwise: old tail points to new node and tail updates.
     *
     * Time Complexity: O(1)
     *
     * @param data element to be inserted
     */
    public void addLast(E data) {
        if (isEmpty()) {
            this.head = new Node<E>(data);
            this.tail = head;
        } else {
            tail.next = new Node<E>(data); // old tail points to new node
            tail = tail.next;              // tail becomes the new node
        }

        size++;
    }

    /**
     * Adds an element to the end of the list (same as addLast).
     *
     * @param data element to be inserted
     */
    public void add(E data) {
        addLast(data);
    }

    /**
     * Inserts an element at a specific index in the list.
     *
     * Notes:
     * - index = 0          -> addFirst
     * - index = size - 1   -> addLast (as written in this implementation)
     * - otherwise, we traverse until we reach the correct position.
     *
     * Time Complexity: O(n) due to traversal.
     *
     * @param index target position
     * @param data  element to insert
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public void add(int index, E data) {
        if (index == 0) {
            addFirst(data);
        } else if (index == size - 1) {
            addLast(data);
        } else if (index > 0 && index < size - 1) {

            Node<E> current = head;
            Node<E> previus = null;

            int i = 0;
            while (i <= index) {
                if (i == index - 1) {
                    previus = current;  // node before insertion point
                }

                if (i == index) {
                    // Insert new node between previous and current
                    previus.next = new Node<E>(data);
                    previus.next.next = current;
                    size++;
                    break;
                }

                current = current.next;
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
     * - If size == 1: list becomes empty (head = tail = null)
     * - Otherwise: move head to the next node
     *
     * Time Complexity: O(1)
     */
    public void deleteFirst() {
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next; // drop the first node by moving head
        }
        size--;
    }

    /**
     * Removes the last element (tail) from the list.
     *
     * Since this is a singly linked list, to remove the last node we must:
     * - Traverse until we reach the node right before the tail (size - 2)
     * - Set its next to null
     * - Update tail reference
     *
     * Time Complexity: O(n)
     */
    public void deleteLast() {
        if (size == 1) {
            head = null;
            tail = null;
            size--;
        } else {
            Node<E> current = head;
            for (int i = 0; i < size; i++) {
                if (i == size - 2) {     // node before the last node
                    current.next = null; // remove last node
                    tail = current;      // update tail
                    size--;
                }
                current = current.next;
            }
        }
    }

    /**
     * Removes the element at a specific index.
     *
     * Cases:
     * - index = 0        -> deleteFirst
     * - index = size - 1 -> deleteLast
     * - otherwise, bypass the node at index by changing links.
     *
     * Time Complexity: O(n)
     *
     * @param index position to remove
     */
    public void delete(int index) {
        if (index == 0) {
            deleteFirst();
        } else if (index == size - 1) {
            deleteLast();
        } else {
            Node<E> current = head;

            // We stop at (index - 1), then link it to (index + 1)
            for (int i = 0; i < size; i++) {
                if (index - 1 == i) {
                    current.next = current.next.next;
                    size--;
                    break;
                }
                current = current.next;
            }
        }
    }

    /**
     * Returns the element stored at a specific index.
     *
     * - index 0        -> head
     * - index size-1   -> tail
     * - otherwise, traverse to index
     *
     * Time Complexity: O(n)
     *
     * @param index target position
     * @return element at index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public E get(int index) {
        if (index == 0) {
            return head.data;
        } else if (index == size - 1) {
            return tail.data;
        } else if (index > 0 && index < size - 1) {
            Node<E> current = head;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    return current.data;
                }
                current = current.next;
            }
        } else {
            throw new IndexOutOfBoundsException("invalid index");
        }
        return null;
    }

    /**
     * Node<T>
     * -------
     * Represents one node in the singly linked list.
     *
     * Each node holds:
     * - data: the value
     * - next: reference to the next node
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
