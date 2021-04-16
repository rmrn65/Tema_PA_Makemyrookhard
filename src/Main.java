import java.util.*;
public class Main {

    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
        Board board = new Board();
        int first_move = 1;
        int start = 0, go = 0, quit = 0;
        String command;
        Bishop myBishop = board.B1;
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
                    first_move = 1;
                    myBishop = board.B1;
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
                    myBishop = board.B1;
                    break;
                case "black":
                    myBishop = board.b1;
                    break;
                case "quit":
                    quit = 1;
                    break;
            }
            //folosim regex pentru a gasi comenzile de mutare
            if (command.matches("[a-h][1-8][a-h][1-8]q?") || go == 1) {
                //process command
                if (go == 0)
                    board.move(command);
                if (start == 1) {
                    myBishop.generateMoves(board);
                    if(myBishop.canMove(board)) {
                        System.out.println("move " + myBishop.move(board));
                    }
                }
                first_move = 0;
                go = 0;
            }
        } while (quit != 1);
    }
}
