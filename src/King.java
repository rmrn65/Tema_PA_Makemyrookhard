
public class King extends Piece{
    public King(String color){
        this.color = color;
    }

    @Override
    String move(Board board) {
        return "";
    }

    @Override
    Boolean canMove(Board board) {
        return false;
    }
}
