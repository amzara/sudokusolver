package sudoku;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter 1 - To play a random Sudoku puzzle, 2 - To make your own Sudoku board");
        Scanner input = new Scanner(System.in);
        int mode = input.nextInt();

        if (mode == 1) {
            int n = 9, remove = 40;
            Sudoku sudoku = new Sudoku(n, remove);
            sudoku.fillValues();

            sudoku.printSudoku();
            System.out.println("Enter 1 - Start playing, 2 - Auto solve");
            int ch = input.nextInt();

            if (ch == 2) {
                do {
                    sudoku.setAns();
                    remove--;
                } while (remove > 0);
                System.out.println(" ---=== Answer ===---");
                sudoku.printSudoku();
            } else if (ch == 1) {
                do {
                    System.out.println("Enter row: ");
                    int row = input.nextInt();
                    System.out.println("Enter column: ");
                    int col = input.nextInt();
                    System.out.println("Enter the digit: ");
                    int ans = input.nextInt();
                    System.out.println("");

                    sudoku.ans(row, col, ans);

                    boolean empty = sudoku.checkEmpty();

                    if (empty) {
                        remove--;
                    }
                } while (remove > 1);

                boolean ans = sudoku.checkFinal();
                if (ans) {
                    System.out.println("You have solved the puzzle, Congrats!");
                } else {
                    System.out.println("Your answer is wrong");
                }
            }
        } else if (mode == 2) {
            int blank = 81;
            SelfSudoku self = new SelfSudoku();
            self.startSudoku();

            while (true) {
                System.out.println("Enter row: (Enter 10 to stop inserting number)");
                int row = input.nextInt();
                if (blank > 40 && row > 9) {
                    System.out.println("Please do not leave more than 40 empty spaces");
                    System.out.println("Enter row: (Enter 10 to stop inserting number)");
                    row = input.nextInt();
                }
                if (row > 9) {
                    System.out.println("");
                    System.out.println("Insert complete\nThis is your puzzle");
                    self.showsudoku();

                    System.out.println("Select 1 - Start playing your own puzzle, 2 - Solve your puzzle with solver, 3 - Exit");

                    int ch = input.nextInt();
                    System.out.println();

                    switch (ch) {
                        case 1:
                            self.showsudoku();
                            do {
                                System.out.println("Enter row: ");
                                int r = input.nextInt();
                                System.out.println("Enter column: ");
                                int c = input.nextInt();
                                System.out.println("Enter the digit: ");
                                int ans = input.nextInt();
                                System.out.println();

                                self.setSudoku(r, c, ans);

                                boolean check = self.checkSudoku();
                                boolean grid = self.checkGrid(r, c);
                                if (check && grid) {
                                    self.showsudoku();
                                    blank--;
                                    continue;
                                } else if (!check || !grid) {
                                    ans = 0;
                                    self.setSudoku(r, c, ans);
                                    self.showsudoku();
                                    continue;
                                }
                            } while (blank != 0);

                            boolean ans = self.checkSudoku();
                            if (ans) {
                                System.out.println("You have solved the puzzle, Congrats!");
                                System.exit(0);
                            } else {
                                System.out.println("Your answer is wrong");
                                System.exit(0);
                            }

                        case 2:
                            do {
                                self.setAns();
                                blank--;
                            } while (blank > 0);
                            System.out.println(" ---=== Answer ===---");
                            self.showsudoku();

                        case 3:
                            System.exit(0);
                    }
                } else {
                    System.out.println("Enter column: ");
                    int col = input.nextInt();
                    System.out.println("Enter the digit: ");
                    int ans = input.nextInt();
                    System.out.println();

                    boolean res = self.setSudoku(row, col, ans);
                    if (!res) {
                        self.showsudoku();
                    } else {
                        boolean result = self.checkSudoku();
                        if (!result) {
                            System.out.println("Invalid input\nTry again");
                            ans = 0;
                            self.setSudoku(row, col, ans);
                            self.showsudoku();
                        } else {
                            boolean res2 = self.checkGrid(row, col);
                            if (!res2) {
                                ans = 0;
                                self.setSudoku(row, col, ans);
                                self.showsudoku();
                            } else {
                                self.showsudoku();
                                blank -= 1;
                            }
                        }
                    }
                }
            }
        }
        input.close();
    }
}
