import java.util.Random;

public class Testat05 {

    static final char[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    static final int FAIL = 0;
    static final int SUCCESS = 1;
    static final int INVALID = -1;
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



    public static void generateBoard() {


        for(int i = 0; i < 10; i++) {

            for(int j = 0; j < 10; j++) {
                board[i][j] = new BoardElement(i, j);
            }
        }
    }

    public static void randomizeBombs() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                boolean chance = new Random().nextInt(4) == 0;
                if(chance) {
                    board[i][j].setBomb();
                }
            }
        }
    }
    public static void printBoard() {
        System.out.println("    A B C D E F G H I J");
        System.out.println("    ————————————————————");
        for(int i = 0; i < 10; i++) {
            System.out.print(i + " | ");
            for(int j = 0; j < 10; j++) {
                System.out.print(board[i][j].getValue() + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("    ————————————————————");
    }

    public static int[] getInput(String action) {
        int[] input = {-1, -1};
        while(input[0] == -1 || input[1] == -1) {
            System.out.println("Bitte Koordinaten zwischen a0 - j9 eingeben: ");
            String inputString = Input.readString();
            char tempY = inputString.charAt(0);

            for(int i = 0; i < characters.length; i++) {
                if(tempY == characters[i]) {
                    input[0] = i;
                    break;
                }
            }

            int tempX = inputString.charAt(1) - '0';
            if(tempX >= 0 && tempX <= 9) {
                input[1] = tempX;
            }
            if(input[0] == -1 || input[1] == -1) {
                System.out.println("Bitte gültige Koordinaten eingeben.");
            }
        }

        return input;
    }

    public static int handleInput(int[] input, String action) {
        int y = input[0];
        int x = input[1];
        switch (action) {
            case "reveal":

                if(board[y][x].isRevealed()) {
                    return INVALID;
                }
                if(board[y][x].isBomb()) {
                    board[y][x].setRevealed();
                    board[y][x].setValue('#');
                    return FAIL;
                }
                if(countBombsAround(input) == 0) {
                    board[y][x].setRevealed();

                }
                board[y][x].setRevealed();
                board[y][x].setValue((char)countBombsAround(input));
                break;
            case "flag":
                board[y][x].setValue('#');
                break;
        }


        return SUCCESS;
    }

    public static void cascadeReveal(int[] coords) {
        int y = coords[0];
        int x = coords[1];
        board[y][x].setRevealed();
        board[y][x].setValue(' ');
        try { if(countBombsAround(new int[]{y, x + 1}) == 0) { cascadeReveal(new int[]{y, x + 1}); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
        try { if(countBombsAround(new int[]{y, x - 1}) == 0) { cascadeReveal(new int[]{y, x - 1}); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
        try { if(countBombsAround(new int[]{y + 1, x + 1}) == 0) { cascadeReveal(new int[]{y + 1, x + 1}); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
        try { if(countBombsAround(new int[]{y + 1, x}) == 0) { cascadeReveal(new int[]{y + 1, x}); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
        try { if(countBombsAround(new int[]{y + 1, x - 1}) == 0) { cascadeReveal(new int[]{y + 1, x - 1}); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
        try { if(countBombsAround(new int[]{y - 1, x + 1}) == 0) { cascadeReveal(new int[]{y - 1, x + 1}); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
        try { if(countBombsAround(new int[]{y - 1, x}) == 0) { cascadeReveal(new int[]{y - 1, x}); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
        try { if(countBombsAround(new int[]{y - 1, x - 1}) == 0) { cascadeReveal(new int[]{y - 1, x - 1}); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
    }

    public static int countBombsAround(int[] coords) {
        int count = 0;
        int y = coords[0];
        int x = coords[1];
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

    public static boolean isBoardComplete() {
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(!board[i][j].isBomb() && !board[i][j].isRevealed()) {
                    return false;
                }
            }
        }
        return true;
    }



    public static void main(String[] args) {
        generateBoard();
        randomizeBombs();
        while(!isBoardComplete()) {
            printBoard();
            System.out.println("Aktion wählen: (reveal / flag) \n");
            String action = Input.readString();

            int[] input = getInput(action);
            switch (handleInput(input, action)) {
                case SUCCESS:
                    break;
                case FAIL:
                    System.out.println("Eine Bombe ist explodiert.");
                    return;
                case INVALID:
                    System.out.println("Diese Koordinate wurde bereits aufgedeckt.");
                    break;
            }


        }



    }

}