import java.util.ArrayList;

public class Board {
    char matrix[][];
    StringBuilder lastMove;
    Piece object_matrix[][];
    Rook R1, R2, r1, r2;
    Night N1, N2, n1, n2;
    Bishop B1, B2, b1, b2;
    King K, k;
    Queen Q, q;
    Pawn P1, P2, P3, P4, P5, P6, P7, P8, p1, p2, p3, p4, p5, p6, p7, p8;
    ArrayList<Piece> WhitePieces = new ArrayList<>();
    ArrayList<Piece> BlackPieces = new ArrayList<>();

    public Board() {
        init_pieces();
        // tabla de obiecte
        object_matrix = new Piece[][] { { R1, N1, B1, Q, K, B2, N2, R2 }, { P1, P2, P3, P4, P5, P6, P7, P8 },
                { null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
                { null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
                { p1, p2, p3, p4, p5, p6, p7, p8 }, { r1, n1, b1, q, k, b2, n2, r2 } };
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 7; j++)
                WhitePieces.add(object_matrix[i][j]);

        }
        for (int i = 6; i <= 7; i++) {
            for (int j = 0; j <= 7; j++)
                BlackPieces.add(object_matrix[i][j]);
        }
    }

    // initializez toate piesele pentru ambele culori
    public void init_pieces() {
        P1 = new Pawn("a2", "white");
        P2 = new Pawn("b2", "white");
        P3 = new Pawn("c2", "white");
        P4 = new Pawn("d2", "white");
        P5 = new Pawn("e2", "white");
        P6 = new Pawn("f2", "white");
        P7 = new Pawn("g2", "white");
        P8 = new Pawn("h2", "white");

        p1 = new Pawn("a7", "black");
        p2 = new Pawn("b7", "black");
        p3 = new Pawn("c7", "black");
        p4 = new Pawn("d7", "black");
        p5 = new Pawn("e7", "black");
        p6 = new Pawn("f7", "black");
        p7 = new Pawn("g7", "black");
        p8 = new Pawn("h7", "black");

        R1 = new Rook("a1", "white");
        R2 = new Rook("h1", "white");
        N1 = new Night("b1", "white");
        N2 = new Night("g1", "white");
        B1 = new Bishop("c1", "white");
        B2 = new Bishop("f1", "white");
        K = new King("e1", "white");
        Q = new Queen("d1", "white");

        r1 = new Rook("a8", "black");
        r2 = new Rook("h8", "black");
        n1 = new Night("b8", "black");
        n2 = new Night("g8", "black");
        b1 = new Bishop("c8", "black");
        b2 = new Bishop("f8", "black");
        k = new King("e8", "black");
        q = new Queen("d8", "black");

    }

    public void move(String squares) {
        if (object_matrix[squares.charAt(1) - '1'][squares.charAt(0) - 'a'] instanceof Pawn
                && squares.charAt(0) != squares.charAt(2)
                && object_matrix[squares.charAt(3) - '1'][squares.charAt(2) - 'a'] == null) {
            System.out.println("S-a mutat enPassant");
            moveEnPassant(squares, object_matrix[squares.charAt(1) - '1'][squares.charAt(0) - 'a'].color);
            return;
        }
        lastMove = new StringBuilder(squares);
        // squares[0:1] -> starting square
        String starting_square = squares.substring(0, 2);
        // squares[2:3] -> destination square
        String dest_square = squares.substring(2, 4);
        Piece current_piece = object_matrix[starting_square.charAt(1) - '1'][starting_square.charAt(0) - 'a'];
        System.out.println(dest_square + "   " + starting_square);
        if (current_piece.getClass().getName().equals("Rook")) {
            ((Rook) current_piece).first_move = 0;
        } else if (current_piece.getClass().getName().equals("King")) {
            ((King) current_piece).first_move = 0;
        }
        if (current_piece.getClass().getName().equals("King")) {
            if (current_piece.color.equals("white") && dest_square.equals("g1") && starting_square.equals("e1"))
                move("h1f1");
            if (current_piece.color.equals("black") && dest_square.equals("g8") && starting_square.equals("e8"))
                move("h8f8");
            if (current_piece.color.equals("white") && dest_square.equals("c1") && starting_square.equals("e1"))
                move("a1d1");
            if (current_piece.color.equals("black") && dest_square.equals("c8") && starting_square.equals("e8"))
                move("a8d8");
        }
        if (object_matrix[dest_square.charAt(1) - '1'][dest_square.charAt(0) - 'a'] != null
                && object_matrix[dest_square.charAt(1) - '1'][dest_square.charAt(0) - 'a'].color == "white") {
            WhitePieces.remove(object_matrix[dest_square.charAt(1) - '1'][dest_square.charAt(0) - 'a']);
        } else if (object_matrix[dest_square.charAt(1) - '1'][dest_square.charAt(0) - 'a'] != null
                && object_matrix[dest_square.charAt(1) - '1'][dest_square.charAt(0) - 'a'].color == "black") {
            BlackPieces.remove(object_matrix[dest_square.charAt(1) - '1'][dest_square.charAt(0) - 'a']);
        }

        object_matrix[starting_square.charAt(1) - '1'][starting_square.charAt(0) - 'a'].current_position = dest_square;
        object_matrix[dest_square.charAt(1) - '1'][dest_square.charAt(0)
                - 'a'] = object_matrix[starting_square.charAt(1) - '1'][starting_square.charAt(0) - 'a'];
        object_matrix[starting_square.charAt(1) - '1'][starting_square.charAt(0) - 'a'] = null;
        if (squares.length() == 5) {
            Pawn promotedPawn = (Pawn) object_matrix[dest_square.charAt(1) - '1'][dest_square.charAt(0) - 'a'];
            System.out.println(squares.charAt(4));
            switch (squares.charAt(4)) {
                case 'q':
                    object_matrix[promotedPawn.current_position.charAt(1) - '1'][promotedPawn.current_position.charAt(0)
                            - 'a'] = new Queen(promotedPawn.current_position, promotedPawn.color);
                    break;
                case 'r':
                    object_matrix[promotedPawn.current_position.charAt(1) - '1'][promotedPawn.current_position.charAt(0)
                            - 'a'] = new Rook(promotedPawn.current_position, promotedPawn.color);
                    break;
                case 'b':
                    object_matrix[promotedPawn.current_position.charAt(1) - '1'][promotedPawn.current_position.charAt(0)
                            - 'a'] = new Bishop(promotedPawn.current_position, promotedPawn.color);
                    break;
                case 'n':
                    object_matrix[promotedPawn.current_position.charAt(1) - '1'][promotedPawn.current_position.charAt(0)
                            - 'a'] = new Night(promotedPawn.current_position, promotedPawn.color);
                    break;
                default:
                    break;
            }
            if (promotedPawn.color.compareTo("black") == 0) {
                BlackPieces.remove(promotedPawn);
                BlackPieces.add(object_matrix[squares.charAt(3) - '1'][squares.charAt(2) - 'a']);
            } else {
                WhitePieces.remove(promotedPawn);
                WhitePieces.add(object_matrix[squares.charAt(3) - '1'][squares.charAt(2) - 'a']);
            }
        }
    }

    public ArrayList<Integer> pos_to_indexes(String pos) {
        ArrayList<Integer> coordinates = new ArrayList<Integer>();
        coordinates.add(pos.charAt(0) - 'a');
        coordinates.add(pos.charAt(1) - '1');
        return coordinates;
    }

    public void moveEnPassant(String squares, String color) {
        int x;
        if (color.equals("white")) {
            x = 1;
        } else {
            x = -1;
        }
        // squares[0:1] -> starting square
        String starting_square = squares.substring(0, 2);
        // squares[2:3] -> destination square
        String dest_square = squares.substring(2, 4);
        lastMove = new StringBuilder(squares);
        Piece takenPiece = object_matrix[dest_square.charAt(1) - '1' - x][dest_square.charAt(0) - 'a'];
        if (takenPiece != null) {
            if (takenPiece.color.compareTo("black") == 0)
                BlackPieces.remove(takenPiece);
            else
                WhitePieces.remove(takenPiece);
        }
        object_matrix[starting_square.charAt(1) - '1'][starting_square.charAt(0) - 'a'].current_position = dest_square;
        object_matrix[dest_square.charAt(1) - '1'][dest_square.charAt(0)
                - 'a'] = object_matrix[starting_square.charAt(1) - '1'][starting_square.charAt(0) - 'a'];
        object_matrix[starting_square.charAt(1) - '1'][starting_square.charAt(0) - 'a'] = null;
        object_matrix[dest_square.charAt(1) - '1' - x][dest_square.charAt(0) - 'a'] = null;
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

    public static double Evaluate(Board board,String myColor){
        ArrayList<Piece> myPieces=new ArrayList(),enemyPieces=new ArrayList();
        if(myColor.equals("white")){
            myPieces=WhitePieces;
            enemyPieces=BlackPieces;
        }else{
            myPieces=BlackPieces;
            enemyPieces=WhitePieces;
        }
        double materialCost=myPieces.stream().MapToDouble(piece -> piece.value).sum()-enemyPieces.stream().MapToDouble(piece -> piece.value).sum();
        double positionalCost=myPieces.stream().MapToDouble(piece -> IndividualCost(board,piece)).sum()-enemyPieces.stream().MapToDouble(piece ->  IndividualCost(board,piece)).sum();
        return materialCost + positionalCost; 
    }

    double static IndividualCost(Board board,Piece myPiece){
        int posX,posY;
        int range=0;
        posX=board.pos_to_indexes(myPiece.current_position).get(0);
        posY=board.pos_to_indexes(myPiece.current_position).get(1);
        double protectionCost;
        double protectionFactor=0.3;
        double rangeCost;
        double rangeFactor=0.1;
        ArrayList<Piece> defending=new ArrayList();
        ArrayList<Piece> attacking=new ArrayList();
        Helper.iterateLine(Direction.DIAG_UPLEFT);
        Helper.iterateLine(Direction.DIAG_UPRIGHT);
        Helper.iterateLine(Direction.DIAG_DOWNLEFT);
        Helper.iterateLine(Direction.DIAG_DOWNRIGHT);
        Helper.iterateLine(Direction.RIGHT);
        Helper.iterateLine(Direction.LEFT);
        Helper.iterateLine(Direction.UP);
        Helper.iterateLine(Direction.DOWN);
        Helper.computeKnightPos(posX+2,posY+1);
        Helper.computeKnightPos(posX+2,posY-1);
        Helper.computeKnightPos(posX-2,posY+1);
        Helper.computeKnightPos(posX-2,posY-1);
        Helper.computeKnightPos(posX+1,posY+2);
        Helper.computeKnightPos(posX+1,posY-2);
        Helper.computeKnightPos(posX-1,posY+2);
        Helper.computeKnightPos(posX-1,posY-2);
        
        attacking.sort( (Piece p1, Piece p2) -> p1.value.compareTo(p2.value));
        defending.sort( (Piece p1, Piece p2) -> p1.value.compareTo(p2.value));
        defending.add(0,myPiece);
        if(attacking.size() > defending.size())
            attacking.trimToSize(defending.size());
        else
            defending.trimToSize(attacking.size());
        protectionCost=protectionFactor*(attacking.stream().mapToDouble(piece -> piece.value).sum()-defending.stream().mapToDouble(piece -> piece.value).sum());
        rangeCost=range*rangeFactor;
        return protectionCost+rangeCost;

        private static class Helper{
            Boolean reachedPiece=false;
            static void iterateLine(Direction dir){
                Boolean checkPawnAttack=false,checkPawnDefend=false;
                Piece currentPiece=board.object_matrix[posX][posY];
                List pos=dir.DirToOffset(myPiece.color);
                int xOffset=pos.get(0);
                int yOffset=pos.get(1);
                if(dir.compareTo(Direction.DIAG_UPLEFT) || dir.compareTo(Direction.DIAG_UPRIGHT))
                    checkPawnAttack=true;
                if(dir.compareTo(Direction.DIAG_DOWNLEFT) || dir.compareTo(Direction.DIAG_DOWNRIGHT))
                    checkPawnDefend=true;
                for(i=x+offsetX,j=y+offsetY;inbounds(i,j);i=i+offsetX,j=j+offsetY){
                    if(currentPiece!=null){
                        reachedPiece=true;
                        if(currentPiece.color.equals(myPiece.color)){
                            if(checkPawnDefend && (currentPiece instanceof Pawn)){
                                checkPawnDefend=false;
                                defending.add(currentPiece);
                                continue;
                            }
                            if(dir.CheckMovement(currentPiece))
                                defending.add(currentPiece);
                            else
                                break;
                        }else{
                            if(checkPawnAttack && (currentPiece instanceof Pawn)){
                                checkPawnAttack=false;
                                attacking.add(currentPiece);
                                continue;
                            }
                            if(dir.checkMovement(currentPiece))
                                attacking.add(currentPiece);
                            else
                                break
                                defending.add(myPiece);
                        }
                    }else{
                        if(!reachedPiece){ 
                            if(dir.CheckMovement(myPiece))
                                range++;
                        }else
                            break;
                    }
            }
            static void computeKnightPos(int X,int Y){
                if(!inBounds(posX,posY))
                    return;
                Piece currentPiece=board.object_matrix[X][Y];
                if(currentPiece != null){
                    if(currentPiece instanceof Night){
                        if(currentPiece.color.equals(myPiece.color))
                            defending.add(currentPiece);
                        else
                            attacking.add(currentPiece);
                    }
                }else{
                    if(myPiece instanceof Night)
                        range++;
                }
            }
        }
        Boolean inBounds(int X,int Y){
            return (X>0 || X<=8 || Y>0 || Y<=8);
        }
        private enum Direction{
            LEFT,RIGHT,UP,DOWN,DIAG_UPLEFT,DIAG_UPRIGHT,DIAG_DOWNLEFT,DIAG_DOWNRIGHT;
            public ArrayList<Integer> DirToOffset(String color){
                ArrayList<Integer> pos=new ArrayList();
                int x=0,y=0;
                switch (this){
                    case LEFT:
                        x=-1;
                        break;
                    case RIGHT:
                        x=1;
                        break;
                    case DOWN:
                        y=-1;
                        break;
                    case UP:
                        y=1;
                        break;
                    case DIAG_DOWNLEFT:
                        x=-1;
                        y=-1
                        break;
                    case DIAG_DOWNRIGHT:
                        x=1;
                        y=-1
                        break;
                    case DIAG_UPLEFT:
                        x=-1;
                        y=1
                        break;
                    case DIAG_UPRIGHT:
                        y=1
                        x=1;
                        break;
                }
                if(color.equals("black")){
                    x = -x;
                    y = -y;
                }
                pos.add(x);pos.add(y);
                return pos;
            }
            public Boolean checkMovement(Piece piece){
                switch (this){
                    case DIAG_UPLEFT,DIAG_UPRIGHT,DIAG_DOWNLEFT,DIAG_DOWNRIGHT:
                        return(piece instanceof Bishop) || (piece instanceof Queen);
                    case LEFT,RIGHT,UP,DOWN:
                        return(piece instanceof Rook) || (piece instanceof Queen);
                }
            }
        }
    }
}