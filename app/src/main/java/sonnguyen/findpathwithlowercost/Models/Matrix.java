package sonnguyen.findpathwithlowercost.Models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sonnguyen on 2/1/17.
 *
 *
 */

public class Matrix {
    int[][] values;

    public Matrix(int[][] values){
        if (values.length < 1 || values.length > 10) {
            throw new IllegalArgumentException("No of rows should be between 1 and 10");
        } else if (values[0].length < 5 || values[0].length > 100) {
            throw new IllegalArgumentException("No of columns should be between 5 and 100");
        }
        this.values = values;
    }
    public int getValueAtRowAndColumn(int row, int column) {
        return values[row - 1][column - 1];
    }

    public int getNoOfRows() {

        return values.length;
    }

    public int getNoOfColumns() {
        return values[0].length;
    }

    public List<Integer> getRowsAdjacentTo(int rowNumber) {
        Set<Integer> adjacentRows = new HashSet<Integer>();

        if (isValidRowNumber(rowNumber)) {
            adjacentRows.add(rowNumber);
            adjacentRows.add(getRowAbove(rowNumber));
            adjacentRows.add(getRowBelow(rowNumber));
        }

        return new ArrayList<Integer>(adjacentRows);
    }

    public String asDelimitedString(String delimiter) {
        StringBuilder builder = new StringBuilder();

        for (int row = 0; row < values.length; row++) {
            for (int column = 0; column < values[row].length; column++) {
                builder.append(values[row][column]);
                if (column < values[row].length - 1) {
                    builder.append(delimiter);
                }
            }
            if (row < values.length - 1) {
                builder.append("\n");
            }
        }

        return builder.toString();
    }

    private boolean isValidRowNumber(int rowNumber) {
        return (rowNumber > 0) && (rowNumber <= values.length);
    }
    private int getRowAbove(int rowNumber) {
        int potentialRowNumber = rowNumber - 1;
        return (potentialRowNumber < 1) ? values.length : potentialRowNumber;
    }

    private int getRowBelow(int rowNumber) {
        int potentialRowNumber = rowNumber + 1;
        return (potentialRowNumber > values.length) ? 1 : potentialRowNumber;
    }
}
