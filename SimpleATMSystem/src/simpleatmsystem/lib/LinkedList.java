package simpleatmsystem.lib;


import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {

    private int size;
    private Node<E> head;
    private Node<E> tail;
    private Node<E> start;

    public LinkedList() {
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }

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

    public void add(E data) {
        addLast(data);
    }

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

    public void removeFirst() {
        if (size == 1) {
            head = null;
            tail = null;
        } else {

            head = head.getNext();
            head.prev.next = null;
            head.setPrev(null);
        }
        size--;
    }

    public void removeLast() {
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrev();
            tail.next.setPrev(null);
            tail.next = null;

        }
        size--;
    }

    public void remove() {
        removeLast();
    }

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
                    prevoius.next = current.getNext();
                    current.next.setPrev(prevoius);
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

    @Override
    public Iterator<E> iterator() {
        start = head;
        return new Iterator() {

            @Override
            public boolean hasNext() {
                return start != null;
            }

            @Override
            public E next() {
                E temp = start.getData();
                start = start.getNext();
                return temp;
            }
        };
    }

    public Node<E> getHead() {
        return head;
    }

    public void setHead(Node<E> head) {
        this.head = head;
    }

    public Node<E> getTail() {
        return tail;
    }

    public void setTail(Node<E> tail) {
        this.tail = tail;
    }

    class Node<T> {

        private T data;
        private Node<T> next;
        private Node<T> prev;

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

    E Duck(LinkedList<E> str, int k) {
        str.tail.setNext(str.head);
        str.head.setPrev(str.tail);
        Node<E> current = str.head;

        while (str.size() != 1) {
            int i = 1;
            while (i < k) {
                current = current.getNext();
                i++;
            }

            Node<E> prev = current.getPrev();
            Node<E> succ = current.getNext();

            prev.setNext(succ);
            succ.setPrev(prev);
            if (current == str.head) {
                str.head = succ;
            }
            if (current == str.tail) {
                str.tail = prev;
            }
            str.size--;
        }
        return str.getHead().data;
    }

}
