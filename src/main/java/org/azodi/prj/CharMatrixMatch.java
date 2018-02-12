package org.azodi.prj;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Char matrix match solution
 * @author Azodi
 * @version 1.0
 */
public class CharMatrixMatch {

    private final boolean matched;

    private final SortedSet<RowColPair> path;

    public CharMatrixMatch(boolean matched, SortedSet<RowColPair> path) {
        this.matched = matched;
        if (matched) {
            this.path = new TreeSet<>(path);
        } else {
            this.path = new TreeSet<>();
        }
    }

    public boolean isMatched() {
        return matched;
    }

    public Set<RowColPair> getPath() {
        return Collections.unmodifiableSet(path);
    }

    public static class RowColPair implements Comparable<RowColPair> {
        private final int charIndex;
        private final int row;
        private final int col;

        public RowColPair(int charIndex, int row, int col) {
            this.charIndex = charIndex;
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getCharIndex() {
            return charIndex;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RowColPair that = (RowColPair) o;
            return row == that.row &&
                    col == that.col;
        }

        @Override
        public int hashCode() {

            return Objects.hash(row, col);
        }


        @Override
        public int compareTo(RowColPair o) {
            return Integer.compare(this.getCharIndex(), o.getCharIndex());
        }

        @Override
        public String toString() {
            return "[" + getRow() + "," + getCol() + "]";
        }
    }
}
