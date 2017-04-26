package puzzleSolver.searcher.astar;

/**
 * Created by maciek on 25.04.17.
 */
public enum HeuristicType {
    MANHATTAN("manh"), HAMMING("hamm");

    private String acronim;

    HeuristicType(String acronim) {
        this.acronim = acronim;
    }

    public static HeuristicType fromAcronim(String acronim) {
        for (HeuristicType heuristic : HeuristicType.values()) {
            if (heuristic.acronim.equals(acronim)) {
                return heuristic;
            }
        }
        throw new IllegalArgumentException("No constant with acronim " + acronim + "found");
    }

    public String getAcronim() {
        return acronim;
    }
}
