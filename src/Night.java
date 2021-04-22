import java.util.*;

public class Night extends Piece {
    int value;
    Vector<NightMoves> possible_moves;
    class NightMoves {
        int linie, coloana;

        public NightMoves(int linie, int coloana) {
            this.linie = linie;
            this.coloana = coloana;
        }
    }


    public Night(String current_position, String color){
        value = 3;
        this.color = color;
        this.current_position = current_position;
    }
    public Boolean canMove(Board board) {
        possible_moves = movesForNight();
        int linie = board.pos_to_indexes(this.current_position).get(1);
        int coloana = board.pos_to_indexes(this.current_position).get(0);

        int linie_aux;
        int coloana_aux;
        ListIterator<NightMoves> it = possible_moves.listIterator();
        while(it.hasNext()) {

            NightMoves aux = it.next();
            linie_aux = linie;
            coloana_aux = coloana;
            linie_aux += aux.linie;
            coloana_aux += aux.coloana;

            if(linie_aux > 7 || coloana_aux > 7 || linie_aux < 0 || coloana_aux < 0) {
                it.remove();
                continue;
            }
            if(board.object_matrix[linie_aux][coloana_aux] != null) {
                if(board.object_matrix[linie_aux][coloana_aux].color.equals(this.color)){
                    it.remove();
                    continue;
                }
                if(board.object_matrix[linie_aux][coloana_aux].getClass().getName().equals("King")) {
                    it.remove();
                }
            }
        }
        if (possible_moves.isEmpty())
            return false;
        return true;
    }
    public Vector<NightMoves> movesForNight() {
        Vector<NightMoves> array = new Vector<>();
        array.add(new NightMoves(2,1));
        array.add(new NightMoves(2,-1));
        array.add(new NightMoves(-2,1));
        array.add(new NightMoves(-2,-1));
        array.add(new NightMoves(1,-2));
        array.add(new NightMoves(-1,-2));
        array.add(new NightMoves(1,2));
        array.add(new NightMoves(-1,2));

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


        return aux+""+new_position;
    }
}
