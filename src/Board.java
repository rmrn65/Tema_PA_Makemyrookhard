import java.util.ArrayList;

public class Board {
    Piece[][] object_matrix;
    Rook R1,R2,r1,r2;
    Night N1,N2,n1,n2;
    Bishop B1, B2,b1,b2;
    King K,k;
    Queen Q,q;
    Pawn P1, P2, P3, P4, P5, P6, P7, P8,p1,p2,p3,p4,p5,p6,p7,p8;
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

        R1 = new Rook("white");
        R2 = new Rook("white");
        N1 = new Night("white");
        N2 = new Night("white");
        B1 = new Bishop("c1", "white");
        B2 = new Bishop("f1", "white");
        K = new King("white");
        Q = new Queen("white");

        r1 = new Rook("black");
        r2 = new Rook("black");
        n1 = new Night("black");
        n2 = new Night("black");
        b1 = new Bishop("c8", "black");
        b2 = new Bishop("f8", "black");
        k = new King("black");
        q = new Queen("black");

    }
    public void move(String squares){
        //squares[0:1] -> starting square
        String starting_square = squares.substring(0,2);
        //squares[2:3] -> destination square
        String dest_square = squares.substring(2,4);
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
}
