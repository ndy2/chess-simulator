package service.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pgn {

    private PgnHeader header;
    private PgnMoveOrder moveOrders;

    public Pgn(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            List<String> lines = br.lines().collect(Collectors.toList());

            List<String> headerLines = new ArrayList<>();
            StringBuilder moveOrderString = new StringBuilder();
            splitFile(lines, headerLines, moveOrderString);

            header = new PgnHeader(headerLines);
            moveOrders = new PgnMoveOrder(moveOrderString.toString());

        }catch(Exception e) {e.printStackTrace();}
    }

    private void splitFile(List<String> lines, List<String> headerLines, StringBuilder moveOrderString) {
        boolean emptyLineFound = false;
        for (String line : lines) {
            if(!line.isEmpty()){
                if (emptyLineFound) {
                    moveOrderString.append(line+" ");
                } else {
                    headerLines.add(line);
                }
            }else{
                emptyLineFound = true;
            }
        }
    }

    public PgnMoveOrder getMoveOrders() {
        return moveOrders;
    }

    public String pgnWhiteInfoDisplay(){
        return header.whiteInfoString();
    }

    public String pgnBlackInfoDisplay(){
        return header.blackInfoString();
    }

}
