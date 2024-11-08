import java.io.*;
import java.util.*;

public class PriorityScheduling {
  public static void main(String args[]) {
    int n, sum = 0;
    float total_tt = 0, total_waiting = 0;
    Scanner s = new Scanner(System.in);
    System.out.println("Enter Number Of Processes You Want To Execute:");
    n = s.nextInt();
    int arrival[] = new int[n];
    int cpu[] = new int[n];
    int pri[] = new int[n];
    int finish[] = new int[n];
    int turntt[] = new int[n];
    int wait[] = new int[n];
    int process[] = new int[n];
    for (int i = 0; i < n; i++) {
      System.out.println("Enter arrival time of Process " + (i + 1) + ": ");
      arrival[i] = s.nextInt();
      System.out.println("Enter CPU time of Process " + (i + 1) + ": ");
      cpu[i] = s.nextInt();
      System.out.println("Enter Priority of Process " + (i + 1) + ": ");
      pri[i] = s.nextInt();
      process[i] = i + 1;
    }
    for (int i = 0; i < n - 1; i++) {
      for (int j = i + 1; j < n; j++) {
        if (pri[i] > pri[j]) {
          int temp = cpu[i];
          cpu[i] = cpu[j];
          cpu[j] = temp;
          temp = process[i];
          process[i] = process[j];
          process[j] = temp;
          temp = pri[i];
          pri[i] = pri[j];
          pri[j] = temp;
        }
      }
    }
    for (int i = 0; i < n; i++) {
      sum += cpu[i];
      finish[i] = sum;
    }
    for (int i = 0; i < n; i++) {
      turntt[i] = finish[i] - arrival[i];
      total_tt += turntt[i];
      wait[i] = turntt[i] - cpu[i];
      total_waiting += wait[i];
    }
    System.out.println("\n\nProcess\t\tAT\tCPU_T\tPriority");
    for (int i = 0; i < n; i++) {
      System.out.println(
          process[i] + "\t\t" + arrival[i] + "\t" + cpu[i] + "\t" + pri[i]);
    }
    System.out.println("\n\n");
    System.out.println("Average Turnaround Time: " + (total_tt / n));
    System.out.println("Average Waiting Time: " + (total_waiting / n));
  }
}
