package simpleatmsystem;

/**
 * The TransactionsHistory class implements a fixed-size circular buffer
 * for storing and displaying ATM transaction descriptions.
 * 
 * It keeps track of:
 * - The most recent transactions only
 * - The current insertion index
 * - The actual number of stored transactions
 * 
 * This design ensures efficient memory usage and constant-time insertions.
 */
public class TransactionsHistory {

    /** Array that stores transaction descriptions */
    private String[] transactions;

    /** Index pointing to the next position for insertion */
    private int index;

    /** Number of valid transactions currently stored */
    private int count;

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

        this.transactions = new String[capacity];
        this.index = 0;
        this.count = 0;
    }

    /**
     * Adds a new transaction description to the history.
     * 
     * If the history is full, the oldest transaction is overwritten
     * using circular indexing.
     *
     * @param description the transaction description to store
     */
    public void add(String description) {
        // Store transaction at the current index
        this.transactions[index] = description;

        // Move index forward in a circular manner
        this.index = (this.index + 1) % this.transactions.length;

        // Increase count only if we haven't reached full capacity
        if (count < this.transactions.length) {
            this.count++;
        }
    }

    /**
     * Prints the last N transactions from most recent to least recent.
     *
     * @param n the number of recent transactions to display
     */
    public void printLast(int n) {
        // Case 1: No transactions exist yet
        if (this.count == 0) {
            System.out.println("No transactions yet!");
            return;
        }

        // Adjust N if it exceeds the number of stored transactions
        if (n > this.count) {
            n = this.count;
        }

        System.out.println("Last " + n + " transactions:");

        int printedTransactions = 0;

        // Start from the most recent transaction
        int current = ((index - 1) + this.transactions.length) % this.transactions.length;

        // Print transactions backward in circular order
        while (printedTransactions < n) {
            System.out.println("- " + this.transactions[current]);

            // Move backward circularly
            current = ((current - 1) + transactions.length) % this.transactions.length;

            // IMPORTANT: increment to avoid infinite loop
            printedTransactions++;
        }
    }

}
