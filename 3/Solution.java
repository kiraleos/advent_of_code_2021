import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        /*
         * Part 1
         */
        Scanner f = new Scanner(new File("input"));
        ArrayList<String> lines = new ArrayList<String>();
        while (f.hasNextLine()) {
            lines.add(f.nextLine());
        }
        char[] mostCommonBits = new char[lines.get(0).length()];
        char[] leastCommonBits = new char[lines.get(0).length()];
        for (int i = 0; i < mostCommonBits.length; i++) {
            int ctr_1 = 0;
            int ctr_0 = 0;
            for (int j = 0; j < lines.size(); j++) {
                if (lines.get(j).charAt(i) == '1') {
                    ctr_1++;
                } else {
                    ctr_0++;
                }
            }
            if (ctr_1 > ctr_0) {
                mostCommonBits[i] = '1';
            } else if (ctr_0 > ctr_1) {
                mostCommonBits[i] = '0';
            } else {
                throw new RuntimeException("Error: equal number of bits 0 and 1");
            }
            leastCommonBits[i] = (mostCommonBits[i] == '1') ? '0' : '1';
        }
        int gamma = Integer.parseInt(new String(mostCommonBits), 2);
        int epsilon = Integer.parseInt(new String(leastCommonBits), 2);
        System.out.println("part 1: " + (gamma * epsilon));

        /*
         * Part 2
         */
        int oxygen = -1;
        char mostCommonBit;
        ArrayList<String> linesOxygen = new ArrayList<String>(lines);
        for (int j = 0; j < linesOxygen.get(0).length(); j++) {
            int ctr_1 = 0;
            int ctr_0 = 0;
            for (int k = 0; k < linesOxygen.size(); k++) {
                if (linesOxygen.get(k).charAt(j) == '1') {
                    ctr_1++;
                } else {
                    ctr_0++;
                }
            }
            if (ctr_1 >= ctr_0) {
                mostCommonBit = '1';
            } else {
                mostCommonBit = '0';
            }

            for (Iterator<String> iterator = linesOxygen.iterator(); iterator.hasNext();) {
                String line = iterator.next();
                if (linesOxygen.size() > 1 && line.charAt(j) != mostCommonBit) {
                    iterator.remove();
                }
            }
        }
        oxygen = Integer.parseInt(linesOxygen.get(0), 2);

        int co2 = -1;
        ArrayList<String> linesCo2 = new ArrayList<String>(lines);
        for (int j = 0; j < linesCo2.get(0).length(); j++) {
            int ctr_1 = 0;
            int ctr_0 = 0;
            for (int k = 0; k < linesCo2.size(); k++) {
                if (linesCo2.get(k).charAt(j) == '1') {
                    ctr_1++;
                } else {
                    ctr_0++;
                }
            }
            if (ctr_1 >= ctr_0) {
                leastCommonBits[j] = '0';
            } else {
                leastCommonBits[j] = '1';
            }
            for (Iterator<String> iterator = linesCo2.iterator(); iterator.hasNext();) {
                String line = iterator.next();
                if (linesCo2.size() > 1 && line.charAt(j) != leastCommonBits[j]) {
                    iterator.remove();
                }
            }

        }
        co2 = Integer.parseInt(linesCo2.get(0), 2);

        System.out.println("part 2: " + (oxygen * co2));
    }
}