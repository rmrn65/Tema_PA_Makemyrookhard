public class Move implements Comparable<Move> {
    String move;
    Float value;
    public Move(String move,float value){
        this.move=move;
        this.value=value;
    }
    @Override
    public int compareTo(Move move_compared) {
        if(value>move_compared.value)
            return -1;
        return 1;
    }
}
