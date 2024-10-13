import java.io.*;
import java.util.*;

public class SJF {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n;
        System.out.println("Enter number of processes:");
        n = input.nextInt();

        int[] pid = new int[n]; // Process IDs
        int[] burstTime = new int[n]; // Burst times
        int[] arrivalTime = new int[n]; // Arrival times
        int[] remainingTime = new int[n]; // Remaining times
        int[] waitTime = new int[n]; // Waiting times
        int[] turnaroundTime = new int[n]; // Turnaround times

        // Input burst time and arrival time
        System.out.println("Enter Arrival and Burst Times:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + " Arrival Time: ");
            arrivalTime[i] = input.nextInt();
            System.out.print("Process " + (i + 1) + " Burst Time: ");
            burstTime[i] = input.nextInt();
            pid[i] = i + 1; // Store process ID
            remainingTime[i] = burstTime[i]; // Initialize remaining time
        }

        int currentTime = 0; // Current time
        int completed = 0; // Number of completed processes
        float totalWaitTime = 0, totalTurnaroundTime = 0;

        // Execute until all processes are completed
        while (completed < n) {
            int idx = -1;
            int minRemainingTime = Integer.MAX_VALUE;

            // Find the process with the shortest remaining time that has arrived
            for (int i = 0; i < n; i++) {
                if (arrivalTime[i] <= currentTime && remainingTime[i] < minRemainingTime && remainingTime[i] > 0) {
                    minRemainingTime = remainingTime[i];
                    idx = i;
                }
            }

            // If a process is found
            if (idx != -1) {
                remainingTime[idx]--; // Decrease remaining time
                currentTime++; // Increase current time

                // If process is completed
                if (remainingTime[idx] == 0) {
                    completed++;
                    turnaroundTime[idx] = currentTime - arrivalTime[idx]; // Calculate turnaround time
                    waitTime[idx] = turnaroundTime[idx] - burstTime[idx]; // Calculate waiting time
                    totalWaitTime += waitTime[idx];
                    totalTurnaroundTime += turnaroundTime[idx];
                }
            } else {
                currentTime++; // No process is ready, just increment time
            }
        }

        // Print results
        System.out.println("P\tAT\tBT\tWT\tTAT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + pid[i] + "\t" + arrivalTime[i] + "\t" + burstTime[i] + "\t" + waitTime[i] + "\t" + turnaroundTime[i]);
        }

        System.out.println("Average Waiting Time: " + (totalWaitTime / n));
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / n));
    }
}
