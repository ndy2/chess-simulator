package service.game.chess.piece;

import service.game.chess.util.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PieceMap{

    private Map<String,Map<Integer, Rook>>  rookMap;
    private Map<String,Map<Integer, Knight>>knightMap;
    private Map<String,Map<Integer, Bishop>> bishopMap;
    private Map<String,Map<Integer, Queen>> queenMap;
    private Map<String,Map<Integer, King>> kingMap;
    private Map<String,Map<Integer, Pawn>> pawnMap;
    private Map<Integer, Dummy> dummyMap;

    public PieceMap() {
        rookMap = new HashMap<String, Map<Integer, Rook>>();
        rookMap.put("black",new HashMap<Integer,Rook>());
        rookMap.put("white",new HashMap<Integer,Rook>());

        knightMap = new HashMap<String, Map<Integer, Knight>>();
        knightMap.put("black",new HashMap<Integer,Knight>());
        knightMap.put("white",new HashMap<Integer,Knight>());

        bishopMap = new HashMap<String, Map<Integer, Bishop>>();
        bishopMap.put("black",new HashMap<Integer,Bishop>());
        bishopMap.put("white",new HashMap<Integer,Bishop>());

        queenMap = new HashMap<String, Map<Integer, Queen>>();
        queenMap.put("black",new HashMap<Integer,Queen>());
        queenMap.put("white",new HashMap<Integer,Queen>());

        kingMap = new HashMap<String, Map<Integer, King>>();
        kingMap.put("black",new HashMap<Integer,King>());
        kingMap.put("white",new HashMap<Integer,King>());

        pawnMap = new HashMap<String, Map<Integer, Pawn>>();
        pawnMap.put("black",new HashMap<Integer,Pawn>());
        pawnMap.put("white",new HashMap<Integer,Pawn>());

        dummyMap = new HashMap<Integer, Dummy>();
    }

    public static Integer getKey(char rank, char file){
        return ('8' - rank) * 8 + (file - 'a');
    }
    public static Integer getKey(Coordinate coor){
        return getKey(coor.getRank(),coor.getFile());
    }

    public Piece put(Integer key, Piece value, String team) {
        switch (value.getType()) {
            case "Rook":
                return rookMap.get(team).put(key, (Rook) value);
            case "Knight":
                return knightMap.get(team).put(key, (Knight) value);
            case "Bishop":
                return bishopMap.get(team).put(key, (Bishop) value);
            case "Queen":
                return queenMap.get(team).put(key, (Queen) value);
            case "King":
                return kingMap.get(team).put(key, (King) value);
            case "Pawn":
                return pawnMap.get(team).put(key, (Pawn) value);
            default:
                return dummyMap.put(key,(Dummy) value);
        }
    }

    public Piece get(Object key, String team) {
        if(rookMap.get(team).get(key)!=null){
           return rookMap.get(team).get(key);
        }else if(knightMap.get(team).get(key)!=null){
            return knightMap.get(team).get(key);
        }else if(bishopMap.get(team).get(key)!=null){
            return bishopMap.get(team).get(key);
        }else if(queenMap.get(team).get(key)!=null){
            return queenMap.get(team).get(key);
        }else if(kingMap.get(team).get(key)!=null){
            return kingMap.get(team).get(key);
        }else if(pawnMap.get(team).get(key)!=null){
            return pawnMap.get(team).get(key);
        }else{
            return dummyMap.get(key);
        }
    }

    public String getTeam(Object key){
        String[] teams = {"white","black"};
        for (String team : teams) {
            if(rookMap.get(team).get(key)!=null){
                return team;
            }else if(knightMap.get(team).get(key)!=null){
                return team;
            }else if(bishopMap.get(team).get(key)!=null){
                return team;
            }else if(queenMap.get(team).get(key)!=null){
                return team;
            }else if(kingMap.get(team).get(key)!=null){
                return team;
            }else if(pawnMap.get(team).get(key)!=null){
                return team;
            }
        }
        return "dummyTeam";
    }

    public Piece get(Object key) {
        return get(key, "white", "black");
    }

    public Piece get(Object key, String... teams) {
        for (String team : teams) {
            if(rookMap.get(team).get(key)!=null){
                return rookMap.get(team).get(key);
            }else if(knightMap.get(team).get(key)!=null){
                return knightMap.get(team).get(key);
            }else if(bishopMap.get(team).get(key)!=null){
                return bishopMap.get(team).get(key);
            }else if(queenMap.get(team).get(key)!=null){
                return queenMap.get(team).get(key);
            }else if(kingMap.get(team).get(key)!=null){
                return kingMap.get(team).get(key);
            }else if(pawnMap.get(team).get(key)!=null){
                return pawnMap.get(team).get(key);
            }
        }
        return new Dummy(new Coordinate((Integer)key));
    }

    public Piece remove(Object key, String team) {
        if(rookMap.get(team).get(key)!=null){
            return rookMap.get(team).remove(key);
        }else if(knightMap.get(team).get(key)!=null){
            return knightMap.get(team).remove(key);
        }else if(bishopMap.get(team).get(key)!=null){
            return bishopMap.get(team).remove(key);
        }else if(queenMap.get(team).get(key)!=null){
            return queenMap.get(team).remove(key);
        }else if(kingMap.get(team).get(key)!=null){
            return kingMap.get(team).remove(key);
        }else if(pawnMap.get(team).get(key)!=null){
            return pawnMap.get(team).remove(key);
        }else{
            return dummyMap.remove(key);
        }    }

    public Piece remove(Object key) {
        String[] teams = {"white", "black"};
        for (String team : teams) {
            if (rookMap.get(team).get(key) != null) {
                return rookMap.get(team).remove(key);
            } else if (knightMap.get(team).get(key) != null) {
                return knightMap.get(team).remove(key);
            } else if (bishopMap.get(team).get(key) != null) {
                return bishopMap.get(team).remove(key);
            } else if (queenMap.get(team).get(key) != null) {
                return queenMap.get(team).remove(key);
            } else if (kingMap.get(team).get(key) != null) {
                return kingMap.get(team).remove(key);
            } else if (pawnMap.get(team).get(key) != null) {
                return pawnMap.get(team).remove(key);
            }
        }
        return dummyMap.remove(key);
   }


   public Piece getFromPiece(String team, String type, Coordinate dest, String cr, int moveCnt){

        List<Piece> pieces = new ArrayList<>();
        if(type == "Rook"){
            pieces = rookMap.get(team).values().stream().filter(rook -> rook.isReachable(this, team, dest)).collect(Collectors.toList());
        }else if(type.equals("Knight")){
            pieces = knightMap.get(team).values().stream().filter(knight -> knight.isReachable(this, team, dest)).collect(Collectors.toList());
        }else if(type.equals("Bishop")){
            pieces = bishopMap.get(team).values().stream().filter(bishop -> bishop.isReachable(this, team, dest)).collect(Collectors.toList());
        }else if(type.equals("Queen")){
            pieces = queenMap.get(team).values().stream().filter(queen -> queen.isReachable(this, team, dest)).collect(Collectors.toList());
        }else if(type.equals("King")){
            pieces = kingMap.get(team).values().stream().filter(king -> king.isReachable(this, team, dest)).collect(Collectors.toList());
        }else if(type.equals("Pawn")){
            pieces = pawnMap.get(team).values().stream().filter(pawn -> pawn.isReachable(this, team, dest, moveCnt)).collect(Collectors.toList());
        }

        if(pieces.size()==1){
            return pieces.get(0);
        }

       return conflictResolving(cr, pieces);
   }

    private Piece conflictResolving(String cr, List<Piece> pieces) {
        //conflict
        if(cr.length()==2){
            for (Piece piece : pieces) {
                if(piece.getCoor().equals(new Coordinate(cr)))
                     return piece;
            }
        }else{
            char c = cr.charAt(0);
            if(c>='1' && c<='8'){
                for (Piece piece : pieces) {
                    if(piece.getCoor().getRank()==c)
                         return piece;
                }
            }else if(c>='a' && c<='h'){
                for (Piece piece : pieces) {
                    if(piece.getCoor().getFile()==c)
                        return piece;
                }
            }
        }
        return null;
    }
}


