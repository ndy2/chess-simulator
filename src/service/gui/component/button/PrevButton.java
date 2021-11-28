package service.gui.component.button;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrevButton extends Button {
    public PrevButton() throws HeadlessException {
        setLabel("prev");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }
}
