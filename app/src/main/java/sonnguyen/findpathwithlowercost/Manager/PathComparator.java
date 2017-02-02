package sonnguyen.findpathwithlowercost.Manager;

import java.util.Comparator;

import sonnguyen.findpathwithlowercost.Models.Path;

/**
 * Created by sonnguyen on 2/1/17.
 * This class contains methods for path comparison
 */

public class PathComparator implements Comparator<Path> {
    private static int SORT_LEFT_BIGGER = -1;
    private static int SORT_RIGHT_BIGGER = 1;
    private static int SORT_EQUAL = 0;


    /**
     *  This override method compares the length and cost of left and right path
     * @param leftPath
     * @param rightPath
     * @return
     */
    @Override
    public int compare(Path leftPath, Path rightPath) {
        int comparedLength = compareLengths(leftPath, rightPath);
        return (comparedLength != 0) ? comparedLength : compareCosts(leftPath, rightPath);
    }

    /**
     * This method compares the length of left and right path
     * @param leftPath
     * @param rightPath
     * @return
     */
    private int compareLengths(Path leftPath, Path rightPath) {
        int leftLength = getLengthFromPath(leftPath);
        int rightLength = getLengthFromPath(rightPath);

        return (leftLength > rightLength) ? SORT_LEFT_BIGGER : (leftLength == rightLength) ? SORT_EQUAL : SORT_RIGHT_BIGGER;
    }


    /**
     * This method compares the cost of left and right path
     * @param leftPath
     * @param rightPath
     * @return
     */
    private int compareCosts(Path leftPath, Path rightPath) {
        int leftCost = getCostFromPath(leftPath);
        int rightCost = getCostFromPath(rightPath);

        return (leftCost < rightCost) ? SORT_LEFT_BIGGER : (leftCost == rightCost) ? SORT_EQUAL : SORT_RIGHT_BIGGER;
    }

    /**
     * This method gets the length of path
     * @param path
     * @return
     */
    private int getLengthFromPath(Path path) {
        if (path != null) {
            return path.getPathLength();
        } else {
            return Integer.MIN_VALUE;
        }
    }

    /**
     * This method gets the total cost of path
     * @param path
     * @return
     */
    private int getCostFromPath(Path path) {
        if (path != null) {
            return path.getTotalCost();
        } else {
            return Integer.MAX_VALUE;
        }
    }
}
