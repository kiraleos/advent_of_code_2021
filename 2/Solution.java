import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner f = new Scanner(new File("input"));

        /*
         * Part 1
         */
        int depth = 0;
        int x_pos = 0;
        while (f.hasNextLine()) {
            String line = f.nextLine();
            String dir = line.split(" ")[0];
            int amt = Integer.parseInt(line.split(" ")[1]);
            if (dir.equals("forward")) {
                x_pos += amt;
            } else if (dir.equals("up")) {
                depth -= amt;
            } else if (dir.equals("down")) {
                depth += amt;
            }
        }
        System.out.printf("part 1: %d\n", depth * x_pos);

        /*
         * Part 2
         */
        f.close();
        f = new Scanner(new File("input"));

        depth = 0;
        x_pos = 0;
        int aim = 0;
        while (f.hasNextLine()) {
            String line = f.nextLine();
            String dir = line.split(" ")[0];
            int amt = Integer.parseInt(line.split(" ")[1]);
            if (dir.equals("forward")) {
                x_pos += amt;
                depth += aim * amt;
            } else if (dir.equals("up")) {
                aim -= amt;
            } else if (dir.equals("down")) {
                aim += amt;
            }
        }
        System.out.printf("part 2: %d\n", depth * x_pos);

    }
}