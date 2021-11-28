package service.game.chess.util;

public class MoveInfo {
    private String team;
    private String type;
    private Coordinate from,dest;
    private int moveCnt;

    private boolean isTake;
    private boolean isPromote;

    public boolean isPromote() {
        return isPromote;
    }

    public boolean isCastling() {
        return isCastling;
    }

    public void setCastling(boolean castling) {
        isCastling = castling;
    }

    private boolean isCastling;


    public String getType() {
        return type;
    }

    public int getMoveCnt() {
        return moveCnt;
    }

    public void setMoveCnt(int moveCnt) {
        this.moveCnt = moveCnt;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFrom(Coordinate from) {
        this.from = from;
    }

    public void setDest(Coordinate dest) {
        this.dest = dest;
    }

    public void setTake(boolean take) {
        isTake = take;
    }

    public void setPromote(boolean promote) {
        isPromote = promote;
    }

    public boolean isTake() {
        return isTake;
    }

    public Coordinate getFrom() {
        return from;
    }

    public Coordinate getDest() {
        return dest;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team =team;
    }


    @Override
    public String toString() {
        return "MoveInfo{" +
                "team='" + team + '\'' +
                ", type='" + type + '\'' +
                ", from=" + from +
                ", dest=" + dest +
                ", moveCnt=" + moveCnt +
                ", isTake=" + isTake +
                ", isPromote=" + isPromote +
                ", isCastling=" + isCastling +
                '}';
    }
}
