/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

/**
 *
 * @author josue
 */
public class Piece {

    public int Type, Orientation;

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public int getOrientation() {
        return Orientation;
    }

    public void setOrientation(int Orientation) {
        this.Orientation = Orientation;
    }
}
