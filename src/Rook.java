import java.util.ArrayList;
import java.util.List;
public class Rook extends Piece{
    public Rook(String current_position,String color,Board board){
        value = 5;
        this.board=board;
        this.current_position=current_position;
        this.color = color;
        List<Integer> positions=Board.pos_to_indexes(current_position);
        this.row=positions.get(0);
        this.line=positions.get(1);
    }
    public boolean canMove(){
        return (possibleMoves().size()==0);
    }
    public List<Move> possibleMoves(){
        List<Move> moves=new ArrayList<Move>();
        int i;
        class moveAdder{
            boolean addMove(int finishLine,int finishRow){
                Piece piece=board.getSquare(finishLine,finishRow);
                if(piece!=null){
                    if(!piece.color.equals(color))
                        moves.add(new Move(Board.indexes_to_pos(line,row)+Board.indexes_to_pos(finishLine,finishRow),piece.value));
                    return true;
                }
                moves.add(new Move(Board.indexes_to_pos(line,row)+Board.indexes_to_pos(finishLine,finishRow),0));
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
        return moves;
    }
}
