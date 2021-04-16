import java.util.*;
public class Main {

    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
        Board board = new Board();
        int first_move = 1;
        int start = 0, go = 0, quit = 0;
        String command;
        Pawn myPawn = board.P5;
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
                    myPawn = board.p5;
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
                    myPawn = board.P5;
                    break;
                case "black":
                    myPawn = board.p5;
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
                    //prima miscare
                    if (first_move == 1) {
                        first_move = 0;
                        System.out.println("move " + myPawn.move_forward(1, board));
                    } else if (myPawn.taken(board))
                        System.out.println("resign");
                    else if (myPawn.can_take_left(board)) {
                        System.out.println("move " + myPawn.take_left(board));
                    } else if (myPawn.can_take_right(board)) {
                        System.out.println("move " + myPawn.take_right(board));
                    } else if (myPawn.can_move_forward(board)) {
                        System.out.println("move " + myPawn.move_forward(0, board));
                    } else
                        System.out.println("resign");
                }
                first_move = 0;
                go = 0;
            }
        } while (quit != 1);
    }
}
