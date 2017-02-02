package sonnguyen.findpathwithlowercost.Manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sonnguyen.findpathwithlowercost.Models.Matrix;
import sonnguyen.findpathwithlowercost.Models.Path;

/**
 * Created by sonnguyen on 2/1/17.
 * This class contains methods for managing the matrix
 */

public class MatrixVisitor {

    private Matrix matrix;
    private PathComparator pathComparator;

    /**
     * Constructor
     * @param matrix
     */
    public MatrixVisitor(Matrix matrix){
        if (matrix == null) {
            throw new IllegalArgumentException("Grid can't be null");
        }

        this.matrix = matrix;
        pathComparator = new PathComparator();
    }

    /**
     * This method gets the lowest path based on values in grid
     * @return
     */
    public Path getLowestCostPathForGrid() {
        List<Path> allAvailablePaths = new ArrayList<Path>();
        for (int row = 1; row <= matrix.getNoOfRows(); row++) {
            RowVisitor visitor = new RowVisitor(row, matrix, new PathCollector());
            allAvailablePaths.add(visitor.getLowestCostPathForRow());
        }

        Collections.sort(allAvailablePaths, pathComparator);

        return allAvailablePaths.get(0);
    }
}
