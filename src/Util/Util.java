package Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class Util {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public int checkChoice(String mess, int a, int b) {
        int output = 0;
        try {
            do {
                System.out.print(mess);
                output = Integer.parseInt(in.readLine());
                if (output >= a && output <= b) {
                    break;
                }
                System.err.printf("\nPlease enter %d-%d !!", a, b);

            } while (true);
        } catch (Exception e) {
            System.err.println("Please enter integer !!!");
        }
        return output;
    }

    public String checkString(String mess) throws IOException {
        String out = "";
        try {
            do {
                System.out.print(mess);
                out = in.readLine();
                if (out.isEmpty()) {
                    System.err.println("Please Enter String");
                } else {
                    break;
                }
            } while (true);
        } catch (Exception e) {
        }

        return out.trim();
    }

    public int checkInterger(String mess) {
        int output = 0;
        do {
            try {
                System.out.print(mess);
                output = Integer.parseInt(in.readLine());
                break;
            } catch (Exception e) {
                System.err.println("Please Enter Interger");
            }

        } while (true);

        return output;
    }

    public String standardlizeString(String s) {
        String strOut = "";
        String st = "";
        st = s.trim().toLowerCase().replaceAll("\\s+", " ");

        String[] temp = new String[100];
        temp = st.split(" ");
        for (int i = 0; i <= temp.length - 1; i++) {
            strOut += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) {
                strOut += " ";
            }
        }
        return strOut;
    }

    public static boolean isCollectionEmpty(Collection collection){
        return !isObjectEmpty(collection) && collection.isEmpty();
    }

    public static boolean isObjectEmpty(Object obj) {
        return obj == null;
    }
}
