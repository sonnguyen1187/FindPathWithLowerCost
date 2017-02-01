package sonnguyen.findpathwithlowercost.Manager;

import sonnguyen.findpathwithlowercost.Models.Path;

/**
 * Created by sonnguyen on 2/1/17.
 */

public class PathCollector {
    private Path bestPath;
    private PathComparator comparator;

    public PathCollector() {
        comparator = new PathComparator();
    }

    public Path getLowestCostPath() {
        return bestPath;
    }

    public void addPath(Path newPath) {
        if (comparator.compare(newPath, bestPath) < 0) {
            bestPath = newPath;
        }
    }
}
