package simpleatmsystem.models;

import simpleatmsystem.lib.LinkedList;

/**
 * The TransactionsHistory class implements a fixed-size circular buffer for
 * storing and displaying ATM transaction descriptions.
 *
 * It keeps track of: - The most recent transactions only - The current
 * insertion index - The actual number of stored transactions
 *
 * This design ensures efficient memory usage and constant-time insertions.
 */
public class TransactionsHistory {


    private LinkedList<String> transactions;
    private int capacity;

    /**
     * Constructs a TransactionsHistory object with a fixed capacity.
     *
     * @param capacity the maximum number of transactions to store
     * @throws IllegalArgumentException if capacity is zero or negative
     */
    public TransactionsHistory(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive!");
        }
        this.capacity = capacity;
        this.transactions = new LinkedList();

    }

    /**
     * Adds a new transaction description to the history.
     *
     * If the history is full, the oldest transaction is overwritten using
     * circular indexing.
     *
     * @param description the transaction description to store
     */
    public void add(String description) {
        this.transactions.addFirst(description);
        if (this.transactions.size() > capacity) {
            this.transactions.removeLast();
        }
    }

    /**
     * Prints the last N transactions from most recent to least recent.
     *
     * @param n the number of recent transactions to display
     */
    public void printLast(int n) {
        // Case 1: No transactions exist yet
        if (this.transactions.isEmpty()) {
            System.out.println("No transactions yet!");
            return;
        }

        // Adjust N if it exceeds the number of stored transactions
        if (n > this.transactions.size()) {
            n = this.transactions.size();
        }

        System.out.println("Last " + n + " transactions:");

        int printedTransactionsCount = 0;
        for (String transacrion : this.transactions) {
            System.out.println("- " + transacrion);
            printedTransactionsCount++;
            if (printedTransactionsCount == n) {
                break;
            }
        }

    }

}
