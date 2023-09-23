package sudoku;

public class Sudoku extends Solver {
    int[] arr[];
    int n;
    int sr;
    int miss;

    // Constructor
    Sudoku(int n, int miss) {
        this.n = n;
        this.miss = miss;

        Double srn = Math.sqrt(n);
        sr = srn.intValue();

        arr = new int[n][n];
    }

    // Generate a Sudoku puzzle
    public void fillValues() {
        diagonal();
        remaining(0, sr);
        remove();
    }

    // Fill the diagonal blocks
    void diagonal() {
        for (int i = 0; i < n; i = i + sr) {
            fill(i, i);
        }
    }

    // Check if a number can be placed in a 3x3 box
    boolean Box(int rowStart, int colStart, int num) {
        for (int i = 0; i < sr; i++) {
            for (int j = 0; j < sr; j++) {
                if (arr[rowStart + i][colStart + j] == num)
                    return false;
            }
        }
        return true;
    }

    // Fill a 3x3 box
    void fill(int row, int col) {
        int num;
        for (int i = 0; i < sr; i++) {
            for (int j = 0; j < sr; j++) {
                do {
                    num = randomGenerator(n);
                } while (!Box(row, col, num));

                arr[row + i][col + j] = num;
            }
        }
    }

    // Random number generator
    int randomGenerator(int num) {
        return (int) Math.floor((Math.random() * num + 1));
    }

    // Check if a number is valid in a given cell
    boolean Check(int i, int j, int num) {
        return (Row(i, num) && Col(j, num) && Box(i - i % sr, j - j % sr, num));
    }

    // Check if a number is valid in a row
    boolean Row(int i, int num) {
        for (int j = 0; j < n; j++) {
            if (arr[i][j] == num) {
                return false;
            }
        }
        return true;
    }

    // Check if a number is valid in a column
    boolean Col(int j, int num) {
        for (int i = 0; i < n; i++) {
            if (arr[i][j] == num) {
                return false;
            }
        }
        return true;
    }

    // Fill the remaining cells using backtracking
    boolean remaining(int i, int j) {
        if (j >= n && i < n - 1) {
            i += 1;
            j = 0;
        }

        if (i >= n && j >= n) {
            return true;
        }

        if (i < sr) {
            if (j < sr)
                j = sr;
        } else if (i < n - sr) {
            if (j == (int) (i / sr) * sr)
                j = j + sr;
        } else {
            if (j == n - sr) {
                i += 1;
                j = 0;
                if (i >= n)
                    return true;
            }
        }

        for (int num = 1; num <= n; num++) {
            if (Check(i, j, num)) {
                arr[i][j] = num;
                if (remaining(i, j + 1)) {
                    return true;
                }
                arr[i][j] = 0;
            }
        }
        return false;
    }

    // Remove digits to create the puzzle
    public void remove() {
        int count = miss;

        while (count != 0) {
            int cell = randomGenerator(n * n) - 1;

            int i = (cell / n);
            int j = cell % 9;
            if (j != 0)
                j -= 1;

            if (arr[i][j] != 0) {
                count--;
                arr[i][j] = 0;
            }
        }
    }

    // Print the Sudoku puzzle
    public void printSudoku() {
        int rcount = 0, ccount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 0) {
                    System.out.print("_ ");
                    ccount++;
                } else {
                    System.out.print(arr[i][j] + " ");
                    ccount++;
                }

                if (ccount == 3) {
                    System.out.print(" | ");
                    ccount = 0;
                } else
                    continue;
            }
            System.out.println(" ");
            rcount++;
            if (rcount == 3) {
                System.out.println("------ | ------ | ------ |");
                rcount = 0;
            }
        }
        System.out.println(" ");
    }

    // Set an answer in a specific cell
    public void ans(int a, int b, int c) {
        a -= 1;
        b -= 1;
        if (arr[a][b] == 0) {
            arr[a][b] = c;
            printSudoku();
        } else
            System.out.println("The box already contains numbers, please enter again");

    }

    // Check if the Sudoku grid is empty
    boolean checkEmpty() {
        for (int a = 0; a <= 8; a++) {
            for (int b = 0; b <= 8; b++) {
                if (arr[a][b] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if the Sudoku puzzle is solved
    boolean checkFinal() {
        boolean col = checkCol();
        boolean row = checkRow();
        return row && col;
    }

    // Check if columns have unique numbers
    boolean checkCol() {
        for (int b = 0; b < 7; b++) {
            for (int a = 0; a <= 8; a++) {
                for (int b2 = b + 1; b2 < 8; b2++) {
                    if (arr[a][b] == arr[a][b2]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Check if a 3x3 grid contains unique numbers
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
        return count == 1;
    }

    // Check if rows have unique numbers
    boolean checkRow() {
        for (int a = 0; a < 7; a++) {
            for (int b = 0; b <= 8; b++) {
                for (int a2 = a + 1; a2 < 8; a2++) {
                    if (arr[a][b] == arr[a2][b]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Set an answer in the Sudoku grid
    public boolean setAns() {
        validAgain(arr);
        return validAgain(arr);
    }}