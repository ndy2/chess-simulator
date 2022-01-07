package service.gui.component.label;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MoverOrderLabel extends JLabel {

    private GridBagLayout gridBagLayout;

    public MoverOrderLabel() throws HeadlessException {

        setIcon(getIcon("grid.JPG"));
        gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        for (int move = 1; move <= 40; move++) {
            pieceGbInsert(new JLabel(move+"         "),0,move*20,200,10);
            pieceGbInsert(new JLabel("♔           "),200,move*20,200,10);
            pieceGbInsert(new JLabel("♕           "),400,move*20,200,10);
        }
    }
    public void pieceGbInsert(Component c, int x, int y, int w, int h){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill= GridBagConstraints.BOTH;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gridBagLayout.setConstraints(c,gbc);
        add(c);
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
