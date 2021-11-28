package service.game.chess.piece;

import service.game.chess.util.Coordinate;

import java.util.List;

public abstract class Piece {
    private String type;
    private Coordinate coor;

    public int getMovedCnt() {
        return movedCnt;
    }

    public void setMovedCnt(int movedCnt) {
        this.movedCnt = movedCnt;
    }

    private int movedCnt;

    public Piece(String type, Coordinate coor) {
        this.type = type;
        this.coor = coor;
        this.movedCnt = 0;
    }

    public String getType() {
        return type;
    }

    public void setCoor(Coordinate coor) {
        this.coor = coor;
    }

    public Coordinate getCoor() {
        return coor;
    }

    public boolean isReachable(PieceMap pieceMap, String team, Coordinate dest) {
        int dontCare = 0;
        return isReachable(pieceMap,team,dest,dontCare);
    }

    public boolean isReachable(PieceMap pieceMap, String team, Coordinate dest, int moveCnt) {
        for (Coordinate coordinate : getTargetList(pieceMap, team, moveCnt)) {
            if(coordinate.equals(dest))
                return true;
        }
        return false;
    }

    protected abstract List<Coordinate> getTargetList(PieceMap pieceMap, String team, int moveCnt);


}
