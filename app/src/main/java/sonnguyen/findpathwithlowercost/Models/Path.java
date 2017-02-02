package sonnguyen.findpathwithlowercost.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sonnguyen on 2/1/17.
 * This class contains Path model content data for related operation
 */

public class Path {

    public static int MAXIMUM_COST = 50;
    private List<Integer> rowsTraversed = new ArrayList<Integer>();
    private int totalCost = 0;
    private int expectedLength = 0;

    // Constructor with expected length parameter
    public Path(int expectedLength){
        this.expectedLength = expectedLength;
    }

    // Constructor with Path parameter
    public Path (Path path){
        this.totalCost = path.totalCost;
        this.expectedLength= path.expectedLength;
        for (int rowTraversed : path.rowsTraversed) {
            this.rowsTraversed.add(rowTraversed);
        }
    }

    /**
     * This method computes row that has cost value
     * @param row
     * @param cost
     */
    public void addRowWithCost(int row, int cost) {
        rowsTraversed.add(row);
        totalCost += cost;
    }

    /**
     * This method checks if the expected length is complete
     * @return
     */
    public boolean isComplete() {
        return rowsTraversed.size() == expectedLength;
    }

    /**
     * This method returns success if complete is true and overcost is false
     * @return
     */
    public boolean isSuccessful() {
        return isComplete() && !isOverCost();
    }

    /**
     * This method returns over cost value
     * @return
     */
    public boolean isOverCost() {
        return totalCost > MAXIMUM_COST;
    }

    /**
     * This method returns number of rows traveled
     * @return
     */
    public List<Integer> getRowsTraversed() {
        return rowsTraversed;
    }

    /**
     *  This method returns the total cost
     * @return
     */
    public int getTotalCost() {
        return totalCost;
    }

    /**
     *  This method returns the size of number of rows traveled
     * @return
     */
    public int getPathLength() {
        return rowsTraversed.size();
    }
}
