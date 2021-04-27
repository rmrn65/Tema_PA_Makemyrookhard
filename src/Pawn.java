import java.util.ArrayList;
import java.util.Random;
public class Pawn extends Piece{
    int value, x;
    ArrayList<String> possible_moves;
    public Pawn(){
        current_position = "";
        color = "";
        value = 1;
        possible_moves = new ArrayList<>();
    }
    public Pawn(String current_position,String color){
        this();
        this.current_position = current_position;
        this.color = color;
        if(color.equals("white")) {
            x = 1;
        }
        else {
            x = -1;
        }
    }
    public String move(Board board){
        possible_moves.clear();
        if(can_move_forward(board)) {
            if((color.compareTo("white") == 0 && current_position.charAt(1) == '2') || (color.compareTo("black") == 0 && current_position.charAt(1) == '7') &&
            board.object_matrix[board.pos_to_indexes(current_position).get(1) + 2 * x][board.pos_to_indexes(current_position).get(0)] == null)
                possible_moves.add(move_forward(2));
            possible_moves.add(move_forward(1));
        }
        if(can_take_left(board))
            possible_moves.add(take_left());
        if(can_take_right(board))
            possible_moves.add((take_right()));
        if(can_enPassant_left(board))
            possible_moves.add(take_left());
        if(can_enPassant_right(board))
            possible_moves.add(take_right());
        Random rand = new Random();
        String randomMove = possible_moves.get(Math.abs(rand.nextInt()) % possible_moves.size());
        if((color.compareTo("white") == 0 && randomMove.charAt(3) == '8') || (color.compareTo("black") == 0 && randomMove.charAt(3) == '1'))
            board.move(randomMove + "q");
        else
            board.move(randomMove);
        current_position = randomMove.charAt(2) + "" + randomMove.charAt(3);
        if((color.compareTo("white") == 0 && randomMove.charAt(3) == '8') || (color.compareTo("black") == 0 && randomMove.charAt(3) == '1'))
            return randomMove + "q";
        return randomMove;
    }
    public Boolean canMove(Board board){
        return can_move_forward(board) || can_take_right(board) || can_take_left(board) || can_enPassant_right(board) || can_enPassant_left(board);
    }
    //move care aplica move_forward take_left take_right
    //if(can take right , list.add (0 , x) )
    //if(can move forward , list.add )
    //if (can move left , list.add )
    //verifică dacă poate merge înainte
    public String move_forward(int noOfSquares){
        String aux = current_position; //rețin starea curentă
        String new_position;
        new_position = current_position.charAt(0) + "" + (char)(current_position.charAt(1) + noOfSquares * x);
        return aux+""+new_position;
    }
    //ia o piesă în stânga
    public String take_left(){
        String aux =current_position;
        String new_position;
        new_position = (char)(current_position.charAt(0) - x) + "" + (char)(current_position.charAt(1) + x);
        return aux+""+new_position;
    }
    public Boolean etapa1(Board board){
        return (int) board.pos_to_indexes(current_position).get(1) < 1 || (int) board.pos_to_indexes(current_position).get(1) > 6;
    }
    //ia o piesă în drepta
    public String take_right(){
        String aux = current_position;
        String new_position;
        new_position = (char)(current_position.charAt(0) + x) + "" + (char)(current_position.charAt(1) + x);
        return aux+""+new_position;
    }
    //verifică dacă poate lua o piesă în stânga
    public Boolean can_take_left(Board board){
        ArrayList<Integer> positions = board.pos_to_indexes(current_position);
        if(etapa1(board) || (positions.get(0) - x > 7 || positions.get(0) - x < 0))
            return false;
        Piece piece = board.object_matrix[board.pos_to_indexes(current_position).get(1) + x][board.pos_to_indexes(current_position).get(0) - x];
        return piece != null && !piece.color.equals(color);
    }
    //verifică dacă poate lua o piesă în drepta
    public Boolean can_take_right(Board board){
        ArrayList<Integer> positions = board.pos_to_indexes(current_position);
        if(etapa1(board) || (positions.get(0) + x > 7 || positions.get(0) + x < 0))
            return false;
        Piece piece = board.object_matrix[board.pos_to_indexes(current_position).get(1) + x][board.pos_to_indexes(current_position).get(0) + x];
        return piece !=null && !piece.color.equals(color);
    }
    //verifică dacă poate merge înainte
    public Boolean can_move_forward(Board board){
        if(etapa1(board))
            return false;
        Piece piece = board.object_matrix[board.pos_to_indexes(current_position).get(1) + x][board.pos_to_indexes(current_position).get(0)];
        return piece == null;
    }
    public Boolean taken(Board board){
        return board.object_matrix[board.pos_to_indexes(current_position).get(1)][board.pos_to_indexes(current_position).get(0)] != this;
    }

    public Boolean can_enPassant_left(Board board) {
        ArrayList<Integer> position = board.pos_to_indexes(current_position);
        StringBuilder pieceLastMove = new StringBuilder();
        if(color.compareTo("white") == 0) {
            if(position.get(0) == 0)
                return false;
            if(position.get(1) != 4)
                return false;
            if(can_take_left(board))
                return false;
            Piece piece = board.object_matrix[position.get(1)][position.get(0) - 1];
            if(!(piece instanceof Pawn))
                return false;
            if(piece.color.compareTo(color) == 0)
                return false;
            pieceLastMove.append((char) ('a' + (position.get(0) - 1))).append('6')
                    .append((char) ('a' + (position.get(0) - 1))).append('4');
            return board.lastMove.compareTo(pieceLastMove) == 0;
        }
        else {
            if(position.get(0) == 0)
                return false;
            if(position.get(1) != 3)
                return false;
            if(can_take_left(board))
                return false;
            Piece piece = board.object_matrix[position.get(1)][position.get(0) - 1];
            if(!(piece instanceof Pawn))
                return false;
            if(piece.color.compareTo(color) == 0)
                return false;
            pieceLastMove.append((char) ('a' + (position.get(0) - 1))).append('1')
                    .append((char) ('a' + (position.get(0) - 1))).append('3');
            return board.lastMove.compareTo(pieceLastMove) == 0;
        }
    }

    public Boolean can_enPassant_right(Board board) {
        ArrayList<Integer> position = board.pos_to_indexes(current_position);
        StringBuilder pieceLastMove = new StringBuilder();
        if(color.compareTo("white") == 0) {
            if(position.get(0) == 7)
                return false;
            if(position.get(1) != 4)
                return false;
            if(can_take_right(board))
                return false;
            Piece piece = board.object_matrix[position.get(1)][position.get(0) + 1];
            if(!(piece instanceof Pawn))
                return false;
            if(piece.color.compareTo(color) == 0)
                return false;
            pieceLastMove.append((char) ('a' + (position.get(0) + 1))).append('7')
                    .append((char) ('a' + (position.get(0) + 1))).append('5');
            return board.lastMove.compareTo(pieceLastMove) == 0;
        }
        else {
            if(position.get(0) == 7)
                return false;
            if(position.get(1) != 3)
                return false;
            if(can_take_right(board))
                return false;
            Piece piece = board.object_matrix[position.get(1)][position.get(0) + 1];
            if(!(piece instanceof Pawn))
                return false;
            if(piece.color.compareTo(color) == 0)
                return false;
            pieceLastMove.append((char) ('a' + (position.get(0) + 1))).append('1')
                    .append((char) ('a' + (position.get(0) + 1))).append('3');
            return board.lastMove.compareTo(pieceLastMove) == 0;
        }
    }

    public void promotePawn(Board board) {
        board.object_matrix[this.current_position.charAt(0) - 'a'][this.current_position.charAt(1) - '1'] = new Queen(current_position, color);
    }
}