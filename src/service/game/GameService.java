package service.game;

import service.game.chess.util.MoveInfo;

import java.util.List;

public interface GameService {

    public abstract void load(Object model);

    public abstract List<MoveInfo> next();

    public void clear();
}
