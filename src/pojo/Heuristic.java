package pojo;

/**
 * User: Rory
 * Date: 2/19/13
 * Time: 12:44 PM
 */

/**
 * This enum stores all the currently supported Heuristics
 */

public enum Heuristic {
    Manhattan, // completes, is apparently not optimal
    Euclidean, // doesn't complete puzzle 3,5
    ManhattanPlusDepth, // completes, is apparently not optimal
    Chebyshev, // doesn't compelte puzzle 3? 4? idr
    ChebyshevPlusDepth,
    ChebyshevTimesDepth // completes, doesn't appear to be optimal
}
