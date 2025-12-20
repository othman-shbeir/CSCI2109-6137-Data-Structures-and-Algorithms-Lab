package simpleatmsystem;

import java.util.Scanner;

/**
 * SimpleATMSystem --------------- This class contains the main (menu-driven)
 * program for a simple ATM system.
 *
 * What the program does: 1) Stores up to 100 bank accounts in an array
 * (Account[]). 2) Allows the user to: - Create a new account (Savings or
 * Checking) - Deposit into an account - Withdraw from an account - Show the
 * last N transactions for an account - Show an account summary (basic details)
 *
 * Important notes for students: - The program uses ARRAYS only (not ArrayList).
 * - Each operation asks the user to authenticate (Account Number + PIN). - The
 * actual account rules (withdraw rules, deposit rules, fees, interest,
 * transaction history) are handled inside the Account classes.
 */
public class SimpleATMSystem {

    /**
     * Entry point of the program.
     *
     * Steps: - Create a Scanner for reading user input. - Create an array of
     * Account objects to store accounts. - Show the main menu in an infinite
     * loop until the user chooses Exit.
     *
     * @param args command line arguments (not used here)
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Stores the user's menu choice
        int choice = 0;

        // Array to store up to 100 accounts (fixed size)
        Account[] accounts = new Account[100];

        // Tracks how many accounts are currently stored in the array
        int count = 0;

        System.out.println("Welcome to the ATM System");

        // Infinite loop: keep showing the menu until user exits (choice 0)
        while (true) {
            printMenu();
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    // Create a new account (Savings or Checking)
                    Account newAccount = createAccount();

                    // NOTE: This debug line prints the account number for testing.
                    // In a real system, we usually remove debug prints.
                    if (newAccount != null) {
                        System.out.println("Debug add: " + newAccount.getAccountNumber());
                    }

                    // If creation was successful, store the new account in the array
                    if (newAccount != null) {
                        // IMPORTANT: In a real program, we should also check:
                        // if (count >= accounts.length) { ... } to avoid overflow.
                        accounts[count] = newAccount;
                        count++;
                    }
                    break;

                case 2:
                    // Deposit operation
                    depositToAccount(accounts);
                    break;

                case 3:
                    // Withdraw operation
                    withdrawFromAccount(accounts);
                    break;

                case 4:
                    // Show last N transactions
                    showLastNTransactions(accounts);
                    break;

                case 5:
                    // Print account summary
                    showAccountSummary(accounts);
                    break;

                case 0:
                    // Exit the program immediately
                    System.exit(0);
                    break;

                default:
                    System.out.println("Wrong Input!");
            }
        }
    }

    /**
     * Prints the main menu for the ATM program. This method only prints text;
     * it does not read input.
     */
    public static void printMenu() {
        System.out.println("============= Main Menu ==============");
        System.out.println("1. Create new Account");
        System.out.println("2. Deposite");
        System.out.println("3. Withdraw");
        System.out.println("4. Show the last N Transactions");
        System.out.println("5. Show the account summary");
        System.out.println("0. Exit");
        System.out.println("=====================================");
    }

    /**
     * Creates a new account by asking the user for: - Account type (Savings or
     * Checking) - Account number - Owner name - PIN - Initial balance - Then
     * type-specific data: - Savings: interest rate - Checking: transaction fee
     *
     * If the user enters an invalid account type, the method returns null.
     *
     * @return a new Account object (SavingsAccount or CheckingAccount), or null
     * if invalid input
     */
    public static Account createAccount() {

        System.out.println("Create new Account: Set the Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Checking Account");

        Scanner input = new Scanner(System.in);
        int inputAccountType = input.nextInt();

        // Read account number (String)
        System.out.println("Enter the Account Number:");
        input.nextLine(); // consume the leftover newline after nextInt()
        String accountNumber = input.nextLine();

        // Read owner name
        System.out.println("Enter the Account Owner Name:");
        String owenrName = input.nextLine();

        // Read PIN (int)
        System.out.println("Enter the Account PIN:");
        int pin = input.nextInt();

        // Read initial balance (double)
        System.out.println("Enter the Account initial Balance:");
        double initialBance = input.nextDouble();

        // Create the correct account type based on user choice
        switch (inputAccountType) {
            case 1: {
                // Savings account needs an interest rate
                System.out.println("Enter the Account interest rate:");
                double interestRate = input.nextDouble();

                Account savingsAccount = new SavingsAccount(
                        accountNumber,
                        owenrName,
                        pin,
                        initialBance,
                        interestRate
                );

                return savingsAccount;
            }
            case 2: {
                // Checking account needs a transaction fee
                System.out.println("Enter the Account transaction fee:");
                double transactionFee = input.nextDouble();

                Account checkingAccount = new CheckingAccount(
                        accountNumber,
                        owenrName,
                        pin,
                        initialBance,
                        transactionFee
                );

                return checkingAccount;
            }
            default: {
                // Invalid account type
                System.out.println("Invalid Input!");
                return null;
            }
        }
    }

    /**
     * Authenticates (verifies) an account by: 1) Asking the user for an account
     * number 2) Searching for that account in the accounts array 3) If found,
     * asking for the PIN 4) Checking the PIN using account.checkPIN(pin)
     *
     * If authentication succeeds, returns the Account object. Otherwise,
     * returns null.
     *
     * @param accounts array of stored accounts
     * @return the authenticated Account, or null if not found / wrong PIN
     */
    public static Account authAccount(Account[] accounts) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the Account Number");
        String accountNumber = input.nextLine();

        // Linear search through the array (because we are using arrays, not hash maps)
        for (Account account : accounts) {

            // Skip empty (null) positions in the array
            if (account == null) {
                continue;
            }

            // Compare account numbers (trim spaces + ignore case)
            if (account.getAccountNumber().equalsIgnoreCase(accountNumber.trim())) {

                System.out.println("Enter the Account PIN");
                int inputPIN = input.nextInt();

                // If PIN correct, return the account
                if (account.checkPIN(inputPIN)) {
                    return account;
                } else {
                    // Account number matched but PIN was wrong
                    return null;
                }
            }
        }

        // No account matched the provided account number
        return null;
    }

    /**
     * Deposits money into an authenticated account.
     *
     * Steps: 1) Authenticate account (account number + PIN) 2) Read deposit
     * amount 3) Call account.deposite(amount) 4) Handle invalid amount using
     * IllegalArgumentException
     *
     * @param accounts array of stored accounts
     */
    public static void depositToAccount(Account[] accounts) {
        Scanner input = new Scanner(System.in);
        System.out.println("=============Deposit to Account===============");

        // Authenticate first
        Account account = authAccount(accounts);
        if (account == null) {
            System.out.println("Wrong Account number or PIN!");
            return;
        }

        System.out.println("Enter the deposit amount: ");
        double amount = input.nextDouble();

        try {
            // The Account class should validate deposit amount (> 0)
            account.deposite(amount);
            System.out.println("Deposit Successful. | New Balance: " + account.getBalance());
        } catch (IllegalArgumentException ex) {
            // For example: deposit of negative or zero amount
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Withdraws money from an authenticated account.
     *
     * Steps: 1) Authenticate account 2) Read withdraw amount 3) Call
     * account.withdraw(amount) 4) Handle: - IllegalArgumentException (invalid
     * amount like <= 0) - InsufficientFundsException (not enough balance)
     *
     * @param accounts array of stored accounts
     */
    public static void withdrawFromAccount(Account[] accounts) {
        Scanner input = new Scanner(System.in);
        System.out.println("=============Withdraw to Account===============");

        // Authenticate first
        Account account = authAccount(accounts);
        if (account == null) {
            System.out.println("Wrong Account number or PIN!");
            return;
        }

        System.out.println("Enter the withdraw amount: ");
        double amount = input.nextDouble();

        try {
            account.withdraw(amount);
            System.out.println("withdraw Successful. | New Balance: " + account.getBalance());
        } catch (IllegalArgumentException ex) {
            // For example: withdraw amount <= 0
            System.out.println("Error: " + ex.getMessage());
        } catch (InsufficientFundsException ex) {
            // For example: withdraw amount greater than balance
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Shows the most recent N transactions for an authenticated account.
     *
     * Steps: 1) Authenticate account 2) Ask user for N (how many recent
     * transactions to show) 3) Call account.printLastNTransactions(n)
     *
     * @param accounts array of stored accounts
     */
    public static void showLastNTransactions(Account[] accounts) {
        Scanner input = new Scanner(System.in);

        System.out.println("=============Show Last N Transactions===============");

        // Authenticate first
        Account account = authAccount(accounts);
        if (account == null) {
            System.out.println("Wrong Account number or PIN!");
            return;
        }

        System.out.println("Enter the number of the most recent transactions to show: ");
        int n = input.nextInt();

        // The Account class should handle cases such as:
        // - n <= 0
        // - n > number of stored transactions
        account.printLastNTransactions(n);
    }

    /**
     * Prints a summary of the authenticated account: - Account number - Owner
     * name - Account type - Current balance
     *
     * @param accounts array of stored accounts
     */
    public static void showAccountSummary(Account[] accounts) {
        System.out.println("=============Show Account Summary===============");

        // Authenticate first
        Account account = authAccount(accounts);
        if (account == null) {
            System.out.println("Wrong Account number or PIN!");
            return;
        }

        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Owner Name: " + account.getOwnerName());
        System.out.println("Account Type: " + account.getAccountType());
        System.out.println("Account Current Balance: " + account.getBalance());
    }
}
