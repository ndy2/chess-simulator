package service.game.chess.piece;

import service.game.chess.util.Coordinate;

import java.util.List;

public class Dummy extends Piece{

    public Dummy(Coordinate coor) {
        super("Dummy", coor);
    }

    @Override
    protected List<Coordinate> getTargetList(PieceMap pieceMap, String team, int moveCnt) {
        return null;
    }

    @Override
    public boolean isReachable(PieceMap pieceMap, String team, Coordinate dest) {
        return false;
    }
}
