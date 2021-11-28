package service.gui.component;

import service.file.Pgn;

import javax.swing.*;
import java.awt.*;

public class ChessComponentFactory implements ComponentFactory{

    private Component createComponent(String order, Object Recipe) {

        Pgn pgn = (Pgn) Recipe;

        //resolve request.
        if (order.equals("whiteInfo")) {
            return new whiteInfoLabel(pgn);
        } else if (order.equals("blackInfo")) {
            return new blackInfoLabel(pgn);
        } else if (order.equals("whitePlayerCapturedPieces")) {
            return new WhitePlayerCapturedPieces();
        } else if (order.equals("blackPlayerCapturedPieces")) {
            return new BlackPlayerCapturedPieces();
        }
        return null;
    }

    @Override
    public JLabel createLabel(String order, Object Recipe) {
        return (JLabel)createComponent(order,Recipe);
    }

    private static class whiteInfoLabel extends JLabel {
        public whiteInfoLabel(Pgn pgn) {
            setText(pgn.pgnWhiteInfoDisplay());
        }
    }

    private static class blackInfoLabel extends JLabel {
        public blackInfoLabel(Pgn pgn) {
            setText(pgn.pgnBlackInfoDisplay());
        }
    }

    private static class WhitePlayerCapturedPieces extends JLabel {
        public WhitePlayerCapturedPieces() {
            setText(" ");
        }
    }

    private static class BlackPlayerCapturedPieces extends JLabel {
        public BlackPlayerCapturedPieces() {
            setText(" ");
        }
    }
}
