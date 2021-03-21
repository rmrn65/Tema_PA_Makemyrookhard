import java.util.*;
public class Main {

    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
        //int turn = 0; //0 = my turn ; 1 = cpu turn
        Board board = new Board();
        int first_move = 1;
        int start = 0, go = 0, quit = 0;
        String command;
        Pawn myPawn = board.P5;
        while(true){
            command = input.next();
            switch (command){
                case "xboard":
                    break;
                case "protover":
                    System.out.println("feature sigint=1 san=0 name=HardRookie");
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
            if(command.matches("[a-h][1-8][a-h][1-8]") || go == 1){
                //process command
                if(go == 0)
                    board.move(command);
                if(start == 1) {
                    if (first_move == 1) {
                        first_move = 0;
                        System.out.println("move " + myPawn.move_forward(1, board));
                    } else if(myPawn.taken(board) || myPawn.current_position.charAt(1) == '8')
                        System.out.println("resign");
                    else if (myPawn.can_take_left(board)) {
                        System.out.println("move " + myPawn.take_left(board));
                    } else if (myPawn.can_take_right(board)) {
                        System.out.println("move " + myPawn.take_right(board));
                    } else if (myPawn.can_move_forward(board)) {
                        System.out.println("move " + myPawn.move_forward(0, board));
                    }
                    else
                        System.out.println("resign");
                }
                first_move = 0;
                go = 0;
            }
//        for(int i = 0; i < 8 ; i ++){
//            for(int j = 0 ; j < 8; j ++){
//                System.out.print(board.matrix[i][j] + "| |");
//            }
//            System.out.println();
//        }
            if(quit == 1)
                break;
        }
    }
}
