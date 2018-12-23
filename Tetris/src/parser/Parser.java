/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.util.ArrayList;
import scanner.Alphabet;
import scanner.Token;
import tetris.Level;
import tetris.Piece;
import tetris.Tetris;
import tetris.Game;

/**
 *
 * @author josue
 */
public class Parser {

    public ArrayList<Token> tokenList;
    private Alphabet alph;
    public Tetris myTetris;
    private ErrorP er;

    public Parser(ArrayList<Token> tokenList) {
        er = new ErrorP();
        this.tokenList = tokenList;
        alph = new Alphabet();
        myTetris = new Tetris();
        tetris();
        Game test = new Game(myTetris);
        test.Start();
        //System.out.println(myTetris.Levels.get(0).Pieces.get(0).Type);
    }

    private boolean validateToken(String lexeme) {
        //System.out.println(tokenList.get(0).Lexeme);
        if (tokenList.get(0).Lexeme.equals(lexeme)) {
            return true;
        }
        return false;
    }

    private boolean validateToken(String[] lexemes) {
        System.out.println(tokenList.get(0).Lexeme);
        for (String lexeme : lexemes) {
            if (tokenList.get(0).Lexeme.equals(lexeme)) {
                System.out.println("SI APARECE");
                return true;
            }
        }

        return false;
    }

    private boolean validateTokenType(int tokenID) {
        //System.out.println(tokenList.get(0).Lexeme);
        if (tokenList.get(0).Type == tokenID) {
            return true;
        }
        return false;
    }

    private void tetris() {
        if (!validateToken("{")) {
            er.PrintError("{", tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        if (!validateToken(alph.GetReservedWord(1))) {
            er.PrintError(1, tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        if (!validateToken(":")) {
            er.PrintError(":", tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        if (validateToken("[")) {
            tokenList.remove(0);
        } else {
            er.PrintError("[", tokenList.get(0));
        }
        switch (tokenList.get(0).Lexeme) {
            case "nivel":
            case "Nivel":
            case "NIVEL":
                System.out.println("ACA VOY");
                niveles();
                dimensionX();
                dimensionY();
                break;
            case "dimensionX":
            case "DimensionX":
            case "DIMENSIONX":
                System.out.println("ACA MULA");
                dimensionX();
                dimensionY();
                niveles();
                break;
        }
        if (!validateToken("]")) {
            er.PrintError("]", tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        if (!validateToken("}")) {
            er.PrintError("}", tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
    }

    private void dimensionX() {
        int x = 0;
        if (!validateToken(alph.GetReservedWord(2))) {
            er.PrintError(2, tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        if (!validateToken(":")) {
            er.PrintError(":", tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        if (!validateTokenType(2)) {
            er.PrintError("Numero", tokenList.get(0));
        } else {
            x = Integer.parseInt(tokenList.get(0).Lexeme);
            tokenList.remove(0);
        }
        if (!validateToken(";")) {
            er.PrintError(";", tokenList.get(0));
        } else {
            myTetris.setDimensionX(x);
            tokenList.remove(0);
        }
    }

    private void dimensionY() {
        int y = 0;
        if (!validateToken(alph.GetReservedWord(3))) {
            er.PrintError(3, tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        if (!validateToken(":")) {
            er.PrintError(":", tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        if (!validateTokenType(2)) {
            er.PrintError("numero", tokenList.get(0));
        } else {
            y = Integer.parseInt(tokenList.get(0).Lexeme);
            tokenList.remove(0);
        }
        if (!validateToken(";")) {
            er.PrintError(";", tokenList.get(0));
        } else {
            myTetris.setDimensionY(y);
            tokenList.remove(0);
        }
    }

    private void niveles() {
        switch (tokenList.get(0).Lexeme) {
            case "nivel":
            case "Nivel":
            case "NIVEL":
                nivel();
                break;
            default:
                break;
        }
    }

    private void nivel() {
        Level newLevel = new Level();
        if (!validateToken(alph.GetReservedWord(4))) {
            er.PrintError(4, tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        if (!validateToken(":")) {
            er.PrintError(":", tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        if (!validateToken("[")) {
            er.PrintError("[", tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        newLevel.setName(name());
        newLevel.setCode(code());
        newLevel.setGoal(goal());
        newLevel.setPieces(pieces());
        if (!validateToken("]")) {
            er.PrintError("]", tokenList.get(0));
        } else {
            myTetris.Levels.add(newLevel);
            tokenList.remove(0);
        }
        niveles();
    }

    private String name() {
        String name = "";
        if (!validateToken(alph.GetReservedWord(5))) {
            er.PrintError(5, tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        if (!validateToken(":")) {
            er.PrintError(":", tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        if (validateTokenType(3)) {
            name = tokenList.get(0).Lexeme;
            tokenList.remove(0);
        } else {
            er.PrintError("cadena de caracteres", tokenList.get(0));
        }
        if (!validateToken(";")) {
            er.PrintError(";", tokenList.get(0));
        } else {
            tokenList.remove(0);
            return name;
        }
        return name;
    }

    private int code() {
        int code = 0;
        if (!validateToken(alph.GetReservedWord(6))) {
            er.PrintError(6, tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        if (!validateToken(":")) {
            er.PrintError(":", tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        if (validateTokenType(2)) {
            code = Integer.parseInt(tokenList.get(0).Lexeme);
            tokenList.remove(0);
        } else {
            er.PrintError("Numero", tokenList.get(0));
        }
        if (!validateToken(";")) {
            er.PrintError(";", tokenList.get(0));
        } else {
            tokenList.remove(0);
            return code;
        }
        return code;
    }

    private int goal() {
        int code = 0;
        if (!validateToken(alph.GetReservedWord(7))) {
            er.PrintError(7, tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        if (!validateToken(":")) {
            er.PrintError(":", tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        if (validateTokenType(2)) {
            code = Integer.parseInt(tokenList.get(0).Lexeme);
            tokenList.remove(0);
        } else {
            er.PrintError("Numero", tokenList.get(0));
        }
        if (!validateToken(";")) {
            er.PrintError(";", tokenList.get(0));
        } else {
            tokenList.remove(0);
            return code;
        }
        return code;
    }

    private ArrayList<Piece> pieces() {
        ArrayList<Piece> pieces = new ArrayList<Piece>();
        if (!validateToken(alph.GetReservedWord(8))) {
            er.PrintError(8, tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        if (!validateToken(":")) {
            er.PrintError(":", tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        if (validateToken("[")) {
            tokenList.remove(0);
        } else {
            er.PrintError("{", tokenList.get(0));
        }
        letters(pieces);
        if (validateToken("]")) {
            tokenList.remove(0);
            return pieces;
        } else {
            er.PrintError("]", tokenList.get(0));
        }
        return pieces;
    }

    private void letters(ArrayList<Piece> pieces) {
        if (alph.ValidatePice(tokenList.get(0).Lexeme)) {
            letter(pieces);
        }
    }

    private void letter(ArrayList<Piece> pieces) {
        Piece piece = new Piece();
        if (!alph.ValidatePice(tokenList.get(0).Lexeme)) {
            er.PrintError("un nombre de pieza valido", tokenList.get(0));
        } else {
            piece.setType(alph.GetLetterID(tokenList.get(0).Lexeme));
            tokenList.remove(0);
        }
        if (!validateToken(",")) {
            er.PrintError(",", tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        if (alph.ValidatePosition(tokenList.get(0).Lexeme)) {
            piece.setOrientation(getPositionID(tokenList.get(0).Lexeme));
            tokenList.remove(0);
        } else {
            er.PrintError("una posicion valida", tokenList.get(0));
        }
        if (!validateToken(";")) {
            er.PrintError(";", tokenList.get(0));
        } else {
            tokenList.remove(0);
        }
        pieces.add(piece);
        letters(pieces);
    }

    private int getPositionID(String position) {
        switch (position) {
            case "^":
                return 1;
            case "V":
            case "v":
                return 3;
            case "<":
                return 4;
            case ">":
                return 2;
            default:
                return 1;
        }
    }
}
