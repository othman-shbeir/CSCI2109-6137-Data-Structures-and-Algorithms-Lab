package problem2solution1;

import java.util.Scanner;

/**
 * Problem2Solution1
 * -----------------
 * This program demonstrates how a LinkedList can be used to model
 * a real-world system using dynamic insertions and removals.
 *
 * Case Study:
 * - A train where wagons can be added or removed from both ends.
 *
 * The program supports the following operations:
 * - FRONT        : add a wagon to the front of the train
 * - BACK         : add a wagon to the back of the train
 * - REMOVE_FRONT : remove a wagon from the front
 * - REMOVE_BACK  : remove a wagon from the back
 *
 * Educational goals:
 * - Practice using LinkedList methods (addFirst, addLast, removeFirst, removeLast)
 * - Understand double-ended data structures
 * - Apply LinkedList in a real-world scenario
 * - Use iteration to display list contents
 */
public class Problem2Solution1 {

    /**
     * Entry point of the program.
     *
     * Program flow:
     * 1) Read the number of operations.
     * 2) Execute each operation on the train (LinkedList).
     * 3) Display the final state of the train.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Scanner object to read user input
        Scanner input = new Scanner(System.in);

        // Read the number of operations to perform on the train
        System.out.println("Enter the number of operations on the train:");
        int q = input.nextInt();

        // LinkedList representing the train wagons
        LinkedList<String> train = new LinkedList<>();

        /**
         * Process each operation.
         * Each operation modifies the train by adding or removing wagons
         * from either the front or the back.
         */
        for (int i = 0; i < q; i++) {

            System.out.println("Enter the operation type:");
            String operation = input.next();

            if (operation.equalsIgnoreCase("FRONT")) {

                // Add a wagon to the front of the train
                System.out.println("Enter the wagon's name:");
                train.addFirst(input.next());

            } else if (operation.equalsIgnoreCase("BACK")) {

                // Add a wagon to the back of the train
                System.out.println("Enter the wagon's name:");
                train.addLast(input.next());

            } else if (operation.equalsIgnoreCase("REMOVE_FRONT")) {

                // Remove the wagon at the front, if the train is not empty
                if (!train.isEmpty()) {
                    train.removeFirst();
                } else {
                    System.out.println("No wagons to remove...");
                }

            } else if (operation.equalsIgnoreCase("REMOVE_BACK")) {

                // Remove the wagon at the back, if the train is not empty
                if (!train.isEmpty()) {
                    train.removeLast();
                } else {
                    System.out.println("No wagons to remove...");
                }
            }
        }

        /**
         * Display the final state of the train.
         * The wagons are printed from front to back.
         */
        System.out.println("The train:");
        for (String wagon : train) {
            System.out.print(wagon + " -> ");
        }
        System.out.println(); // New line for clean output
    }
}
