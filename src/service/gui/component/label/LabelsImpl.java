package service.gui.component.label;

import service.gui.component.ComponentRectangle;
import service.gui.component.ComponentStore;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LabelsImpl{

    public static void addAll(ComponentStore componentStore) {
        componentStore.put("blackInfo", new ComponentRectangle(new JLabel("BlackPlayer(Rating)"), new Rectangle(0,0,640,10)));
        componentStore.put("blackPlayerCapturedPieces", new ComponentRectangle(new JLabel("Black Player Captured Pieces"), new Rectangle(0,10,640,10)));

        componentStore.put("whiteInfo", new ComponentRectangle(new JLabel("WhitePlayer(Rating)"), new Rectangle(0,670,640,10)));
        componentStore.put("whitePlayerCapturedPieces", new ComponentRectangle(new JLabel("White Player Captured Pieces"), new Rectangle(0,680,640,10)));

        JLabel boardLabel = new JLabel(getIcon("board.JPG"));
        boardLabel.setLayout(new GridBagLayout());
        componentStore.put("board", new ComponentRectangle(boardLabel, new Rectangle(0,20,640,640)));

        componentStore.put("moveOrder", new ComponentRectangle(new MoverOrderLabel(), new Rectangle(640,20,50,640)));
    }


    private static Icon getIcon(String filename) {
        try {
            return new ImageIcon(ImageIO.read(LabelsImpl.class.getResourceAsStream("/resources/" + filename)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
