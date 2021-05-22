public class Search {
    public abPruningResult alphaBeta(Board board, int depth, float alpha, float beta) {
        if(depth == 0 || board.isCheckMate() || board.isDraw())
            return new abPruningResult(evaluate(board), "");
        else
            if(board.colorToMove.compareTo("white") == 0) {
                String bestMove = "";
                for(String move : board.getAllLegalMoves()) {
                    abPruningResult nextRes = alphaBeta(board.simulateMove(move), depth - 1, alpha, beta);
                    float score = nextRes.eval;
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
                    float score = nextRes.eval;
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
}

class abPruningResult {
    float eval;
    String bestPosition;
    abPruningResult(float eval, String bestPosition) {
        this.eval = eval;
        this.bestPosition = bestPosition;
    }
}

