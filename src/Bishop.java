import java.nio.charset.CoderResult;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class Bishop extends Piece{
    int value;
    ArrayList<Coordinates> possibleMoves;
    public Bishop(String current_position, String color){
        value = 3;
        possibleMoves = new ArrayList<>();
        this.current_position = current_position;
        this.color = color;
    }

    void generateMoves(Board board) {
        possibleMoves.clear();
        ArrayList<Integer> initCoords = board.pos_to_indexes(current_position);
        int row = initCoords.get(1);
        int col = initCoords.get(0);
        System.out.println(col + " " + row);
        for(int i = row + 1, j = col + 1; i <= 7 && j <= 7; i++, j++) {
            System.out.println(i + " " + j);
            if(board.object_matrix[i][j] == null)
                possibleMoves.add(new Coordinates(i, j));
            else if(board.object_matrix[i][j].color.compareTo(color) != 0) {
                possibleMoves.add(new Coordinates(i, j));
                break;
            }
            else break;
        }
        for(int i = row + 1, j = col - 1; i <= 7 && j >= 0; i++, j--) {
            System.out.println(i + " " + j);
            if(board.object_matrix[i][j] == null)
                possibleMoves.add(new Coordinates(i, j));
            else if(board.object_matrix[i][j].color.compareTo(color) != 0) {
                possibleMoves.add(new Coordinates(i, j));
                break;
            }
            else break;
        }
        for(int i = row - 1, j = col + 1; i >= 0 && j <= 7; i--, j++) {
            System.out.println(i + " " + j);
            if(board.object_matrix[i][j] == null)
                possibleMoves.add(new Coordinates(i, j));
            else if(board.object_matrix[i][j].color.compareTo(color) != 0) {
                possibleMoves.add(new Coordinates(i, j));
                break;
            }
            else break;
        }
        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            System.out.println(i + " " + j);
            if(board.object_matrix[i][j] == null)
                possibleMoves.add(new Coordinates(i, j));
            else if(board.object_matrix[i][j].color.compareTo(color) != 0) {
                possibleMoves.add(new Coordinates(i, j));
                break;
            }
            else break;
        }
        System.out.println(possibleMoves.size());
    }

    boolean canMove(Board board) {
        generateMoves(board);
        return (possibleMoves.size() != 0);
    }

    String move(Board board) {
        Random randomMove = new Random();
        Coordinates myMove = possibleMoves.get(Math.abs(randomMove.nextInt()) % possibleMoves.size());
        String aux = current_position;
        String new_position = (char)('a' + myMove.col) + "" + (char)('1' + myMove.row);
        board.move(current_position + "" + new_position);
        current_position = new_position;
        return aux + "" + new_position;
    }

    public Boolean taken(Board board){
        return board.object_matrix[(int)board.pos_to_indexes(current_position).get(1)][(int)board.pos_to_indexes(current_position).get(0)] != this;
    }
}

class Coordinates {
    int row;
    int col;
    Coordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
