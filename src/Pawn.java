
public class Pawn extends Piece{
    public Pawn(String color, int row, int col){
        super(color, row, col);
        value = 1;
    }

    @Override
    public void move(Object[][] matrix, String color) {

        int nextrow = 0;
        String opposedcolor = "";
        int try_left = this.col - 1;
        int try_right = this.col + 1;


        if(color.equals("White")) {
            nextrow = this.row + 1;
            opposedcolor = "Black";
        }
        if(color.equals("Black")) {
            nextrow = this.row - 1;
            opposedcolor = "White";
        }
        if(try_left  < 1) {
            if (matrix[nextrow][try_right] != null &&
                    ((Piece)matrix[nextrow][try_right]).color.equals(opposedcolor)) {
                System.out.println("move "+(char)(this.col+96)+""+this.row+""+(char)(try_right+96)+""+nextrow);
                matrix[nextrow][try_right] = matrix[this.row][this.col];
                matrix[this.row][this.col] = null;
                ((Piece)matrix[nextrow][try_right]).row = nextrow;
                ((Piece)matrix[this.row][try_right]).col = try_right;

                if(opposedcolor.equals("Black") && (nextrow == 8)) {
                    matrix[nextrow][try_right] = new Queen("White", nextrow, try_right);
                    System.out.println("resign");
                }
                if(opposedcolor.equals("White") && (nextrow == 1)) {
                    matrix[nextrow][try_right] = new Queen("Black", nextrow, try_right);
                    System.out.println("resign");
                }

                return ;
            }

        }
        if(try_right > 8){
            if(matrix[nextrow][try_left] != null &&
                    ((Piece)matrix[nextrow][try_left]).color.equals(opposedcolor)) {
                System.out.println("move " + (char) (this.col + 96) + "" + this.row + "" + (char) (try_left + 96) + "" + nextrow);
                matrix[nextrow][try_left] = matrix[this.row][this.col];
                matrix[this.row][this.col] = null;
                ((Piece) matrix[nextrow][try_left]).row = nextrow;
                ((Piece) matrix[this.row][try_left]).col = try_left;

                if (opposedcolor.equals("Black") && (nextrow == 8)) {
                    matrix[nextrow][try_left] = new Queen("White", nextrow, try_left);
                    System.out.println("resign");
                }
                if (opposedcolor.equals("White") && (nextrow == 1)){
                    matrix[nextrow][try_left] = new Queen("Black", nextrow, try_left);
                    System.out.println("resign");
                }
                return;
            }
        }
        if(try_right < 9 && try_left > 0) {
            if(matrix[nextrow][try_left] != null && matrix[nextrow][try_right] != null)
                if(((Piece)matrix[nextrow][try_left]).value > ((Piece)matrix[nextrow][try_right]).value){
                    if(((Piece)matrix[nextrow][try_left]).color.equals(opposedcolor)) {
                        System.out.println("move " + (char) (this.col + 96) + "" + this.row + "" + (char) (try_left + 96) + "" + nextrow);
                        matrix[nextrow][try_left] = matrix[this.row][this.col];
                        matrix[this.row][this.col] = null;
                        ((Piece)matrix[nextrow][try_left]).row = nextrow;
                        ((Piece)matrix[this.row][try_left]).col = try_left;
                        System.out.println("move " + (char) (this.col + 96) + "" + this.row + "" + (char) (try_left + 96) + "" + nextrow);
                        if(opposedcolor.equals("Black") && (nextrow == 8)) {
                            matrix[nextrow][try_left] = new Queen("White", nextrow, try_left);
                            System.out.println("resign");
                        }
                        if(opposedcolor.equals("White") && (nextrow == 1)) {
                            matrix[nextrow][try_left] = new Queen("Black", nextrow, try_left);
                            System.out.println("resign");
                        }
                        return;
                    }
                }
            else
                {
                    if(((Piece)matrix[nextrow][try_right]).color.equals(opposedcolor)) {
                        System.out.println("move " + (char) (this.col + 96) + "" + this.row + "" + (char) (try_right + 96) + "" + nextrow);
                        matrix[nextrow][try_right] = matrix[this.row][this.col];
                        matrix[this.row][this.col] = null;
                        ((Piece)matrix[nextrow][try_right]).row = nextrow;
                        ((Piece)matrix[this.row][try_right]).col = try_right;
                        if(opposedcolor.equals("Black") && (nextrow == 8)) {
                            matrix[nextrow][try_right] = new Queen("White", nextrow, try_right);
                            System.out.println("resign");
                        }
                        if(opposedcolor.equals("White") && (nextrow == 1)) {
                            matrix[nextrow][try_right] = new Queen("Black", nextrow, try_right);
                            System.out.println("resign");
                        }
                        return;
                    }
                }
            if(matrix[nextrow][try_left] != null &&
                    ((Piece)matrix[nextrow][try_left]).color.equals(opposedcolor)){
                System.out.println("move "+(char)(this.col+96)+""+this.row+""+(char)(try_left+96)+""+nextrow);
                matrix[nextrow][try_left] = matrix[this.row][this.col];
                matrix[this.row][this.col] = null;
                ((Piece)matrix[nextrow][try_left]).row = nextrow;
                ((Piece)matrix[this.row][try_left]).col = try_left;
                if(opposedcolor.equals("Black") && (nextrow == 8)) {
                    matrix[nextrow][try_left] = new Queen("White", nextrow, try_left);
                    System.out.println("resign");
                }
                if(opposedcolor.equals("White") && (nextrow == 1)) {
                    matrix[nextrow][try_left] = new Queen("Black", nextrow, try_left);
                    System.out.println("resign");
                }

                return;
            }
            if(matrix[nextrow][try_right] != null &&
                    ((Piece)matrix[nextrow][try_right]).color.equals(opposedcolor)){

                System.out.println("move "+(char)(this.col+96)+""+this.row+""+(char)(try_right+96)+""+nextrow);
                matrix[nextrow][try_right] = matrix[this.row][this.col];
                matrix[this.row][this.col] = null;
                ((Piece)matrix[nextrow][try_right]).row = nextrow;
                ((Piece)matrix[this.row][try_right]).col = try_right;

                if(opposedcolor.equals("Black") && (nextrow == 8)) {
                    matrix[nextrow][try_right] = new Queen("White", nextrow, try_right);
                    System.out.println("resign");
                }
                if(opposedcolor.equals("White") && (nextrow == 1)) {
                    matrix[nextrow][try_right] = new Queen("Black", nextrow, try_right);
                    System.out.println("resign");
                }
                return ;
            }
        }
        if(matrix[nextrow][this.col] == null) {
            System.out.println("move " + (char) (this.col + 96) + "" + this.row + "" + (char) (this.col + 96) + "" + nextrow);
            matrix[nextrow][this.col] = matrix[this.row][this.col];
            matrix[this.row][this.col] = null;
            ((Piece)matrix[nextrow][this.col]).row = nextrow;
            ((Piece)matrix[this.row][this.col]).col = this.col;
            if(opposedcolor.equals("Black") && (nextrow == 8)) {
                matrix[nextrow][this.col] = new Queen("White", nextrow, this.col);
                System.out.println("resign");
            }
            if(opposedcolor.equals("White") && (nextrow == 1)) {
                matrix[nextrow][this.col] = new Queen("Black", nextrow, this.col);
                System.out.println("resign");
            }
        } else
            System.out.println("resign");
//        if(color.equals("Black"))
//        {
//            int try_left = this.col - 1;
//            int try_right = this.col + 1;
//            System.out.println(try_left + "      "+try_right);
//            if(try_left  < 1) {
//                if (matrix[this.row - 1][try_right] != null &&
//                        ((Piece)matrix[this.row - 1][try_right]).color.equals("White")) {
//                    System.out.println("move "+(char)(this.col+96)+""+this.row+""+(char)(try_right+96)+""+(this.row-1));
//                    matrix[this.row - 1][try_right] = matrix[this.row][this.col];
//                    matrix[this.row][this.col] = null;
//                    ((Piece)matrix[this.row - 1][try_right]).row = this.row - 1;
//                    ((Piece)matrix[this.row][try_right]).col = try_right;
//
//                    return ;
//                }
//
//            }
//            if(try_right > 8){
//                if(matrix[this.row - 1][try_left] != null &&
//                        ((Piece)matrix[this.row - 1][try_left]).color.equals("White")){
//                    System.out.println("move "+(char)(this.col+96)+""+this.row+""+(char)(try_left+96)+""+(this.row-1));
//                    matrix[this.row  - 1][try_left] = matrix[this.row][this.col];
//                    matrix[this.row][this.col] = null;
//                    ((Piece)matrix[this.row - 1][try_left]).row = this.row - 1;
//                    ((Piece)matrix[this.row][try_left]).col = try_left;
//
//                    return;
//                }
//            }
//            if(try_right < 9 && try_left > 0 ) {
//                if(matrix[this.row - 1][try_left] != null && matrix[this.row - 1][try_right] != null)
//                    if(((Piece)matrix[this.row - 1][try_left]).value > ((Piece)matrix[this.row - 1][try_right]).value){
//                        System.out.println("move " + (char) (this.col + 96) + "" + this.row + "" + (char) (try_left + 96) + "" + (this.row-1));
//                        if(((Piece)matrix[this.row - 1][try_left]).color.equals("White")) {
//                            matrix[this.row - 1][try_left] = matrix[this.row][this.col];
//                            matrix[this.row][this.col] = null;
//                            ((Piece)matrix[this.row - 1][try_left]).row = this.row - 1;
//                            ((Piece)matrix[this.row][try_left]).col = try_left;
//                            return;
//                        }
//                    }
//                    else
//                    {
//                        if(((Piece)matrix[this.row - 1][try_right]).color.equals("White")) {
//                            System.out.println("move " + (char) (this.col + 96) + "" + this.row + "" + (char) (try_right + 96) + "" + (this.row-1));
//                            matrix[this.row - 1][try_right] = matrix[this.row][this.col];
//                            matrix[this.row][this.col] = null;
//                            ((Piece)matrix[this.row - 1][try_right]).row = this.row - 1;
//                            ((Piece)matrix[this.row][try_right]).col = try_right;
//
//
//                            return;
//                        }
//                    }
//                if(matrix[this.row - 1][try_left] != null &&
//                        ((Piece)matrix[this.row - 1][try_left]).color.equals("White")){
//                    System.out.println("move "+(char)(this.col+96)+""+this.row+""+(char)(try_left+96)+""+(this.row-1));
//                    matrix[this.row - 1][try_left] = matrix[this.row][this.col];
//                    matrix[this.row][this.col] = null;
//                    ((Piece)matrix[this.row - 1][try_left]).row = this.row - 1;
//                    ((Piece)matrix[this.row][try_left]).col = try_left;
//
//
//                    return;
//                }
//                if(matrix[this.row - 1][try_right] != null &&
//                        ((Piece)matrix[this.row - 1][try_right]).color.equals("White")){
//                    System.out.println("move "+(char)(this.col+96)+""+this.row+""+(char)(try_right+96)+""+(this.row-1));
//                    matrix[this.row - 1][try_right] = matrix[this.row][this.col];
//                    matrix[this.row][this.col] = null;
//                    ((Piece)matrix[this.row - 1][try_right]).row = this.row - 1;
//                    ((Piece)matrix[this.row][try_right]).col = try_right;
//
//                    return ;
//                }
//            }
//            if(matrix[this.row - 1][this.col] == null) {
//                System.out.println("move " + (char) (this.col + 96) + "" + this.row + "" + (char) (this.col + 96) + "" + (this.row-1));
//                matrix[this.row - 1][this.col] = matrix[this.row][this.col];
//                matrix[this.row][this.col] = null;
//                ((Piece)matrix[this.row - 1][this.col]).row = this.row - 1;
//                ((Piece)matrix[this.row][this.col]).col = this.col;
//
//            } else
//                System.out.println("resign");
//        }
    }


}
