package simpleatmsystem.models;

import simpleatmsystem.exceptions.InsufficientFundsException;

/**
 * The CheckingAccount class represents a checking (current) bank account.
 * 
 * Unlike savings accounts, a checking account applies a transaction fee
 * for every withdrawal operation.
 * 
 * This class overrides the withdraw method to account for this additional fee.
 */
public class CheckingAccount extends Account {

    /** Fixed fee applied to each withdrawal transaction */
    private double transactionFee;

    /**
     * Constructs a CheckingAccount with the specified details.
     *
     * @param accountNumber   the unique account number
     * @param ownerName       the account owner's full name
     * @param pin             the PIN used for authentication
     * @param initialBalance  the initial balance when the account is created
     * @param transactionFee the withdrawal transaction fee
     *
     * @throws IllegalArgumentException if the transaction fee is negative
     */
    public CheckingAccount(String accountNumber, String ownerName,
                           int pin, double initialBalance, double transactionFee) {

        // Call parent constructor to initialize common account properties
        super(accountNumber, ownerName, pin, initialBalance);

        // Validate transaction fee
        if (transactionFee < 0) {
            throw new IllegalArgumentException("Transaction Fee must be positive!");
        }

        this.transactionFee = transactionFee;
    }

    /**
     * Withdraws a specified amount from the checking account.
     * 
     * The total deducted amount includes:
     * - The withdrawal amount
     * - The transaction fee
     *
     * @param amount the amount to withdraw (excluding the transaction fee)
     * @throws IllegalArgumentException     if the withdrawal amount is invalid
     * @throws InsufficientFundsException  if the balance is not enough to cover
     *                                     both the withdrawal and the fee
     */
    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        // Validate withdrawal amount
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive!");
        }

        // Calculate the total amount to be deducted including the fee
        double totalAmount = amount + this.transactionFee;

        // Check for sufficient funds before performing withdrawal
        if (totalAmount > balance) {
            throw new InsufficientFundsException(
                    "Insufficient Funds in Checking Account!"
            );
        }

        // Deduct the total amount from the balance
        balance -= totalAmount;

        // record into history + tree using record(amount, msg)
        record(totalAmount, "Checking Account Withdrawal: " + amount
                + " | Fee: " + this.transactionFee
                + " | New Balance: " + balance);
    }

}