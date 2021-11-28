package app;

import service.file.FileParser;
import service.game.GameService;
import service.game.chess.util.MoveInfo;
import service.gui.GuiService;

import java.io.File;
import java.util.List;

public class GameSimulator {
    private static GameService gameService;
    private static GuiService guiService;
    private static FileParser fileParser;

    public GameSimulator(GameService gameService, GuiService guiService, FileParser fileParser) {
        this.gameService = gameService;
        this.guiService = guiService;
        this.fileParser = fileParser;
    }

    public static void next() {
        List<MoveInfo> miList = gameService.next();
        if(miList.isEmpty()){
            System.out.println("gameEnd");
            return;
        }
        guiService.next(miList);
    }

    public void run() {
        guiService.pack();
    }

    public static void load(File file){

        gameService.clear();
        guiService.clear();

        Object model = fileParser.parse(file);
        gameService.load(model);
        guiService.load(model);
    }
}
