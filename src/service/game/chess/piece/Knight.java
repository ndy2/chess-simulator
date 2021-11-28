package service.game.chess.piece;

import service.game.chess.util.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{

    private final int[] dy;
    private final int[] dx;

    public Knight(Coordinate coor) {
        super("Knight",coor);
        dy = new int[]{-2,-1,1,2,2,1,-1,-2};
        dx = new int[]{1,2,2,1,-1,-2,-2,-1};
    }


    @Override
    protected List<Coordinate> getTargetList(PieceMap pieceMap, String team, int moveCnt){
        List<Coordinate> list = new ArrayList<>();

        for (int d = 0; d < 8; d++) {
            Coordinate moved = getCoor().move(dy[d],dx[d]);
            if(moved.isValid()){
                String targetTeam = pieceMap.getTeam(PieceMap.getKey(moved));
                if(targetTeam!=team){
                    list.add(moved);
                }
            }
        }
        return list;
    }


}
