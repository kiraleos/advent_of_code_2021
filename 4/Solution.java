import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
        ArrayList<String[][]> boards2 = new ArrayList<>(boards);

        /*
         * Part 1
         */
        int answer = 0;
        outerloop: for (String num : nums) {
            for (String[][] board : boards) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (board[i][j].equals(num)) {
                            board[i][j] = board[i][j] + "+";
                        }
                        if (checkBingo(board)) {
                            int sum = 0;
                            for (int k = 0; k < 5; k++) {
                                for (int l = 0; l < 5; l++) {
                                    if (!board[k][l].contains("+")) {
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

        /*
         * Part 2
         */
        int answer2 = 0;
        var last_num = "0";
        var winners = new ArrayList<String[][]>();
        outerloop: for (String num : nums) {
            for (String[][] board : boards2) {
                boards: for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (checkBingo(board)) {
                            winners.add(board);
                            last_num = num;
                            break boards;
                        }
                        if (board[i][j].equals(num)) {
                            board[i][j] = board[i][j] + "+";
                        }
                    }
                }
            }
        }
        var last_to_win = winners.get(winners.size() - 1);
        int sum = 0;
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 5; l++) {
                if (!last_to_win[k][l].contains("+")) {
                    sum += Integer.parseInt(last_to_win[k][l]);
                }
            }
        }
        answer2 = sum * Integer.parseInt(last_num);
        System.out.println("part 2: " + answer2);
        System.out.println("last_to_win: " + Arrays.deepToString(last_to_win));
        System.out.println("last num: " + last_num);

    }

    public static void checkNum(String[][] board, String num) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j].equals(num)) {
                    board[i][j] = board[i][j] + "+";
                }
            }
        }
    }

    public static void printBoard(String[][] board) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(String.format("%5s ", board[i][j]));
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
            if (board[i][0].contains("+") && board[i][1].contains("+") && board[i][2].contains("+")
                    && board[i][3].contains("+")
                    && board[i][4].contains("+")) {
                return true;
            }
            if (board[0][i].contains("+") && board[1][i].contains("+") && board[2][i].contains("+")
                    && board[3][i].contains("+")
                    && board[4][i].contains("+")) {
                return true;
            }
        }
        return false;
    }
}