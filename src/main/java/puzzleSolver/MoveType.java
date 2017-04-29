package puzzleSolver;

public enum MoveType {
	LEFT('L'),RIGHT('R'),UP('U'),DOWN('D');
	
	private char moveType;
	MoveType(char moveType)
	{
		this.moveType = moveType; 	
	}
	
    public static MoveType fromAcronim(char moveType) {
        for (MoveType heuristic : MoveType.values()) {
            if (heuristic.moveType == moveType) {
                return heuristic;
            }
        }
        throw new IllegalArgumentException("No constant with acronim " + moveType + "found");
    }

}
