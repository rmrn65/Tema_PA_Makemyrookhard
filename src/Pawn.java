import java.util.ArrayList;

public class Pawn extends Piece{
    Character symbol;
    int value, x;
    public Pawn(){
        current_position = "";
        color = "";
        value = 1;
    }
    public Pawn(String current_position,String color){
        this();
        this.current_position = current_position;
        this.color = color;
        if(color.equals("white")) {
            x = 1;
            symbol = 'p';
        }
        else {
            x = -1;
            symbol = 'P';
        }
    }
    //verifică dacă poate merge înainte
    public String move_forward(int first_move, Board board){
        String aux =current_position; //rețin starea curentă
        String new_position;
        if(first_move == 0){
            new_position = current_position.charAt(0) + "" + (char)(current_position.charAt(1) + x);
        }
        else
        {
            new_position = current_position.charAt(0) + "" + (char)(current_position.charAt(1) + 2 * x);
        }
        board.move(current_position +""+ new_position);
        current_position = new_position;
        return aux+""+new_position;
    }
    //ia o piesă în stânga
    public String take_left(Board board){
        String aux =current_position;
        String new_position;
        new_position = (char)(current_position.charAt(0) - x) + "" + (char)(current_position.charAt(1) + x);
        System.out.println(current_position+""+new_position);
        board.move(current_position+""+new_position);
        current_position = new_position;
        return aux+""+new_position;
    }
    public Boolean etapa1(Board board){
        return (int) board.pos_to_indexes(current_position).get(1) < 1 || (int) board.pos_to_indexes(current_position).get(1) > 6;
    }
    //ia o piesă în drepta
    public String take_right(Board board){
        String aux = current_position;
        String new_position;
        new_position = (char)(current_position.charAt(0) + x) + "" + (char)(current_position.charAt(1) + x);
        board.move(current_position+""+new_position);
        current_position = new_position;
        return aux+""+new_position;
    }
    //verifică dacă poate lua o piesă în stânga
    public Boolean can_take_left(Board board){
        ArrayList<Integer> positions = board.pos_to_indexes(current_position);
        if(etapa1(board) == true || (positions.get(0) - x > 7 || positions.get(0) - x < 0))
            return false;
        Piece piece = board.object_matrix[(int)board.pos_to_indexes(current_position).get(1) + x][(int)board.pos_to_indexes(current_position).get(0) - x];
        return piece != null && piece.color != color;
    }
    //verifică dacă poate lua o piesă în drepta
    public Boolean can_take_right(Board board){
        ArrayList<Integer> positions = board.pos_to_indexes(current_position);
        if(etapa1(board) == true || (positions.get(0) + x > 7 || positions.get(0) + x < 0))
            return false;
        Piece piece = board.object_matrix[(int)board.pos_to_indexes(current_position).get(1) + x][(int)board.pos_to_indexes(current_position).get(0) + x];
        return piece !=null && piece.color != color;
    }
    //verifică dacă poate merge înainte
    public Boolean can_move_forward(Board board){
        if(etapa1(board) == true)
            return false;
        Piece piece = board.object_matrix[(int)board.pos_to_indexes(current_position).get(1) + x][(int)board.pos_to_indexes(current_position).get(0)];
        return piece == null;
    }
    public Boolean taken(Board board){
        return board.object_matrix[(int)board.pos_to_indexes(current_position).get(1)][(int)board.pos_to_indexes(current_position).get(0)] != this;
    }
}
