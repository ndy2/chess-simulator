package service.file;

import java.util.List;

public class PgnHeader {
    private String white;
    private String black;

    private int whiteElo;
    private int blackElo;

    public PgnHeader(List<String> headers) {
        for (String header : headers) {
            String[] headerSplit = header.substring(1, header.length() - 1).split(" ");
            String name = headerSplit[0];
            String value = headerSplit[1].replaceAll("\"","");

            if(name.equals("White")){
                white = value;
            }else if(name.equals("Black")){
                black = value;
            }else if(name.equals("WhiteElo")){
                whiteElo = Integer.parseInt(value);
            }else if(name.equals("BlackElo")){
                blackElo = Integer.parseInt(value);
            }
        }
    }


    public String whiteInfoString() {
        return white+"("+String.valueOf(whiteElo)+")";
    }

    public String blackInfoString() {
        return black+"("+String.valueOf(blackElo)+")";
    }
}
