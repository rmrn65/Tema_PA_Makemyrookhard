import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import java.util.Vector;

public class King extends Piece{
    int first_move;
    Vector<KingMoves> possible_moves;
    Vector<String> optionsToDefend = new Vector<String>();

    class KingMoves {
        int linie, coloana;
        public KingMoves(int linie, int coloana) {
            this.linie = linie;
            this.coloana = coloana;
        }
        public String toString(){
            return (char)(coloana) + "" + (char)(linie);
        }
    }
    public King(String current_position, String color){
        this.current_position = current_position;
        this.color = color;
        first_move = 1;
    }
    public int[] isInCheck(String position, Board board) {
        int[] check_position = new int[5];
        check_position[4] = 0;
        int linie = board.pos_to_indexes(position).get(1);
        int coloana = board.pos_to_indexes(position).get(0);

        int axisy = board.pos_to_indexes(position).get(0) ;
        int axisx = board.pos_to_indexes(position).get(1) ;
        int i = 1;
        while(axisy + i <= 7) {
            if (board.object_matrix[axisx][axisy + i] == null || board.object_matrix[axisx][axisy + i] == this) {
                i++;
            } else if (board.object_matrix[axisx][axisy + i].color != color &&
                    (board.object_matrix[axisx][axisy + i].getClass().getName().equals("Rook")
                            || board.object_matrix[axisx][axisy + i].getClass().getName().equals("Queen"))) {
                check_position[check_position[4]] = axisx;
                check_position[check_position[4] + 1] = axisy + i;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
                break;
            } else
                break;
        }
        i = 1;
        while(axisy - i >= 0){
            if (board.object_matrix[axisx][axisy - i] == null || board.object_matrix[axisx][axisy - i] == this) {
                i++;
            }else if(board.object_matrix[axisx][axisy - i].color != color &&
                    (board.object_matrix[axisx][axisy - i].getClass().getName().equals("Rook")
                            || board.object_matrix[axisx][axisy - i].getClass().getName().equals("Queen"))){
                check_position[check_position[4]] = axisx;
                check_position[check_position[4] + 1] = axisy - i;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
                break;
            }else
                break;
        }
        if(outOfBounds(linie + 2, coloana + 1 ))
            if (board.object_matrix[linie + 2][coloana + 1] != null && board.object_matrix[linie + 2][coloana + 1].color != color &&
                    board.object_matrix[linie + 2][coloana + 1].getClass().getName().equals("Night")) {
                check_position[check_position[4]] = linie + 2;
                check_position[check_position[4] + 1] = coloana + 1;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
            }
        if(outOfBounds(linie + 2, coloana - 1 ))
            if (board.object_matrix[linie + 2][coloana - 1] != null && board.object_matrix[linie + 2][coloana - 1].color != color &&
                    board.object_matrix[linie + 2][coloana - 1].getClass().getName().equals("Night")) {
                check_position[check_position[4]] = linie + 2;
                check_position[check_position[4] + 1] = coloana - 1;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
            }
        if(outOfBounds(linie - 2, coloana + 1 ))
            if (board.object_matrix[linie - 2][coloana + 1] != null && board.object_matrix[linie - 2][coloana + 1].color != color &&
                    board.object_matrix[linie - 2][coloana + 1].getClass().getName().equals("Night")) {
                check_position[check_position[4]] = linie - 2;
                check_position[check_position[4] + 1] = coloana + 1;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
            }
        if(outOfBounds(linie - 2, coloana - 1 ))
            if (board.object_matrix[linie - 2][coloana - 1] != null && board.object_matrix[linie - 2][coloana - 1].color != color &&
                    board.object_matrix[linie - 2][coloana - 1].getClass().getName().equals("Night")) {
                check_position[check_position[4]] = linie - 2;
                check_position[check_position[4] + 1] = coloana - 1;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
            }
        if(outOfBounds(linie + 1, coloana - 2 ))
            if (board.object_matrix[linie + 1][coloana - 2] != null && board.object_matrix[linie + 1][coloana - 2].color != color &&
                    board.object_matrix[linie + 1][coloana - 2].getClass().getName().equals("Night")){
                check_position[check_position[4]] = linie + 1;
                check_position[check_position[4] + 1] = coloana - 2;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
            }
        if(outOfBounds(linie - 1, coloana - 2 ))
            if (board.object_matrix[linie - 1][coloana - 2] != null && board.object_matrix[linie - 1][coloana - 2].color != color &&
                    board.object_matrix[linie - 1][coloana - 2].getClass().getName().equals("Night")){
                check_position[check_position[4]] = linie - 1;
                check_position[check_position[4] + 1] = coloana - 2;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
            }
        if(outOfBounds(linie + 1, coloana + 2 ))
            if (board.object_matrix[linie + 1][coloana + 2] != null && board.object_matrix[linie + 1][coloana + 2].color != color &&
                    board.object_matrix[linie + 1][coloana + 2].getClass().getName().equals("Night")) {
                check_position[check_position[4]] = linie + 1;
                check_position[check_position[4] + 1] = coloana + 2;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
            }
        if(outOfBounds(linie - 1, coloana + 2))
            if (board.object_matrix[linie - 1][coloana + 2] != null && board.object_matrix[linie - 1][coloana + 2].color != color &&
                    board.object_matrix[linie - 1][coloana + 2].getClass().getName().equals("Night")){
                check_position[check_position[4]] = linie - 1;
                check_position[check_position[4] + 1] = coloana + 2;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
            }


        axisy = board.pos_to_indexes(position).get(0) ;
        axisx = board.pos_to_indexes(position).get(1) ;
        i = 1;
        while(axisx + i <= 7){
            if(board.object_matrix[axisx + i][axisy] == null || board.object_matrix[axisx + i][axisy] == this) {
                i++;
            } else if(board.object_matrix[axisx + i][axisy].color != color &&
                    (board.object_matrix[axisx + i][axisy].getClass().getName().equals("Rook")
                            || board.object_matrix[axisx + i][axisy].getClass().getName().equals("Queen"))){
                check_position[check_position[4]] = axisx + i;
                check_position[check_position[4] + 1] = axisy;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
                break;
            } else
                break;
        }
        i = 1;
        while(axisx - i >= 0){
            if (board.object_matrix[axisx - i][axisy] == null || board.object_matrix[axisx - i][axisy] == this) {
                i++;
            } else if(board.object_matrix[axisx - i][axisy].color != color &&
                    (board.object_matrix[axisx - i][axisy].getClass().getName().equals("Rook")
                            || board.object_matrix[axisx - i][axisy].getClass().getName().equals("Queen"))){
                check_position[check_position[4]] = axisx - i;
                check_position[check_position[4] + 1] = axisy;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
                break;
            } else
                break;
        }
        i = 1;
        //axisx + i && axisy + i
        while(axisx + i <= 7 && axisy + i <= 7){
            if(board.object_matrix[axisx + i][axisy + i] == null || board.object_matrix[axisx + i][axisy + i] == this) {
                i++;
            } else if(board.object_matrix[axisx + i][axisy + i].color != color &&
                    (board.object_matrix[axisx + i][axisy + i].getClass().getName().equals("Bishop")
                            || board.object_matrix[axisx + i][axisy + i].getClass().getName().equals("Queen"))){
                check_position[check_position[4]] = axisx + i;
                check_position[check_position[4] + 1] = axisy + i;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
                break;
            } else
                break;
        }
        i = 1;
        //axisx - i && axisy - i
        while(axisx - i >= 0 && axisy -i >= 0){
            if (board.object_matrix[axisx - i][axisy - i] == null || board.object_matrix[axisx - i][axisy - i] == this) {
                i++;
            } else if(board.object_matrix[axisx - i][axisy - i].color != color &&
                    (board.object_matrix[axisx - i][axisy - i].getClass().getName().equals("Bishop")
                            || board.object_matrix[axisx - i][axisy - i].getClass().getName().equals("Queen"))){
                check_position[check_position[4]] = axisx - i;
                check_position[check_position[4] + 1] = axisy - i;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
                break;
            } else
                break;
        }
        i = 1;
        //axisx + i && axis y - i
        while(axisx + i <= 7 && axisy - i >= 0){
            if (board.object_matrix[axisx + i][axisy - i] == null || board.object_matrix[axisx + i][axisy - i] == this) {
                i++;
            } else if(board.object_matrix[axisx + i][axisy - i].color != color &&
                    (board.object_matrix[axisx + i][axisy - i].getClass().getName().equals("Bishop")
                            || board.object_matrix[axisx + i][axisy - i].getClass().getName().equals("Queen"))){
                check_position[check_position[4]] = axisx + i;
                check_position[check_position[4] + 1] = axisy - i;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
                break;
            } else
                break;
        }
        i = 1;
        //axisx -i && axisy + i
        while(axisx - i >= 0 && axisy + i <= 7){
            if (board.object_matrix[axisx - i][axisy + i] == null || board.object_matrix[axisx - i][axisy + i] == this) {
                i++;
            } else if(board.object_matrix[axisx - i][axisy + i].color != color &&
                    (board.object_matrix[axisx - i][axisy + i].getClass().getName().equals("Bishop")
                            || board.object_matrix[axisx - i][axisy + i].getClass().getName().equals("Queen"))){

                check_position[check_position[4]] = axisx - i;
                check_position[check_position[4] + 1] = axisy + i;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
                break;
            } else
                break;
        }
        if (this.color.equals("white")) {
            if(outOfBounds(linie+1, coloana-1))
                if (board.object_matrix[linie + 1][coloana - 1] != null && board.object_matrix[linie + 1][coloana - 1].color != color &&
                        board.object_matrix[linie + 1][coloana - 1].getClass().getName().equals("Pawn")) {
                    check_position[check_position[4]] = linie + 1;
                    check_position[check_position[4] + 1] = coloana - 1;
                    check_position[4] += 2;
                    if (check_position[4] == 4)
                        return check_position;
                }

            if(outOfBounds(linie+1, coloana+1))
                if (board.object_matrix[linie + 1][coloana + 1] != null && board.object_matrix[linie + 1][coloana + 1].color != color &&
                        board.object_matrix[linie + 1][coloana + 1].getClass().getName().equals("Pawn")) {
                    check_position[check_position[4]] = linie + 1;
                    check_position[check_position[4] + 1] = coloana + 1;
                    check_position[4] += 2;
                    if (check_position[4] == 4)
                        return check_position;
                }
        }
        if (this.color.equals("black")) {
            if(outOfBounds(linie-1, coloana-1))
                if (board.object_matrix[linie - 1][coloana - 1] != null && board.object_matrix[linie - 1][coloana - 1].color != color &&
                        board.object_matrix[linie - 1][coloana - 1].getClass().getName().equals("Pawn")) {
                    check_position[check_position[4]] = linie - 1;
                    check_position[check_position[4] + 1] = coloana - 1;
                    check_position[4] += 2;
                    if (check_position[4] == 4)
                        return check_position;
                }
            if(outOfBounds(linie-1, coloana+1))
                if (board.object_matrix[linie - 1][coloana + 1] != null && board.object_matrix[linie - 1][coloana + 1].color != color &&
                        board.object_matrix[linie - 1][coloana + 1].getClass().getName().equals("Pawn")) {
                    check_position[check_position[4]] = linie - 1;
                    check_position[check_position[4] + 1] = coloana + 1;
                    check_position[4] += 2;
                    if (check_position[4] == 4)
                        return check_position;
                }
        }
        if(outOfBounds(linie, coloana  - 1))
            if(board.object_matrix[linie][coloana - 1] != null && board.object_matrix[linie][coloana - 1].color != color &&
                    board.object_matrix[linie][coloana - 1].getClass().getName().equals("King")) {
                check_position[check_position[4]] = linie;
                check_position[check_position[4] + 1] = coloana - 1;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
            }
        if(outOfBounds(linie, coloana  + 1))
            if(board.object_matrix[linie][coloana + 1] != null && board.object_matrix[linie][coloana + 1].color != color &&
                    board.object_matrix[linie][coloana + 1].getClass().getName().equals("King")) {
                check_position[check_position[4]] = linie;
                check_position[check_position[4] + 1] = coloana + 1;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
            }
        if(outOfBounds(linie - 1, coloana))
            if(board.object_matrix[linie - 1][coloana] != null && board.object_matrix[linie - 1][coloana].color != color &&
                    board.object_matrix[linie - 1][coloana].getClass().getName().equals("King")) {
                check_position[check_position[4]] = linie - 1;
                check_position[check_position[4] + 1] = coloana;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
            }
        if(outOfBounds(linie + 1, coloana))
            if(board.object_matrix[linie + 1][coloana] != null && board.object_matrix[linie + 1][coloana].color != color &&
                    board.object_matrix[linie + 1][coloana].getClass().getName().equals("King")) {
                check_position[check_position[4]] = linie + 1;
                check_position[check_position[4] + 1] = coloana;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
            }
        if(outOfBounds(linie + 1, coloana + 1))
            if(board.object_matrix[linie + 1][coloana + 1] != null && board.object_matrix[linie + 1][coloana + 1].color != color &&
                    board.object_matrix[linie + 1][coloana + 1].getClass().getName().equals("King")) {
                check_position[check_position[4]] = linie + 1;
                check_position[check_position[4] + 1] = coloana + 1;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
            }
        if(outOfBounds(linie - 1, coloana - 1))
            if(board.object_matrix[linie - 1][coloana - 1] != null && board.object_matrix[linie - 1][coloana - 1].color != color &&
                    board.object_matrix[linie - 1][coloana - 1].getClass().getName().equals("King")) {
                check_position[check_position[4]] = linie - 1;
                check_position[check_position[4] + 1] = coloana - 1;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
            }
        if(outOfBounds(linie + 1, coloana - 1))
            if(board.object_matrix[linie + 1][coloana - 1] != null && board.object_matrix[linie + 1][coloana - 1].color != color &&
                    board.object_matrix[linie + 1][coloana - 1].getClass().getName().equals("King")) {
                check_position[check_position[4]] = linie + 1;
                check_position[check_position[4] + 1] = coloana - 1;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
            }
        if(outOfBounds(linie - 1, coloana + 1))
            if(board.object_matrix[linie - 1][coloana + 1] != null && board.object_matrix[linie - 1][coloana + 1].color != color &&
                    board.object_matrix[linie - 1][coloana + 1].getClass().getName().equals("King")) {
                check_position[check_position[4]] = linie - 1;
                check_position[check_position[4] + 1] = coloana + 1;
                check_position[4] += 2;
                if (check_position[4] == 4)
                    return check_position;
            }
        return check_position[4] == 0? null : check_position;
    }

    public Vector<String> defendKing(String position, Board board) {
        int[] check_position = new int[2];

        int linie = board.pos_to_indexes(position).get(1);
        int coloana = board.pos_to_indexes(position).get(0);
        int index = 0;
        int axisy = board.pos_to_indexes(position).get(0) ;
        int axisx = board.pos_to_indexes(position).get(1) ;
        int i = 1;
        while(axisy + i <= 7) {
            if (board.object_matrix[axisx][axisy + i] == null) {
                i++;
            } else if (board.object_matrix[axisx][axisy + i].color == color &&
                    (board.object_matrix[axisx][axisy + i].getClass().getName().equals("Rook")
                            || board.object_matrix[axisx][axisy + i].getClass().getName().equals("Queen"))) {
                check_position[0] = axisx;
                check_position[1] = axisy + i;
                optionsToDefend.add((char)(check_position[1] + 'a') + "" + (char)(check_position[0] + '1') + position);
                break;
            } else
                break;
        }
        i = 1;
        while(axisy - i >= 0){
            if (board.object_matrix[axisx][axisy - i] == null ) {
                i++;
            }else if(board.object_matrix[axisx][axisy - i].color == color &&
                    (board.object_matrix[axisx][axisy - i].getClass().getName().equals("Rook")
                            || board.object_matrix[axisx][axisy - i].getClass().getName().equals("Queen"))){
                check_position[0] = axisx;
                check_position[1] = axisy - i;
                optionsToDefend.add((char)(check_position[1] + 'a') + "" + (char)(check_position[0] + '1') + position);
                break;
            }else
                break;
        }
        if(outOfBounds(linie + 2, coloana + 1 ))
            if (board.object_matrix[linie + 2][coloana + 1] != null && board.object_matrix[linie + 2][coloana + 1].color == color &&
                    board.object_matrix[linie + 2][coloana + 1].getClass().getName().equals("Night")) {
                check_position[0] = linie + 2;
                check_position[1] = coloana + 1;
                optionsToDefend.add((char)(check_position[1] + 'a') + "" + (char)(check_position[0] + '1') + position);
            }
        if(outOfBounds(linie + 2, coloana - 1 ))
            if (board.object_matrix[linie + 2][coloana - 1] != null && board.object_matrix[linie + 2][coloana - 1].color == color &&
                    board.object_matrix[linie + 2][coloana - 1].getClass().getName().equals("Night")) {
                check_position[0] = linie + 2;
                check_position[1] = coloana - 1;
                optionsToDefend.add((char)(check_position[1] + 'a') + "" + (char)(check_position[0] + '1') + position);
            }
        if(outOfBounds(linie - 2, coloana + 1 ))
            if (board.object_matrix[linie - 2][coloana + 1] != null && board.object_matrix[linie - 2][coloana + 1].color == color &&
                    board.object_matrix[linie - 2][coloana + 1].getClass().getName().equals("Night")) {
                check_position[0] = linie - 2;
                check_position[1] = coloana + 1;
                optionsToDefend.add((char)(check_position[1] + 'a') + "" + (char)(check_position[0] + '1') + position);
            }
        if(outOfBounds(linie - 2, coloana - 1 ))
            if (board.object_matrix[linie - 2][coloana - 1] != null && board.object_matrix[linie - 2][coloana - 1].color == color &&
                    board.object_matrix[linie - 2][coloana - 1].getClass().getName().equals("Night")) {
                check_position[0] = linie - 2;
                check_position[1] = coloana - 1;
                optionsToDefend.add((char)(check_position[1] + 'a') + "" + (char)(check_position[0] + '1') + position);
            }
        if(outOfBounds(linie + 1, coloana - 2 ))
            if (board.object_matrix[linie + 1][coloana - 2] != null && board.object_matrix[linie + 1][coloana - 2].color == color &&
                    board.object_matrix[linie + 1][coloana - 2].getClass().getName().equals("Night")){
                check_position[0] = linie + 1;
                check_position[1] = coloana - 2;
                optionsToDefend.add((char)(check_position[1] + 'a') + "" + (char)(check_position[0] + '1') + position);
            }
        if(outOfBounds(linie - 1, coloana - 2 ))
            if (board.object_matrix[linie - 1][coloana - 2] != null && board.object_matrix[linie - 1][coloana - 2].color == color &&
                    board.object_matrix[linie - 1][coloana - 2].getClass().getName().equals("Night")){
                check_position[0] = linie - 1;
                check_position[1] = coloana - 2;
                optionsToDefend.add((char)(check_position[1] + 'a') + "" + (char)(check_position[0] + '1') + position);
            }
        if(outOfBounds(linie + 1, coloana + 2 ))
            if (board.object_matrix[linie + 1][coloana + 2] != null && board.object_matrix[linie + 1][coloana + 2].color == color &&
                    board.object_matrix[linie + 1][coloana + 2].getClass().getName().equals("Night")) {
                check_position[0] = linie + 1;
                check_position[1] = coloana + 2;
                optionsToDefend.add((char)(check_position[1] + 'a') + "" + (char)(check_position[0] + '1') + position);
            }
        if(outOfBounds(linie - 1, coloana + 2))
            if (board.object_matrix[linie - 1][coloana + 2] != null && board.object_matrix[linie - 1][coloana + 2].color == color &&
                    board.object_matrix[linie - 1][coloana + 2].getClass().getName().equals("Night")){
                check_position[0] = linie - 1;
                check_position[1] = coloana + 2;
                optionsToDefend.add((char)(check_position[1] + 'a') + "" + (char)(check_position[0] + '1') + position);
            }


        axisy = board.pos_to_indexes(position).get(0) ;
        axisx = board.pos_to_indexes(position).get(1) ;
        i = 1;
        while(axisx + i <= 7){
            if(board.object_matrix[axisx + i][axisy] == null ) {
                i++;
            } else if(board.object_matrix[axisx + i][axisy].color == color &&
                    (board.object_matrix[axisx + i][axisy].getClass().getName().equals("Rook")
                            || board.object_matrix[axisx + i][axisy].getClass().getName().equals("Queen"))){
                check_position[0] = axisx + i;
                check_position[1] = axisy;
                optionsToDefend.add((char)(check_position[1] + 'a') + "" + (char)(check_position[0] + '1') + position);
                break;
            } else
                break;
        }
        i = 1;
        while(axisx - i >= 0){
            if (board.object_matrix[axisx - i][axisy] == null ) {
                i++;
            } else if(board.object_matrix[axisx - i][axisy].color == color &&
                    (board.object_matrix[axisx - i][axisy].getClass().getName().equals("Rook")
                            || board.object_matrix[axisx - i][axisy].getClass().getName().equals("Queen"))){
                check_position[0] = axisx - i;
                check_position[1] = axisy;
                optionsToDefend.add((char)(check_position[1] + 'a') + "" + (char)(check_position[0] + '1') + position);
                break;
            } else
                break;
        }
        i = 1;
        //axisx + i && axisy + i
        while(axisx + i <= 7 && axisy + i <= 7){
            if(board.object_matrix[axisx + i][axisy + i] == null ) {
                i++;
            } else if(board.object_matrix[axisx + i][axisy + i].color == color &&
                    (board.object_matrix[axisx + i][axisy + i].getClass().getName().equals("Bishop")
                            || board.object_matrix[axisx + i][axisy + i].getClass().getName().equals("Queen"))){
                check_position[0] = axisx + i;
                check_position[1] = axisy + i;
                optionsToDefend.add((char)(check_position[1] + 'a') + "" + (char)(check_position[0] + '1') + position);
                break;
            } else
                break;
        }
        i = 1;
        //axisx - i && axisy - i
        while(axisx - i >= 0 && axisy -i >= 0){
            if (board.object_matrix[axisx - i][axisy - i] == null ) {
                i++;
            } else if(board.object_matrix[axisx - i][axisy - i].color == color &&
                    (board.object_matrix[axisx - i][axisy - i].getClass().getName().equals("Bishop")
                            || board.object_matrix[axisx - i][axisy - i].getClass().getName().equals("Queen"))){
                check_position[0] = axisx - i;
                check_position[1] = axisy - i;
                optionsToDefend.add((char)(check_position[1] + 'a') + "" + (char)(check_position[0] + '1') + position);
                break;
            } else
                break;
        }
        i = 1;
        //axisx + i && axis y - i
        while(axisx + i <= 7 && axisy - i >= 0){
            if (board.object_matrix[axisx + i][axisy - i] == null ) {
                i++;
            } else if(board.object_matrix[axisx + i][axisy - i].color == color &&
                    (board.object_matrix[axisx + i][axisy - i].getClass().getName().equals("Bishop")
                            || board.object_matrix[axisx + i][axisy - i].getClass().getName().equals("Queen"))){
                check_position[0] = axisx + i;
                check_position[1] = axisy - i;
                optionsToDefend.add((char)(check_position[1] + 'a') + "" + (char)(check_position[0] + '1') + position);
                break;
            } else
                break;
        }
        i = 1;
        //axisx -i && axisy + i
        while(axisx - i >= 0 && axisy + i <= 7){
            if (board.object_matrix[axisx - i][axisy + i] == null ) {
                i++;
            } else if(board.object_matrix[axisx - i][axisy + i].color == color &&
                    (board.object_matrix[axisx - i][axisy + i].getClass().getName().equals("Bishop")
                            || board.object_matrix[axisx - i][axisy + i].getClass().getName().equals("Queen"))){

                check_position[0] = axisx - i;
                check_position[1] = axisy + i;
                optionsToDefend.add((char)(check_position[1] + 'a') + "" + (char)(check_position[0] + '1') + position);
                break;
            } else
                break;
        }
        if (this.color.equals("black")) {
            if(board.object_matrix[linie][coloana] != null) {
                if (outOfBounds(linie + 1, coloana - 1))
                    if (board.object_matrix[linie + 1][coloana - 1] != null && board.object_matrix[linie + 1][coloana - 1].color == color &&
                            board.object_matrix[linie + 1][coloana - 1].getClass().getName().equals("Pawn")) {
                        check_position[0] = linie + 1;
                        check_position[1] = coloana - 1;
                        optionsToDefend.add((char) (check_position[1] + 'a') + "" + (char) (check_position[0] + '1') + position);
                    }

                if (outOfBounds(linie + 1, coloana + 1))
                    if (board.object_matrix[linie + 1][coloana + 1] != null && board.object_matrix[linie + 1][coloana + 1].color == color &&
                            board.object_matrix[linie + 1][coloana + 1].getClass().getName().equals("Pawn")) {
                        check_position[0] = linie + 1;
                        check_position[1] = coloana + 1;
                        optionsToDefend.add((char) (check_position[1] + 'a') + "" + (char) (check_position[0] + '1') + position);
                    }
            }
            if(outOfBounds(linie+1, coloana))
                if (board.object_matrix[linie + 1][coloana] != null && board.object_matrix[linie + 1][coloana].color == color &&
                        board.object_matrix[linie + 1][coloana ].getClass().getName().equals("Pawn") && board.object_matrix[linie][coloana] == null) {
                    check_position[0] = linie + 1;
                    check_position[1] = coloana ;
                    optionsToDefend.add((char)(check_position[1] + 'a') + "" + (char)(check_position[0] + '1') + position);
                }
        }
        if (this.color.equals("white")) {
            if(board.object_matrix[linie][coloana] != null) {
                if (outOfBounds(linie - 1, coloana - 1))
                    if (board.object_matrix[linie - 1][coloana - 1] != null && board.object_matrix[linie - 1][coloana - 1].color == color &&
                            board.object_matrix[linie - 1][coloana - 1].getClass().getName().equals("Pawn")) {
                        check_position[0] = linie - 1;
                        check_position[1] = coloana - 1;
                        optionsToDefend.add((char) (check_position[1] + 'a') + "" + (char) (check_position[0] + '1') + position);
                    }
                if (outOfBounds(linie - 1, coloana + 1))
                    if (board.object_matrix[linie - 1][coloana + 1] != null && board.object_matrix[linie - 1][coloana + 1].color == color &&
                            board.object_matrix[linie - 1][coloana + 1].getClass().getName().equals("Pawn")) {
                        check_position[0] = linie - 1;
                        check_position[1] = coloana + 1;
                        optionsToDefend.add((char) (check_position[1] + 'a') + "" + (char) (check_position[0] + '1') + position);
                    }
            }
            if(outOfBounds(linie - 1, coloana))
                if (board.object_matrix[linie - 1][coloana] != null && board.object_matrix[linie - 1][coloana].color == color &&
                        board.object_matrix[linie - 1][coloana].getClass().getName().equals("Pawn") && board.object_matrix[linie][coloana] == null) {
                    check_position[0] = linie - 1;
                    check_position[1] = coloana;
                    optionsToDefend.add((char)(check_position[1] + 'a') + "" + (char)(check_position[0] + '1') + position);
                }
        }
        return optionsToDefend;
    }
    public String canKingDefend(Board board) {
        String canDef = null;
        optionsToDefend = new Vector<String>();

        int []array = isInCheck(current_position,board);
        int current_line = board.pos_to_indexes(current_position).get(1);
        int current_col = board.pos_to_indexes(current_position).get(0);
        System.out.println("array [0] : " + array[0] + " array[1] : " + array[1] + " current_linie : " + current_line + " current col: " + current_col);
        if (array[1] == current_col) {
            int diff = array[0] - current_line;
            diff = diff > 0? -1 : 1;
            for (int i = array[0]; i != current_line; i += diff)
                defendKing((char)(array[1] + 'a') + "" + (char)(i + '1'), board);
        }
        if (array[0] == current_line) {
            int diff = array[1] - current_col;
            diff = diff > 0? -1 : 1;
            for (int i = array[1]; i != current_col; i += diff)
                defendKing((char)(i + 'a') + "" + (char)(array[0] + '1'), board);
        }

        if(current_line < array[0] && current_col > array[1]) {
            int linie = array[0];
            int coloana = array[1];

            while(linie != current_line && coloana != current_col) {
                defendKing((char)(coloana + 'a') + "" + (char)(linie + '1'), board);
                linie--;
                coloana++;
            }

        }

        if(current_line > array[0] && current_col > array[1]) {
            int linie = array[0];
            int coloana = array[1];

            while(linie != current_line && coloana != current_col) {
                defendKing((char)(coloana + 'a') + "" + (char)(linie + '1'), board);
                linie++;
                coloana++;
            }

        }

        if(current_line < array[0] && current_col < array[1]) {
            int linie = array[0];
            int coloana = array[1];

            while(linie != current_line && coloana != current_col) {
                defendKing((char)(coloana + 'a') + "" + (char)(linie + '1'), board);
                linie--;
                coloana--;
            }

        }
        if(current_line > array[0] && current_col < array[1]) {
            int linie = array[0];
            int coloana = array[1];

            while(linie != current_line && coloana != current_col) {
                defendKing((char)(coloana + 'a') + "" + (char)(linie + '1'), board);
                linie++;
                coloana--;
            }
        }
        Random rand = new Random();
        ListIterator<String> it = optionsToDefend.listIterator();
        while(it.hasNext()){
            String aux = it.next();
            String starting_square = aux.substring(0,2);
            if(!canIMove(starting_square, board)){
                it.remove();
            }
        }
        if(optionsToDefend.size() == 0)
            return canDef;

        canDef = optionsToDefend.get(rand.nextInt(optionsToDefend.size()));

        return canDef;
    }
    public boolean canIMove(String position, Board board){
        int []array = isInCheck(current_position, board);
        if(array != null && array[4] == 2){
            int current_line = board.pos_to_indexes(position).get(1);
            int current_col = board.pos_to_indexes(position).get(0);
            Piece aux = board.object_matrix[current_line][current_col];
            board.object_matrix[current_line][current_col] = null;
            int []array2 = isInCheck(current_position, board);

            if(array2[4] == 4) {
                board.object_matrix[current_line][current_col] = aux;
                return false;
            }
            board.object_matrix[current_line][current_col] = aux;
            return true;
        }

        int current_line = board.pos_to_indexes(position).get(1);
        int current_col = board.pos_to_indexes(position).get(0);
        Piece aux = board.object_matrix[current_line][current_col];
        board.object_matrix[current_line][current_col] = null;
        if(isInCheck(current_position, board) != null){
            board.object_matrix[current_line][current_col] = aux;
            return false;
        }
        board.object_matrix[current_line][current_col] = aux;
        return true;
    }


    public boolean canMove(Board board) {
        possible_moves = movesForKing(board);
        int linie = board.pos_to_indexes(this.current_position).get(1);
        int coloana = board.pos_to_indexes(this.current_position).get(0);
        System.out.println(possible_moves.size());
        System.out.println(possible_moves);

        int linie_aux;
        int coloana_aux;
        ListIterator<KingMoves> it = possible_moves.listIterator();
        while(it.hasNext()) {
            KingMoves aux = it.next();
            linie_aux = linie;
            coloana_aux = coloana;
            linie_aux += aux.linie;
            coloana_aux += aux.coloana;

            String newPosition = (char)(coloana_aux + 'a') + "" + (char)(linie_aux + '1');

            if(linie_aux > 7 || coloana_aux > 7 || linie_aux < 0 || coloana_aux < 0) {
                it.remove();
                continue;
            }
            if(board.object_matrix[linie_aux][coloana_aux] != null) {
                if(board.object_matrix[linie_aux][coloana_aux].color.equals(this.color)){
                    it.remove();
                    continue;
                }

            }
            System.out.println(newPosition);
            if (isInCheck(newPosition, board) != null) {
                it.remove();
                continue;
            }
        }
        if (possible_moves.isEmpty())
            return false;
        return true;
    }

    public boolean canCastleShort(Board board){
        if(isInCheck(current_position,board) != null)
            return false;
        if(color == "white") {
            if (board.object_matrix[0][7] != null && board.object_matrix[0][7].getClass().getName().equals("Rook")
                    && ((Rook) (board.object_matrix[0][7])).first_move == 1) {
                for (int i = 5; i <= 6; i++) {
                    if (board.object_matrix[0][i] != null)
                        return false;
                    if (isInCheck((char)(i + 'a') + "" + '1', board) != null)
                        return false;
                }
                return true;
            }
        }
        else if(color == "black"){
            if (board.object_matrix[7][7] != null && board.object_matrix[7][7].getClass().getName().equals("Rook")
                    && ((Rook) (board.object_matrix[7][7])).first_move == 1) {
                for (int i = 5; i <= 6; i++) {
                    if (board.object_matrix[7][i] != null)
                        return false;
                    if (isInCheck((char) (i + 'a') + "" + '8', board) != null)
                        return false;
                }
                return true;
            }
        }
        return false;
    }
    public boolean canCastleLong(Board board){
        if(isInCheck(current_position,board) != null)
            return false;
        if(color == "white") {
            if (board.object_matrix[0][0] != null && board.object_matrix[0][0].getClass().getName().equals("Rook")
                    && ((Rook) (board.object_matrix[0][0])).first_move == 1) {
                for (int i = 1; i <= 3; i++) {
                    if (board.object_matrix[0][i] != null)
                        return false;
                    if (isInCheck((char) (i + 'a') + "" + '1', board) != null)
                        return false;
                }
                return true;
            }
        } else if(color == "black"){
            if (board.object_matrix[7][0] != null && board.object_matrix[7][0].getClass().getName().equals("Rook")
                    && ((Rook) (board.object_matrix[7][0])).first_move == 1) {
                for (int i = 1; i <= 3; i++) {
                    if (board.object_matrix[7][i] != null)
                        return false;
                    if (isInCheck((char) (i + 'a') + "" + '8', board) != null)
                        return false;
                }
                return true;
            }
        }
        return false;
    }
    public Vector<KingMoves> movesForKing(Board board) {
        Vector<KingMoves> array = new Vector<>();
        array.add(new KingMoves(0,1));
        array.add(new KingMoves(0,-1));
        array.add(new KingMoves(-1,0));
        array.add(new KingMoves(1,0));
        array.add(new KingMoves(1,1));
        array.add(new KingMoves(-1,-1));
        array.add(new KingMoves(1,-1));
        array.add(new KingMoves(-1,1));

        if(color == "white" && first_move == 1 && canCastleShort(board)){
            array = new Vector<>();//etapa2
            array.add(new KingMoves(0, 2));
        } else if(color == "black" && first_move == 1 && canCastleShort(board)){
            array = new Vector<>();//etapa2
            array.add(new KingMoves(0, 2));
        }
        if(color == "white" && first_move == 1 && canCastleLong(board)){
            array = new Vector<>();//etapa2
            array.add(new KingMoves(0, -2));
        } else if(color == "black" && first_move == 1 && canCastleLong(board)){
            array = new Vector<>();//etapa2
            array.add(new KingMoves(0, -2));
        }
        return array;
    }

    public String move(Board board) {
        if(!canMove(board))
            return "";
        String aux = current_position;
        String new_position = "";
        int linie = board.pos_to_indexes(this.current_position).get(1);
        int coloana = board.pos_to_indexes(this.current_position).get(0);

        Random random = new Random();
        int index = random.nextInt(possible_moves.size());
        linie += possible_moves.get(index).linie;
        coloana += possible_moves.get(index).coloana;
        new_position = (char)(coloana + 'a') + "" + (char)(linie + '1');
        board.move(current_position+""+new_position);
        first_move = 0;
        return aux+""+new_position;
    }
    public boolean outOfBounds(int index1, int index2) {
        return !(index1 > 7 || index2> 7 || index1 < 0 || index2 < 0);

    }

    ArrayList<String> generateMoves(Board board) {
        ArrayList<String> legalMoves = new ArrayList<>();
        if(!canMove(board))
            return legalMoves;
        for(KingMoves m : possible_moves) {
            int linie = board.pos_to_indexes(this.current_position).get(1);
            int coloana = board.pos_to_indexes(this.current_position).get(0);
            linie += m.linie;
            coloana += m.coloana;
            String new_position = (char)(coloana + 'a') + "" + (char)(linie + '1');
            legalMoves.add(current_position + new_position);
        }
        return legalMoves;
    }

}