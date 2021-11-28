package service.gui.component.button;

import app.GameSimulator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextButton extends Button {
    public NextButton() throws HeadlessException {
        setLabel("next");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameSimulator.next();
            }
        });
    }
}
