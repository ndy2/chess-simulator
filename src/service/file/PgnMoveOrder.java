package service.file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PgnMoveOrder {
    private int moveNumber;

    private final List<String> whiteMoves;
    private final List<String> blackMoves;

    public PgnMoveOrder(String moveOrderString) {
        moveOrderString = remove_parenthesis(moveOrderString)
                .replaceAll("(=|\\?|±|(\\d{1}|\\d{2})\\.\\.\\.)", " ");

        String[] split = moveOrderString.split("\\s+");
        String[] mo = Arrays.copyOf(split, split.length - 1);

        whiteMoves = new ArrayList<>();
        blackMoves = new ArrayList<>();

        int turn = 0;
        for (String m : mo) {
            if(turn ==1){
                whiteMoves.add(m);
            }else if(turn ==2){
                blackMoves.add(m);
            }
            turn = (turn+1)%3;
        }
        moveNumber = whiteMoves.size();

        if(turn ==2){
            blackMoves.add("♕");
        }
    }

    private static String remove_parenthesis(String input_string){
        // removing parenthesis and everything inside them, works for () and {}
        return input_string.replaceAll("\\s*\\{[^\\}]*\\}\\s*", " ")
                .replaceAll("\\s*\\([^\\)]*\\)\\s*", " ");
    }

    public int getMoveNumber() {
        return moveNumber;
    }

    public String getBlackMoveOf(int moveOrder){
        return blackMoves.get(moveOrder-1);
    }

    public String getWhiteMoveOf(int moveOrder){
        return whiteMoves.get(moveOrder-1);
    }
}

