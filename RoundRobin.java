import java.util.Scanner;

public class RoundRobin{
	int burst[], run[], np, quantum = 0, wait[], time = 0, rp = 0, ta[];
	RoundRobin() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of processes: ");
		np = sc.nextInt();
		rp = np;
		burst = new int[np];
		run = new int[np];
		wait = new int[np];
		ta = new int[np];
		System.out.println("Enter their burst times: ");
		for (int i = 0; i < np; i++) {
			burst[i] = sc.nextInt();
			run[i] = burst[i];
			wait[i] = 0;
		}
		System.out.println("Enter the quantum time: ");
		quantum = sc.nextInt();
        sc.close();
		logic();
	}
	public void logic() {
		int i = 0;
		while (rp != 0) {
			if (run[i] > quantum) {
				run[i] -= quantum;
				time += quantum;
				System.out.println("Process: " + i + " ran for " + quantum + " units. Time: " + time);
			} else if (run[i] <= quantum && run[i] > 0) {
				time += run[i];
				run[i] = 0;
				ta[i] = time; // Completion time for process i
				rp--; // Process finished
				System.out.println("Process: " + i + " finished at time: " + time);
			}
			i++;
			// Round-robin: if we reach the last process, go back to the first process
			if (i == np) {
				i = 0;
			}
		}
		// Calculate waiting time and print results
		System.out.println("\nProcess\tBurst\tTurnaround\tWaiting");
		for (int j = 0; j < np; j++) {
			wait[j] = ta[j] - burst[j]; // Waiting time = Turnaround time - Burst time
			System.out.println(j + "\t" + burst[j] + "\t" + ta[j] + "\t\t" + wait[j]);
		}
	}
	public static void main(String[] args) {
		new RoundRobin();
	}
}
