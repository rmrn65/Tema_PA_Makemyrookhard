import java.util.ArrayList;

public abstract class Piece {
    String color;
    String current_position;
    int value;

    public Piece(){}
    //move : - returneaza string cu mutarea -
    // -face mutarea pe object-matrix
    // - daca mutarea nu e posibila returnez "null"
    abstract String move(Board board);
    abstract boolean canMove(Board board);
    abstract ArrayList<String> generateMoves(Board board);
    //String getColor
    //String getPosition
    //String setPosition
    //CONSTRUCTORI: PIESA(POSITIE, CULOARE)
}
