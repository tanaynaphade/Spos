package Pass2;

import java.io.*;
import java.util.*;

public class pass2 {

    static Obj[] symb_table = new Obj[10];
    static Obj[] literal_table = new Obj[10];
    static int symb_found = 0;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter total no. of symbols:");
        int total_symb = sc.nextInt();
        int pos, num;

        // Input symbols
        for (int i = 0; i < total_symb; i++) {
            symb_table[i] = new Obj("", 0);
            System.out.println("Enter Symbol Name");
            symb_table[i].name = sc.next();
            System.out.println("Enter Symbol Address");
            symb_table[i].addr = sc.nextInt();
        }

        // Input literals
        System.out.println("Enter Total no. of literals");
        int total_ltr = sc.nextInt();
        for (int i = 0; i < total_ltr; i++) {
            literal_table[i] = new Obj("", 0);
            System.out.println("Enter Literal Name");
            literal_table[i].name = sc.next();
            System.out.println("Enter Literal Address");
            literal_table[i].addr = sc.nextInt();
        }

        // Display symbol and literal tables
        System.out.println("**************************SYMBOL TABLE**********************");
        System.out.println("\nSYMBOL\tADDRESS");
        for (int i = 0; i < total_symb; i++) {
            System.out.println(symb_table[i].name + "\t" + symb_table[i].addr);
        }

        System.out.println("***************************LITERAL TABLE**********************");
        System.out.println("\nINDEX\tLITERAL\tADDRESS");
        for (int i = 0; i < total_ltr; i++) {
            System.out.println((i + 1) + "\t" + literal_table[i].name + "\t" + literal_table[i].addr);
        }

        BufferedReader br2 = new BufferedReader(new FileReader("C:\\Users\\tanay\\Downloads\\Spos\\Pass2\\output.txt"));
        String line;
        boolean symbol_error = false, undef_mnemonic = false;

        System.out.println("\n*********************OUTPUT FILE***********************");
        lab: while ((line = br2.readLine()) != null) {
            String[] token_list = line.split("\\s+", 5);
            symbol_error = undef_mnemonic = false;

            lab1: for (String token : token_list) {
                if (token.length() > 0) {
                    pos = -1;

                    if (token.equals("---")) {
                        System.out.println("\t---");
                        undef_mnemonic = true;
                    } else if (token.matches("[0-9]+")) {
                        System.out.println("\n\n" + token);
                    } else {
                        String letters = token.replaceAll("[^A-Za-z]+", "");
                        String numStr = token.replaceAll("[^0-9]+", "");

                        // Check if numStr is empty before parsing
                        if (!numStr.isEmpty()) {
                            num = Integer.parseInt(numStr);
                        } else {
                            num = -1; // Default value to indicate an error if needed
                        }

                        switch (letters.toUpperCase()) {
                            case "S":
                                if (num > 0 && num <= total_symb && symb_table[num - 1].addr != 0) {
                                    System.out.print("\t" + symb_table[num - 1].addr);
                                } else {
                                    System.out.print("\t---");
                                    symbol_error = true;
                                }
                                break;

                            case "L":
                                if (num > 0 && num <= total_ltr) {
                                    System.out.print("\t" + literal_table[num - 1].addr);
                                } else {
                                    System.out.print("\t---");
                                    symbol_error = true;
                                }
                                break;

                            case "AD":
                                System.out.print("\n");
                                continue lab;

                            case "DL":
                                if (num == 1) {
                                    System.out.print("\n");
                                    continue lab;
                                } else if (num == 2) {
                                    System.out.print("\t 00 \t 00");
                                }
                                continue lab1;

                            case "C":
                                System.out.print("\t" + num);
                                break;

                            default:
                                System.out.print("\t" + "00" + num);
                        }
                    }
                }
            }
        }

        if (symbol_error)
            System.out.println("\n\n***********SYMBOL IS NOT DEFINED*****************");
        if (undef_mnemonic)
            System.out.println("\n\n***********INVALID MNEMONIC*****************");

        // Check for duplicate symbols
        int[] flag = new int[total_symb];
        for (int i = 0; i < total_symb; i++) {
            symb_found = 0;
            for (int j = 0; j < total_symb; j++) {
                if (symb_table[i].name.equalsIgnoreCase(symb_table[j].name) && flag[j] == 0) {
                    symb_found++;
                    flag[i] = flag[j] = 1;
                }
            }
            if (symb_found > 1) {
                System.out.println("\n\n**************" + symb_table[i].name + "**IS DUPLICATE SYMBOL*********");
            }
        }
        br2.close();
        sc.close();
    }
}
