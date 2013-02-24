package sorter;

import pojo.Heuristic;
import pojo.State;

import java.util.Comparator;

/**
 * User: Rory
 * Date: 2/13/13
 * Time: 5:05 PM
 */

/**
 * This class handles sorting of States based on different heuristics
 */
public class HeuristicSorter implements Comparator<State>{

    Heuristic heuristic;

    public HeuristicSorter(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    @Override
    /**
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     *         first argument is less than, equal to, or greater than the
     *         second.
     */
    public int compare(State o1, State o2) {
        int result;
        switch(heuristic){
            case Euclidean: result = o1.getEuclideanDistanceFromGoal() - o2.getEuclideanDistanceFromGoal(); break;
            case Manhattan: result = o1.getManhattanDistanceFromGoal() - o2.getManhattanDistanceFromGoal(); break;
            case ManhattanPlusDepth: result = (o1.getDepth() + o1.getManhattanDistanceFromGoal()) -
                                              (o2.getDepth() + o2.getManhattanDistanceFromGoal()); break;
            case Chebyshev: result = o1.getChebyshevDistanceFromGoal() - o2.getChebyshevDistanceFromGoal(); break;
            case ChebyshevPlusDepth: result = (o1.getDepth() + o1.getChebyshevDistanceFromGoal()) -
                    (o2.getDepth() + o2.getChebyshevDistanceFromGoal()); break;
            case ChebyshevTimesDepth: result = (o1.getDepth() + o1.getChebyshevDistanceFromGoal()) -
                                              (o2.getDepth() * o2.getChebyshevDistanceFromGoal()); break;
            default: result = o1.getManhattanDistanceFromGoal() - o2.getManhattanDistanceFromGoal(); break;
        }
        return result;
    }
}
