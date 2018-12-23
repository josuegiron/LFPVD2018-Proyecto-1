/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import scanner.*;

/**
 *
 * @author josuegiron
 */
public class ErrorP {
    private Alphabet alph = new Alphabet();
    public int ID, Row, Colum, Offset;
    
    public int Length(){
        return Error.length();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getRow() {
        return Row;
    }

    public void setRow(int Row) {
        this.Row = Row;
    }

    public int getColum() {
        return Colum;
    }

    public void setColum(int Colum) {
        this.Colum = Colum;
    }

    public String getError() {
        return Error;
    }

    public void setError(String Error) {
        this.Error = Error;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public ErrorP(int ID, int Row, int Colum, String Error, String Description, int Offset) {
        this.ID = ID;
        this.Row = Row;
        this.Colum = Colum;
        this.Error = Error;
        this.Description = Description;
        this.Offset = Offset;
    }
    

    public ErrorP() {
    }
    
    public String Error, Description;
    
    
    
    public void PrintError(int rwID, Token token){
        System.out.println("Error lexico: se esperaba la palabra reservada " + alph.GetReservedWord(rwID)[2] + " en: " + token.Row + ", " + token.Colum);
    }
    
    public void PrintError(String type, Token token){
        System.out.println("Error lexico: se esperaba  " + type + " en: " + token.Row + ", " + token.Colum);
    }
}
