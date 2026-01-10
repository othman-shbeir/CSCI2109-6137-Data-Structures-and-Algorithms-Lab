package problem1solution;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Problem1Solution
 * ----------------
 * This program demonstrates the basic usage of an ArrayList in Java.
 *
 * The program:
 * 1. Reads the number of students.
 * 2. Stores student names in an ArrayList according to their arrival order.
 * 3. Iterates over the list to display all students.
 * 4. Accesses and displays the first and last students in the list.
 *
 * This example helps students understand:
 * - Dynamic storage using ArrayList
 * - Iteration using enhanced for-loop
 * - Accessing elements using indices
 */
public class Problem1Solution {

    /**
     * The main method is the entry point of the program.
     *
     * @param args command-line arguments (not used in this program)
     */
    public static void main(String[] args) {

        // Scanner object to read input from the user
        Scanner input = new Scanner(System.in);

        // Ask the user to enter the number of students
        System.out.println("Enter the number of students (n):");
        int n = input.nextInt();

        // Create an ArrayList to store student names dynamically
        ArrayList<String> students = new ArrayList<>();

        /**
         * Read student names based on their arrival order.
         * Each name is added to the ArrayList.
         */
        for (int i = 0; i < n; i++) {
            students.add(input.next());
        }

        // Display all students stored in the ArrayList
        System.out.println("The students list is:");
        for (String std : students) {
            System.out.print(std + ", ");
        }
        System.out.println(); // Move to a new line after printing the list

        /**
         * Accessing elements using indices:
         * - Index 0 represents the first element
         * - size() - 1 represents the last element
         */

        // Access and print the first student
        System.out.println("The first student is: " + students.get(0));

        // Access and print the last student
        System.out.println("The last student is: " + students.get(students.size() - 1));
    }
}
