
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class LongKnight extends Piece {
    private boolean color = super.getColor();
    
    public LongKnight(boolean isWhite, String img_file) {
      super(isWhite, img_file);
      this.color = isWhite;
    }

    @Override
    public String toString() {
      if (color)
      return "white " + "LongKnight";
      else
      return "black " + "LongKnight";
    }
  
    
    //precondition: moves do not go out of bounds
    //post condition: finds the correct square that the piece can move too, up or down 3, left or right 1.
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
      ArrayList<Square> moves = new ArrayList<Square>();

      int row =start.getRow(); 
      int col =start.getCol();
      //check to see if the spot is out of bounds, or it's in bounds but occupied by my own colored piece and if neither is a problem go there!
  
        if (row+3<=7 && col+1<=7 ){
          moves.add(board[row+3][col+1]);
        };
        if (row-3>=0 && col+1<=7 ){
          moves.add(board[row-3][col+1]);
        };
        if (row-3>=0 && col-1>=0 ){
          moves.add(board[row-3][col-1]);
        };
        if (row+3<=7 && col-1>=0){
          moves.add(board[row+3][col-1]);
        };
        if (row+1<=7 && col-3<=7 ){
          moves.add(board[row+1][col-3]);
        };
        if (row+1<=7 && col+3<=7 ){
          moves.add(board[row+1][col+3]);
        };
        if (row-1>=0 && col-3>=0){
          moves.add(board[row-1][col-3]);
        };
        if (row-1>=0 && col+3<=7){
          moves.add(board[row-1][col+3]);
        };

     return moves;

        }
       
      
    
//precondition: moves do not go out of bounds
//post condition: finds the correct square that the piece can move too, up or down 3, left or right 1. (Long knight)
    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    public ArrayList<Square> getLegalMoves(Board b, Square start){
      ArrayList<Square> moves = new ArrayList<Square>();

      int row =start.getRow(); 
      int col =start.getCol();
      //check to see if the spot is out of bounds, or it's in bounds but occupied by my own colored piece and if neither is a problem go there!
  
        if (row+3<=7 && col+1<=7 && (!b.getSquareArray()[row+3][col+1].isOccupied() || b.getSquareArray()[row+3][col+1].getOccupyingPiece().getColor() != color)){
          moves.add(b.getSquareArray()[row+3][col+1]);
        };
        if (row-3>=0 && col+1<=7 && (!b.getSquareArray()[row-3][col+1].isOccupied() || b.getSquareArray()[row-3][col+1].getOccupyingPiece().getColor() != color)){
          moves.add(b.getSquareArray()[row-3][col+1]);
        };
        if (row-3>=0 && col-1>=0 && (!b.getSquareArray()[row-3][col-1].isOccupied() || b.getSquareArray()[row-3][col-1].getOccupyingPiece().getColor() != color)){
          moves.add(b.getSquareArray()[row-3][col-1]);
        };
        if (row+3<=7 && col-1>=0 && (!b.getSquareArray()[row+3][col-1].isOccupied() || b.getSquareArray()[row+3][col-1].getOccupyingPiece().getColor() != color)){
          moves.add(b.getSquareArray()[row+3][col-1]);
        };
        if (row+1<=7 && col-3>=0 && (!b.getSquareArray()[row+1][col-3].isOccupied() || b.getSquareArray()[row+1][col-3].getOccupyingPiece().getColor() != color)){
          moves.add(b.getSquareArray()[row+1][col-3]);
        };
        if (row+1<=7 && col+3<=7 && (!b.getSquareArray()[row+1][col+3].isOccupied() || b.getSquareArray()[row+1][col+3].getOccupyingPiece().getColor() != color)){
          moves.add(b.getSquareArray()[row+1][col+3]);
        };
        if (row-1>=0 && col-3>=0 && (!b.getSquareArray()[row-1][col-3].isOccupied() || b.getSquareArray()[row-1][col-3].getOccupyingPiece().getColor() != color)){
          moves.add(b.getSquareArray()[row-1][col-3]);
        };
        if (row-1>=0 && col+3<=7 && (!b.getSquareArray()[row-1][col+3].isOccupied() || b.getSquareArray()[row-1][col+3].getOccupyingPiece().getColor() != color)){
          moves.add(b.getSquareArray()[row-1][col+3]);
        };

     return moves;

        }
       
      }
    
    
    