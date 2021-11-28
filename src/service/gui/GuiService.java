package service.gui;

import service.game.chess.util.MoveInfo;
import service.gui.component.ChessComponentFactory;
import service.gui.component.ComponentFactory;
import service.gui.component.ComponentStore;
import service.gui.component.button.ButtonsImpl;
import service.gui.component.label.LabelsImpl;
import service.gui.component.manager.BoardManager;
import service.gui.component.manager.MoveOrderManager;

import javax.swing.*;
import java.util.List;


public class GuiService {

    MainFrame mainFrame;
    ComponentFactory componentFactory;
    ComponentStore componentStore;
    BoardManager boardManager;
    MoveOrderManager moveOrderManager;

    public GuiService() {
        componentFactory = new ChessComponentFactory();
        componentStore = new ComponentStore();

        ButtonsImpl.addAll(componentStore);
        LabelsImpl.addAll(componentStore);

        mainFrame = new MainFrame(componentStore);
    }

    public void load(Object model) {
        convertLabelText("whiteInfo", componentFactory.createLabel("whiteInfo", model).getText());
        convertLabelText("whitePlayerCapturedPieces", componentFactory.createLabel("whitePlayerCapturedPieces", model).getText());

        convertLabelText("blackInfo", componentFactory.createLabel("blackInfo", model).getText());
        convertLabelText("blackPlayerCapturedPieces", componentFactory.createLabel("blackPlayerCapturedPieces", model).getText());

        JLabel board = componentStore.getLabel("board");
        boardManager = new BoardManager(board);

        JLabel moveOrder = componentStore.getLabel("moveOrder");
        moveOrderManager = new MoveOrderManager(moveOrder,model,1);
        moveOrderManager.updatePage();
    }

    private void convertLabelText(String componentName, String text) {
        mainFrame.convertLabelText(componentName, text);
    }

    public void pack() {
        mainFrame.pack();
    }

    public void next(List<MoveInfo> miList) {
        for (MoveInfo mi : miList) {
            boardManager.next(mi);
        }

        moveOrderManager.next(miList.get(0));
    }

    public void clear() {
        if(boardManager!=null){
            boardManager.clear();
        }

        if(moveOrderManager!=null){
            moveOrderManager.clear();
        }
    }
}

