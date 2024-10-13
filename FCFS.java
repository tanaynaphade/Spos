import java.util.*;

public class FCFS {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes: ");
        int n = sc.nextInt();
        int pid[] = new int[n]; // process ids
        int ar[] = new int[n];  // arrival times
        int bt[] = new int[n];  // burst times
        int ct[] = new int[n];  // completion times
        int ta[] = new int[n];  // turnaround times
        int wt[] = new int[n];  // waiting times
        int remaining_bt[] = new int[n];  // remaining burst times
        boolean isCompleted[] = new boolean[n];  // process completion flags
        int currentTime = 0, completed = 0, prev = 0;
        float avgwt = 0, avgta = 0;
        // Input the arrival and burst times
        for (int i = 0; i < n; i++) {
            System.out.println("Enter process " + (i + 1) + " arrival time: ");
            ar[i] = sc.nextInt();
            System.out.println("Enter process " + (i + 1) + " burst time: ");
            bt[i] = sc.nextInt();
            pid[i] = i + 1;
            remaining_bt[i] = bt[i];  // Initialize remaining burst time
            isCompleted[i] = false;   // Initially, all processes are incomplete
        }
        while (completed != n) {
            int idx = -1;
            int minRemainingTime = Integer.MAX_VALUE;
            // Find the process with the shortest remaining time that has arrived
            for (int i = 0; i < n; i++) {
                if (ar[i] <= currentTime && !isCompleted[i] && remaining_bt[i] < minRemainingTime) {
                    minRemainingTime = remaining_bt[i];
                    idx = i;
                }
            }
            // If no process is found, increment the time
            if (idx == -1) {
                currentTime++;
            } else {
                // Process the selected process for 1 time unit
                remaining_bt[idx]--;
                currentTime++;
                // If the process is completed
                if (remaining_bt[idx] == 0) {
                    ct[idx] = currentTime; // Completion time
                    ta[idx] = ct[idx] - ar[idx];  // Turnaround time
                    wt[idx] = ta[idx] - bt[idx];  // Waiting time

                    avgwt += wt[idx]; // Accumulate waiting time
                    avgta += ta[idx]; // Accumulate turnaround time

                    isCompleted[idx] = true; // Mark process as completed
                    completed++;
                }
            }
        }
        // Print the results
        System.out.println("\nPID  Arrival  Burst  Completion  Turnaround  Waiting");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "    \t " + ar[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + ta[i] + "\t" + wt[i]);
        }
        sc.close();
        System.out.println("\nAverage waiting time: " + (avgwt / n));   // Average waiting time
        System.out.println("Average turnaround time: " + (avgta / n));  // Average turnaround time
    }
}
