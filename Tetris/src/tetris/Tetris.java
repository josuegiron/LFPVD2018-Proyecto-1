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
public class Tetris {
    public int DimensionX, DimensionY;
    public ArrayList<Level> Levels;
    public Tetris(){
        Levels = new ArrayList<Level>();
        
    }

    public int getDimensionX() {
        return DimensionX;
    }

    public void setDimensionX(int DimensionX) {
        this.DimensionX = DimensionX;
    }

    public int getDimensionY() {
        return DimensionY;
    }

    public void setDimensionY(int DimensionY) {
        this.DimensionY = DimensionY;
    }
    
    public void Play(){
        
    }
}
