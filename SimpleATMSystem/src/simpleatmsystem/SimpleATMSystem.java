package simpleatmsystem;

import java.util.Scanner;

/**
 * SimpleATMSystem
 * ----------------
 * This class contains the main (menu-driven) program for a simple ATM system.
 *
 * What the program does:
 * 1) Stores bank accounts dynamically using a custom ArrayList (MyArrayList<Account>).
 * 2) Allows the user to:
 *    - Create a new account (Savings or Checking)
 *    - Deposit money into an account
 *    - Withdraw money from an account
 *    - Show the last N transactions for an account
 *    - Display an account summary (basic details)
 *
 * Important notes for students:
 * - The program uses a custom ArrayList implementation (MyArrayList),
 *   NOT a fixed-size array.
 * - Accounts can grow dynamically without a predefined limit.
 * - Each operation requires authentication (Account Number + PIN).
 * - Business rules (withdraw rules, deposit validation, fees, interest,
 *   transaction history) are handled inside the Account classes.
 *
 * This class focuses on:
 * - Applying ArrayList concepts in a real-world case study
 * - Separating responsibilities between system logic and account logic
 */
public class SimpleATMSystem {

    /**
     * Entry point of the program.
     *
     * Steps:
     * - Create a Scanner for reading user input.
     * - Create a dynamic list of Account objects using MyArrayList.
     * - Continuously display the main menu until the user chooses to exit.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Stores the user's menu choice
        int choice = 0;

        // Dynamic list to store all bank accounts
        MyArrayList<Account> accounts = new MyArrayList<>();

        System.out.println("Welcome to the ATM System");

        // Infinite loop: keeps running until the user selects Exit (choice 0)
        while (true) {
            printMenu();
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    // Create a new Savings or Checking account
                    Account newAccount = createAccount();

                    // Debug line to confirm account creation (for learning/testing only)
                    if (newAccount != null) {
                        System.out.println("Debug add: " + newAccount.getAccountNumber());
                    }

                    // Add the new account to the dynamic list
                    if (newAccount != null) {
                        accounts.add(newAccount);
                    }
                    break;

                case 2:
                    // Deposit money into an account
                    depositToAccount(accounts);
                    break;

                case 3:
                    // Withdraw money from an account
                    withdrawFromAccount(accounts);
                    break;

                case 4:
                    // Display the last N transactions
                    showLastNTransactions(accounts);
                    break;

                case 5:
                    // Display account summary
                    showAccountSummary(accounts);
                    break;

                case 0:
                    // Exit the program
                    System.exit(0);
                    break;

                default:
                    System.out.println("Wrong Input!");
            }
        }
    }

    /**
     * Prints the main menu options for the ATM system.
     * This method is responsible only for displaying menu text.
     */
    public static void printMenu() {
        System.out.println("============= Main Menu ==============");
        System.out.println("1. Create new Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Show the last N Transactions");
        System.out.println("5. Show the account summary");
        System.out.println("0. Exit");
        System.out.println("=====================================");
    }

    /**
     * Creates a new bank account by collecting required information from the user.
     *
     * The method asks for:
     * - Account type (Savings or Checking)
     * - Account number
     * - Owner name
     * - PIN
     * - Initial balance
     *
     * Additional data based on account type:
     * - SavingsAccount: interest rate
     * - CheckingAccount: transaction fee
     *
     * @return a newly created Account object, or null if input is invalid
     */
    public static Account createAccount() {

        System.out.println("Create new Account: Set the Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Checking Account");

        Scanner input = new Scanner(System.in);
        int inputAccountType = input.nextInt();

        System.out.println("Enter the Account Number:");
        input.nextLine(); // Consume leftover newline
        String accountNumber = input.nextLine();

        System.out.println("Enter the Account Owner Name:");
        String ownerName = input.nextLine();

        System.out.println("Enter the Account PIN:");
        int pin = input.nextInt();

        System.out.println("Enter the Account initial Balance:");
        double initialBalance = input.nextDouble();

        switch (inputAccountType) {
            case 1:
                System.out.println("Enter the Account interest rate:");
                double interestRate = input.nextDouble();
                return new SavingsAccount(
                        accountNumber, ownerName, pin, initialBalance, interestRate
                );

            case 2:
                System.out.println("Enter the Account transaction fee:");
                double transactionFee = input.nextDouble();
                return new CheckingAccount(
                        accountNumber, ownerName, pin, initialBalance, transactionFee
                );

            default:
                System.out.println("Invalid Input!");
                return null;
        }
    }

    /**
     * Authenticates an account using:
     * - Account number
     * - PIN
     *
     * The method performs a linear search over the ArrayList of accounts.
     *
     * @param accounts dynamic list of stored accounts
     * @return the authenticated Account object, or null if authentication fails
     */
    public static Account authAccount(MyArrayList<Account> accounts) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the Account Number");
        String accountNumber = input.nextLine();

        // Linear search through the ArrayList
        for (Account account : accounts) {
            if (account.getAccountNumber().equalsIgnoreCase(accountNumber.trim())) {
                System.out.println("Enter the Account PIN");
                int inputPIN = input.nextInt();

                if (account.checkPIN(inputPIN)) {
                    return account;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * Deposits money into an authenticated account.
     *
     * @param accounts dynamic list of stored accounts
     */
    public static void depositToAccount(MyArrayList<Account> accounts) {
        Scanner input = new Scanner(System.in);
        System.out.println("=============Deposit to Account===============");

        Account account = authAccount(accounts);
        if (account == null) {
            System.out.println("Wrong Account number or PIN!");
            return;
        }

        System.out.println("Enter the deposit amount:");
        double amount = input.nextDouble();

        try {
            account.deposite(amount);
            System.out.println("Deposit Successful. | New Balance: " + account.getBalance());
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Withdraws money from an authenticated account.
     *
     * @param accounts dynamic list of stored accounts
     */
    public static void withdrawFromAccount(MyArrayList<Account> accounts) {
        Scanner input = new Scanner(System.in);
        System.out.println("=============Withdraw to Account===============");

        Account account = authAccount(accounts);
        if (account == null) {
            System.out.println("Wrong Account number or PIN!");
            return;
        }

        System.out.println("Enter the withdraw amount:");
        double amount = input.nextDouble();

        try {
            account.withdraw(amount);
            System.out.println("Withdraw Successful. | New Balance: " + account.getBalance());
        } catch (IllegalArgumentException | InsufficientFundsException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Displays the most recent N transactions of an authenticated account.
     *
     * @param accounts dynamic list of stored accounts
     */
    public static void showLastNTransactions(MyArrayList<Account> accounts) {
        Scanner input = new Scanner(System.in);

        System.out.println("=============Show Last N Transactions===============");

        Account account = authAccount(accounts);
        if (account == null) {
            System.out.println("Wrong Account number or PIN!");
            return;
        }

        System.out.println("Enter the number of recent transactions to show:");
        int n = input.nextInt();

        account.printLastNTransactions(n);
    }

    /**
     * Displays a summary of an authenticated account.
     *
     * @param accounts dynamic list of stored accounts
     */
    public static void showAccountSummary(MyArrayList<Account> accounts) {
        System.out.println("=============Show Account Summary===============");

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
