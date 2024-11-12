import java.io.*;
import java.util.*;

public class macro {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\tanay\\Downloads\\Spos\\input.txt"));
             PrintWriter mntFile = new PrintWriter("C:\\Users\\tanay\\Downloads\\Spos\\mnt.txt");
             PrintWriter mdtFile = new PrintWriter("C:\\Users\\tanay\\Downloads\\Spos\\mdt.txt");
             PrintWriter adtFile = new PrintWriter("C:\\Users\\tanay\\Downloads\\Spos\\adt.txt")) {

            String input, token;
            String[] mnt = new String[10], AR = new String[20];
            int[] macroIndex = new int[10];
            int macroCount = 0, argCount = 0, lineIndex = 1;
            boolean isMacro = false;

            while ((input = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(input, " ");
                token = tokenizer.nextToken();

                if (token.equals("MACRO")) {
                    isMacro = true;
                    mnt[macroCount] = tokenizer.nextToken();
                    macroIndex[macroCount] = lineIndex;
                    mntFile.println(mnt[macroCount] + "\t" + macroIndex[macroCount]);
                    mdtFile.println(mnt[macroCount]);
                    adtFile.println(mnt[macroCount]);
                    macroCount++;

                    String Args = tokenizer.nextToken();
                    for (String arg : Args.split(",")) {
                        if (arg.startsWith("&")) {
                            AR[argCount++] = arg;
                            adtFile.println(arg);
                        }
                    }
                } else if (isMacro) {
                    if (input.equals("MEND")) {
                        isMacro = false;
                        mdtFile.println("MEND");
                    } else {
                        processMacroLine(input, AR, argCount, mdtFile);
                    }
                }
                lineIndex++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processMacroLine(String input, String[] AR, int argCount, PrintWriter mdtFile) {
        StringTokenizer tokenizer = new StringTokenizer(input, " ");
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            boolean isArg = false;

            for (int i = 0; i < argCount; i++) {
                if (token.equals(AR[i])) {
                    mdtFile.print("AR" + i + " ");
                    isArg = true;
                    break;
                }
            }
            if (!isArg) {
                mdtFile.print(token + " ");
            }
        }
        mdtFile.println();
    }
}
