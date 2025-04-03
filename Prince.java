
//Ian 
//Prince
//Can move like the king, but two instead of one
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
public class Prince extends Piece {
	private boolean color = super.getColor();
	private BufferedImage img;
	
    public Prince(boolean isWhite, String img_file) {
       super(isWhite, img_file);
       
        this.color = isWhite;
       
        try {
            if (this.img == null) {
              this.img = ImageIO.read(getClass().getResource(img_file));
            }
          } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
          }
    }

    @Override
    public String toString(){
      if(color){
        return "white" + "Prince";
      }
      else{
        return "black Prince";
      }
    }
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
      ArrayList<Square> moves = new ArrayList<Square>();
      boolean same = true;
     
      if(start.getCol() != 0){
        if(start.getCol() != 1){
          if((board[start.getRow()][start.getCol()-1]).isOccupied() == false){
          moves.add(board[start.getRow()][start.getCol()-2]);
          }
          }
         
          moves.add(board[start.getRow()][start.getCol()-1]);
         
        }
      if(start.getCol() < 7){
        if(start.getCol() < 6){
          if((board[start.getRow()][start.getCol()+1]).isOccupied() == false){
            moves.add(board[start.getRow()][start.getCol()+2]);
          }
        }
        moves.add(board[start.getRow()][start.getCol()+1]);
     
      }
     if(start.getRow() != 0){
      if(start.getRow() != 1){
        if((board[start.getRow()-1][start.getCol()]).isOccupied() == false){
          moves.add(board[start.getRow()-2][start.getCol()]);
        }
      }
           moves.add(board[start.getRow()-1][start.getCol()]);
     
     }
     if(start.getRow() != 7){
      if(start.getRow() != 6){
        if((board[start.getRow()+1][start.getCol()]).isOccupied() == false){
                moves.add(board[start.getRow()+2][start.getCol()]);
        }
      }
             moves.add(board[start.getRow()+1][start.getCol()]);
     
     }
     if(start.getRow() != 7 && start.getCol() != 7){
      if(start.getRow() != 6 && start.getCol() != 6){
        if(board[start.getRow()+1][start.getCol()+1].isOccupied() == false){
                    moves.add(board[start.getRow()+2][start.getCol()+2]);
        }
      }
           moves.add(board[start.getRow()+1][start.getCol()+1]);
     
     }
     if(start.getRow() != 7 && start.getCol() != 0){
      if(start.getRow() != 6 && start.getCol() != 1){
        if(board[start.getRow()+1][start.getCol()-1].isOccupied() == false){
                    moves.add(board[start.getRow()+2][start.getCol()-2]);
        }
      }
            moves.add(board[start.getRow()+1][start.getCol()-1]);
     
     }
     if(start.getRow() != 0 && start.getCol() != 7){
      if(start.getRow() != 1 && start.getCol() != 6){
        if(board[start.getRow()-1][start.getCol()+1].isOccupied() == false){
                    moves.add(board[start.getRow()-2][start.getCol()+2]);
        }
      }
            moves.add(board[start.getRow()-1][start.getCol()+1]);
     }
     if(start.getRow() != 0 && start.getCol() != 0){
      if(start.getRow() != 1 && start.getCol() != 1){
        if(board[start.getRow()-1][start.getCol()-1].isOccupied() == false){
                    moves.add(board[start.getRow()-2][start.getCol()-2]);
        }
      }
          moves.add(board[start.getRow()-1][start.getCol()-1]);

     }
      return moves;
    }
   
   

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.


    //precondition: Board is not null, start is not null
    //postcondition: Piece can move in any direction diagonally 1 or 2 squares, up down left and right 1 or 2 squares, and cannot jump other pieces
    public ArrayList<Square> getLegalMoves(Board b, Square start){
      ArrayList<Square> moves = new ArrayList<Square>();
      boolean same = true;
     
      if(start.getCol() != 0){
        if(start.getCol() != 1){
          if((b.getSquareArray()[start.getRow()][start.getCol()-1]).isOccupied() == false && (!b.getSquareArray()[start.getRow()][start.getCol()-2].isOccupied() || (b.getSquareArray()[start.getRow()][start.getCol()-2]).getColor() != color )){
          moves.add(b.getSquareArray()[start.getRow()][start.getCol()-2]);
          }
          }
          if(!b.getSquareArray()[start.getRow()][start.getCol()-1].isOccupied() ||(b.getSquareArray()[start.getRow()][start.getCol()-1]).getOccupyingPiece().getColor() != color){
          moves.add(b.getSquareArray()[start.getRow()][start.getCol()-1]);
          }
        }
      if(start.getCol() < 7){
        if(start.getCol() < 6){
          if((b.getSquareArray()[start.getRow()][start.getCol()+1]).isOccupied() == false &&(!b.getSquareArray()[start.getRow()][start.getCol()+2].isOccupied() || (b.getSquareArray()[start.getRow()][start.getCol()+2]).getColor() != color)){
            moves.add(b.getSquareArray()[start.getRow()][start.getCol()+2]);
          }
        }
        if(!b.getSquareArray()[start.getRow()][start.getCol()+1].isOccupied() || b.getSquareArray()[start.getRow()][start.getCol()+1].getOccupyingPiece().getColor() != color){
        moves.add(b.getSquareArray()[start.getRow()][start.getCol()+1]);
        }
      }
     if(start.getRow() != 0){
      if(start.getRow() != 1){
        if((b.getSquareArray()[start.getRow()-1][start.getCol()]).isOccupied() == false && (!b.getSquareArray()[start.getRow()-2][start.getCol()].isOccupied() || (b.getSquareArray()[start.getRow()-2][start.getCol()]).getOccupyingPiece().getColor() != color)){
        moves.add(b.getSquareArray()[start.getRow()-2][start.getCol()]);
        }
      }
      if(!b.getSquareArray()[start.getRow()-1][start.getCol()].isOccupied() || (b.getSquareArray()[start.getRow()-1][start.getCol()]).getOccupyingPiece().getColor() != color){
        moves.add(b.getSquareArray()[start.getRow()-1][start.getCol()]);
      }
     }
     if(start.getRow() != 7){
      if(start.getRow() != 6){
        if((b.getSquareArray()[start.getRow()+1][start.getCol()]).isOccupied() == false && ((!b.getSquareArray()[start.getRow()+2][start.getCol()].isOccupied() ||(b.getSquareArray()[start.getRow()+2][start.getCol()]).getOccupyingPiece().getColor()!= color))){
        moves.add(b.getSquareArray()[start.getRow()+2][start.getCol()]);
        }
      }
      if(!b.getSquareArray()[start.getRow()+1][start.getCol()].isOccupied() ||(b.getSquareArray()[start.getRow()+1][start.getCol()]).getOccupyingPiece().getColor() != color){
        moves.add(b.getSquareArray()[start.getRow()+1][start.getCol()]);
      }
     }
     if(start.getRow() != 7 && start.getCol() != 7){
      if(start.getRow() != 6 && start.getCol() != 6){
        if((b.getSquareArray()[start.getRow()+1][start.getCol()+1]).isOccupied() == false && (!b.getSquareArray()[start.getRow()+2][start.getCol()+2].isOccupied() || ((b.getSquareArray()[start.getRow()+2][start.getCol()+2]).getOccupyingPiece()).getColor() != color)){
          moves.add(b.getSquareArray()[start.getRow()+2][start.getCol()+2]);
        }
      }
      if(!b.getSquareArray()[start.getRow()+1][start.getCol()+1].isOccupied() || ((b.getSquareArray()[start.getRow()+1][start.getCol()+1]).getOccupyingPiece()).getColor() != color){
      moves.add(b.getSquareArray()[start.getRow()+1][start.getCol()+1]);
      }
     }
     if(start.getRow() != 7 && start.getCol() != 0){
      if(start.getRow() != 6 && start.getCol() != 1){
        if((b.getSquareArray()[start.getRow()+1][start.getCol()-1]).isOccupied() == false && (!b.getSquareArray()[start.getRow()+2][start.getCol()-2].isOccupied() || ((b.getSquareArray()[start.getRow()+2][start.getCol()-2]).getOccupyingPiece()).getColor() != color)){
          moves.add(b.getSquareArray()[start.getRow()+2][start.getCol()-2]);
        }
      }
      if(!b.getSquareArray()[start.getRow()+1][start.getCol()-1].isOccupied() ||((b.getSquareArray()[start.getRow()+1][start.getCol()-1]).getOccupyingPiece()).getColor() != true){
      moves.add(b.getSquareArray()[start.getRow()+1][start.getCol()-1]);
      }
     }
     if(start.getRow() != 0 && start.getCol() != 7){
      if(start.getRow() != 1 && start.getCol() != 6){
        if((b.getSquareArray()[start.getRow()-1][start.getCol()+1]).isOccupied() == false &&(!b.getSquareArray()[start.getRow()-2][start.getCol()+2].isOccupied() || ((b.getSquareArray()[start.getRow()-2][start.getCol()+2]).getOccupyingPiece()).getColor() != true)){
          moves.add(b.getSquareArray()[start.getRow()-2][start.getCol()+2]);
        }
      }
      if(!b.getSquareArray()[start.getRow()-1][start.getCol()+1].isOccupied() ||((b.getSquareArray()[start.getRow()-1][start.getCol()+1]).getOccupyingPiece()).getColor() != color){
      moves.add(b.getSquareArray()[start.getRow()-1][start.getCol()+1]);
      }
     }
     if(start.getRow() != 0 && start.getCol() != 0){
      if(start.getRow() != 1 && start.getCol() != 1){
        if((b.getSquareArray()[start.getRow()-1][start.getCol()-1]).isOccupied() == false &&(!b.getSquareArray()[start.getRow()-2][start.getCol()-2].isOccupied() || ((b.getSquareArray()[start.getRow()-2][start.getCol()-2]).getOccupyingPiece()).getColor() != true)){
          moves.add(b.getSquareArray()[start.getRow()-2][start.getCol()-2]);
        }
      }
      if(!b.getSquareArray()[start.getRow()-1][start.getCol()-1].isOccupied() ||((b.getSquareArray()[start.getRow()-1][start.getCol()-1]).getOccupyingPiece()).getColor() != color){
      moves.add(b.getSquareArray()[start.getRow()-1][start.getCol()-1]);
      }
     }
      return moves;
    }

}
