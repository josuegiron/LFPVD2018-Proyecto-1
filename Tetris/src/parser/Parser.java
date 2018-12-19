/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.ArrayList;
import scanner.Alphabet;
import scanner.Token;
import tetris.Tetris;

/**
 *
 * @author josue
 */
public class Parser {

    public ArrayList<Token> tokenList;
    private Alphabet alph;
    public Tetris myTetris;

    public Parser(ArrayList<Token> tokenList) {
        this.tokenList = tokenList;
        alph = new Alphabet();
        myTetris = new Tetris();
        tetris();
    }

    private boolean validateToken(String lexeme) {
        System.out.println(tokenList.get(0).Lexeme);
        if (tokenList.get(0).Lexeme.equals(lexeme)) {
            tokenList.remove(0);
            return true;
        }
        return false;
    }

    private void tetris() {

        if (!validateToken("{")) {
            System.out.println("Error sintactico, se esperaba {");
        }
        if (!validateToken(alph.GetReservedWord(1))) {
            System.out.println("Error sintactico, se esperaba palabra reservada");
        }
        if (!validateToken(":")) {
            System.out.println("Error sintactico, se esperaba :");
        }
        if (validateToken("[")) {
            System.out.println("Deberia seguir validadndo...");
        } else {
            System.out.println("Error sintactico, se esperaba [");
        }
        if (!validateToken("]")) {
            System.out.println("Error sintactico, se esperaba ]");
        }
        if (!validateToken("}")) {
            System.out.println("Error sintactico, se esperaba }");
        }
    }
}
