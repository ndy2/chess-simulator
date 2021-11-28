package service.game.chess.piece;

import service.game.chess.util.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece{

    private final int[] dy;
    private final int[] dx;

    public Bishop(Coordinate coor) {
        super("Bishop", coor);

        dy = new int[]{-1,1,1,-1};
        dx = new int[]{1,1,-1,-1};
    }

    @Override
    protected List<Coordinate> getTargetList(PieceMap pieceMap, String team, int moveCnt) {
        List<Coordinate> list = new ArrayList<>();

        for (int d = 0; d < 4; d++) {
            int len = 1;
            do {
                Coordinate moved = getCoor().move(dy[d] * len, dx[d] * len);
                if (!moved.isValid()) {break;}
                String targetTeam = pieceMap.getTeam(PieceMap.getKey(moved));
                if(targetTeam.equals(team)){ break;}

                list.add(moved);
                if(!targetTeam.equals("dummyTeam")){break;}

                len+=1;
            }while(true);
        }
        return list;
    }

}
