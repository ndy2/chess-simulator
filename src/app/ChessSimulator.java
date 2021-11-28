package app;

import service.file.PgnParser;
import service.game.chess.ChessService;
import service.gui.GuiService;

public class ChessSimulator extends GameSimulator {

    public ChessSimulator() {
        super(
                new ChessService(),
                new GuiService(),
                new PgnParser()
        );
    }
}