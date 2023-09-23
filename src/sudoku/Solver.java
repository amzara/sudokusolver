package sudoku;

public class Solver {
    int[] arr[];
    int arr2[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    static int row, col;
    static int count = 0;

    // Check if any valid value can be placed in a given row and column
    public boolean validNum(int row, int col, int arr[][], int num) {
        if (validCol(row, num, arr) && validRow(col, num, arr) && validGrid(col, row, num, arr)) {
            return true;
        } else {
            return false;
        }
    }

    static boolean validCol(int row, int num, int arr[][]) {
        for (int i = 0; i < 9; i++) {
            if (num == arr[row][i]) {
                return false;
            }
        }
        return true;
    }

    static boolean validRow(int col, int num, int arr[][]) {
        for (int i = 0; i < 9; i++) {
            if (num == arr[i][col]) {
                return false;
            }
        }
        return true;
    }

    static boolean validGrid(int col, int row, int num, int arr[][]) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((num == arr[row / 3 * 3 + i][col / 3 * 3 + j])) {
                    return false;
                }
            }
        }
        return true;
    }

    // Find and set a valid number for empty cells
    public boolean findValid(int arr[][]) {
        // Loop through rows
        for (int row = 0; row < 9; row++) {
            // Loop through columns
            for (int col = 0; col < 9; col++) {
                // If the cell is empty
                if (arr[row][col] == 0) {
                    // Find a valid value
                    int count = 0;
                    for (int i = 0; i < 9; i++) {
                        int num = arr2[i];
                        validNum(row, col, arr, num);
                        if (validNum(row, col, arr, num)) {
                            count++;
                        }
                    }
                    // If there's only one valid value, set it
                    if (count == 1) {
                        setValid(row, col, arr);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Attempt to solve the Sudoku puzzle
    public boolean validAgain(int arr[][]) {
        boolean first = findValid(arr);
        if (first == false) {
            // Loop through rows
            for (int row = 0; row < 9; row++) {
                // Loop through columns
                for (int col = 0; col < 9; col++) {
                    // If the cell is empty
                    if (arr[row][col] == 0) {
                        // Find a valid value
                        int count = 0;
                        for (int i = 0; i < 9; i++) {
                            int num = arr2[i];
                            validNum(row, col, arr, num);
                            if (validNum(row, col, arr, num)) {
                                count++;
                            }
                        }
                        // If there are two valid values, set one
                        if (count == 2) {
                            setValid(row, col, arr);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Set a valid number in the Sudoku grid
    public void setValid(int row, int col, int arr[][]) {
        for (int i = 0; i < 9; i++) {
            int num = arr2[i];
            validNum(row, col, arr, num);
            if (validNum(row, col, arr, num)) {
                arr[row][col] = num;
            }
        }
    }
}