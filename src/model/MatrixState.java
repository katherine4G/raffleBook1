/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kathe
 */
public class MatrixState {
    
    private static int winnerNumber;
    private static String raffleName;

    public static String getRaffleName() {
        return raffleName;
    }

    public static void setRaffleName(String name) {
        raffleName = name;
    }

    public static int getWinnerNumber() {
        return winnerNumber;
    }

    public static void setWinnerNumber(int number) {
        winnerNumber = number;
    }
}
