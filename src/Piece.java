abstract public class Piece {
    String color;
    String current_position;
    public Piece(){}
    abstract String move(Board board);
    abstract Boolean canMove(Board board);
}
