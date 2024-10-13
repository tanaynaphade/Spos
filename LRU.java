import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class LRU {

   private void lru() {
      int pageFaults = 0, pageHits = 0, frameSize, numOfPages = 15;
      Scanner sc = new Scanner(System.in);

      HashSet<Integer> frames = new HashSet<>(numOfPages);
      HashMap<Integer, Integer> indexes = new HashMap<>();
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
               int lruPage = Integer.MAX_VALUE, lruIndex = Integer.MAX_VALUE;
               for (int page : frames) {
                  if (indexes.get(page) < lruIndex) {
                     lruIndex = indexes.get(page);
                     lruPage = page;
                  }
               }
               frames.remove(lruPage);
            }

            frames.add(currentPage);
         }

         indexes.put(currentPage, i); // update the most recent index
         System.out.println("Current frames: " + frames);
      }

      System.out.println("Page Faults: " + pageFaults);
      System.out.println("Page Hits: " + pageHits);
   }

   public static void main(String[] args) {
      LRU algo = new LRU();
      algo.lru();
   }
}
