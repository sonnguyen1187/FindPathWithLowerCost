package sonnguyen.findpathwithlowercost;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sonnguyen.findpathwithlowercost.Models.Matrix;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by sonnguyen on 2/1/17.
 */

public class MatrixTest {

    @Test(expected = IllegalArgumentException.class)
    public void checkGridHasAtleastOneRow() {
        int values[][] = new int[0][0];
        Matrix subject = new Matrix(values);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkGridHasTenOrLessRows() {
        int values[][] = new int[15][7];
        Matrix subject = new Matrix(values);
    }

    @Test
    public void checksValueAtRowAndColumn() {
        int values[][] = new int[][]{ { 3, 5, 2, 5, 1 }, { 7, 3, 5, 9, 7 } };
        Matrix subject = new Matrix(values);

        assertThat(subject.getValueAtRowAndColumn(2, 1), equalTo(7));
        assertThat(subject.getValueAtRowAndColumn(1, 5), equalTo(1));
    }

    @Test
    public void getAdjacentRowsForOneRowGrid() {
        Matrix oneRowGrid = new Matrix(new int[][]{ { 2, 2, 3, 3, 3 } });
        List<Integer> adjacentRows = new ArrayList<Integer>(
                Arrays.asList(new Integer[]{ 1 })
        );

        assertThat(oneRowGrid.getRowsAdjacentTo(1), equalTo(adjacentRows));
    }

    @Test
    public void getAdjacentRowsForTwoRowGrid() {
        Matrix twoRowGrid = new Matrix(new int[][]{ { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 } });
        List<Integer> adjacentRows = new ArrayList<Integer>(
                Arrays.asList(new Integer[]{ 1, 2 })
        );

        assertThat(twoRowGrid.getRowsAdjacentTo(1), equalTo(adjacentRows));
        assertThat(twoRowGrid.getRowsAdjacentTo(2), equalTo(adjacentRows));
    }

    @Test
    public void getAdjacentRowsForThreeRowsGrid() {
        Matrix threeRowGrid = new Matrix(new int[][]{ { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 } });
        List<Integer> adjacentRows = new ArrayList<Integer>(
                Arrays.asList(new Integer[]{ 1, 2, 3 })
        );

        assertThat(threeRowGrid.getRowsAdjacentTo(1), equalTo(adjacentRows));
        assertThat(threeRowGrid.getRowsAdjacentTo(2), equalTo(adjacentRows));
        assertThat(threeRowGrid.getRowsAdjacentTo(3), equalTo(adjacentRows));
    }

    @Test
    public void getAdjacentRowsReturnsNothingWhenInvalidRowIsPassed() {
        Matrix oneRowGrid = new Matrix(new int[][]{ { 2, 2, 2, 2, 2 } });
        assertThat(oneRowGrid.getRowsAdjacentTo(0).size(), equalTo(0));
        assertThat(oneRowGrid.getRowsAdjacentTo(2).size(), equalTo(0));
    }


    @Test
    public void getAdjacentRowsReturnsWrappedRowsWhenMoreThanThreeRows() {
        Matrix fourRowGrid = new Matrix(new int[][]{ { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 12, 14, 15 }, { 16, 17, 18, 19, 20 } });
        List<Integer> expectedRows = new ArrayList<Integer>(
                Arrays.asList(new Integer[]{ 1, 2, 4 })
        );

        assertThat(fourRowGrid.getRowsAdjacentTo(1), equalTo(expectedRows));
    }

    @Test
    public void asDelimitedStringOutputsValuesForARowSeparatedByChosenDelimiter() {
        Matrix oneRowGrid = new Matrix(new int[][]{ { 1, 2, 3, 4, 5 } });

        assertThat(oneRowGrid.asDelimitedString("|"), equalTo("1|2|3|4|5"));
    }

    @Test
    public void asDelimitedStringOutputsValuesForMultipleRowsWithTrailingLineBreaks() {
        Matrix twoRowGrid = new Matrix(new int[][]{ { 1, 2, 3, 4, 5 }, { 2, 4, 6, 8, 10 } });

        assertThat(twoRowGrid.asDelimitedString("\t"), equalTo("1\t2\t3\t4\t5\n2\t4\t6\t8\t10"));
    }
}
