package service.gui.component.button;

import app.GameSimulator;

import javax.swing.*;
import java.awt.*;

public class LoadButton extends Button {
    public LoadButton() throws HeadlessException {
        setLabel("load");
        addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            int dialog = jfc.showSaveDialog(null);
            if(dialog == 0) {
                GameSimulator.load(jfc.getSelectedFile());
            }
        });
    }
}
