import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Board board = new Board();
        int first_move = 1;
        int start = 0, go = 0, quit = 0;
        Search searchTool=new Search();
        String command;
//        Pawn myPawn = board.P5;
        King myKing= board.k;
        ArrayList<Piece> random_pieces = board.BlackPieces;
        while(true){
            command = input.next(); // primește input
            //tratez comanda
            switch (command){
                case "xboard":
                    break;
                case "protover":
                    System.out.println("feature sigint=0 san=0 name=HardRookie");
                    break;
                case "new":
                    start = 1;
                    board = new Board();
                    first_move = 1;
                    myKing = board.k;
                    random_pieces = board.BlackPieces;
                    board.colorToMove = "black";
                    break;
                case "force":
                    first_move = 0;
                    start = 0;
                    break;
                case "go":
                    start = 1;
                    go = 1;
                    break;
                case "white":
                    myKing= board.K;
                    random_pieces = board.WhitePieces;
                    board.colorToMove = "white";
                    break;
                case "black":
                    myKing= board.k;
                    random_pieces = board.BlackPieces;
                    board.colorToMove = "black";
                    break;
                case "quit":
                    quit = 1;
                    break;
            }
            //folosim regex pentru a găsi comenzile de mutare
            if(command.matches("[a-h][1-8][a-h][1-8]q?r?b?n?") || go == 1){
                //process command
                if(go == 0){ // de schimbat first_move pentru piese cand se da force
                    board.move(command);
                    double cost=searchTool.Evaluate(board,board.colorToMove);
                    System.out.println("costul pentru "+board.colorToMove+ " este "+cost);
                }
                if(start == 1) {
                    //prima miscare
                    while(true) {
                        String aux = "";
                        // ----- ETAPA2 -----
                        if(myKing.canMove(board)) {
                            if (myKing.first_move == 1 && myKing.canCastleShort(board)) {
                                aux = myKing.move(board);
                                System.out.println("move " + aux);
                                break;
                            } else if ( myKing.first_move == 1 && myKing.canCastleLong(board) ) {
                                aux = myKing.move(board);
                                System.out.println("move " + aux);
                                break;
                            }
                        }
                        // ------- ETAPA2 -------
                        Random rand = new Random();
                        int []array = myKing.isInCheck(myKing.current_position, board);
                        if (array != null && array[4] == 2) {
                            String rezultat = myKing.canKingDefend(board);
                            if (rezultat == null)
                                aux = myKing.move(board);
                            else {
                                board.move(rezultat);
                                aux = rezultat;
                            }

                            System.out.println("move " + aux);
                            break;
                        }
                        if (array != null && array[4] == 4) {
                            aux = myKing.move(board);
                            System.out.println("move " + aux);
                            break;
                        }

                        int index = rand.nextInt(random_pieces.size());
                        Piece rpiece = random_pieces.get(index);
                        if(rpiece.canMove(board) && myKing.canIMove(rpiece.current_position, board)) {
                            System.out.println("Piesa selectata se afla la " + rpiece.current_position);
                            aux = rpiece.move(board);
                        }
                        else
                            continue;
                        if (aux.equals(""))
                            continue;
                        else {
                            System.out.println("move " + aux);
                            break;
                        }
                    }
                }
                double cost=searchTool.Evaluate(board,board.colorToMove);
                System.out.println("costul pentru "+board.colorToMove+ " este "+cost);
                go = 0;
            }
            if(quit == 1)
                break;
        }
    input.close();
    }
}