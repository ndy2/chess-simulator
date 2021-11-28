package service.game.chess;

import service.file.Pgn;
import service.file.PgnMoveOrder;
import service.game.GameService;
import service.game.chess.piece.*;
import service.game.chess.util.Coordinate;
import service.game.chess.util.MoveInfo;
import service.game.chess.piece.PieceMap;
import service.game.chess.piece.PieceMove;

import java.util.ArrayList;
import java.util.List;

import static service.game.chess.piece.PieceMap.getKey;

public class ChessService implements GameService {

    private PgnMoveOrder moveOrders;
    /*model*/
    private Object model;

    /*PieceMap*/
    private PieceMap pieceMap;

    /*CapturedPieces*/
    private List<Piece> whiteCapturedPieces;
    private List<Piece> blackCapturedPieces;

    /*gameStatus*/
    private String team;
    private int moveCnt;

    public ChessService() {
        clear();
    }

    private void initializePieceMap() {
        pieceMap = new PieceMap();
        putMainPieces("black", '8');
        putPawns("black", '7');
        putDummyPieces('6');
        putDummyPieces('5');

        putDummyPieces('4');
        putDummyPieces('3');
        putPawns("white", '2');
        putMainPieces("white", '1');
    }
    private void putMainPieces(String team, char rank) {
        pieceMap.put(getKey(rank,'a'),new Rook(new Coordinate(rank,'a')), team);
        pieceMap.put(getKey(rank,'b'),new Knight(new Coordinate(rank,'b')), team);
        pieceMap.put(getKey(rank,'c'),new Bishop(new Coordinate(rank,'c')), team);
        pieceMap.put(getKey(rank,'d'),new Queen(new Coordinate(rank,'d')), team);
        pieceMap.put(getKey(rank,'e'),new King(new Coordinate(rank,'e')), team);
        pieceMap.put(getKey(rank,'f'),new Bishop(new Coordinate(rank,'f')), team);
        pieceMap.put(getKey(rank,'g'),new Knight(new Coordinate(rank,'g')), team);
        pieceMap.put(getKey(rank,'h'),new Rook(new Coordinate(rank,'h')), team);
    }
    private void putPawns(String team, char rank) {
        for (char file = 'a'; file <= 'h'; file++) {
            pieceMap.put(getKey(rank,file), new Pawn(new Coordinate(rank,file)), team);
        }
    }
    private void putDummyPieces(char rank) {
        for (char file = 'a'; file <= 'h'; file++) {
            pieceMap.put(getKey(rank,file), new Dummy(new Coordinate(rank,file)),"white");
        }
    }

    @Override
    public void load(Object model) {
        this.model = model;
        Pgn pgn = (Pgn) model;
        moveOrders = pgn.getMoveOrders();
    }

    private void update(List<MoveInfo> miList) {


        for (MoveInfo mi : miList) {
            Piece destPiece = updatePieceMap(mi);
            if(mi.isTake()){
                updateCapturedPieces(destPiece);
            }
        }
        if(!miList.isEmpty())
            updateGameStatus(miList.get(0));
    }

    private void updateGameStatus(MoveInfo mi) {
        if(mi.getTeam().equals("black")){
            team = "white";
            moveCnt+=1;
        }else{
            team = "black";
        }
    }

    private void updateCapturedPieces(Piece capturedPiece) {
            if(team.equals("white")) whiteCapturedPieces.add(capturedPiece);
            else blackCapturedPieces.add(capturedPiece);
    }

    private Piece updatePieceMap(MoveInfo mi) {
        Integer fromKey = getKey(mi.getFrom());
        Integer destKey = getKey(mi.getDest());

        Piece fromPiece=pieceMap.get(fromKey, mi.getTeam());
        Piece destPiece = pieceMap.get(destKey);

        pieceMap.remove(fromKey, mi.getTeam());
        pieceMap.remove(destKey);

        fromPiece.setCoor(mi.getDest());
        pieceMap.put(destKey, fromPiece,mi.getTeam());
        return destPiece;
    }


    @Override
    public List<MoveInfo> next() {
        List<MoveInfo> miList = PieceMove.move(pieceMap, getMove(), team, moveCnt);
        update(miList);
        return miList;
    }

    @Override
    public void clear() {
        whiteCapturedPieces = new ArrayList<>();
        blackCapturedPieces = new ArrayList<>();
        initializePieceMap();

        team = "white";
        moveCnt = 1;
    }

    private String getMove() {
        Pgn pgn = (Pgn) model;
        int moveNumber = pgn.getMoveOrders().getMoveNumber();

        if(moveCnt>moveNumber)
            return "gameEnd";

        return team.equals("white") ?
                moveOrders.getWhiteMoveOf(moveCnt):
                moveOrders.getBlackMoveOf(moveCnt);
    }
}
