import java.util.HashSet;
import java.util.Scanner;

public class Optimal {

   private void optimalPageReplacement() {
      int pageFaults = 0, pageHits = 0, frameSize, numOfPages = 15;
      Scanner sc = new Scanner(System.in);

      HashSet<Integer> frames = new HashSet<>(numOfPages);
      int[] pages = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 1, 2, 0};

      System.out.println("Enter Frame Size :");
      frameSize = sc.nextInt();

      for (int i = 0; i < numOfPages; i++) {
         int currentPage = pages[i];

         if (frames.contains(currentPage)) {
            pageHits++;
         } else {
            pageFaults++;

            if (frames.size() == frameSize) {
               int farthest = i;
               int pageToReplace = -1;

               for (int page : frames) {
                  int nextUse = Integer.MAX_VALUE;

                  for (int j = i + 1; j < numOfPages; j++) {
                     if (pages[j] == page) {
                        nextUse = j;
                        break;
                     }
                  }

                  if (nextUse > farthest) {
                     farthest = nextUse;
                     pageToReplace = page;
                  }
               }

               frames.remove(pageToReplace);
            }

            frames.add(currentPage);
         }

         System.out.println("Current frames: " + frames);
      }

      System.out.println("Page Faults: " + pageFaults);
      System.out.println("Page Hits: " + pageHits);
   }

   public static void main(String[] args) {
      Optimal algo = new Optimal();
      algo.optimalPageReplacement();
   }
}
