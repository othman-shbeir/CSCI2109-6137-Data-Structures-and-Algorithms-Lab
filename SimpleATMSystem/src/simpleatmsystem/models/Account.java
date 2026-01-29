package simpleatmsystem.models;

import simpleatmsystem.comparators.AmountComparator;
import simpleatmsystem.exceptions.InsufficientFundsException;
import simpleatmsystem.exceptions.InsufficientFundsException;
import simpleatmsystem.lib.BinaryTree;

/**
 * The Account class represents a generic bank account in the ATM system.
 *
 * This is an abstract class and serves as a base class for specific account
 * types such as SavingsAccount, CurrentAccount, etc.
 *
 * It provides common functionality such as: - PIN authentication - Deposits -
 * Transaction history tracking - Balance management
 */
public abstract class Account {

    /**
     * Unique account number used to identify the account
     */
    private String accountNumber;

    /**
     * Full name of the account owner
     */
    private String ownerName;

    /**
     * Personal Identification Number (PIN) used for authentication
     */
    int pin;

    /**
     * Current balance of the account (accessible by subclasses)
     */
    protected double balance;

    /**
     * Stores the history of all transactions performed on the account
     */
    private TransactionsHistory history;

    private BinaryTree<TransactionsEntry> transactionsBinaryTree = new BinaryTree<>();
    private AmountComparator amoutComparator = new AmountComparator();

    /**
     * Constructs a new Account with essential account details.
     *
     * @param accountNumber the unique account number
     * @param ownerName the name of the account owner
     * @param pin the PIN used for authentication
     * @param initialBalance the starting balance of the account
     * @throws IllegalArgumentException if the initial balance is not positive
     */
    public Account(String accountNumber, String ownerName, int pin, double initialBalance) {
        // Prevent creation of an account with invalid balance
        if (initialBalance <= 0) {
            throw new IllegalArgumentException("Balance must be positive!");
        }

        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.pin = pin;
        this.balance = initialBalance;

        // Initialize transaction history with fixed capacity
        this.history = new TransactionsHistory(20);

        // Record the account creation as the first transaction
        this.history.add("Account is created with balance " + this.balance);
    }

    /**
     * Checks whether the entered PIN matches the account's actual PIN.
     *
     * @param inputPIN the PIN entered by the user
     * @return true if the PIN is correct, false otherwise
     */
    public boolean checkPIN(int inputPIN) {
        return this.pin == inputPIN;
    }

    /**
     * Deposits a valid positive amount into the account.
     *
     * @param amount the amount to deposit
     * @throws IllegalArgumentException if the amount is zero or negative
     */
    public void deposite(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive!");
        }

        this.balance = this.balance + amount;

        String msg = "Deposited: " + amount + " | New Balance: " + this.balance;

        // history (LinkedList-based)
        this.history.add(msg);

        // add to transactions tree
        this.transactionsBinaryTree.add(new TransactionsEntry(amount, msg),
                this.amoutComparator);

    }

    /**
     * Withdraws money from the account.
     *
     * This method is abstract because withdrawal rules may differ between
     * account types.
     *
     * @param amount the amount to withdraw
     * @throws InsufficientFundsException if the balance is insufficient
     */
    public abstract void withdraw(double amount) throws InsufficientFundsException;

    /**
     * Records a custom transaction message in the history.
     *
     * This method is protected so it can only be accessed by subclasses of
     * Account.
     *
     * @param message the transaction description to record
     */
    // ===================== Recording =====================
    // For messages with amount: record into BOTH history + tree
    protected void record(double amount, String message) {
        this.history.add(message);

        // add to transactions tree
        this.transactionsBinaryTree.add(new TransactionsEntry(amount, message),
                this.amoutComparator);
    }

    /**
     * Prints the last N transactions from the transaction history.
     *
     * @param n the number of transactions to display
     */
    public void printLastNTransactions(int n) {
        this.history.printLast(n);
    }

    /**
     * Returns the run-time type of the account (e.g., SavingsAccount,
     * CurrentAccount, etc.).
     *
     * @return the account type as a string
     */
    public String getAccountType() {
        return this.getClass().getSimpleName();
    }

    /**
     * Returns the account number.
     *
     * @return the account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Returns the owner's full name.
     *
     * @return the owner's name
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Returns the current account balance.
     *
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    public BinaryTree<TransactionsEntry> getTransactionsBinaryTree() {
        return transactionsBinaryTree;
    }

    public AmountComparator getAmoutComparator() {
        return amoutComparator;
    }
    
    

}
