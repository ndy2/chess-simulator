package service.game.chess.piece;

import service.game.chess.util.Coordinate;
import service.game.chess.util.MoveInfo;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{

    private final int[] dy;
    private final int[] dx;

    public King(Coordinate coor) {
        super("King", coor);

        dy = new int[]{-1,0,1,1,1,0,-1,-1};
        dx = new int[]{1,1,1,0,-1,-1,-1,0};
    }

    public static void castle(String move, String team, List<MoveInfo> miList, int moveCnt) {
        if(team.equals("black")){
            if(move.equals("O-O-O")){
                MoveInfo kingMoveInfo = new MoveInfo();
                kingMoveInfo.setType("King");
                kingMoveInfo.setFrom(new Coordinate("e8"));
                kingMoveInfo.setDest(new Coordinate("c8"));
                miList.add(kingMoveInfo);

                MoveInfo rookMoveInfo = new MoveInfo();
                rookMoveInfo.setType("Rook");
                rookMoveInfo.setFrom(new Coordinate("a8"));
                rookMoveInfo.setDest(new Coordinate("d8"));
                miList.add(rookMoveInfo);

            }else if(move.equals("O-O")){
                MoveInfo kingMoveInfo = new MoveInfo();
                kingMoveInfo.setType("King");
                kingMoveInfo.setFrom(new Coordinate("e8"));
                kingMoveInfo.setDest(new Coordinate("g8"));
                miList.add(kingMoveInfo);

                MoveInfo rookMoveInfo = new MoveInfo();
                rookMoveInfo.setType("Rook");
                rookMoveInfo.setFrom(new Coordinate("h8"));
                rookMoveInfo.setDest(new Coordinate("f8"));
                miList.add(rookMoveInfo);
            }
        }else{
            if(move.equals("O-O-O")){
                MoveInfo kingMoveInfo = new MoveInfo();
                kingMoveInfo.setType("King");
                kingMoveInfo.setFrom(new Coordinate("e1"));
                kingMoveInfo.setDest(new Coordinate("c1"));
                miList.add(kingMoveInfo);

                MoveInfo rookMoveInfo = new MoveInfo();
                rookMoveInfo.setType("Rook");
                rookMoveInfo.setFrom(new Coordinate("a1"));
                rookMoveInfo.setDest(new Coordinate("d1"));
                miList.add(rookMoveInfo);

            }else if(move.equals("O-O")){
                MoveInfo kingMoveInfo = new MoveInfo();
                kingMoveInfo.setType("King");
                kingMoveInfo.setFrom(new Coordinate("e1"));
                kingMoveInfo.setDest(new Coordinate("g1"));
                miList.add(kingMoveInfo);

                MoveInfo rookMoveInfo = new MoveInfo();
                rookMoveInfo.setType("Rook");
                rookMoveInfo.setFrom(new Coordinate("h1"));
                rookMoveInfo.setDest(new Coordinate("f1"));
                miList.add(rookMoveInfo);
            }
        }
        for (MoveInfo mi : miList) {
            mi.setTeam(team);
            mi.setCastling(true);
            mi.setMoveCnt(moveCnt);
        }
    }

    @Override
    protected List<Coordinate> getTargetList(PieceMap pieceMap, String team, int moveCnt) {
        List<Coordinate> list = new ArrayList<>();
        for (int d = 0; d < 8; d++) {
            Coordinate moved = getCoor().move(dy[d], dx[d]);
            String targetTeam = pieceMap.getTeam(PieceMap.getKey(moved));
            if(moved.isValid()&& !targetTeam.equals(team)){
                list.add(moved);
            }
        }
        return list;
    }


}
