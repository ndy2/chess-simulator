package service.gui.component;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ComponentStore extends HashMap<String, ComponentRectangle> {
    public Component getComponent(String key){
        return get(key).getComponent();
    }

    public JLabel getLabel(String key){
        return (JLabel)getComponent(key);
    }
}
