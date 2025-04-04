

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

//You will be implmenting a part of a function and a whole function in this document. Please follow the directions for the 
//suggested order of completion that should make testing easier.
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
	// Resource location constants for piece images
    private static final String RESOURCES_WBISHOP_PNG = "wbishop.png";
	private static final String RESOURCES_BBISHOP_PNG = "bbishop.png";
	private static final String RESOURCES_WKNIGHT_PNG = "wknight.png";
	private static final String RESOURCES_BKNIGHT_PNG = "bknight.png";
	private static final String RESOURCES_WROOK_PNG = "wrook.png";
	private static final String RESOURCES_BROOK_PNG = "brook.png";
	private static final String RESOURCES_WKING_PNG = "wking.png";
	private static final String RESOURCES_BKING_PNG = "bking.png";
	private static final String RESOURCES_BQUEEN_PNG = "bqueen.png";
	private static final String RESOURCES_WQUEEN_PNG = "wqueen.png";
	private static final String RESOURCES_WPAWN_PNG = "wpawn.png";
	private static final String RESOURCES_BPAWN_PNG = "bpawn.png";
	
	// Logical and graphical representations of board
	private final Square[][] board;
    private final GameWindow g;
 
    //contains true if it's white's turn.
    private boolean whiteTurn;

    //if the player is currently dragging a piece this variable contains it.
    private Piece currPiece;
    private Square fromMoveSquare;
    
    //used to keep track of the x/y coordinates of the mouse.
    private int currX;
    private int currY;
    
    // pre condition: valid color provided 
    // postcondition: gets king of color provided and returns boolean if king is in check;
    public boolean IsInCheck(boolean color) {
        Piece placement;
        boolean FoundKing = false;
        int kingRow = -1;
        int kingColumn = -1;

        for (int i = 0; i < 8; i++) {
            if (FoundKing) {
                break;
            }
            for (int  col = 0; col < 8; col ++) {
                Square Current = board[i][col];
                boolean ExistingPiece = Current.isOccupied();

                if (ExistingPiece) {
                    Piece CurrentPiece = Current.getOccupyingPiece();
                    
                    if (CurrentPiece.getColor() == color && CurrentPiece instanceof King) {
                        placement = CurrentPiece;
                        FoundKing = true;
                        kingRow = i;
                        kingColumn = col;
                        break;
                    }
                }

            }
        }

        if (FoundKing) {
            placement = board[kingRow][kingColumn].getOccupyingPiece(); // cz of errors/warns;...

            for (int row = 0; row < 8; row++){
                for (int column = 0; column < 8; column++) {
                    Square SquareAt = board[row][column];
                    boolean isOccupied = SquareAt.isOccupied();
                    if (!isOccupied || column == kingColumn && row == kingRow) {
                        continue;
                    }

                    Piece CoolBen = SquareAt.getOccupyingPiece();

                    if (CoolBen.equals(placement)) { 
                        continue;
                    }

                    if (CoolBen.getColor() == color) {
                        continue;
                   }

                    ArrayList<Square> Controlled = CoolBen.getControlledSquares(board, board[row][column]);

                    for (int customControlled = 0; customControlled < Controlled.size(); customControlled++) {
                        Square Custom = Controlled.get(customControlled);
                        int customColumn = Custom.getCol();
                        int customRow = Custom.getRow();

                        if (kingRow == customRow && customColumn == kingColumn) {
                            System.out.println("Is in Check!!!!!");
                            return true;
                        }
                    }

                }
            }
        }

        return false;
    }
    
    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //TO BE IMPLEMENTED FIRST
        
        int counted = 0;

        for (int i = 0; i < 8; i++) {
            for (int index = 0; index < 8; index ++) {
                board[i][index] = new Square(this, counted % 2 == 0, i, index);
                this.add(board[i][index]); 
                counted++;
            }
            counted++;
        }

      //for (.....)  
//        	populate the board with squares here. Note that the board is composed of 64 squares alternating from 
//        	white to black.

        initializePieces();

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        whiteTurn = true;

    }

    
	//set up the board such that the black pieces are on one side and the white pieces are on the other.
	//since we only have one kind of piece for now you need only set the same number of pieces on either side.
	//it's up to you how you wish to arrange your pieces.

    /*
     * POST CONDITION: board initilized
     * POST CONDITION: set up board with two white pieces, and two black pieces.
     */
    private void initializePieces() {
        int columnIndex = 1;

        /*ArrayList<String> pieces = new ArrayList<String>();
        
        pieces.add("rook");
        pieces.add("knight");
        pieces.add("bishop");
        pieces.add("king");
        pieces.add("queen");
        pieces.add("bishop");
        pieces.add("rook");
        pieces.add("knight");
        
        for (int i = 0; i < 16; i ++) {
            if ((i > 1) && (i % 8 == 0)) {
                columnIndex = 6;
            }

            String currentEndPoint = (columnIndex == 6) ? "b" : "w";
            int imageGet = (i == 0 || i == 8 || i == 16) ? 0 : i%8;

            if (i == 14 || i == 6) {
                imageGet = 1;
            } else if (i == 15 || i == 7) {
                imageGet = 0;
            }

            board[columnIndex][i%8].put(new Piece(columnIndex != 6, ((columnIndex == 6) ? RESOURCES_BPAWN_PNG : RESOURCES_WPAWN_PNG)));
            board[((columnIndex==1) ? columnIndex-1 : columnIndex+1)][i%8].put(new Piece(columnIndex != 6, currentEndPoint + pieces.get(imageGet) + ".png"));
        };*/

       // board[1][0].put(new RandomMover(true, RESOURCES_WKING_PNG));
       // board[0][0].put(new RandomMover(true, RESOURCES_WKING_PNG));
        

       board[0][0].put(new RandomMover(true, RESOURCES_WPAWN_PNG));
       board[0][1].put(new ItalianKratos(true, RESOURCES_WKNIGHT_PNG));
       board[0][2].put(new Bishop(true, RESOURCES_WBISHOP_PNG));
       board[0][3].put(new Prince(true, RESOURCES_WQUEEN_PNG));
       board[0][4].put(new King(true, RESOURCES_WKING_PNG));
       board[0][5].put(new Bishop(true, RESOURCES_WBISHOP_PNG));
       board[0][6].put(new ItalianKratos(true, RESOURCES_WKNIGHT_PNG));
       board[0][7].put(new RandomMover(true, RESOURCES_WPAWN_PNG));
       
       board[7][0].put(new RandomMover(false, RESOURCES_BPAWN_PNG));
       board[7][1].put(new ItalianKratos(false, RESOURCES_BKNIGHT_PNG));
       board[7][2].put(new Bishop(false, RESOURCES_BBISHOP_PNG));
       board[7][3].put(new Prince(false, RESOURCES_BQUEEN_PNG));
       board[7][4].put(new King(false, RESOURCES_BKING_PNG));
       board[7][5].put(new Bishop(false, RESOURCES_BBISHOP_PNG));
       board[7][6].put(new ItalianKratos(false, RESOURCES_BKNIGHT_PNG));
       board[7][7].put(new RandomMover(false, RESOURCES_BPAWN_PNG));

        //pieces.clear();
    }
    
    /* POST CONDITION: Valid row and valid column
     * POST CONDITION: Returns boolean if area is/is not occupied.
     */
    public boolean PieceAt(int row, int column) {
        return this.board[row][column].isOccupied();
    }

    /* POST CONDITION: Valid row and valid column
     * POST CONDITION: Returns square of which you inputted.
     */
    
    public Square GetPiece(int row, int column) {
        return this.board[row][column];
    }

    public Square[][] getSquareArray() {
        return this.board;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    public void setCurrPiece(Piece p) {
        this.currPiece = p;
    }

    public Piece getCurrPiece() {
        return this.currPiece;
    }

    @Override
    public void paintComponent(Graphics g) {

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square sq = board[x][y];
                if(sq == fromMoveSquare)
                	 sq.setBorder(BorderFactory.createLineBorder(Color.blue));
                sq.paintComponent(g);
                
            }
        }
    	if (currPiece != null) {
            if ((currPiece.getColor() && whiteTurn)
                    || (!currPiece.getColor()&& !whiteTurn)) {
                final Image img = currPiece.getImage();
                g.drawImage(img, currX, currY, null);
            }
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();

        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (sq.isOccupied()) {
            //System.out.println("aaaa");
            currPiece = sq.getOccupyingPiece();
            fromMoveSquare = sq;
            if (!currPiece.getColor() && whiteTurn)
                return;
            if (currPiece.getColor() && !whiteTurn)
                return;
            sq.setDisplay(false);
        }
        repaint();
    }

    //TO BE IMPLEMENTED!
    //should move the piece to the desired location only if this is a legal move.
    //use the pieces "legal move" function to determine if this move is legal, then complete it by
    //moving the new piece to it's new board location. 
    @Override

    /*
     * POST CONDITION: valid mouse event, and current piece preferably being ~= null.
     * POST CONDITION: if valid movement/takeover move and takeover piece/move space.
     */

    public void mouseReleased(MouseEvent e) {
        if (currPiece == null) { System.out.println("CURRENT PIECE IS NULL"); return;}
      // Square currentSquare = (Square) this.getComponentAt(new Point(currX, currY));
        Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
        ArrayList <Square> allowedSquares = currPiece.getLegalMoves(this, fromMoveSquare);
        ArrayList <Square> controlledSquares = currPiece.getControlledSquares(board, fromMoveSquare);
       // System.out.println(endSquare);
        //using currPiece
        boolean isValid = false;
        
        for (int i = 0; i < allowedSquares.size(); i++) {
            Square comparison = allowedSquares.get(i);
            if (comparison.getRow() == endSquare.getRow() && comparison.getCol() == endSquare.getCol()) {
                isValid = true;
                break;
            }
        }

        for (int i = 0; i < controlledSquares.size(); i++) {
            Square comparison = controlledSquares.get(i);
            if (comparison.getRow() == endSquare.getRow() || comparison.getCol() == endSquare.getCol()) {
                isValid = true;
                break;
            }
        }

        /*
         * idea for makin king mve to places that wouldnt put him in check
         * iterate through opposite color of units on board; if it's a controlled square then isvalid will becom false; 👀 mad simple
         */

         Square currentAt = board[endSquare.getRow()][endSquare.getCol()];

         if (currPiece instanceof King ) {
            boolean CantMove = false;
            for (int row = 0; row < 8; row++) {
                for (int column = 0; column < 8; column++) {
                    Square current = board[row][column];

                    if (!current.isOccupied()) {
                        continue;
                    }

                    if (current.getColor() == currPiece.getColor()) {
                        continue;
                    }

                    Piece AtRightNow = current.getOccupyingPiece();
                    ArrayList<Square> Controlled = AtRightNow.getControlledSquares(board, current);

                    for (int index = 0; index < Controlled.size(); index++) {
                        Square IndexedSquare = Controlled.get(index);

                        if (IndexedSquare.getCol() == currentAt.getCol() && IndexedSquare.getRow() == currentAt.getRow()) {
                            isValid = false;
                            CantMove = true;
                            break;
                        }
                    }
                }

                if (CantMove) {
                    break;
                }
            }
         }
        
        for(Square [] row: board) {
        	for(Square s: row) {
        		s.setBorder(null);
     
        	}
        	
        }


        if (isValid) {
            fromMoveSquare.removePiece();
            currentAt.put(currPiece);
        }

        System.out.println(isValid);
       // System.out.println(IsInCheck(currentAt.getOccupyingPiece().getColor()));
        fromMoveSquare.setDisplay(true);
        currPiece = null;
        repaint();
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        currX = e.getX() - 24;
        currY = e.getY() - 24;

        if(currPiece!= null) {
        	for(Square s: currPiece.getLegalMoves(this, fromMoveSquare)) {
        		s.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        	}
        	
        }
        
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}