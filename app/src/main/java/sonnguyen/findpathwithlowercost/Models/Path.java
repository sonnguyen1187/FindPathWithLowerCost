package sonnguyen.findpathwithlowercost.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sonnguyen on 2/1/17.
 */

public class Path {

    public static int MAXIMUM_COST = 50;
    private List<Integer> rowsTraversed = new ArrayList<Integer>();

    private int totalCost = 0;
    private int expectedLength = 0;

    public Path(int expectedLength){
        this.expectedLength = expectedLength;
    }

    public Path (Path path){
        this.totalCost = path.totalCost;
        this.expectedLength= path.expectedLength;
        for (int rowTraversed : path.rowsTraversed) {
            this.rowsTraversed.add(rowTraversed);
        }
    }

    public void addRowWithCost(int row, int cost) {
        rowsTraversed.add(row);
        totalCost += cost;
    }

    public boolean isComplete() {
        return rowsTraversed.size() == expectedLength;
    }


    public boolean isSuccessful() {
        return isComplete() && !isOverCost();
    }

    public boolean isOverCost() {
        return totalCost > MAXIMUM_COST;
    }


    public List<Integer> getRowsTraversed() {
        return rowsTraversed;
    }

    public void setRowsTraversed(List<Integer> rowsTraversed) {
        this.rowsTraversed = rowsTraversed;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getExpectedLength() {
        return expectedLength;
    }

    public void setExpectedLength(int expectedLength) {
        this.expectedLength = expectedLength;
    }
    public int getPathLength() {
        return rowsTraversed.size();
    }
}
