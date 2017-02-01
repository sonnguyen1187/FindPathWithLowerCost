package sonnguyen.findpathwithlowercost;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import sonnguyen.findpathwithlowercost.Models.Path;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by sonnguyen on 2/1/17.
 */

public class PathTest {
    private Path subject;

    @Before
    public void setUp() {
        subject = new Path(2);
    }

    @Test
    public void beginsWithNoRowsTraversed() {
        assertThat(subject.getRowsTraversed().size(), equalTo(0));
    }

    @Test
    public void beginsWithNoCost() {
        assertThat(subject.getTotalCost(), equalTo(0));
    }

    @Test
    public void beginsUnsuccessful() {
        assertThat(subject.isSuccessful(), is(false));
    }

    @Test
    public void beginsWithPathLengthOfZero() {
        assertThat(subject.getPathLength(), equalTo(0));
    }

    @Test
    public void beginsIncomplete() {
        assertThat(subject.isComplete(), is(false));
    }

    @Test
    public void returnsRowsAdded() {
        List<Integer> expectedRows = new ArrayList<Integer>();

        expectedRows.add(1);
        subject.addRowWithCost(1, 0);
        assertThat(subject.getRowsTraversed(), equalTo(expectedRows));

        expectedRows.add(10);
        subject.addRowWithCost(10, 0);
        assertThat(subject.getRowsTraversed(), equalTo(expectedRows));
    }


}
