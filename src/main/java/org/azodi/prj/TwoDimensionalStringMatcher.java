package org.azodi.prj;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TwoDimensionalStringMatcher {

    public static void main(String[] args) throws IOException {
        // try to find matrix file to initialize CharMatrix from
        CharMatrix charMatrix;
        System.out.println("Initializing char matrix");
        if (args == null || args.length < 1) {
            System.out.println("Please specify a file address from which you " +
                    "want to initialize your character matrix.");
            System.out.println("Using default matrix source");
            charMatrix = CharMatrix.Builder.init().from(TwoDimensionalStringMatcher.class.getClassLoader().getResourceAsStream("matrix.txt")).build();
        } else {
            String sourceAddress = args[0];
            charMatrix = CharMatrix.Builder.init().from(Files.newInputStream(Paths.get(sourceAddress))).build();
        }
        System.out.println("Character matrix initialized");
        System.out.println(charMatrix);

        CharMatrixMatcher matcher = new BackTrackingCharMatrixMatcher();

        Scanner scanner = new Scanner(System.in);
        //noinspection InfiniteLoopStatement
        while (true) {
            System.out.println("Enter next sequence to match: ");
            String sequence = scanner.next();
            CharMatrixMatch match = charMatrix.contains(sequence, matcher);
            if (match.isMatched()) {
                System.out.println("Match Found");
                System.out.println(match.getPath());
//                printMatrixSolution(charMatrix, match);
            } else {
                System.out.println("Match not found");
            }
        }
    }

    @SuppressWarnings("unused")
    private static void printMatrixSolution(CharMatrix charMatrix, CharMatrixMatch match) {
        List<CharMatrixMatch.RowColPair> solutionPath = new ArrayList<>(match.getPath());
        int[][] solutionMatrix = new int[charMatrix.numRows()][charMatrix.numCols()];
        for (int i = 0; i < charMatrix.numRows(); i++) {
            for (int j = 0; j < charMatrix.numCols(); j++) {
                CharMatrixMatch.RowColPair pair = new CharMatrixMatch.RowColPair(-1, i, j);
                if (solutionPath.contains(pair)) {
                    solutionMatrix[i][j] = solutionPath.get(solutionPath.indexOf(pair)).getCharIndex() + 1;
                } else {
                    solutionMatrix[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < charMatrix.numRows(); i++) {
            for (int j = 0; j < charMatrix.numCols(); j++) {
                System.out.print(solutionMatrix[i][j]);
                if (j < charMatrix.numCols() - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }


//    private static String randomMatrix() {
//        StringBuilder result = new StringBuilder();
//        String possibleValues = "abcdefghijklmnopqrstuvwxyz";
//        Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 20; j++) {
//                result.append(possibleValues.charAt(random.nextInt(possibleValues.length())));
//                result.append(",");
//            }
//            result.deleteCharAt(result.length() - 1);
//            result.append("\n");
//        }
//        return result.toString();
//    }
}
