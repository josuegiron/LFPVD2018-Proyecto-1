/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;

public class Game extends JPanel {

    private static final long serialVersionUID = -8715353373678321308L;
    private int dimensionX, dimensionY, levelID, level, goal, numLevel;
    private String message;
    private boolean stop;

    private final Point[][][] Tetraminos = {
        // I-Piece
        {
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3)}
        },
        // J-Piece
        {
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0)}
        },
        // L-Piece
        {
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0)},
            {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0)}
        },
        // O-Piece
        {
            {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)},
            {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)},
            {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)},
            {new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1)}
        },
        // S-Piece
        {
            {new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1)},
            {new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)},
            {new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1)},
            {new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)}
        },
        // T-Piece
        {
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1)},
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)},
            {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2)},
            {new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2)}
        },
        // Z-Piece
        {
            {new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1)},
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2)},
            {new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1)},
            {new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2)}
        }
    };

    private final Color[] tetraminoColors = {
        Color.cyan, Color.blue, Color.orange, Color.yellow, Color.green, Color.pink, Color.red
    };

    private Point pieceOrigin;
    private Piece currentPiece;
    private int rotation;
    private ArrayList<Piece> nextPieces = new ArrayList<Piece>();

    private long score;
    private Color[][] well;

    // Creates a border around the well and initializes the dropping piece
    private void init() {
        well = new Color[dimensionX][dimensionY];
        for (int i = 0; i < dimensionX; i++) {
            for (int j = 0; j < dimensionY; j++) {
                if (i == 0 || i == dimensionX - 1 || j == dimensionY - 1) {
                    well[i][j] = Color.GRAY;
                } else {
                    well[i][j] = Color.BLACK;
                }
            }
        }
        //currentPiece =  myTetris.Levels.get(0).Pieces.get(0);
        newPiece();
    }

    // Put a new, random piece into the dropping position
    public void newPiece() {
        pieceOrigin = new Point(dimensionX / 2, 0);
        rotation = 0;
        if (nextPieces.isEmpty()) {
            for (Piece currentPiece : myTetris.Levels.get(levelID).Pieces) {
                //System.out.println(currentPiece.Type);
                nextPieces.add(currentPiece);
                //Collections.shuffle(nextPieces);
            }

        }
        currentPiece = nextPieces.get(0);
        rotateIn(currentPiece.Orientation);
        nextPieces.remove(0);
    }

    // Collision test for the dropping piece
    private boolean collidesAt(int x, int y, int rotation) {
        for (Point p : Tetraminos[currentPiece.Type][rotation]) {
            if (well[p.x + x][p.y + y] != Color.BLACK) {
                return true;
            }
        }
        return false;
    }

    // Rotate the piece clockwise or counterclockwise
    public void rotate(int i) {
        int newRotation = (rotation + i) % 4;
        if (newRotation < 0) {
            newRotation = 3;
        }
        if (!collidesAt(pieceOrigin.x, pieceOrigin.y, newRotation)) {
            rotation = newRotation;
        }
        repaint();
    }
    
    // Rotate the piece clockwise or counterclockwise
    public void rotateIn(int i) {
        int newRotation = (rotation + i) % 4;
        if (newRotation < 0) {
            newRotation = 3;
        }
        if (!collidesAt(pieceOrigin.x, pieceOrigin.y, newRotation)) {
            rotation = newRotation;
        }
    }

    // Move the piece left or right
    public void move(int i) {
        if (!collidesAt(pieceOrigin.x + i, pieceOrigin.y, rotation)) {
            pieceOrigin.x += i;
        }
        repaint();
    }

    // Drops the piece one line or fixes it to the well if it can't drop
    public void dropDown() {
        if (!collidesAt(pieceOrigin.x, pieceOrigin.y + 1, rotation)) {
            pieceOrigin.y += 1;
        } else {
            fixToWell();
        }
        repaint();
    }

    // Make the dropping piece part of the well, so it is available for
    // collision detection.
    public void fixToWell() {
        for (Point p : Tetraminos[currentPiece.Type][rotation]) {
            well[pieceOrigin.x + p.x][pieceOrigin.y + p.y] = tetraminoColors[currentPiece.Type];
        }
        clearRows();
        newPiece();
    }

    public void deleteRow(int row) {
        for (int j = row - 1; j > 0; j--) {
            for (int i = 1; i < dimensionX - 1; i++) {
                well[i][j + 1] = well[i][j];
            }
        }
        score += 10;
        validateWin();
    }

    private void validateWin() {
        if (score >= goal) {
            levelID += 1;
            level += 1;
            if (numLevel < level) {
                message = "!GANASTE CAMPEON!";
                //showMessageDialog(null, "!GANASTE CAMPEON!");
                stop = true;
            } else {
                score = 0;
                nextPieces.clear();
                goal = myTetris.Levels.get(levelID).Goal;
            }
        }
    }

    // Clear completed rows from the field and award score according to
    // the number of simultaneously cleared rows.
    public void clearRows() {
        boolean gap;

        for (int j = dimensionY - 2; j > 0; j--) {
            gap = false;
            for (int i = 1; i < dimensionX - 1; i++) {
                if (well[i][j] == Color.BLACK) {
                    gap = true;
                    break;
                }
            }
            if (!gap) {
                deleteRow(j);
                j += 1;
            }
        }
    }

    // Draw the falling piece
    private void drawPiece(Graphics g) {
        g.setColor(tetraminoColors[currentPiece.Type]);
        for (Point p : Tetraminos[currentPiece.Type][rotation]) {
            g.fillRect((p.x + pieceOrigin.x) * 26,
                    (p.y + pieceOrigin.y) * 26,
                    25, 25);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        // Paint the well
        g.fillRect(0, 0, 26 * dimensionX, 26 * dimensionY);
        for (int i = 0; i < dimensionX; i++) {
            for (int j = 0; j < dimensionY; j++) {
                g.setColor(well[i][j]);
                g.fillRect(26 * i, 26 * j, 25, 25);
            }
        }

        // Display the score
        g.setColor(Color.WHITE);
        g.drawString("" + myTetris.Levels.get(levelID).getName(), 19 * dimensionX, 15);
        g.drawString("Puntos: " + score, 19 * dimensionX, 25);
        g.drawString(message, 26 * dimensionX / 2, 25 * dimensionY / 2 - 25);

        // Draw the currently falling piece
        drawPiece(g);
    }

    private Tetris myTetris;

    public void Start() {
        System.out.println(myTetris.Levels.get(0).Pieces.get(0).Type);
        JFrame f = new JFrame("Tetris");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(26 * dimensionX + 10, 26 * dimensionY + 25);
        f.setVisible(true);

        final Game game = new Game(myTetris);
        game.init();
        f.add(game);

        // Keyboard controls
        f.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        game.rotate(+1);
                        break;
                    case KeyEvent.VK_DOWN:
                        //game.rotate(+1);
                        game.dropDown();
                        break;
                    case KeyEvent.VK_LEFT:
                        game.move(-1);
                        break;
                    case KeyEvent.VK_RIGHT:
                        game.move(+1);
                        break;
                    case KeyEvent.VK_SPACE:
                        game.dropDown();
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        });

        // Make the falling piece drop every second
        new Thread() {
            @Override
            public void run() {

                while (!game.stop) {
                    try {
//                        if (game.rotation == 0) {
//                            game.rotate(game.currentPiece.Orientation);
//                        }
                        game.dropDown();
                        Thread.sleep(1000);
                        
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
        }.start();
    }

    public Game(Tetris myTetris) {
        this.myTetris = myTetris;
        dimensionX = myTetris.DimensionX + 2;
        dimensionY = myTetris.DimensionY + 1;
        levelID = 0;
        level = 1;
        message = "";
        numLevel = myTetris.Levels.size();
        stop = false;
        Collections.sort(this.myTetris.Levels);
    }

}
