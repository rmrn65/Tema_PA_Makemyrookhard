import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
public class Search {
    public abPruningResult alphaBeta(Board board, int depth, double alpha, double beta) {
    //TODO  if(depth == 0 || board.isCheckMate() || board.isDraw())
    //          return new abPruningResult(Evaluate(board), "");
        if(depth==0)
            return new abPruningResult(Evaluate(board,board.colorToMove), "");
        else
            if(board.colorToMove.compareTo("white") == 0) {
                String bestMove = "";
                for(String move : board.getAllLegalMoves()) {
                    abPruningResult nextRes = alphaBeta(board.simulateMove(move), depth - 1, alpha, beta);
                    double score = nextRes.eval;
                    if(score > alpha) {
                        alpha = score;
                        bestMove = move;
                        if(alpha >= beta)
                            break;
                    }
                }
                return new abPruningResult(alpha, bestMove);
            }
            else {
                String bestMove = "";
                for(String move : board.getAllLegalMoves()) {
                    abPruningResult nextRes = alphaBeta(board.simulateMove(move), depth - 1, alpha, beta);
                    double score = nextRes.eval;
                    if(score < beta) {
                        beta = score;
                        bestMove = move;
                        if(alpha >= beta)
                            break;
                    }
                }
                return new abPruningResult(beta, bestMove);
            }
    }
    public double Evaluate(Board board,String myColor){
        ArrayList<Piece> myPieces=new ArrayList<Piece>(),enemyPieces=new ArrayList<Piece>();
        if(myColor.equals("white")){
            myPieces=board.WhitePieces;
            enemyPieces=board.BlackPieces;
        }else{
            myPieces=board.BlackPieces;
            enemyPieces=board.WhitePieces;
        }
        double myMaterial=myPieces.stream().mapToDouble(piece -> piece.value).sum();
        double theirMaterial=enemyPieces.stream().mapToDouble(piece -> piece.value).sum();
        double materialCost=myMaterial-theirMaterial;
        for(Piece piece: myPieces){
            System.out.println(piece.getClass().getName()+" "+piece.value);
        }
        System.out.println("my material "+myMaterial+ " their material "+ theirMaterial );
        double positionalCost=myPieces.stream().mapToDouble(piece -> IndividualCost(board,piece)).sum()-enemyPieces.stream().mapToDouble(piece ->  IndividualCost(board,piece)).sum();
        return materialCost+ positionalCost;
    }

       public double IndividualCost(Board board,Piece myPiece){
           int posX, posY;
           Integer range = 0;
           posX = board.pos_to_indexes(myPiece.current_position).get(0);
           posY = board.pos_to_indexes(myPiece.current_position).get(1);
           double protectionCost;
           double protectionFactor = 0.2;
           double rangeCost;
           double rangeFactor = 0.01;
           BufferedWriter bw;
           try{
                bw=new BufferedWriter(new FileWriter("cost.txt"));
                bw.append(myPiece.color);
                bw.flush();
           }catch(IOException e){
               e.printStackTrace();
           }
           ArrayList<Piece> defending = new ArrayList<Piece>();
           ArrayList<Piece> attacking = new ArrayList<Piece>();
           class Helper {
               boolean reachedPiece = false;
               final Piece myPiece;
               Integer range;
               public Helper(Piece myPiece,Integer range) {
                   this.myPiece = myPiece;
                   this.range=range;
               }

               void iterateLine(Direction dir) {
                   int i,j;
                   boolean checkPawnAttack = false, checkPawnDefend = false;
                   Piece currentPiece = board.object_matrix[posX][posY];
                   List<Integer> pos = dir.DirToOffset(myPiece.color);
                   int offsetX = pos.get(0);
                   int offsetY = pos.get(1);
                   if (dir.compareTo(Direction.DIAG_UPLEFT)==0 || dir.compareTo(Direction.DIAG_UPRIGHT)==0)
                       checkPawnAttack = true;
                   if (dir.compareTo(Direction.DIAG_DOWNLEFT)==0 || dir.compareTo(Direction.DIAG_DOWNRIGHT)==0)
                       checkPawnDefend = true;
                   for (i = posX + offsetX, j = posY + offsetY; inBounds(i, j); i = i + offsetX, j = j + offsetY) {
                       if (currentPiece != null) {
                           reachedPiece = true;
                           if (currentPiece.color.equals(myPiece.color)) {
                               if (checkPawnDefend && (currentPiece instanceof Pawn)) {
                                   checkPawnDefend = false;
                                   defending.add(currentPiece);
                                   continue;
                               }
                               if (dir.CheckMovement(currentPiece))
                                   defending.add(currentPiece);
                               else
                                   break;
                           } else {
                               if (checkPawnAttack && (currentPiece instanceof Pawn)) {
                                   checkPawnAttack = false;
                                   attacking.add(currentPiece);
                                   continue;
                               }
                               if (dir.CheckMovement(currentPiece))
                                   attacking.add(currentPiece);
                               else
                                   break;
                               defending.add(myPiece);
                           }
                       } else {
                           if (!reachedPiece) {
                               if (dir.CheckMovement(myPiece))
                                   range++;
                           } else
                               break;
                       }
                   }
               }

               void computeKnightPos(int X, int Y) {
                   if (!inBounds(X, Y))
                       return;
                    System.out.print(X+""+Y);
                    Piece currentPiece = board.object_matrix[X][Y];
                    if (currentPiece != null) {
                       if (currentPiece instanceof Night) {
                           if (currentPiece.color.equals(myPiece.color))
                               defending.add(currentPiece);
                           else
                               attacking.add(currentPiece);
                       }
                   } else {
                       if (myPiece instanceof Night)
                           range++;
                   }
               }
           }
           Helper helper = new Helper(myPiece,range);
           helper.iterateLine(Direction.DIAG_UPLEFT);
           helper.iterateLine(Direction.DIAG_UPRIGHT);
           helper.iterateLine(Direction.DIAG_DOWNLEFT);
           helper.iterateLine(Direction.DIAG_DOWNRIGHT);
           helper.iterateLine(Direction.RIGHT);
           helper.iterateLine(Direction.LEFT);
           helper.iterateLine(Direction.UP);
           helper.iterateLine(Direction.DOWN);
           helper.computeKnightPos(posX + 2, posY + 1);
           helper.computeKnightPos(posX + 2, posY - 1);
           helper.computeKnightPos(posX - 2, posY + 1);
           helper.computeKnightPos(posX - 2, posY - 1);
           helper.computeKnightPos(posX + 1, posY + 2);
           helper.computeKnightPos(posX + 1, posY - 2);
           helper.computeKnightPos(posX - 1, posY + 2);
           helper.computeKnightPos(posX - 1, posY - 2);

           attacking.sort((Piece p1, Piece p2) -> ((Double)p1.value).compareTo(p2.value));
           defending.sort((Piece p1, Piece p2) ->  ((Double)p1.value).compareTo(p2.value));
           defending.add(0, myPiece);
           if (attacking.size() > defending.size())
               attacking.subList(0,defending.size());
           else
               defending.subList(0,attacking.size());
           protectionCost = protectionFactor * (attacking.stream().mapToDouble(piece -> piece.value).sum() - defending.stream().mapToDouble(piece -> piece.value).sum());
           rangeCost = range * rangeFactor * myPiece.value;
           return protectionCost + rangeCost;

       }
       enum Direction {
           LEFT, RIGHT, UP, DOWN, DIAG_UPLEFT, DIAG_UPRIGHT, DIAG_DOWNLEFT, DIAG_DOWNRIGHT;

           public ArrayList<Integer> DirToOffset(String color) {
               ArrayList<Integer> pos = new ArrayList<Integer>();
               int x = 0, y = 0;
               switch (this) {
                   case LEFT -> x = -1;
                   case RIGHT -> x = 1;
                   case DOWN -> y = -1;
                   case UP -> y = 1;
                   case DIAG_DOWNLEFT -> {
                       x = -1;
                       y = -1;
                   }
                   case DIAG_DOWNRIGHT -> {
                       x = 1;
                       y = -1;
                   }
                   case DIAG_UPLEFT -> {
                       x = -1;
                       y = 1;
                   }
                   case DIAG_UPRIGHT -> {
                       y = 1;
                       x = 1;
                   }
               }
               if (color.equals("black")) {
                   x = -x;
                   y = -y;
               }
               pos.add(x);
               pos.add(y);
               return pos;
           }

           public Boolean CheckMovement(Piece piece) {
               return switch (this) {
                   case DIAG_UPLEFT, DIAG_UPRIGHT, DIAG_DOWNLEFT, DIAG_DOWNRIGHT -> (piece instanceof Bishop) || (piece instanceof Queen);
                   case LEFT, RIGHT, UP, DOWN -> (piece instanceof Rook) || (piece instanceof Queen);
               };
           }
       }

    Boolean inBounds ( int X, int Y){
        return (X >= 0 && X < 8 && Y >= 0 && Y < 8);
    }
}
class abPruningResult {
    double eval;
    String bestPosition;
    public abPruningResult(double eval, String bestPosition) {
        this.eval = eval;
        this.bestPosition = bestPosition;
    }
}

