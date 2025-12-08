package simpleatmsystem;

/**
 * The SavingsAccount class represents a savings bank account.
 * 
 * A savings account:
 * - Does NOT apply transaction fees on withdrawals
 * - Allows interest to be calculated and added to the balance
 * 
 * This class provides a specialized implementation of withdrawal
 * behavior and introduces interest calculation functionality.
 */
public class SavingsAccount extends Account {

    /** Interest rate applied to the account balance */
    private double interestRate;

    /**
     * Constructs a SavingsAccount with the specified details.
     *
     * @param accountNumber   the unique account number
     * @param ownerName       the full name of the account owner
     * @param pin             the PIN used for authentication
     * @param initialBalance  the starting account balance
     * @param interestRate    the interest rate applied to the balance
     */
    public SavingsAccount(String accountNumber,
                          String ownerName,
                          int pin,
                          double initialBalance,
                          double interestRate) {

        // Initialize base Account properties
        super(accountNumber, ownerName, pin, initialBalance);

        this.interestRate = interestRate;
    }

    /**
     * Withdraws a specified amount from the savings account.
     *
     * Unlike a checking account, no transaction fee is applied.
     *
     * @param amount the amount to withdraw
     * @throws IllegalArgumentException     if the withdrawal amount is invalid
     * @throws InsufficientFundsException  if the account balance is insufficient
     */
    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        // Validate withdrawal amount
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive!");
        }

        // Check if balance is sufficient
        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Insufficient Funds in Savings Account!"
            );
        }

        // Deduct the requested amount
        balance -= amount;

        // Record the successful withdrawal
        record("Savings Withdrawal: " + amount + " | New Balance: " + balance);
    }

    /**
     * Applies interest to the current balance.
     *
     * The formula used:
     * interestAmount = balance × interestRate
     *
     * The calculated interest is added directly to the account balance.
     */
    public void applyInterest() {
        // Calculate the interest amount
        double interestAmount = balance * this.interestRate;

        // Add interest to the balance
        balance += interestAmount;

        // Record the interest transaction
        record("Interest Applied: " + interestAmount
                + " | New Balance: " + balance);
    }

}
