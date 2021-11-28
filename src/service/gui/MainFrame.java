package service.gui;

import service.gui.component.ComponentRectangle;
import service.gui.component.ComponentStore;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private GridBagLayout gridBagLayout;
    private ComponentStore componentStore;

    private void init() {
        gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public MainFrame(ComponentStore componentStore) {
        this.componentStore = componentStore;

        init();
        for (String name : componentStore.keySet()) {
            gbInsert(componentStore.get(name));
        }
    }

    public void gbInsert(Component c, Rectangle r){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill= GridBagConstraints.BOTH;
        gbc.gridx = r.x;
        gbc.gridy = r.y;
        gbc.gridwidth = r.width;
        gbc.gridheight = r.height;
        gridBagLayout.setConstraints(c,gbc);
        add(c);
    }

    public void gbInsert(ComponentRectangle componentRectangle){
        gbInsert(componentRectangle.getComponent(), componentRectangle.getRectangle());
    }


    public void convertLabelText(String componentName, String text) {
        ComponentRectangle componentRectangle = componentStore.get(componentName);
        JLabel label = (JLabel) componentRectangle.getComponent();

        remove(label);
        componentStore.remove(componentName);

        componentRectangle.setLabelText(text);

        gbInsert(componentRectangle);
        label.updateUI();

        componentStore.put(componentName,componentRectangle);

    }
}