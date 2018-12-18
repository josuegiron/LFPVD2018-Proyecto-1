/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author josuegiron
 */
public class Alphabet {

    String[] Lm = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "\\u00f1", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    String[] LM = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "\\u00d1", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    String[] N = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    String[] B = {"{", "}", "[", "]"};
    String[] PV = {"^", "v"};
    String[] Mm = {"<", ">"};
    String[] P = {",", ":", ";"};
    String[] Cm = {"\""};
    String[] SL = {"\n"};
    Map<Integer, String> ReservedWords = new HashMap<Integer, String>();
    Map<Integer, String> Pices = new HashMap<Integer, String>();
    Map<Integer, String[]> Token = new HashMap<Integer, String[]>();
    String[] des;
    private char[] specialChar = {' ', '\t', '\b', '\f', '\r', '\n'};

    public boolean ValidateAlphabet(String[] alph, char currentChar) {
        String currentCharStr = String.valueOf(currentChar);
        for (String item : alph) {
            if (item.equals(currentCharStr)) {
                return true;
            }
        }
        return false;
    }

    public boolean ValidateSpecialChar(char cuerrentChar) {
        for (char item : specialChar) {
            if (item == cuerrentChar) {
                return true;
            }
        }
        return false;
    }

    public boolean ValidateLexeme(String lexeme) {
        Iterator it = ReservedWords.keySet().iterator();
        while (it.hasNext()) {
            Integer key = (Integer) it.next();
            if (ReservedWords.get(key).equals(lexeme)) {
                return true;
            }

        }
        return false;
    }
    
    public boolean ValidatePice(String pice) {
        Iterator it = Pices.keySet().iterator();
        while (it.hasNext()) {
            Integer key = (Integer) it.next();
            if (Pices.get(key).equals(pice)) {
                return true;
            }

        }
        return false;
    }

    public String[] getToken(int id) {
        Iterator it = Token.keySet().iterator();
        while (it.hasNext()) {
            Integer key = (Integer) it.next();
            if (key.equals(id)) {
                return Token.get(key);
            }

        }
        return null;
    }

    public Alphabet() {
        ReservedWords.put(1, "Tetris");
        ReservedWords.put(2, "DimensionX");
        ReservedWords.put(3, "DimensionY");
        ReservedWords.put(4, "Nivel");
        ReservedWords.put(5, "Nombre");
        ReservedWords.put(6, "Codigo");
        ReservedWords.put(7, "Meta");
        ReservedWords.put(8, "Piezas");
        
        Pices.put(1, "I");
        Pices.put(2, "J");
        Pices.put(3, "L");
        Pices.put(4, "O");
        Pices.put(5, "S");
        Pices.put(6, "Z");
        Pices.put(7, "T");

        String[] reservada = {"Reservada", "Letra mayuscula, seguidas de letras minusculas que termina o no en Mayuscula"};
        Token.put(1, reservada);
        String[] numero = {"Numero", "Numero seguidos de numeros"};
        Token.put(2, numero);
        String[] cadena = {"Cadena", "Comillas seguida de numeros y letras y finaliza en comillas"};
        Token.put(3, cadena);
        String[] bloque = {"Llaves y corchetes", "Definen los bloques en el programa"};
        Token.put(4, bloque);
        String[] movhorzontal = {"Menor y mayor que", "Simbolo menor y mayor que"};
        Token.put(5, movhorzontal);
        String[] movVertical = {"Potencia y letra V", "Simbolos de potencia y la letra V"};
        Token.put(6, movVertical);
        String[] simbolos = {"Coma, dos puntos y punto y coma", "Simbolos especiales y reservados del lenguaje"};
        Token.put(7, simbolos);
    }

}
