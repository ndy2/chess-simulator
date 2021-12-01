package service.game.chess.piece;

import service.game.chess.util.Coordinate;
import service.game.chess.util.MoveInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static service.game.chess.piece.PieceMap.getKey;

public class Pawn extends Piece{

    private final Map<String, int[] > dy;
    private final Map<String, int[] > dx;

    private boolean doEnPassant;

    public static List<MoveInfo> createEnPassantMoveInfo(MoveInfo mi) {
        List<MoveInfo> miList = new ArrayList<>();

        String team = mi.getTeam();

        MoveInfo takeMove = new MoveInfo();
        takeMove.setTake(true);
        takeMove.setFrom(mi.getFrom());
        if(team.equals("white")){
            takeMove.setDest(mi.getDest().move(1,0));
        }else if(team.equals("black")){
            takeMove.setDest(mi.getDest().move(-1,0));
        }

        MoveInfo forwardMove = new MoveInfo();
        forwardMove.setTake(false);
        forwardMove.setFrom(takeMove.getDest());
        forwardMove.setDest(mi.getDest());

        miList.add(takeMove);
        miList.add(forwardMove);
        for (MoveInfo moveInfo : miList) {
            moveInfo.setType("Pawn");
            moveInfo.setTeam(team);
            moveInfo.setMoveCnt(mi.getMoveCnt());
            moveInfo.setPromoteTo("no promotion");
        }

        return miList;
    }

    public boolean isDoEnPassant() {
        return doEnPassant;
    }

    public Pawn(Coordinate coor) {
        super("Pawn", coor);
        dy = new HashMap<>();
        dy.put("white", new int[]{-1,-2,-1,-1});
        dy.put("black", new int[]{1,2,1,1});

        dx = new HashMap<>();
        dx.put("white", new int[]{0,0,1,-1});
        dx.put("black", new int[]{0,0,1,-1});
    }
    //      여기
    //여기   여기   여기
    //앙첵1  백폰  앙첵2
    @Override
    protected List<Coordinate> getTargetList(PieceMap pieceMap, String team, int moveCnt) {
        List<Coordinate> list = new ArrayList<>();

        for (int d = 0; d < 2; d++) {
            Coordinate moved = getCoor().move(dy.get(team)[d],dx.get(team)[d]);
            if(moved.isValid()){
                String targetTeam = pieceMap.getTeam(getKey(moved));
                if(targetTeam.equals("dummyTeam")){
                    list.add(moved);
                }
            }
        }

        for(int d = 2; d<4; d++){
            Coordinate moved = getCoor().move(dy.get(team)[d],dx.get(team)[d]);
            if(moved.isValid()){
                String targetTeam = pieceMap.getTeam(getKey(moved));
                if(!targetTeam.equals("dummyTeam") && !targetTeam.equals(team)){
                    list.add(moved);
                }
                //en passant 확인
                else if(canEnPassant(pieceMap,d,moveCnt,team)){
                    doEnPassant = true;
                    list.add(moved);
                }
            }
        }

        return list;
    }

    private boolean canEnPassant(PieceMap pieceMap, int d, int moveCnt, String team) {

        Coordinate moved = getCoor().move(0,dx.get(team)[d]);
        Integer key = getKey(moved);
        Piece checkPiece = pieceMap.get(key);
        String checkTeam = pieceMap.getTeam(key);

        if(checkPiece.getType().equals("Pawn")){
            if(team.equals("white") && checkTeam.equals("black")&&getCoor().getRank()=='5'){
                return checkPiece.getMovedCnt() == moveCnt-1;
            }else if(team.equals("black") && checkTeam.equals("white")&&getCoor().getRank()=='4'){
                return checkPiece.getMovedCnt() == moveCnt;
            }
        }
        return false;
    }

}
