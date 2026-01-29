package simpleatmsystem.lib;

import java.util.Comparator;

public class BinaryTree<E> {

    private Node<E> root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(E i) {
        this.root = new Node<>(i);
    }

    // =========================================================
    // INSERT (supports duplicates by going RIGHT when cmp == 0)
    // =========================================================
    private Node<E> addRecursive(Node<E> current, E value, Comparator<E> c) {

        if (current == null) {
            return new Node<>(value);
        }

        int cmp = c.compare(value, current.value);

        if (cmp < 0) {
            current.left = addRecursive(current.left, value, c);
        } else {
            // cmp > 0 OR cmp == 0 (duplicates)
            current.right = addRecursive(current.right, value, c);
        }

        return current;
    }

    public void add(E value, Comparator<E> c) {
        root = addRecursive(root, value, c);
    }

    // =========================================================
    // DELETE
    // =========================================================
    private Node<E> deleteNode(Node<E> root, E value, Comparator<E> c) {

        if (root == null) { // base case
            return root;
        }

        int cmp = c.compare(value, root.value);

        if (cmp < 0) {
            root.left = deleteNode(root.left, value, c);

        } else if (cmp > 0) {
            root.right = deleteNode(root.right, value, c);

        } else {
            // Found the node to delete (one occurrence)

            // Case 1: no children
            if (root.left == null && root.right == null) {
                root = null;

                // Case 2: one child (right)
            } else if (root.left == null) {
                root = root.right;

                // Case 2: one child (left)
            } else if (root.right == null) {
                root = root.left;

                // Case 3: two children
            } else {
                Node<E> temp = findMinimum(root.right); // successor
                root.value = temp.value;
                root.right = deleteNode(root.right, temp.value, c);
            }
        }

        return root;
    }

    private Node<E> findMinimum(Node<E> root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public void delete(E value, Comparator<E> c) {
        this.root = this.deleteNode(this.root, value, c);
    }

    // =========================================================
    // CONTAINS
    // =========================================================
    private boolean containsNodeRecursive(Node<E> current, E value, Comparator<E> c) {

        if (current == null) {
            return false;
        }
        if (c.compare(value, current.value) == 0) {
            return true;
        }

        return c.compare(value, current.value) < 0
                ? containsNodeRecursive(current.left, value, c)
                : containsNodeRecursive(current.right, value, c);
    }

    public boolean contains(E value, Comparator<E> c) {
        return containsNodeRecursive(root, value, c);
    }

    // =========================================================
    // DFS TRAVERSALS (node-based)
    // =========================================================
    public void inOrder(Node<E> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.value);
            inOrder(node.right);
        }
    }

    public void preOrder(Node<E> node) {
        if (node != null) {
            System.out.println(node.value);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void postOrder(Node<E> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.value);
        }
    }

    // =========================================================
    // DFS TRAVERSALS (wrappers, so users don't access root)
    // =========================================================
    public void inOrder() {
        inOrder(root);
    }

    public void preOrder() {
        preOrder(root);
    }

    public void postOrder() {
        postOrder(root);
    }

    // =========================================================
    // BFS TRAVERSAL
    // =========================================================
    public void breadthTraverse() {
        if (this.root == null) {
            return;
        }

        Queue<Node<E>> nodes = new LinkedQueue<>();
        nodes.enqueue(root);

        while (!nodes.isEmpty()) {
            Node<E> node = nodes.dequeue();

            System.out.println(node.value);

            if (node.left != null) {
                nodes.enqueue(node.left);
            }

            if (node.right != null) {
                nodes.enqueue(node.right);
            }
        }
    }

    // Range Search 
    public MyArrayList<E> rangeSearch(E min, E max, Comparator<E> c) {
        MyArrayList result = new MyArrayList();
        rangeSearchRecursive(root, min, max, c, result);
        return result;
    }

    public void rangeSearchRecursive(Node<E> node, E min, E max, Comparator<E> c, MyArrayList<E> result) {
        if (node == null) {
            return;
        }

        if (c.compare(min, node.value) < 0) {
            rangeSearchRecursive(node.left, min, max, c, result);
        }

        if (c.compare(min, node.value) <= 0 && c.compare(node.value, max) <= 0) {
            result.add(node.value);
        }

        if (c.compare(node.value, max) < 0) {
            rangeSearchRecursive(node.right, min, max, c, result);
        }
    }

    // =========================================================
    // NODE
    // =========================================================
    class Node<E> {

        E value;
        Node<E> left;
        Node<E> right;

        Node(E value) {
            this.value = value;
            right = null;
            left = null;
        }
    }
}
