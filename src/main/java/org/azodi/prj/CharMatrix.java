package org.azodi.prj;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Basic char matrix structure
 *
 * @author Azodi
 * @version 1.0
 */
public class CharMatrix {

    char[][] matrix;

    public CharMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * @return matrix char row length
     */
    public int numRows() {
        return matrix.length;
    }

    /**
     * @return Matrix char column length
     */
    public int numCols() {
        return matrix[0].length;
    }

    /**
     * @param row cell row index
     * @param col cell column index
     * @return Cell value
     */
    public char charAt(int row, int col) {
        return matrix[row][col];
    }

    /**
     * Searches the matrix to find the first match result.
     *
     * @param sequence the sequence to search for
     * @param matcher  mather implementation
     * @return {@link CharMatrixMatch} result for the sequence.
     */
    public CharMatrixMatch contains(CharSequence sequence, CharMatrixMatcher matcher) {
        return matcher.match(this, sequence);
    }

    @Override
    public String toString() {
        StringBuilder printObject = new StringBuilder();
        for (int i = 0; i < numRows(); i++) {
            for (int j = 0; j < numCols(); j++) {
                printObject.append(charAt(i, j));
                printObject.append(",");
            }
            printObject.deleteCharAt(printObject.length() - 1);
            printObject.append("\n");
        }
        return printObject.toString();
    }

    public static class Builder {

        private CharMatrix result;

        public static Builder init() {
            return new Builder();
        }

        public Builder from(InputStream inputStream) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            List<List<String>> map = reader.lines()
                    .map(line -> Arrays.asList(line.split(",")))
                    .collect(Collectors.toList());
            int rows = map.size();
            int cols = map.get(0).size();
            char[][] matrix = new char[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = map.get(i).get(j).charAt(0);
                }
            }
            result = new CharMatrix(matrix);
            return this;
        }

        public CharMatrix build() {
            return result;
        }

    }

}
