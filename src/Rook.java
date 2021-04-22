import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Rook extends Piece{
    List<String> possibleMoves=new ArrayList<String>();
    public Rook(String current_position,String color){
        value = 5;
        this.current_position=current_position;
        this.color = color;
        List<Integer> positions=Board.pos_to_indexes(current_position);
        this.row=positions.get(0);
        this.line=positions.get(1);
    }

    public String Move(Board board){
        Random random=new Random();
        String move = possibleMoves.get(Math.abs(random.nextInt()%(possibleMoves.size())));
        board.move(move);
        possibleMoves.clear();
        return move;
    }
    public boolean canMove(Board board){
        int i;
        class moveAdder{
            boolean addMove(int finishLine,int finishRow){
                Piece piece=board.getSquare(finishLine,finishRow);
                if(piece!=null){
                    if(!piece.color.equals(color))
                        possibleMoves.add(Board.indexes_to_pos(line,row)+Board.indexes_to_pos(finishLine,finishRow));
                    return true;
                }
                possibleMoves.add(Board.indexes_to_pos(line,row)+Board.indexes_to_pos(finishLine,finishRow));
                return false;
            }
        }
        moveAdder adder=new moveAdder();
        for (i=line+1;i<8;i++){
            if(adder.addMove(i,row))
                break;
        }
        for (i=line-1;i>=0;i--){
            if(adder.addMove(i,row))
                break;
        }
        for (i=row+1;i<8;i++){    
            if(adder.addMove(line,i))
                break;
        }
        for (i=row-1;i>=0;i--){
            if(adder.addMove(line,i))
                break;
        }
        return ( possibleMoves.size() > 0);
    }
}
