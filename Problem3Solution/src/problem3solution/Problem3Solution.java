package problem3solution;

import java.util.Scanner;

/**
 * Problem 3 Solution
 * ----------------
 * Browser History Simulation using a Stack.
 *
 * Case Study:
 * - Each visited page is pushed onto the stack.
 * - Pressing the Back button removes the most recent page (pop).
 * - The current page is always the top of the stack.
 *
 * This program demonstrates:
 * - How stacks represent "history" in real applications
 * - LIFO behavior: last visited page is the first to be removed
 */
public class Problem3Solution {

    /**
     * Entry point of the program.
     *
     * Program flow:
     * 1) Read number of visited pages (n)
     * 2) Push each page into the stack
     * 3) Read number of back presses (k)
     * 4) Pop k times
     * 5) Print the current page (top)
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Read the number of visited pages
        System.out.println("Enter the number of visited pages:");
        int n = input.nextInt();

        // Stack represents browser history (most recent page is on top)
        Stack<String> browserHistory = new LinkedStack<>();

        // Push visited pages onto the stack in the visiting order
        for (int i = 0; i < n; i++) {
            System.out.println("Page " + (i + 1) + ": ");
            browserHistory.push(input.next());
        }

        // Read number of times user pressed "Back"
        System.out.println("Enter the number of back button pressed:");
        int k = input.nextInt(); // Each back press means: pop()

        // Remove the most recent pages k times
        for (int i = 0; i < k; i++) {
            browserHistory.pop();
        }

        // Print the current page (top of the stack)
        System.out.println("The Current page is: " + browserHistory.top());
    }
}
