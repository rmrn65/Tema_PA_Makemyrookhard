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
    public String move_forward(int first_move, Board board){
        String aux =current_position;
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
        return (int) board.pos_to_indexes(current_position).get(1) < 1 || (int) board.pos_to_indexes(current_position).get(1) > 8;
    }
    public String take_right(Board board){
        String aux = current_position;
        String new_position;
        new_position = (char)(current_position.charAt(0) + x) + "" + (char)(current_position.charAt(1) + x);
        board.move(current_position+""+new_position);
        current_position = new_position;
        return aux+""+new_position;
    }
    public Boolean can_take_left(Board board){
        if(etapa1(board) == true)
            return false;
        Piece piece = board.object_matrix[(int)board.pos_to_indexes(current_position).get(1) + x][(int)board.pos_to_indexes(current_position).get(0) - x];
        return piece != null && piece.color != color;
    }
    public Boolean can_take_right(Board board){
        if(etapa1(board) == true)
            return false;
        Piece piece = board.object_matrix[(int)board.pos_to_indexes(current_position).get(1) + x][(int)board.pos_to_indexes(current_position).get(0) + x];
        return piece !=null && piece.color != color;
    }
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
