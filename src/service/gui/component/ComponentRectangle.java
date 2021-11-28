package service.gui.component;

import javax.swing.*;
import java.awt.*;

public class ComponentRectangle {
    private Component component;
    private final Rectangle rectangle;

    public ComponentRectangle(Component component, Rectangle rectangle) {
        this.component = component;
        this.rectangle = rectangle;
    }

    public Component getComponent() {
        return component;
    }
    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
    public void setLabelText(String text) {
        ((JLabel)component).setText(text);
    }
}
