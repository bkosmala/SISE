package puzzleSolver;

import puzzleSolver.searcher.astar.HeuristicType;

public enum MovingOrder {
	LRUD("LRUD"),
	LRDU("LRDU"),
	LURD("LURD"),
	LUDR("LUDR"),
	LDUR("LDUR"),
	LDRU("LDRU"),
	RLUD("RLUD"),
	RLDU("RLDU"),
	RULD("RULD"),
	RUDL("RUDL"),
	RDUL("RDUL"),
	RDLU("RDLU"),
	ULRD("ULRD"),
	ULDR("ULDR"),
	URDL("URDL"),
	URLD("URLD"),
	UDRL("UDRL"),
	UDLR("UDLR"),
	DLRU("DLRU"),
	DLUR("DLUR"),
	DULR("DULR"),
	DURL("DURL"),
	DRUL("DRUL"),
	DRLU("DRLU");
	
    private String acronim;
    private MoveType[] sequence;

    MovingOrder(String acronim) {
        this.acronim = acronim;     
        this.sequence = new MoveType[4];
        
        for(int i = 0; i<acronim.length();i++)
        {
        	this.sequence[i] =  MoveType.fromAcronim(acronim.charAt(i));
        }
    }

    public static MovingOrder fromAcronim(String acronim) {
        for (MovingOrder heuristic : MovingOrder.values()) {
            if (heuristic.acronim.equals(acronim)) {
                return heuristic;
            }
        }
        throw new IllegalArgumentException("No constant with acronim " + acronim + "found");
    }

    public String getAcronim() {
        return acronim;
    }

	public MoveType[] getSequence() {
		return sequence;
	}
    


}
