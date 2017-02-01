package sonnguyen.findpathwithlowercost;

import org.junit.Before;
import org.junit.Test;

import sonnguyen.findpathwithlowercost.Manager.PathCollector;
import sonnguyen.findpathwithlowercost.Models.Path;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by sonnguyen on 2/1/17.
 */

public class PathCollectorTest {
    private PathCollector collector;

    @Before
    public void setUp() {
        collector = new PathCollector();
    }

    @Test
    public void collectorShouldReturnsNullIfNoPathIsAdded() {
        assertThat(collector.getLowestCostPath(), is(nullValue()));
    }

    @Test
    public void collectorReturnsPathAddedIfOnlyOnePathAdded() {
        Path expectedPath = new Path(5);
        expectedPath.addRowWithCost(1, 5);

        collector.addPath(expectedPath);

        assertThat(collector.getLowestCostPath(), equalTo(expectedPath));
    }

    @Test
    public void collectorReturnsLongerPathOfTwoPathsAdded() {
        Path longerPath = new Path(5);
        longerPath.addRowWithCost(1, 5);
        longerPath.addRowWithCost(1, 5);
        Path shorterPath = new Path(5);
        shorterPath.addRowWithCost(1, 10);

        collector.addPath(longerPath);
        collector.addPath(shorterPath);

        assertThat(collector.getLowestCostPath(), equalTo(longerPath));
    }

    @Test
    public void collectorReturnsLowerCostPathOfTwoEqualLengthPathsAdded() {
        Path lowCostPath = new Path(5);
        lowCostPath.addRowWithCost(1, 5);
        lowCostPath.addRowWithCost(1, 5);
        Path highCostPath = new Path(5);
        highCostPath.addRowWithCost(1, 10);
        highCostPath.addRowWithCost(1, 20);

        collector.addPath(lowCostPath);
        collector.addPath(highCostPath);

        assertThat(collector.getLowestCostPath(), equalTo(lowCostPath));
    }

    @Test
    public void collectorPrefersExistingPathIfPathsAreEqual() {
        Path firstPath = new Path(5);
        firstPath.addRowWithCost(1, 5);
        firstPath.addRowWithCost(1, 5);
        Path secondPath = new Path(5);
        secondPath.addRowWithCost(1, 5);
        secondPath.addRowWithCost(1, 5);

        collector.addPath(firstPath);
        collector.addPath(secondPath);

        assertThat(collector.getLowestCostPath(), equalTo(firstPath));
    }
}
