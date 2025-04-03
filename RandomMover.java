/*
 * Name : Reggie Daniel
 * Piece Name: Random_Mover
 * Description: Is randomly allotted x amount of space to move up, left or right.
 * 
 * 
 */


import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;


public class RandomMover extends Piece {
    private boolean color = super.getColor();

    private static Random lol = new Random();
    private static int rowLimit = lol.nextInt(1,3);
    private static int columnLimit = lol.nextInt(1,3);

    public RandomMover(boolean isWhite, String img_file) {
        super(isWhite, img_file);
    }

    public String toString() {
      return "A " + super.toString() + " random_mover";
    }

     public ArrayList<Square> getControlledSquares(Square[][] b, Square start) {
      ArrayList<Square> controlledSquares = new ArrayList<Square>();

      int myColumn = start.getCol();
      int myRow = start.getRow();

      for (int i = myRow; i < 8; i++) {
        if (i == myRow) {
          continue;
        }

        int Distance = Math.abs(i-myRow);

        if (Distance > rowLimit) {
          System.out.println("toofar");
          break;
        }

        if (b[i][myColumn].isOccupied() && b[i][myColumn].getColor() == color) {
          controlledSquares.add(b[i][myColumn]);
          System.out.println("controlled");
        }
      }

      for (int i = myRow; i <= 0; i--) {
        if (i < 0) {
          break; // bro??
        }

        if (i == myRow) {
          continue;
        }
        int Distance = Math.abs(i-myRow);

        if (Distance > rowLimit) {
          break;
        }

        if (b[i][myColumn].isOccupied() && b[i][myColumn].getColor() == color) {
          controlledSquares.add(b[i][myColumn]);
          System.out.println("controlled");
          System.out.println("ROW: "+ i);
          System.out.println("COLUMN: " +myColumn);
        }
      }

      for (int i = myColumn; i > 8; i++) {
        int Distance = Math.abs(i-myColumn);

        if (i == myColumn) {
          continue;
        }

        if (Distance > columnLimit) {
          break;
        }

        if (b[myRow][i].isOccupied() && b[myRow][i].getColor() == color) {
          controlledSquares.add(b[myRow][i]);
          System.out.println("controlled");
        }
      }


      for (int i = myColumn; i >= 0; i--) {
        if (i < 0) {
          break;
        }

        if (i == myColumn) {
          continue;
        }

        int Distance = Math.abs(i-myColumn);

        if (Distance > columnLimit) {
          break;
        }

        if (b[myRow][i].isOccupied() && b[myRow][i].getColor() == color) {
          controlledSquares.add(b[myRow][i]);
          System.out.println("controlled");
        }
      }

      System.out.println(controlledSquares);
      return controlledSquares;
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.

      /* POST CONDITION: valid boad and valid starting square
     * POST CONDITION: returns places you can move at this very moment.
     */
    public ArrayList<Square> getLegalMoves(Board b, Square start){
      // the random
      // it can move random amounts of spaces ahead
      // depends on how its feeling;

      ArrayList<Square> allowedMovements = new ArrayList<Square>();

      System.out.println("ROW:" + String.valueOf(start.getRow()));
      System.out.println("COLUMN: "+ String.valueOf(start.getCol()));

      int currentColumn = start.getCol();
      int currentRow = start.getRow();

      for (int i = currentRow; i < 8; i++) {
        if (i == currentRow) {
          System.out.println("ROW INCREASING: continued same index");
          continue;
        }

        if (b.getSquareArray()[i][currentColumn].isOccupied()) {
          System.out.println("ROW INCREASING: broken occupied");
          break;
        }

        int Distance = Math.abs(i-currentRow); 

        if (Distance > rowLimit) {
          System.out.println("ROW INCREASING: broken distance too much row");
          break;
        }

        allowedMovements.add(b.getSquareArray()[i][currentColumn]);
      }

      for (int i = currentRow; i >= 0; i--) {
        if (i == currentRow) {
          System.out.println("ROW DECREASING: continued same row");
          continue;
        }

        if (b.getSquareArray()[i][currentColumn].isOccupied()) {
          System.out.println("ROW DECREASING: broken occupied row");
          break;
        }

        int Distance = Math.abs(i-currentRow); 

        if (Distance > rowLimit) {
          System.out.println("ROW DECREASING: broken distance too much row");
          break;
        }

        allowedMovements.add(b.getSquareArray()[i][currentColumn]);
      }

      for (int i = currentColumn; i < 8; i++) {
        if (i == currentColumn) {
          System.out.println("COLUMN INCREASING: continued same column");
          continue;
        }

        if (b.getSquareArray()[currentRow][i].isOccupied()) {
          System.out.println("COLUMN INCREASING: broken occupied");
          break;
        }

        int Distance = Math.abs(i-currentColumn); 

        if (Distance > columnLimit) {
          System.out.println("COLUMN INCREASING: broken distance");
          break;
        }

        allowedMovements.add(b.getSquareArray()[currentRow][i]);
      }

      for (int i = currentColumn; i >= 0; i--) {
        if (i == currentColumn) {
          System.out.println("COLUMN DECREASING: continued same column");
          continue;
        }

        if (b.getSquareArray()[currentRow][i].isOccupied()) {
          System.out.println("COLUMN DECREASING: broken cuz occupied");
          break;
        }

        int Distance = Math.abs(i-currentColumn); 

        if (Distance > columnLimit) {
          System.out.println("COLUMN DECREASING: broken cuz distance >");
          break;
        }

        allowedMovements.add(b.getSquareArray()[currentRow][i]);
      }



      System.out.println(allowedMovements);
      return allowedMovements;
    }
}