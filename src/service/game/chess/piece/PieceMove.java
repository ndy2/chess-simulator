package service.game.chess.piece;

import service.game.chess.util.Coordinate;
import service.game.chess.util.MoveInfo;

import java.util.ArrayList;
import java.util.List;

import static service.game.chess.piece.PieceMap.getKey;

public class PieceMove {

    private static String getType(char c) {
        switch (c) {
            case 'N':
                return "Knight";
            case 'K':
            case 'O':
                return "King";
            case 'B':
                return "Bishop";
            case 'R':
                return "Rook";
            case 'Q':
                return "Queen";
            default:
                return "Pawn";
        }
    }

    public static List<MoveInfo> move(PieceMap pieceMap, String move, String team, int moveCnt) {
        boolean isTake = move.indexOf('x')!=-1;
        String type = getType(move.charAt(0));
        return moveLogic(pieceMap,team,type,isTake,move,moveCnt);
    }

    private static List<MoveInfo> moveLogic(PieceMap pieceMap, String team, String type, boolean isTake, String move, int moveCnt) {
        List<MoveInfo> miList = new ArrayList<>();
        if(move.equals("♕")||move.equals("gameEnd")){
            return miList;
        }
        if(move.charAt(0)=='O'){
            King.castle(move, team, miList,moveCnt);
            return miList;
        }

        MoveInfo mi = new MoveInfo();
        mi.setTake(isTake);
        mi.setTeam(team);
        mi.setType(type);
        mi.setMoveCnt(moveCnt);

        //check 표시 + 제거
        move =move.replaceAll("\\+","");

        int l = move.length();

        //승진 처리
        String promoteType = "no promotion";
        if(move.charAt(l-2)=='='){
            char pt = move.charAt(l-1);
            promoteType = pt=='Q'?"Queen":
                          pt=='R'?"Rookt":
                          pt=='B'?"Bishop":
                          pt=='N'?"Knight":"no promotion";
            move = move.substring(0,l-2);
            l -=2;
        }
        mi.setPromoteTo(promoteType);
        
        String destStr = move.substring(l-2,l);
        Coordinate dest = new Coordinate(destStr);
        mi.setDest(dest);

        String cr = getConflictResolver(type, move);
        Piece fromPiece = pieceMap.getFromPiece(team, type, dest, cr, moveCnt);

        fromPiece.setMovedCnt(moveCnt);
        mi.setFrom(fromPiece.getCoor());

        miList.add(mi);

        if(fromPiece.getType().equals("Pawn")){
            Pawn pawn = (Pawn) fromPiece;
            if(pawn.isDoEnPassant()){
                return Pawn.createEnPassantMoveInfo(mi);
            }
        }

        return miList;
    }

    private static String getConflictResolver(String type, String move) {
        String cr = move.replaceAll("x","");
        if(type.equals("Pawn")){
            cr = cr.substring(0,1);
        }else{
            cr = cr.substring(1,cr.length()-2);
        }
        return cr;
    }


    private static boolean isPawnAt(PieceMap pieceMap,char rank, char file) {
        return getPiece(pieceMap, rank, file).getType().equals("Pawn");
    }

    public static Piece getPiece(PieceMap pieceMap,char rank, char file){
        return pieceMap.get(getKey(rank,file));
    }

}
