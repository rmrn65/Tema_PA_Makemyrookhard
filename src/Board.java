import java.util.ArrayList;

public class Board {
    char matrix[][];
    StringBuilder lastMove;
    Piece object_matrix[][];
    Rook R1,R2,r1,r2;
    Night N1,N2,n1,n2;
    Bishop B1, B2,b1,b2;
    King K,k;
    Queen Q,q;
    Pawn P1, P2, P3, P4, P5, P6, P7, P8,p1,p2,p3,p4,p5,p6,p7,p8;
    ArrayList<Piece> WhitePieces = new ArrayList<>();
    ArrayList<Piece> BlackPieces = new ArrayList<>();
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
        for(int i = 0 ; i <=1 ; i ++){
            for( int j = 0; j <= 7; j++ )
                WhitePieces.add(object_matrix[i][j]);

        }
        for(int i = 6 ; i <= 7 ; i ++){
            for( int j = 0; j <= 7; j++ )
                BlackPieces.add(object_matrix[i][j]);
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
        P8 = new Pawn("h2","white");

        p1 = new Pawn("a7","black");
        p2 = new Pawn("b7","black");
        p3 = new Pawn("c7","black");
        p4 = new Pawn("d7","black");
        p5 = new Pawn("e7","black");
        p6 = new Pawn("f7","black");
        p7 = new Pawn("g7","black");
        p8 = new Pawn("h7","black");

        R1 = new Rook("a1","white");
        R2 = new Rook("h1","white");
        N1 = new Night("b1","white");
        N2 = new Night("g1","white");
        B1 = new Bishop("c1","white");
        B2 = new Bishop("f1","white");
        K = new King("e1","white");
        Q = new Queen("d1","white");


        r1 = new Rook("a8","black");
        r2 = new Rook("h8","black");
        n1 = new Night("b8","black");
        n2 = new Night("g8","black");
        b1 = new Bishop("c8","black");
        b2 = new Bishop("f8","black");
        k = new King("e8","black");
        q = new Queen("d8","black");

    }
    public void move(String squares){
        //squares[0:1] -> starting square
        String starting_square = squares.substring(0,2);
        //squares[2:3] -> destination square
        String dest_square = squares.substring(2,4);
        Piece current_piece = object_matrix[starting_square.charAt(1)-'1'][starting_square.charAt(0)-'a'];
        System.out.println(dest_square + "   " + starting_square);
        if(current_piece.getClass().getName().equals("Rook")){
            ((Rook) current_piece).first_move = 0;
        } else if(current_piece.getClass().getName().equals("King")){
            ((King) current_piece).first_move = 0;
        }
        if(current_piece.getClass().getName().equals("King")) {
            if (current_piece.color == "white" && dest_square.equals("g1") && starting_square.equals("e1"))
                move("h1f1");
            if (current_piece.color == "black" && dest_square.equals("g8") && starting_square.equals("e8"))
                move("h8f8");
            if (current_piece.color == "white" && dest_square.equals("c1") && starting_square.equals("e1"))
                move("a1d1");
            if (current_piece.color == "black" && dest_square.equals("c8") && starting_square.equals("e8"))
                move("a8d8");
        }
        if( object_matrix[dest_square.charAt(1)-'1'][dest_square.charAt(0)-'a'] != null &&  object_matrix[dest_square.charAt(1)-'1'][dest_square.charAt(0)-'a'].color == "white"){
            WhitePieces.remove( object_matrix[dest_square.charAt(1)-'1'][dest_square.charAt(0)-'a']);
        } else if( object_matrix[dest_square.charAt(1)-'1'][dest_square.charAt(0)-'a'] != null &&  object_matrix[dest_square.charAt(1)-'1'][dest_square.charAt(0)-'a'].color =="black" ){
            BlackPieces.remove( object_matrix[dest_square.charAt(1)-'1'][dest_square.charAt(0)-'a']);
        }

        object_matrix[starting_square.charAt(1)-'1'][starting_square.charAt(0)-'a'].current_position = dest_square;
        object_matrix[dest_square.charAt(1)-'1'][dest_square.charAt(0)-'a'] = object_matrix[starting_square.charAt(1)-'1'][starting_square.charAt(0)-'a'];
        object_matrix[starting_square.charAt(1)-'1'][starting_square.charAt(0)-'a'] = null;
    }
    public ArrayList<Integer> pos_to_indexes(String pos){
        ArrayList<Integer> coordinates = new ArrayList<Integer>();
        coordinates.add(pos.charAt(0) - 'a');
        coordinates.add(pos.charAt(1) - '1');
        return coordinates;
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
        object_matrix[starting_square.charAt(1)-'1'][starting_square.charAt(0)-'a'].current_position = dest_square;
        object_matrix[dest_square.charAt(1)-'1'][dest_square.charAt(0)-'a'] = object_matrix[starting_square.charAt(1)-'1'][starting_square.charAt(0)-'a'];
        object_matrix[starting_square.charAt(1)-'1'][starting_square.charAt(0)-'a'] = null;
        object_matrix[dest_square.charAt(1) - '1' - x][dest_square.charAt(0) - 'a'] = null;
    }
}