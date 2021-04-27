import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Board board = new Board();
        int first_move = 1;
        int start = 0, go = 0, quit = 0;
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
                    break;
                case "black":
                    myKing= board.k;
                    random_pieces = board.BlackPieces;
                    break;
                case "quit":
                    quit = 1;
                    break;
            }
            //folosim regex pentru a găsi comenzile de mutare
            if(command.matches("[a-h][1-8][a-h][1-8]") || go == 1){
                //process command
                if(go == 0) // de schimbat first_move pentru piese cand se da force
                    board.move(command);
                if(start == 1) {
                    //prima miscare
                    while(true) {
                        Random rand = new Random();
                        String aux = "";
                        if(myKing.isInCheck(myKing.current_position, board) != null) {
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

                        int index = rand.nextInt(random_pieces.size());
                        Piece rpiece = random_pieces.get(index);
                        if(rpiece.canMove(board))
                            aux = rpiece.move(board);
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
                first_move = 0;
                go = 0;
            }
            if(quit == 1)
                break;
        }
    }
}