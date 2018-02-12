package org.azodi.prj;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Simple back track algorithm implementation to find sequence match in {@link CharMatrix}
 * @author Azodi
 * @version 1.0
 */
public class BackTrackingCharMatrixMatcher implements CharMatrixMatcher {

    private int[][] solution;

    @Override
    public synchronized CharMatrixMatch match(CharMatrix matrix, CharSequence word) {

        int rows = matrix.numRows();
        int cols = matrix.numCols();

        // Initialize an empty solution
        solution = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                solution[i][j] = -1;
            }
        }


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (doMatch(matrix, word, i, j, 0)) {
                    SortedSet<CharMatrixMatch.RowColPair> solutionPath = new TreeSet<>();
                    for (int row = 0; row < rows; row++) {
                        for (int col = 0; col < cols; col++) {
                            if (solution[row][col] != -1) {
                                solutionPath.add(new CharMatrixMatch.RowColPair(
                                        solution[row][col],
                                        row,
                                        col
                                ));
                            }
                        }
                    }
                    return new CharMatrixMatch(true, solutionPath);
                }
            }
        }
        return new CharMatrixMatch(false, null);
    }

    private boolean doMatch(CharMatrix matrix,
                            CharSequence word,
                            int row,
                            int col,
                            int index) {

        // check if matrix cell has not been used and cell value matches character value at sequence index
        if (solution[row][col] != -1 || word.charAt(index) != matrix.charAt(row, col)) {
            // does not match return false for this branch
            return false;
        }

        // cell value matches the sequence char at index
        // occupy solution cell
        solution[row][col] = index;

        // if we are at the end of the sequence
        if (index == word.length() - 1) {
            // we are and the solution found
            return true;
        }

        if (row + 1 < matrix.numRows() && doMatch(matrix, word, row + 1, col, index + 1)) {
            // go down (vertical adjacent)
            return true;
        }
        if (row - 1 >= 0 && doMatch(matrix, word, row - 1, col, index + 1)) {
            // go up (vertical adjacent)
            return true;
        }
        if (col + 1 < matrix.numCols() && doMatch(matrix, word, row, col + 1, index + 1)) {
            // go right (horizontal adjacent)
            return true;
        }
        if (col - 1 >= 0 && doMatch(matrix, word, row, col - 1, index + 1)) {
            // go left (horizontal adjacent)
            return true;
        }
        /*
         * can be extended to support diagonal match
         */

        // if none of the option works out, BACKTRACK and return false
        solution[row][col] = -1;
        return false;
    }

}
