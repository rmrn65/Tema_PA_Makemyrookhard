public abstract class Piece {
   public String color;
    public int value;
    public int row;
    public int col;
    public Piece(String color, int row, int col){
        this.color = color;
        this.row = row;
        this.col = col;
    }

    public String toString(){
        return getClass().getName() +" "+ this.row + " "+this.col;
    }

    public abstract void move(Object[][] matrix, String color);
}
