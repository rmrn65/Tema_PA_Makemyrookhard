public abstract class Piece {
    String color;
    String current_position;
    int value;

    public Piece(){}
    //move : - returneaza string cu mutarea -
    // -face mutarea pe object-matrix
    // - daca mutarea nu e posibila returnez "null"
    abstract String move(Board board);
    abstract Boolean canMove(Board board);
    //String getColor
    //String getPosition
    //String setPosition
    //CONSTRUCTORI: PIESA(POSITIE, CULOARE)
}
