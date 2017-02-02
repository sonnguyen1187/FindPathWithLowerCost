package sonnguyen.findpathwithlowercost.Manager;

import sonnguyen.findpathwithlowercost.Models.Path;

/**
 * Created by sonnguyen on 2/1/17.
 * This class contains methods for managing the best path taken
 */

public class PathCollector {
    private Path bestPath;
    private PathComparator comparator;

    /**
     * Constructor
     */
    public PathCollector() {
        comparator = new PathComparator();
    }

    /**
     *  This method gets the lowest cost path
     * @return
     */
    public Path getLowestCostPath() {
        return bestPath;
    }

    /**
     * This method will check and add the lowest path
     * @param newPath
     */
    public void addPath(Path newPath) {
        if (comparator.compare(newPath, bestPath) < 0) {
            bestPath = newPath;
        }
    }
}
