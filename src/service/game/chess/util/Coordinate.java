package service.game.chess.util;

import java.util.Objects;

public class Coordinate {
    char file;
    char rank;

    public Coordinate(Integer key) {
        this.file = (char) ('8' - key/8);
        this.rank = (char) ('a' + key%8);
    }

    public char getFile() {
        return file;
    }

    public char getRank() {
        return rank;
    }

    public Coordinate(String pos) {
        this.file = pos.charAt(0);
        this.rank = pos.charAt(1);
    }

    public Coordinate(char rank, char file) {
        this.rank = rank;
        this.file = file;
    }

    public boolean isValid(){
        return file>='a'&&file<='h'&&rank>='1'&&rank<='8';
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return file == that.file && rank == that.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public Coordinate move(int dy, int dx) {
        return new Coordinate((char)((int)rank-dy),(char)((int)file+dx));
    }
}
