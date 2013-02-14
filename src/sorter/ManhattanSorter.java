package sorter;

import pojo.State;

import java.util.Comparator;

/**
 * User: Rory
 * Date: 2/13/13
 * Time: 5:04 PM
 */

public class ManhattanSorter implements Comparator<State> {
    @Override
    /**
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     *         first argument is less than, equal to, or greater than the
     *         second.
     */
    public int compare(State o1, State o2) {
        return o1.getManhattanDistanceFromGoal() - o2.getManhattanDistanceFromGoal();
    }
}
