public class Queen extends Piece{

    public Queen(String color, int row, int col){
        super(color, row, col);
        value = 9;
    }

    @Override
    public void move(Object[][] matrix, String color) {
        System.out.println("resign");
    }

}
