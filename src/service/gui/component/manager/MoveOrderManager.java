package service.gui.component.manager;

import service.file.Pgn;
import service.file.PgnMoveOrder;
import service.game.chess.util.MoveInfo;

import javax.swing.*;
import java.awt.*;

public class MoveOrderManager {
    private JLabel moveOrderLabel;
    private Object model;
    private int page;

    public MoveOrderManager(JLabel moveOrderLabel, Object model, int page) {
        this.moveOrderLabel = moveOrderLabel;
        this.model = model;
        this.page = page;
    }

    public void updatePage(){
        PgnMoveOrder moveOrder = ((Pgn) model).getMoveOrders();
        int moveNumber = moveOrder.getMoveNumber();

        int from = (page-1)*40 +1;
        int end = page*40;

        int to = Math.min(end, moveNumber);

        for(int order = 1 ; order<=40 ; order++){
            getLabel(order,0).setText("0");
            getLabel(order,1).setText("♔");
            getLabel(order,2).setText("♕");
        }

        for(int order = from ; order <=to ; order ++){
            getLabel(order,0).setText(String.valueOf(order));
            getLabel(order,1).setText(moveOrder.getWhiteMoveOf(order));
            getLabel(order,2).setText(moveOrder.getBlackMoveOf(order));
        }
    }

    private JLabel getLabel(int row, int col){
        if(row>40) row=row%40;
        return (JLabel) moveOrderLabel.getComponent((row-1)*3+col);
    }

    public void next(MoveInfo mi) {
        int moveCnt= mi.getMoveCnt();
        String team = mi.getTeam();


        if(moveCnt>1&& (moveCnt-1)%40==0 && team.equals("white")){
            page+=1;
            updatePage();
        }

        dehighlight(moveCnt,team);
        highlight(moveCnt,team);
    }

    private void highlight(int moveCnt, String team) {
        int idx = team=="white"?1:2;
        JLabel moveLabel = getLabel(moveCnt,idx);
        moveLabel.setForeground(Color.white);
        moveLabel.setBackground(Color.black);
        moveLabel.setOpaque(true);
    }


    private void dehighlight(int moveCnt, String team) {
        int idx = team.equals("white") ?1:2;

        if(team.equals("black")){
            idx=1;
        }else{
            if(moveCnt==1){return;}
            moveCnt-=1;
            idx++;
        }
        JLabel prevLabel = getLabel(moveCnt,idx);
        prevLabel.setForeground(Color.black);
        prevLabel.setOpaque(false);
    }

    public void clear() {
        for (int moveCnt = 1; moveCnt < 40; moveCnt++) {
            dehighlight(moveCnt,"white");
            dehighlight(moveCnt,"black");
        }
        updatePage();
    }
}
