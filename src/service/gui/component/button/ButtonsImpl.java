package service.gui.component.button;

import service.gui.component.ComponentRectangle;
import service.gui.component.ComponentStore;

import java.awt.*;

public class ButtonsImpl{

    public static void addAll(ComponentStore componentStore) {
        componentStore.put("loadButton", new ComponentRectangle(new LoadButton(), new Rectangle(0,690,10,10)));
        componentStore.put("prevButton", new ComponentRectangle(new PrevButton(), new Rectangle(10,690,10,10)));
        componentStore.put("nextButton", new ComponentRectangle(new NextButton(), new Rectangle(20,690,10,10)));
    }
}
