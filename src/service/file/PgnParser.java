package service.file;

import java.io.File;

public class PgnParser implements FileParser {
    @Override
    public Object parse(File file) {
        return new Pgn(file);
    }
}
