import java.io.*;
import java.util.*;

public class ASPass {
    static int address = 0, lc = 0, scount = 0, lcount = 0;
    static int[] sadd = new int[10], ladd = new int[10];
    static String[] IS = { "ADD", "SUB", "MUL", "MOV" }, UserReg = { "AREG", "BREG", "CREG", "DREG" }, AD = { "START", "END" }, DL = { "DC", "DS" };
    static String[] sv = new String[10], lv = new String[10];

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\tanay\\Downloads\\Spos\\initial.txt"));
             PrintWriter p = new PrintWriter(new File("C:\\Users\\tanay\\Downloads\\Spos\\IM.txt"));
             PrintWriter p1 = new PrintWriter(new File("C:\\Users\\tanay\\Downloads\\Spos\\ST.txt"));
             PrintWriter p2 = new PrintWriter(new File("C:\\Users\\tanay\\Downloads\\Spos\\LT.txt"))) {

            String input;
            while ((input = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(input, " ");
                while (st.hasMoreTokens()) {
                    String tt = st.nextToken();
                    if (tt.matches("\\d{3,}")) {
                        lc = Integer.parseInt(tt);
                        p.println(lc);
                        address = lc - 1;
                    } else {
                        parseToken(tt, st, p);
                    }
                }
                address++;
            }
            address--;
            printTables(p1, p2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void parseToken(String tt, StringTokenizer st, PrintWriter p) {
        int flag = Arrays.asList(IS).contains(tt) ? 1 : 0;
        int i = Arrays.asList(AD).indexOf(tt); if (i >= 0) p.print("AD " + (i + 1) + " ");
        i = Arrays.asList(IS).indexOf(tt); if (i >= 0) p.print("IS " + (i + 1) + " ");
        i = Arrays.asList(UserReg).indexOf(tt); if (i >= 0) p.print((i + 1) + " ");
        i = Arrays.asList(DL).indexOf(tt); if (i >= 0) p.print("DL " + (i + 1) + " ");
        
        if (tt.length() == 1 && flag == 1) {
            addOrRetrieveSymbol(tt, p);
        } else if (tt.length() == 1 && st.hasMoreTokens()) {
            p.print(tt + " ");
            sadd[scount] = address;
            sv[scount++] = tt;
        }
        if (tt.charAt(0) == '=') { p.print("L" + lcount); lv[lcount++] = tt; }
        if (!st.hasMoreTokens()) p.println();
        if (tt.equals("DS")) { address += Integer.parseInt(st.nextToken()) - 1; p.println(); }
    }

    static void addOrRetrieveSymbol(String tt, PrintWriter p) {
        int idx = findSymbolIndex(tt);
        if (idx >= 0) {
            p.print("S" + idx);
        } else {
            sv[scount] = tt;
            sadd[scount] = address;
            p.print("S" + scount);
            scount++;
        }
    }

    static int findSymbolIndex(String symbol) {
        for (int i = 0; i < scount; i++) {
            if (symbol.equals(sv[i])) return i;
        }
        return -1;
    }

    static void printTables(PrintWriter p1, PrintWriter p2) {
        for (int i = 0; i < scount; i++) p1.println(i + "\t" + sv[i] + "\t" + sadd[i]);
        for (int i = 0; i < lcount; i++) { ladd[i] = address++; p2.println(i + "\t" + lv[i] + "\t" + ladd[i]); }
    }
}
