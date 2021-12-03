import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner f = new Scanner(new File("input"));
        ArrayList<String> lines = new ArrayList<String>();
        while (f.hasNextLine()) {
            lines.add(f.nextLine());
        }
        char[] most_common_bits = new char[lines.get(0).length()];
        char[] least_common_bits = new char[lines.get(0).length()];
        for (int i = 0; i < most_common_bits.length; i++) {
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
                most_common_bits[i] = '1';
            } else if (ctr_0 > ctr_1) {
                most_common_bits[i] = '0';
            } else {
                throw new RuntimeException("Error: equal number of bits 0 and 1");
            }
            least_common_bits[i] = (most_common_bits[i] == '1') ? '0' : '1';
        }
        int gamma = Integer.parseInt(new String(most_common_bits), 2);
        int epsilon = Integer.parseInt(new String(least_common_bits), 2);
        System.out.println("part 1: " + (gamma * epsilon));
    }
}