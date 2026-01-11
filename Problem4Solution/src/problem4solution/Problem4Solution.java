package problem4solution;

import java.util.Scanner;

/**
 * Problem 4 Solution
 * ----------------
 * This program simulates a simple patient queue system using the Queue data structure.
 *
 * Case Study:
 * - Patients arrive and wait in a queue.
 * - Patients are treated in the same order they arrive (FIFO).
 *
 * The program demonstrates:
 * - Queue operations: enqueue, dequeue, first
 * - Real-world use of queues (hospital / service systems)
 * - Iteration over a queue using enhanced for-loop
 */
public class Problem4Solution {

    /**
     * Entry point of the program.
     *
     * Program flow:
     * 1) Read number of patients.
     * 2) Enqueue all patient names into the queue.
     * 3) Read number of treated patients.
     * 4) Dequeue treated patients.
     * 5) Display remaining patients and the next patient to be treated.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Read number of patients
        System.out.println("Enter the patients number (n): ");
        int n = input.nextInt();

        // Queue to store patients in arrival order
        Queue<String> patients = new LinkedQueue<>();

        // Enqueue patients as they arrive
        for (int i = 0; i < n; i++) {
            System.out.println("Enter patient's " + (i + 1) + " name:");
            patients.enqueue(input.next());
        }

        // Read number of treated patients
        System.out.println("Enter the number of treated patients (k):");
        int k = input.nextInt();

        // Dequeue treated patients
        for (int i = 0; i < k; i++) {
            patients.dequeue();
        }

        // Display remaining patients in the queue
        System.out.println("Remaining patients:");
        for (String patient : patients) {
            System.out.print(patient + " <- ");
        }
        System.out.println();

        // Display the next patient to be treated
        System.out.println("The turn stops with: " + patients.first());
    }
}
