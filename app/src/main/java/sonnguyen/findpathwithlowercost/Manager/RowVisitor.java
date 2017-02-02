package sonnguyen.findpathwithlowercost.Manager;

import java.util.List;

import sonnguyen.findpathwithlowercost.Models.Matrix;
import sonnguyen.findpathwithlowercost.Models.Path;

/**
 * Created by sonnguyen on 2/1/17.
 * This class contains methods for handling when each row is visited in the input data matrix
 */

public class RowVisitor {

    private int row;
    private Matrix grid;
    private PathCollector pathCollector;

    /**
     * Constructor
     * @param startRow
     * @param grid
     * @param collector
     */
    public RowVisitor(int startRow, Matrix grid, PathCollector collector) {
        if (grid == null) {
            throw new IllegalArgumentException("Grid is Null");
        } else if (collector == null) {
            throw new IllegalArgumentException("Collector is Null");
        } else if (startRow <= 0 || startRow > grid.getNoOfRows()) {
            throw new IllegalArgumentException("Value should be between grid boundaries");
        }

        this.row = startRow;
        this.grid = grid;
        this.pathCollector = collector;
    }

    /**
     *  This method gets the lowest cost path in 1 row
     * @return
     */
    public Path getLowestCostPathForRow() {
        Path initialPath = new Path(grid.getNoOfColumns());

        visitPathsForRow(row, initialPath);

        return pathCollector.getLowestCostPath();
    }

    /**
     *  This method lists the path for 1 row
     *  @param row
     *  @param path
     *
     */
    private void visitPathsForRow(int row, Path path) {
        if (canVisitRowOnPath(row, path)) {
            visitRowOnPath(row, path);
        }

        List<Integer> adjacentRows = grid.getRowsAdjacentTo(row);
        boolean currentPathAdded = false;

        for (int adjacentRow : adjacentRows) {
            if (canVisitRowOnPath(adjacentRow, path)) {
                Path pathCopy = new Path(path);
                visitPathsForRow(adjacentRow, pathCopy);
            } else if (!currentPathAdded) {
                pathCollector.addPath(path);
                currentPathAdded = true;
            }
        }
    }

    /**
     * This method checks if path is complete and the cost is below maximum
     * @param row
     * @param path
     * @return
     */
    private boolean canVisitRowOnPath(int row, Path path) {
        return !path.isComplete() && !nextVisitOnPathWouldExceedMaximumCost(row, path);
    }

    /**
     * This method adds the row to path
     * @param row
     * @param path
     */
    private void visitRowOnPath(int row, Path path) {
        int columnToVisit = path.getPathLength() + 1;
        path.addRowWithCost(row, grid.getValueAtRowAndColumn(row, columnToVisit));
    }


    /**
     * This method checks the cost is below maximum
     * @param row
     * @param path
     * @return
     */
    private boolean nextVisitOnPathWouldExceedMaximumCost(int row, Path path) {
        int nextColumn = path.getPathLength() + 1;
        return (path.getTotalCost() + grid.getValueAtRowAndColumn(row, nextColumn)) > Path.MAXIMUM_COST;
    }
}
