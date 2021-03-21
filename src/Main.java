import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.*;

public class Main {
    private static final int rows = 9;
    private static  final int cols = 9;
    private static Object[][] matrix;
    private static String playas = "Black";

   public static void main(String []args){

       Scanner scanner = new Scanner(System.in);
       String message;

//       initMatrix();
       int turn = 0;

       Piece my_piece_as_black = null;
       Piece my_piece_as_white = null;
       Piece my_piece = null;
       while(true){
           message = scanner.next();
           switch (message){
               case "xboard":
                   System.out.println("feature sigint=0 san=0 name=Valoros");
                   break;
               case "force":
                   playas = makeChanges(scanner);
                   if(playas.equals("Black"))
                       my_piece = my_piece_as_black;
                   if(playas.equals("White"))
                       my_piece = my_piece_as_white;
                   if(!playas.equals("new")) {
                       makeMove(my_piece);
                       break;
                   }
               case "new":
                   initMatrix();
                   turn = 0;
                   my_piece_as_black = (Piece)matrix[7][3];
                   my_piece_as_white = (Piece)matrix[2][3];
                   my_piece = my_piece_as_black;
                   playas = "Black";
                   break;
               case "white":
                   playas = "White";
                   my_piece = my_piece_as_white;
                   if(turn == 0) {
                       makeMove(my_piece);
                       turn = 1;
                   }
                   break;
               case "black":
                   playas = "Black";
                   my_piece = my_piece_as_black;
                   if(turn == 1) {
                       makeMove(my_piece);
                       turn = 0;
                   }
                   break;

               case "quit":
                   System.exit(0);
                   break;
               default:
                   break;
           }
           if(message.matches("^[a-h][0-9][a-h][0-9]")){

               matrix[message.charAt(3)-48][message.charAt(2)-96] = matrix[message.charAt(1)-48][message.charAt(0)-96];
               matrix[message.charAt(1)-48][message.charAt(0)-96] = null;

               ((Piece)matrix[message.charAt(3)-48][message.charAt(2)-96]).row = message.charAt(3)-48;
               ((Piece)matrix[message.charAt(3)-48][message.charAt(2)-96]).col = message.charAt(2)-96;

               makeMove(my_piece);
               System.out.println(matrix[my_piece.row][my_piece.col]);

           }


       }

//        makeMove(my_piece);
//        makeMove(my_piece);
//        makeMove(my_piece);
//       makeMove(my_piece);
//       makeMove(my_piece);

//       initMatrix();
//       ((Piece)matrix[2][1]).move(matrix, "White");
//       System.out.println(matrix[3][1] + "\n" + matrix[2][1]);

   }

   public static void initMatrix() {
       matrix = new Object[rows][cols];
       for(int i = 1; i < 9; i++){
           Pawn t1 = new Pawn("White",2,i);
           Pawn t2 = new Pawn("Black",7,i);
           matrix[2][i] = t1;
           matrix[7][i] = t2;
       }
       matrix[1][1] = new Rook("White",1,1);
       matrix[1][8] = new Rook("White",1,8);
       matrix[1][2] = new Knight("White",1,2);
       matrix[1][7] = new Knight("White",1,7);
       matrix[1][3] = new Bishop("White",1,3);
       matrix[1][6] = new Bishop("White",1,6);
       matrix[1][4] = new Queen("White",1,4);
       matrix[1][5] = new King("White",1,5);
       matrix[8][1] = new Rook("Black",8,1);
       matrix[8][8] = new Rook("Black",8,8);
       matrix[8][2] = new Knight("Black",8,2);
       matrix[8][7] = new Knight("Black",8,7);
       matrix[8][3] = new Bishop("Black",8,3);
       matrix[8][6] = new Bishop("Black",8,6);
       matrix[8][4] = new Queen("Black",8,4);
       matrix[8][5] = new King("Black",8,5);

   }
   public static String makeChanges(Scanner sc){
       String message = sc.next();
       String color = playas;
       while(true){
           if(message.contains("go"))
               break;
           if(message.contains("new")){
               return "new";
           }
           if(message.equals("black"))
               color = "Black";
           if(message.equals("white"))
               color = "White";

           if(message.matches("^[a-h][0-9][a-h][0-9]")){
               matrix[message.charAt(3) - 48][message.charAt(2) - 96] = matrix[message.charAt(1) - 48][message.charAt(0) - 96];
               matrix[message.charAt(1) - 48][message.charAt(0) - 96] = null;

               ((Piece) matrix[message.charAt(3) - 48][message.charAt(2) - 96]).row = message.charAt(3) - 48;
               ((Piece) matrix[message.charAt(3) - 48][message.charAt(2) - 96]).col = message.charAt(2) - 96;
           }
           message = sc.next();
       }
       return color;

   }
//   public static void matrixChange(String message){
//       ((Piece)matrix[message.charAt(3)-48][message.charAt(2)-96]).row = (message.charAt(3)-48);
//       ((Piece)matrix[message.charAt(3)-48][message.charAt(2)-96]).col = (message.charAt(2)-96);
//   }
   public static void makeMove(Piece piece){
       piece.move(matrix,playas);
   }
}
