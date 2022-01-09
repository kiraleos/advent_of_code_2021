import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner f = new Scanner(new File("input"));
        ArrayList<Integer> lines = new ArrayList<Integer>();
        while (f.hasNextLine()) {
            lines.add(Integer.parseInt(f.nextLine()));
        }

        // part 1
        int ctr = 0;
        for (int i = 0; i < lines.size() - 1; i++) {
            if (lines.get(i + 1) > lines.get(i)) {
                ctr += 1;
            }
        }

        // part 2
        ctr = 0;
        ArrayList<ArrayList<Integer>> windows = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < lines.size() - 3; i++) {
            windows.add(new ArrayList<Integer>(lines.subList(i, i + 3)));
        }
        for (int i = 0; i < lines.size(); i++) {
            if (windows.get(i).size() == 3 && windows.get(i + 1).size() == 3) {
                if (windows.get(i).stream().reduce(0, Integer::sum) > windows.get(i + 1).stream().reduce(0,
                        Integer::sum)) {
                    ctr += 1;
                }
            }
        }
        System.out.println(ctr);
    }
}
