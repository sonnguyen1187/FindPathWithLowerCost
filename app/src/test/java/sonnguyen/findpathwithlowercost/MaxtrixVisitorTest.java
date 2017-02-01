package sonnguyen.findpathwithlowercost;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sonnguyen.findpathwithlowercost.Manager.MatrixVisitor;
import sonnguyen.findpathwithlowercost.Models.Matrix;
import sonnguyen.findpathwithlowercost.Models.Path;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by sonnguyen on 2/1/17.
 */

public class MaxtrixVisitorTest {

    @Test(expected = IllegalArgumentException.class)
    public void checkGridVisitorForNullValue() {
        MatrixVisitor subject = new MatrixVisitor(null);
    }

    @Test
    public void findPathForExampleOne() {
        Matrix grid = new Matrix(new int[][]{
                { 3, 4, 1, 2, 8, 6 },
                { 6, 1, 8, 2, 7, 4 },
                { 5, 9, 3, 9, 9, 5 },
                { 8, 4, 1, 3, 2, 6 },
                { 3, 7, 2, 8, 6, 4 }
        });
        MatrixVisitor visitor = new MatrixVisitor(grid);
        List<Integer> expectedPath = new ArrayList<Integer>(
                Arrays.asList(new Integer[]{ 1, 2, 3, 4, 4, 5 })
        );

        Path solution = visitor.getLowestCostPathForGrid();
        assertThat(solution.isSuccessful(), is(true));
        assertThat(solution.getTotalCost(), equalTo(16));
        assertThat(solution.getRowsTraversed(), equalTo(expectedPath));
    }

    @Test
    public void findsPathForExampleTwo() {
        Matrix grid = new Matrix(new int[][]{
                { 3, 4, 1, 2, 8, 6 },
                { 6, 1, 8, 2, 7, 4 },
                { 5, 9, 3, 9, 9, 5 },
                { 8, 4, 1, 3, 2, 6 },
                { 3, 7, 2, 1, 2, 3 }
        });
        MatrixVisitor visitor = new MatrixVisitor(grid);
        List<Integer> expectedPath = new ArrayList<Integer>(
                Arrays.asList(new Integer[]{ 1, 2, 1, 5, 4, 5 })
        );

        Path solution = visitor.getLowestCostPathForGrid();
        assertThat(solution.isSuccessful(), is(true));
        assertThat(solution.getTotalCost(), equalTo(11));
        assertThat(solution.getRowsTraversed(), equalTo(expectedPath));
    }

    @Test
    public void findsPathForExampleThree() {
        Matrix grid = new Matrix(new int[][]{
                { 19, 10, 19, 10, 19 },
                { 21, 23, 20, 19, 12 },
                { 20, 12, 20, 11, 10 }
        });
        MatrixVisitor visitor = new MatrixVisitor(grid);
        List<Integer> expectedPath = new ArrayList<Integer>(
                Arrays.asList(new Integer[]{ 1, 1, 1 })
        );

        Path solution = visitor.getLowestCostPathForGrid();
        assertThat(solution.isSuccessful(), is(false));
        assertThat(solution.getTotalCost(), equalTo(48));
        assertThat(solution.getRowsTraversed(), equalTo(expectedPath));
    }
}
