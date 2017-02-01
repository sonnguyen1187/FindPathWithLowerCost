package sonnguyen.findpathwithlowercost.Manager;

import java.util.List;

import sonnguyen.findpathwithlowercost.MainActivity;
import sonnguyen.findpathwithlowercost.Models.Matrix;
import sonnguyen.findpathwithlowercost.Models.Path;

/**
 * Created by sonnguyen on 2/1/17.
 */

public class RowVisitor {

    private int row;
    private Matrix grid;

    private PathCollector pathCollector;

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


    public Path getLowestCostPathForRow() {
        Path initialPath = new Path(grid.getNoOfColumns());

        visitPathsForRow(row, initialPath);

        return pathCollector.getLowestCostPath();
    }


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

    private boolean canVisitRowOnPath(int row, Path path) {
        return !path.isComplete() && !nextVisitOnPathWouldExceedMaximumCost(row, path);
    }

    private void visitRowOnPath(int row, Path path) {
        int columnToVisit = path.getPathLength() + 1;
        path.addRowWithCost(row, grid.getValueAtRowAndColumn(row, columnToVisit));
    }


    private boolean nextVisitOnPathWouldExceedMaximumCost(int row, Path path) {
        int nextColumn = path.getPathLength() + 1;
        return (path.getTotalCost() + grid.getValueAtRowAndColumn(row, nextColumn)) > Path.MAXIMUM_COST;
    }
}
