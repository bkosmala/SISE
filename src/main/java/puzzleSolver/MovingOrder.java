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
    private MoveType current;

    MovingOrder(String acronim) {
        this.acronim = acronim;     
        this.sequence = new MoveType[4];
        
        for(int i = 0; i<acronim.length();i++)
        {
        	this.sequence[i] =  MoveType.fromAcronim(acronim.charAt(i));
        }
        this.current = MoveType.NONE;
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
    
    public MoveType getLastMove()
    {
    	return this.current;
    }
    
    public MoveType nextMove()
    {
    	if(this.current == MoveType.NONE)
    	{
    		this.current = this.sequence[0];
    		return this.sequence[0];
    	}
    	else if (this.current == this.sequence[0])
    	{
    		this.current = this.sequence[1];
    		return this.sequence[1];
    	}
    	else if (this.current == this.sequence[1])
    	{
    		this.current = this.sequence[2];
    		return this.sequence[2];
    	}
    	else if (this.current == this.sequence[2])
    	{
    		this.current = this.sequence[3];
    		return this.sequence[3];
    	}
    	else
    	{
    		return MoveType.NONE;
    	}	
    }

}
