import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner f = new Scanner(new File("input"));
        ArrayList<String> nums = new ArrayList<String>();
        for (String s : f.nextLine().split(",")) {
            nums.add(s);
        }
        ArrayList<String[][]> boards = new ArrayList<String[][]>();
        while (f.hasNextLine()) {
            String[][] board = new String[5][5];
            for (int i = 0; i < 5; i++) {
                String line = f.nextLine();
                if (line.equals("")) {
                    break;
                }
                ArrayList<String> tokens = new ArrayList<String>(Arrays.asList(line.split(" ")));
                tokens.removeIf(t -> t.equals("") || t.equals(" "));

                for (int j = 0; j < 5; j++) {
                    board[i][j] = tokens.get(j);
                }
            }
            if (board[0][0] != null)
                boards.add(board);
        }
        int answer = 0;
        boolean found = false;
        outerloop: for (String num : nums) {
            for (String[][] board : boards) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (board[i][j].equals(num)) {
                            board[i][j] = board[i][j] + "+";
                        }
                        found = checkBingo(board);
                        if (found) {
                            printBoard(board);
                            int sum = 0;
                            for (int k = 0; k < 5; k++) {
                                for (int l = 0; l < 5; l++) {
                                    if (!board[k][l].endsWith("+")) {
                                        sum += Integer.parseInt(board[k][l]);
                                    }
                                }
                            }
                            answer = sum * Integer.parseInt(num);
                            break outerloop;
                        }
                    }
                }
            }
        }
        System.out.println("part 1: " + answer);
    }

    public static void printBoard(String[][] board) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == null) {
                    throw new RuntimeException("found null in board");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printAllBoards(ArrayList<String[][]> boards) {
        for (String[][] board : boards) {
            printBoard(board);
            System.out.println();
        }
    }

    public static boolean checkBingo(String[][] board) {
        for (int i = 0; i < 5; i++) {
            if (board[i][0].endsWith("+") && board[i][1].endsWith("+") && board[i][2].endsWith("+")
                    && board[i][3].endsWith("+")
                    && board[i][4].endsWith("+")) {
                System.out.println("Bingo in row " + i);
                return true;
            }
            if (board[0][i].endsWith("+") && board[1][i].endsWith("+") && board[2][i].endsWith("+")
                    && board[3][i].endsWith("+")
                    && board[4][i].endsWith("+")) {
                System.out.println("Bingo in column " + i);
                return true;
            }
        }
        return false;
    }
}