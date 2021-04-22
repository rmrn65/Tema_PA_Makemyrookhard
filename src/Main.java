import java.util.*;
public class Main {

    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
        Board board = new Board();
        int start = 0, go = 0, quit = 0;
        String color = "black";
        String command;
        ArrayList<Piece> canMovePieces;
        do {
            command = input.next(); // primeste input
            //tratez comanda
            switch (command) {
                case "xboard":
                    break;
                case "protover":
                    System.out.println("feature sigint=0 san=0 name=HardRookie");
                    break;
                case "new":
                    start = 1;
                    board = new Board();
                    break;
                case "force":
                    start = 0;
                    break;
                case "go":
                    start = 1;
                    go = 1;
                    break;
                case "white":
                    color = "white";
                    break;
                case "black":
                    color = "black";
                    break;
                case "quit":
                    quit = 1;
                    break;
            }
            //folosim regex pentru a gasi comenzile de mutare
            if (command.matches("[a-h][1-8][a-h][1-8]q?") || go == 1) {
                //process command
                if (go == 0) {
                    board.move(command);
                }
                if (start == 1) {
                    Random rand = new Random();
                    if(color.compareTo("black") == 0)
                        canMovePieces = board.getMovePieces(board.blackPieces);
                    else
                        canMovePieces = board.getMovePieces(board.whitePieces);
                    if(canMovePieces.size() == 0)
                        System.out.println("resign");
                    Piece myPiece = canMovePieces.get(Math.abs(rand.nextInt()) % canMovePieces.size());
                    System.out.println("Piesa selectata se afla la " + myPiece.current_position);
                    System.out.println("move " + myPiece.move(board));
                }
                go = 0;
                System.out.println(board.whitePieces.size() + " " + board.blackPieces.size());
                board.printBoard();
            }
        } while (quit != 1);
    }
}
