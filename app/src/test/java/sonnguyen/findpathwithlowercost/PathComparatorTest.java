package sonnguyen.findpathwithlowercost;

import org.junit.Before;
import org.junit.Test;

import sonnguyen.findpathwithlowercost.Manager.PathComparator;
import sonnguyen.findpathwithlowercost.Models.Path;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by sonnguyen on 2/1/17.
 */

public class PathComparatorTest {

    private PathComparator subject;

    private Path lowerCostPath;
    private Path higherCostPath;

    private Path shorterPath;
    private Path longerPath;

    @Before
    public void setUp() {
        subject = new PathComparator();

        lowerCostPath = new Path(1);
        lowerCostPath.addRowWithCost(1, 1);

        higherCostPath = new Path(1);
        higherCostPath.addRowWithCost(1, 10);

        shorterPath = new Path(5);
        shorterPath.addRowWithCost(1, 9);

        longerPath = new Path(5);
        longerPath.addRowWithCost(1, 5);
    }

    @Test
    public void returnsNegativeOneIfFirstPathIsLongerThanSecond() {
        assertThat(subject.compare(longerPath, shorterPath), equalTo(-1));
    }

    @Test
    public void returnsPositiveOneIfFirstPathIsShorterThanSecond() {
        assertThat(subject.compare(shorterPath, longerPath), equalTo(1));
    }

    @Test
    public void returnsZeroIfPathsHaveSameLengthAndCost() {
        assertThat(subject.compare(shorterPath, shorterPath), equalTo(0));
    }

    @Test
    public void returnsNegativeOneIfFirstPathHasLowerCostThanSecondWithTheSameLength() {
        assertThat(subject.compare(lowerCostPath, higherCostPath), equalTo(-1));
    }

    @Test
    public void returnsPositiveOneIfFirstPathHasLowerCostThanSecondWithTheSameLength() {
        assertThat(subject.compare(higherCostPath, lowerCostPath), equalTo(1));
    }

    @Test
    public void returnsPositiveOneIfFirstPathIsNull() {
        assertThat(subject.compare(null, lowerCostPath), equalTo(1));
    }

    @Test
    public void returnsNegativeOneIfLastPathIsNull() {
        assertThat(subject.compare(lowerCostPath, null), equalTo(-1));
    }

    @Test
    public void returnsZeroIfBothPathsAreNull() {
        assertThat(subject.compare(null, null), equalTo(0));
    }
}
