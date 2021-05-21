import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Rook extends Piece{
    int first_move;
    public Rook(String current_position, String color){
        this.current_position = current_position;
        this.color = color;
        first_move = 1;
        value=5;
    }
    public ArrayList<String> can_move_horizontally(Board board){
        int axisy = board.pos_to_indexes(current_position).get(0) ;
        int axisx = board.pos_to_indexes(current_position).get(1) ;
        ArrayList<String> possible_squares = new ArrayList<String>();
        int i = 1;
        while(axisy + i <= 7){
            if(board.object_matrix[axisx][axisy + i] == null) {
                possible_squares.add((char) (axisy + i  + 'a') + "" + (char) (axisx + '1'));
                i++;
            } else if(board.object_matrix[axisx][axisy + i].color != color &&
                    (board.object_matrix[axisx][axisy + i] != board.k && board.object_matrix[axisx][axisy + i] != board.K)){
                possible_squares.add((char) (axisy + i  + 'a') + "" + (char) (axisx + '1'));
                i++;
                break;
            }else
                break;
        }
        i = 1;
        while(axisy - i >= 0){
            if (board.object_matrix[axisx][axisy - i] == null) {
                possible_squares.add((char) (axisy - i  + 'a') + "" + (char) (axisx + '1'));
                i++;
            }else if(board.object_matrix[axisx][axisy - i].color != color &&
                    (board.object_matrix[axisx][axisy - i] != board.k && board.object_matrix[axisx][axisy - i] != board.K)){
                possible_squares.add((char) (axisy - i  + 'a') + "" + (char) (axisx + '1'));
                i++;
                break;
            }else
                break;
        }
        return possible_squares;
    }
    public ArrayList<String> can_move_vertically(Board board){
        int axisy = board.pos_to_indexes(current_position).get(0) ;
        int axisx = board.pos_to_indexes(current_position).get(1) ;
        ArrayList<String> possible_squares = new ArrayList<String>();
        int i = 1;
        while(axisx + i <= 7){
            if(board.object_matrix[axisx + i][axisy] == null) {
                possible_squares.add((char) (axisy + 'a') + "" + (char) (axisx + i + '1'));
                i++;
            } else if(board.object_matrix[axisx + i][axisy].color != color &&
                    (board.object_matrix[axisx + i][axisy] != board.k && board.object_matrix[axisx + i][axisy] != board.K)){
                possible_squares.add((char) (axisy  + 'a') + "" + (char) (axisx + i + '1'));
                i++;
                break;
            }else
                break;
        }
        i = 1;
        while(axisx - i >= 0){
            if (board.object_matrix[axisx - i][axisy] == null) {
                possible_squares.add((char) (axisy + 'a') + "" + (char) (axisx - i + '1'));
                i++;
            } else if(board.object_matrix[axisx - i][axisy].color != color &&
                    (board.object_matrix[axisx - i][axisy] != board.k && board.object_matrix[axisx - i][axisy] != board.K)){
                possible_squares.add((char) (axisy  + 'a') + "" + (char) (axisx - i + '1'));
                i++;
                break;
            }else
                break;
        }
        return possible_squares;
    }

    public String move_horizontally(Board board){
        ArrayList<String> moves = can_move_horizontally(board);
        if(moves.size() == 0)
            return null;
        Random rand = new Random();
        return current_position + "" + moves.get(rand.nextInt(moves.size()));
    }

    public String move_vertically(Board board){
        ArrayList<String> moves = can_move_vertically(board);
        if(moves.size() == 0)
            return null;
        Random rand = new Random();
        return current_position + "" + moves.get(rand.nextInt(moves.size()));
    }

    Boolean canMove(Board board){
        return can_move_vertically(board).size() != 0 || can_move_horizontally(board).size() != 0;
    }

    String move(Board board){
        ArrayList<String> possible_moves = new ArrayList<String>();
        if(move_vertically(board) != null)
            possible_moves.add(move_vertically(board));
        if(move_horizontally(board) != null)
            possible_moves.add(move_horizontally(board));
        if(possible_moves.size() == 0)
            return "";
        Random rand = new Random();
        int index = rand.nextInt(possible_moves.size());
        board.move( possible_moves.get(index));
        current_position = possible_moves.get(index).substring(2);
        first_move = 0;
        return possible_moves.get(index);
    }
}