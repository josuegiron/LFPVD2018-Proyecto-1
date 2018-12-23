/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.util.ArrayList;

/**
 *
 * @author josue
 */
public class Level implements Comparable<Level> {
    public String Name;
    public int Code, Goal;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public int getGoal() {
        return Goal;
    }

    public void setGoal(int Goal) {
        this.Goal = Goal;
    }
    public ArrayList<Piece> Pieces;

    public ArrayList<Piece> getPieces() {
        return Pieces;
    }

    public void setPieces(ArrayList<Piece> Pieces) {
        this.Pieces = Pieces;
    }

    @Override
    public int compareTo(Level level) {
        if (Code < level.Code) {
                return -1;
            }
            if (Code > level.Code) {
                return 1;
            }
            return 0;
    }
}
