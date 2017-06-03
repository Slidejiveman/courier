package courierpd;

/**
 * An enumerator for use with Routes. 
 * The enumerator has been added so that generated directions 
 * contain human readable turn information.
 */
public enum Direction {
    /**
     * Represents the direction North. True vs. Magnetic is not an important detail here.
     */
    North,
    /**
     * Represents the direction South.
     */
    South,
    /**
     * Represents the direction East.
     */
    East,
    /**
     * Represents the direction West.
     */
    West
}