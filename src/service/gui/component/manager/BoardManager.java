package service.gui.component.manager;

import service.game.chess.util.Coordinate;
import service.game.chess.util.MoveInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static service.game.chess.piece.PieceMap.getKey;

public class BoardManager {

    private JLabel board;

    public BoardManager(JLabel board) {
        this.board = board;
        pieceLabelUpdate();
    }

    public void pieceLabelUpdate(){
        addMainPieces("black", '8');
        addPawns("black",'7');
        addDummies('6');
        addDummies('5');

        addDummies('4');
        addDummies('3');
        addPawns("white",'2');
        addMainPieces("white", '1');
    }

    private void addMainPieces(String team, char rank) {
        insertPieceLabelToGrid(rank, 'a', getLabel(team,"Rook"));
        insertPieceLabelToGrid(rank, 'b', getLabel(team,"Knight"));
        insertPieceLabelToGrid(rank, 'c', getLabel(team,"Bishop"));
        insertPieceLabelToGrid(rank, 'd', getLabel(team,"Queen"));
        insertPieceLabelToGrid(rank, 'e', getLabel(team,"King"));
        insertPieceLabelToGrid(rank, 'f', getLabel(team,"Bishop"));
        insertPieceLabelToGrid(rank, 'g', getLabel(team,"Knight"));
        insertPieceLabelToGrid(rank, 'h', getLabel(team,"Rook"));
    }

    private void addPawns(String team,char rank) {
        for(char file = 'a' ; file<='h' ; file++){
            insertPieceLabelToGrid(rank, file, getLabel(team,"Pawn"));
        }
    }

    private void addDummies(char rank){
        for(char file = 'a' ; file<='h' ; file++){
            insertPieceLabelToGrid(rank, file, getDummyPieceLabel());
        }
    }

    private JLabel getLabel(String team, String type) {
        return new JLabel(getIcon("pieces/"+team+"/"+type+".png"));
    }

    private JLabel getDummyPieceLabel() {
        return new JLabel(getIcon("pieces/black/Dummy.png"));
    }

    private void insertPieceLabelToGrid(char rank, char file, JLabel label) {
        pieceGbInsert(label, 8*(file-'a'),670-80*(rank-'0'),8,80, getKey(rank,file));
    }

    private void insertPieceLabelToGrid(Coordinate coor, JLabel label) {
        insertPieceLabelToGrid(coor.getRank(), coor.getFile(),label);
    }

    private void pieceGbInsert(Component c, int x, int y, int w, int h, int index){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill= GridBagConstraints.BOTH;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        ((GridBagLayout)board.getLayout()).setConstraints(c,gbc);
        board.add(c,index);
    }

    private static Icon getIcon(String filename) {
        try {
            return new ImageIcon(ImageIO.read(BoardManager.class.getResourceAsStream("/" + filename)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void next(MoveInfo mi) {
        //from 지우기
        board.remove(getKey(mi.getFrom()));

        //from dummy로 채우기
        insertPieceLabelToGrid(mi.getFrom(),getDummyPieceLabel());

        //Dest 지우기
        board.remove(getKey(mi.getDest()));

        //Dest 채우기
        insertPieceLabelToGrid(mi.getDest(),getLabel(mi.getTeam(),mi.getType()));

        board.updateUI();

    }

    public void clear() {
        board.removeAll();
    }
}


