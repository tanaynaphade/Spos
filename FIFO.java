import java.util.Scanner;

public class FIFO {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n, capacity, index = 0;
		int hit = 0, fault = 0;
		double faultRatio, hitRatio;
		System.out.print("Enter the number of pages you want to enter: ");
		n = sc.nextInt();
		int pages[] = new int[n];

		for (int i = 0; i < n; i++) {
			pages[i] = sc.nextInt();
		}
		System.out.print("Enter the capacity of frame: ");
		capacity = sc.nextInt();
		int frame[] = new int[capacity];
		int table[][] = new int[n][capacity];

		for (int i = 0; i < capacity; i++) {
			frame[i] = 0;
		}
		System.out.println("\n----------------------------------------------------------------------");
		for (int i = 0; i < n; i++) {
			int search = -1;
			for (int j = 0; j < capacity; j++) {
				if (frame[j] == pages[i]) {
					search = j;
					hit++;
					System.out.print("H ");
					break;
				}
			}
			if (search == -1) {
				frame[index] = pages[i];
				fault++;
				System.out.print("F ");
				index = (index + 1) % capacity;
			}
			System.arraycopy(frame, 0, table[i], 0, capacity);
		}
		System.out.println("\n----------------------------------------------------------------------");
		for (int i = 0; i < capacity; i++) {
			for (int j = 0; j < n; j++)
				System.out.print(table[j][i]+" ");
			System.out.println();
		}

		System.out.println("----------------------------------------------------------------------");
		faultRatio = ((double) fault / n) * 100;
		hitRatio = ((double) hit / n) * 100;
		System.out.println("Page Fault: " + fault + "\nPage Hit: " + hit);
		System.out.println("Hit Ratio: " + hitRatio + "\nFault Ratio: " + faultRatio);
		sc.close();
	}

}