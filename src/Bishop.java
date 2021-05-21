import java.util.ArrayList;
import java.util.Random;

public class Bishop extends Piece{
    // toate mutarile posibile intr-o anumita stare a tablei
    ArrayList<Coordinates> possibleMoves;
    public Bishop(String current_position, String color){
        value = 3.25;
        possibleMoves = new ArrayList<>();
        this.current_position = current_position;
        this.color = color;
    }

    // functie de generare a mutarilor valide
    void generateMoves(Board board) {
        possibleMoves.clear();
        // coordonate initiale
        ArrayList<Integer> initCoords = board.pos_to_indexes(current_position);
        int row = initCoords.get(1);
        int col = initCoords.get(0);
        // 4 for-uri pentru cele 4 diagonale pe care se poate deplasa nebunul
        for(int i = row + 1, j = col + 1; i <= 7 && j <= 7; i++, j++) {
            // daca gasim mutare valida o adaugam in array-ul de mutari
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
    }

    // genrare mutari posibile + verificare daca o piesa se poate misca
    Boolean canMove(Board board) {
        generateMoves(board);
        return (possibleMoves.size() != 0);
    }

    // functia de mutare
    String move(Board board) {
        Random randomMove = new Random();
        Coordinates myMove = possibleMoves.get(Math.abs(randomMove.nextInt()) % possibleMoves.size());
        String aux = current_position;
        String new_position = (char)('a' + myMove.col) + "" + (char)('1' + myMove.row);
        board.move(current_position + "" + new_position);
        current_position = new_position;
        return aux + "" + new_position;
    }

    // verificam ca piesa a fost luata
    public Boolean taken(Board board){
        return board.object_matrix[(int)board.pos_to_indexes(current_position).get(1)][(int)board.pos_to_indexes(current_position).get(0)] != this;
    }
}

// clasa de coordonate (linie + coloana)
class Coordinates {
    int row;
    int col;
    Coordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
