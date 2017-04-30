package puzzleSolver;

public enum MoveType {
    LEFT('L'), RIGHT('R'), UP('U'), DOWN('D'), NONE('N');

    private char moveType;

    MoveType(char moveType) {
        this.moveType = moveType;
    }

    public static MoveType fromAcronim(char moveType) {
        for (MoveType mvType : MoveType.values()) {
            if (mvType.moveType == moveType) {
                return mvType;
            }
        }
        throw new IllegalArgumentException("No constant with acronim " + moveType + "found");
    }

}
