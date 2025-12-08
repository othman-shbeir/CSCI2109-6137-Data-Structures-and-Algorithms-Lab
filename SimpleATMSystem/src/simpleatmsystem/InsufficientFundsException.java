package simpleatmsystem;

/**
 * Custom exception thrown when a withdrawal operation
 * is attempted with insufficient account balance.
 * 
 * This exception is used to enforce financial safety rules
 * in the ATM system and prevents the account from
 * being overdrawn.
 */
public class InsufficientFundsException extends Exception {

    /**
     * Constructs a new InsufficientFundsException with
     * a detailed error message.
     *
     * @param message a descriptive message explaining the error
     */
    public InsufficientFundsException(String message) {
        super(message);
    }

}
