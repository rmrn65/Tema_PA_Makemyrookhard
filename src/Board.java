import java.util.ArrayList;
import java.util.HashSet;

public class Board {
    Piece[][] object_matrix;
    StringBuilder lastMove;
    Rook R1,R2,r1,r2;
    Night N1,N2,n1,n2;
    Bishop B1, B2,b1,b2;
    King K,k;
    Queen Q,q;
    Pawn P1, P2, P3, P4, P5, P6, P7, P8,p1,p2,p3,p4,p5,p6,p7,p8;
    ArrayList<Piece> whitePieces;
    ArrayList<Piece> blackPieces;
    public Board(){
        init_pieces();
        //tabla de obiecte
        object_matrix = new Piece[][]{{R1,N1,B1,Q,K,B2,N2,R2},
                {P1,P2,P3,P4,P5,P6,P7,P8},
                {null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null},
                {p1,p2,p3,p4,p5,p6,p7,p8},
                {r1,n1,b1,q,k,b2,n2,r2}};
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        for(int i = 0; i <= 1; i++) {
            for(int j = 0; j <= 7; j++) {
                whitePieces.add(object_matrix[i][j]);
            }
        }
        for(int i = 6; i <= 7; i++) {
            for(int j = 0; j <= 7; j++) {
                blackPieces.add(object_matrix[i][j]);
            }
        }
    }
    //initializez toate piesele pentru ambele culori
    public void init_pieces(){
        P1 = new Pawn("a2","white");
        P2 = new Pawn("b2","white");
        P3 = new Pawn("c2","white");
        P4 = new Pawn("d2","white");
        P5 = new Pawn("e2","white");
        P6 = new Pawn("f2","white");
        P7 = new Pawn("g2","white");
        P8 = new Pawn("f2","white");

        p1 = new Pawn("a7","black");
        p2 = new Pawn("b7","black");
        p3 = new Pawn("c7","black");
        p4 = new Pawn("d7","black");
        p5 = new Pawn("e7","black");
        p6 = new Pawn("f7","black");
        p7 = new Pawn("g7","black");
        p8 = new Pawn("h7","black");

        R1 = new Rook("a1", "white", this);
        R2 = new Rook("h1", "white", this);
        N1 = new Night("b1", "white");
        N2 = new Night("g1", "white");
        B1 = new Bishop("c1", "white");
        B2 = new Bishop("f1", "white");
        K = new King("white");
        Q = new Queen("d1", "white");

        r1 = new Rook("a8", "black", this);
        r2 = new Rook("h8", "black", this);
        n1 = new Night("b8","black");
        n2 = new Night("g8","black");
        b1 = new Bishop("c8", "black");
        b2 = new Bishop("f8", "black");
        k = new King("black");
        q = new Queen("d8","black");

    }
    public void move(String squares){
        //squares[0:1] -> starting square
        String starting_square = squares.substring(0,2);
        //squares[2:3] -> destination square
        String dest_square = squares.substring(2,4);
        lastMove = new StringBuilder(squares);
        Piece takenPiece = object_matrix[dest_square.charAt(1)-'1'][dest_square.charAt(0)-'a'];
        if(takenPiece != null) {
            if(takenPiece.color.compareTo("black") == 0)
                blackPieces.remove(takenPiece);
            else
                whitePieces.remove(takenPiece);
        }
        object_matrix[starting_square.charAt(1)-'1'][starting_square.charAt(0)-'a'].current_position = dest_square;
        object_matrix[dest_square.charAt(1)-'1'][dest_square.charAt(0)-'a'] = object_matrix[starting_square.charAt(1)-'1'][starting_square.charAt(0)-'a'];
        object_matrix[starting_square.charAt(1)-'1'][starting_square.charAt(0)-'a'] = null;
    }
    public void moveEnPassant(String squares, String color){
        int x;
        if(color.equals("white")) {
            x = 1;
        }
        else {
            x = -1;
        }
        //squares[0:1] -> starting square
        String starting_square = squares.substring(0,2);
        //squares[2:3] -> destination square
        String dest_square = squares.substring(2,4);
        lastMove = new StringBuilder(squares);
        Piece takenPiece = object_matrix[dest_square.charAt(1)-'1' - x][dest_square.charAt(0)-'a'];
        if(takenPiece != null) {
            if(takenPiece.color.compareTo("black") == 0)
                blackPieces.remove(takenPiece);
            else
                whitePieces.remove(takenPiece);
        }
        object_matrix[starting_square.charAt(1)-'1'][starting_square.charAt(0)-'a'].current_position = dest_square;
        object_matrix[dest_square.charAt(1)-'1'][dest_square.charAt(0)-'a'] = object_matrix[starting_square.charAt(1)-'1'][starting_square.charAt(0)-'a'];
        object_matrix[starting_square.charAt(1)-'1'][starting_square.charAt(0)-'a'] = null;
        object_matrix[dest_square.charAt(1) - '1' - x][dest_square.charAt(0) - 'a'] = null;
    }
    public ArrayList<Integer> pos_to_indexes(String pos){
        ArrayList<Integer> coordinates = new ArrayList<Integer>();
        coordinates.add(pos.charAt(0) - 'a');
        coordinates.add(pos.charAt(1) - '1');
        return coordinates;
    }

    public void printBoard() {
        for(int i = 0; i < object_matrix.length; i++) {
            System.out.print("|");
            for(int j = 0; j < object_matrix[0].length; j++) {
                if (object_matrix[i][j] != null)
                    System.out.print(" " + object_matrix[i][j].getClass().getName() + " " + "|");
                else System.out.print(" " + " " + " " + "|");
            }
            System.out.println("");
        }
    }

    public static String indexes_to_pos(int line,int row){
        char[] pos=new char[2];
        pos[0]=(char)(row+'a');
        pos[1]=(char)(line+'1');
        return new String(pos);
    }
    public Piece getSquare(int line,int row){
        return object_matrix[line][row];
    }

    public ArrayList<Piece> getMovePieces(ArrayList<Piece> piecesList) {
        ArrayList<Piece> ans = new ArrayList<>();
        for(Piece p : piecesList) {
            System.out.print(p.getClass().getName() + ", ");
            if(p.canMove(this)) {
                ans.add(p);
            }
        }
        System.out.println();
        return ans;
    }
}