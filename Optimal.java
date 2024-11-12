import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Optimal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of frames: ");
        int frameCount = sc.nextInt();

        System.out.print("Enter the number of pages: ");
        int pageCount = sc.nextInt();
        
        int[] pages = new int[pageCount];
        System.out.print("Enter the page references: ");
        for (int i = 0; i < pageCount; i++) {
            pages[i] = sc.nextInt();
        }

        List<Integer> frames = new ArrayList<>();
        int pageFaults = 0;

        for (int i = 0; i < pageCount; i++) {
            int page = pages[i];
            if (!frames.contains(page)) {
                if (frames.size() < frameCount) {
                    frames.add(page);
                } else {
                    int replaceIndex = findOptimalIndex(frames, pages, i + 1);
                    frames.set(replaceIndex, page);
                }
                pageFaults++;
            }
            System.out.println("Frames after accessing page " + page + ": " + frames);
        }

        System.out.println("Total page faults: " + pageFaults);
    }

    private static int findOptimalIndex(List<Integer> frames, int[] pages, int startIndex) {
        int maxIndex = -1;
        int farthest = startIndex;

        for (int i = 0; i < frames.size(); i++) {
            int j;
            for (j = startIndex; j < pages.length; j++) {
                if (frames.get(i) == pages[j]) {
                    if (j > farthest) {
                        farthest = j;
                        maxIndex = i;
                    }
                    break;
                }
            }
            if (j == pages.length) {
                return i;
            }
        }
        return maxIndex != -1 ? maxIndex : 0;
    }
}
