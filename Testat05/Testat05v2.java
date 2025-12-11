/**
 * Softwareentwicklung 1 Praktikum (SWE1P), WS2024/24
 *
 * @author M.Sc. Jan-Philipp Göbel, Technische Hochschule Ingolstadt
 * @version uxd_swe1p_ws2023_27.10.2024
 * @since 30-Nov-23
 **/


// Library for randomized numbers
import java.util.Arrays;
import java.util.Random;
// Library for Scanner
import java.util.Scanner;

public class Testat05v2 {
    // ################################
    // Declaration of "Class" variables
    //
    // -> accessible in the whole class
    // ################################

    // The two relevant fields (hidden / visible)
    //private static char[][] bombField = new char[10][10];
    //private static char[][] playField = new char[10][10];
    static final int FAIL = 0;
    static final int SUCCESS = 1;
    static final int INVALID = -1;
    static final char[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    static BoardElement[][] board = new BoardElement[10][10];

    public static class BoardElement {
        public int x;
        public int y;
        public char value;
        public boolean bomb;
        public boolean revealed;

        public BoardElement(int x, int y) {
            this.x = x;
            this.y = y;
            this.value = '?';
            this.bomb = false;
            this.revealed = false;
        }
        public void setRevealed() {
            this.revealed = true;
        }
        public boolean isRevealed() {
            return this.revealed;
        }
        public void setBomb() {
            this.bomb = true;
        }
        public boolean isBomb() {
            return bomb;
        }
        public void setValue(char value) {
            this.value = value;
        }
        public char getValue() {
            return this.value;
        }
    }

    // The two parameters for our position determination
    private static int X, Y;


    // ###########################################################
    //                    !!! COMPLETED !!!
    // ###########################################################
    public static void main(String[] args) {

        // Header
        System.out.println("*******************************");
        System.out.println("Willkommen zum MineSweeper-Game");
        System.out.println("*******************************");
        System.out.println();

        // ####################################
        // Declaration of "Main" variables
        //
        // -> available only in "main" function
        // ####################################
        boolean gameover = false;

        // Initialize every element with '?'
        InitializePlayField();

        // Initialize bombs with '#' and...
        InitializeBombField();

        // Game Loop
        do {

            // Draw field
            PrintPlayField();

            // User input
            GetUserInput("Bitte gib eine mögliche Zelle (zum Beispiel \"A7\") ein!");

            // Reveal Selection on board
            int pick = RevealPick(X, Y);

            // Determine what this reveal means
            switch (pick) {
                case 0:
                    // Bomb
                    gameover = true;
                    break;
                case 1:
                    // None
                    break;
            }
        } while (!gameover && !IsBoardCompleted());

        // Final board
        PrintPlayField();
        System.out.println();

        // Rating of the game
        if (gameover) {
            System.out.println("Ohhh nein... du hast eine Bombe erwischt!");
            System.out.println("--- --- --- --- GAME OVER --- --- --- ---");
        } else {
            System.out.println("YEAH... du hast alle Bomben erfolgreich detektiert!");
            System.out.println("--- --- --- --- --- GRATULATION --- --- --- --- ---");
        }
    }








    // ####################################################
    //
    // This Function should return true, if the "value" is
    // inside the range of our accessible field dimensions.
    //
    // ####################################################
    //                   Finish this one!
    // ####################################################
    private static boolean IsInRange(int value) {

        if(value >= 0 && value < 10) {
            return true;
        }
        return false;
    }


    // ###################################################
    //
    // This Function should return true, if the coordiante
    // combination has been already checked and revealed.
    //
    // ###################################################
    //                   Finish this one!
    // ###################################################
    private static boolean IsAlreadyRevealed(int posX, int posY) {
        return board[posY][posX].isRevealed(); //redundant
    }


    // #################################################
    //
    // This function should return true, when the passed
    // coordiantes would hit a bomb in the hidden field.
    //
    // #################################################
    //                   Finish this one!
    // #################################################
    private static boolean CheckForBomb(int posX, int posY) {
        return board[posY][posX].isBomb(); //redundant
    }


    // ######################################################
    //
    // This function "copys" the value of the hidden field to
    // the visible one and returns the newly uncovered value.
    //
    // ######################################################
    //                    Finish this one!
    // ######################################################
    private static int RevealPick(int x, int y) {


        if (board[y][x].isRevealed()) {
            return INVALID;
        }
        if (board[y][x].isBomb()) {
            board[y][x].setRevealed();
            board[y][x].setValue('#');
            return FAIL;
        }
        if (CountBombsAround(y, x) == 0) {
            RevealDirectNeighbours(y, x);
        } else {
            board[y][x].setRevealed();
            board[y][x].setValue(Character.forDigit(CountBombsAround(y, x), 10));
        }

        return SUCCESS;
    }


    // ####################################################
    //
    // This Function checks the user input and parses it
    // into the two variables X (column) and Y (row).
    //
    // ####################################################
    //                   Finish this one!
    // ####################################################
    private static void GetUserInput(String printOutBevorInput) {

        // Prints a message bevor the user input gets requested.
        System.out.println(printOutBevorInput);

        // User input
        String input = Input.readString().toUpperCase();

        // Checks for correctness of input -> read "Message to see, what single check should be done in the different checks"
        // -> YOUR IMPLEMENTATION FOR THE DIFFERENT CHECKS

        if (input.length() != 2) {
            GetUserInput("Eingabe fehlerhaft, zu viele/ wenige Zeichen. Bitte wiederholen.");
        }

        if (input.charAt(0) < 'A' || input.charAt(0) > 'J') {
            GetUserInput("Falsche Eingabe für die Spalte, bitte akzeptierten Spalten-Code eingeben.");
        }

        if (input.charAt(1) < '0' || input.charAt(1) > '9') {
            GetUserInput("Falsche Eingabe für die Zeile, bitte akzeptierten Zeilen-Wert eingeben.");
        }

        if (board[input.charAt(1) - '0'][input.charAt(0) - 'A'].isRevealed()) {
            GetUserInput("Dein ausgewähltes Feld wurde bereits aufgedeckt, bitte anderes Feld eingeben.");
        }
        X = input.charAt(0) - 'A';
        Y = input.charAt(1) - '0';
    }


    // ###########################################################
    //
    // This function randomly places bombs '#' of the specified
    // number of "bombCount" on the bombField. It must be ensured,
    // that no bomb is placed on top of an already placed bomb.
    //
    // ###########################################################
    //                      Finish this one!
    // ###########################################################
    private static void RandomizeBombs(int bombCount) {
        int count = 0;
        while(count < bombCount) {
            for(int i = 0; i < 10; i++) {
                for(int j = 0; j < 10; j++) {
                    boolean chance = new Random().nextInt(20) == 0;
                    if(chance && count < bombCount) {
                        board[i][j].setBomb();
                        board[i][j].setValue('#');
                        count++;
                    }
                }
            }
        }

    }


    // ########################################################
    //
    // This function checks, if all spots in the visible field,
    // except the places covered by bombs, were revealed.
    //
    // ########################################################
    //                     Finish this one!
    // ########################################################
    private static boolean IsBoardCompleted() {
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(!board[i][j].isBomb() && !board[i][j].isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }


    // ###################################################
    //
    // This function calculates, how many bombs are around
    // the passed coordinate spot. [max count can be 8]
    //
    // ###################################################
    //                    Finish this one!
    // ###################################################
    private static int CountBombsAround(int y, int x) {
        int count = 0;

        try { if(board[y][x+1].isBomb()) { count++; } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
        try { if(board[y][x-1].isBomb()) { count++; } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
        try { if(board[y+1][x+1].isBomb()) { count++; } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
        try { if(board[y+1][x].isBomb()) { count++; } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
        try { if(board[y+1][x-1].isBomb()) { count++; } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
        try { if(board[y-1][x+1].isBomb()) { count++; } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
        try { if(board[y-1][x].isBomb()) { count++; } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
        try { if(board[y-1][x-1].isBomb()) { count++; } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }

        return count;
    }










    // #########################################################
    //
    // This function reveals the direct neighbours of an "empty"
    // spot... checks recursively (iterativ) if one of the newly
    // revealed spots is also ' ' and continues with this loop
    // till no "empty" spot is remaining in this connected area.
    //
    // #########################################################
    //                     !!! COMPLETED !!!
    // #########################################################
    private static void RevealDirectNeighbours(int y, int x) {

        board[y][x].setRevealed();
        int bombCount = CountBombsAround(y, x);

        if (bombCount == 0) {
            board[y][x].setValue(' ');


            try { if(!board[y][x + 1].isRevealed() && !board[y][x + 1].isBomb()) { RevealDirectNeighbours(y, x + 1); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
            try { if(!board[y][x - 1].isRevealed() && !board[y][x - 1].isBomb()) { RevealDirectNeighbours(y, x - 1); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
            try { if(!board[y + 1][x + 1].isRevealed() && !board[y + 1][x + 1].isBomb()) { RevealDirectNeighbours(y + 1, x + 1); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
            try { if(!board[y + 1][x].isRevealed() && !board[y + 1][x].isBomb()) { RevealDirectNeighbours(y + 1, x); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
            try { if(!board[y + 1][x - 1].isRevealed() && !board[y + 1][x - 1].isBomb()) { RevealDirectNeighbours(y + 1, x - 1); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
            try { if(!board[y - 1][x + 1].isRevealed() && !board[y - 1][x + 1].isBomb()) { RevealDirectNeighbours(y - 1, x + 1); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
            try { if(!board[y - 1][x].isRevealed() && !board[y - 1][x].isBomb()) { RevealDirectNeighbours(y - 1, x); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
            try { if(!board[y - 1][x - 1].isRevealed() && !board[y - 1][x - 1].isBomb()) { RevealDirectNeighbours(y - 1, x - 1); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
        } else {
            board[y][x].setValue(Character.forDigit(bombCount, 10));
        }
    }


    // ###########################################################
    //
    // This function prints the current state of the visible field
    // in the TERMINAL of our IDE or in the Command Line Interface
    //
    // ###########################################################
    //                    !!! COMPLETED !!!
    // ###########################################################
    private static void PrintPlayField() {

        System.out.println("    A B C D E F G H I J");
        System.out.println("    --------------------");
        for(int i = 0; i < 10; i++) {
            System.out.print(i + " | ");
            for(int j = 0; j < 10; j++) {
                System.out.print(board[i][j].getValue() + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("    --------------------");
    }


    // #########################################################
    //
    // This function initializes the whole playField with '?',
    // so we can start to search for bombs in the visible field.
    //
    // #########################################################
    //                    !!! COMPLETED !!!
    // #########################################################
    private static void InitializePlayField() {
        for(int i = 0; i < 10; i++) {

            for(int j = 0; j < 10; j++) {
                board[i][j] = new BoardElement(i, j);
            }
        }
    }


    // ###########################################################
    //
    // This function initializes the bombField with [10] bombs and
    // calculates for every other spot in the field the number
    // of bombs in their surrounding area [max count can be 8] or
    // with ' ' when there is no bomb around the current spot.
    //
    // ###########################################################
    //                    !!! COMPLETED !!!
    // ###########################################################
    private static void InitializeBombField() {

        // 1) Generation of a number of bombs
        RandomizeBombs(10);

        // 2) Determine the number of bombs around each other spot
        /*for (int x = 0; x < bombField.length; x++) {
            for (int y = 0; y < bombField[0].length; y++) {

                if (CheckForBomb(x, y)) {
                    // skip the bombs
                    continue;
                }

                int count = CountBombsAround(x, y);

                if(count == 0) {
                    // "empty" spot
                    bombField[y][x] = ' ';
                } else {
                    // bombs around
                    bombField[y][x] = (char)(count + (int)'0');
                }
            }
        }*/
    }
}
