package sudoku;

public class SelfSudoku extends Solver {
    int[][] arr;
    int row, col;
    int rcount = 0, ccount = 0, count = 0;

    public void startSudoku() {
        arr = new int[9][9];
        for (row = 0; row < 9; row++) {
            for (col = 0; col < 9; col++) {
                System.out.print("_ ");
                ccount++;
                if (ccount == 3) {
                    System.out.print(" | ");
                    ccount = 0;
                }
            }
            System.out.println("");
            rcount++;
            if (rcount == 3) {
                System.out.println("------ | ------ | ------ |");
                rcount = 0;
            }
        }
        System.out.println("");
    }

    public void showSudoku() {
        for (row = 0; row < 9; row++) {
            for (col = 0; col < 9; col++) {
                if (arr[row][col] != 0) {
                    System.out.print(arr[row][col] + " ");
                    ccount++;
                } else {
                    System.out.print("_ ");
                    ccount++;
                }
                if (ccount == 3) {
                    System.out.print(" | ");
                    ccount = 0;
                }
            }
            System.out.println("");
            rcount++;
            if (rcount == 3) {
                System.out.println("------ | ------ | ------ |");
                rcount = 0;
            }
        }
    }

    boolean setSudoku(int a, int b, int c) {
        if (arr[a - 1][b - 1] != 0 && c != 0) {
            System.out.println("Already consists of a number");
            return false;
        } else {
            arr[a - 1][b - 1] = c;
            return true;
        }
    }

    boolean checkRow() {
        int row, row2, col;
        for (row = 0; row < 8; row++) {
            for (col = 0; col < 9; col++) {
                for (row2 = row + 1; row2 < 9; row2++) {
                    if (arr[row][col] != 0) {
                        if (arr[row][col] == arr[row2][col]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    boolean checkCol() {
        int row, col, col2;
        for (col = 0; col < 8; col++) {
            for (row = 0; row < 9; row++) {
                for (col2 = col + 1; col2 < 9; col2++) {
                    if (arr[row][col] != 0) {
                        if (arr[row][col] == arr[row][col2]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    boolean checkGrid(int row, int col) {
        row -= 1;
        col -= 1;
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((arr[row][col] == arr[row / 3 * 3 + i][col / 3 * 3 + j]) && arr[row][col] != 0) {
                    count++;
                }
            }
        }
        if (count > 1) {
            System.out.println("Same number in the same box\nTry Again");
            return false;
        }
        return true;
    }

    boolean checkSudoku() {
        return checkRow() && checkCol();
    }

    public boolean setAns() {
        validAgain(arr);
        return validAgain(arr);
    }
}