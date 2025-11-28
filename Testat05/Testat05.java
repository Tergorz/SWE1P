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
                boolean chance = new Random().nextInt(5) == 0;
                if(chance) {
                    board[i][j].setBomb();
                    board[i][j].setValue('#');
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

    public static String getAction() {
        System.out.println("Aktion wählen: ('reveal' / 'flag') \n");
        String action = Input.readString();
        while(!action.equals("reveal") && !action.equals("flag")) {
            System.out.println("Bitte gültige aktion eingeben. \n Aktion wählen: (reveal / flag) \n");
            action = Input.readString();
        }
        return action;
    }

    public static int[] getInput(String action) {
        int[] input = {-1, -1};
        while(input[0] == -1 || input[1] == -1) {
            System.out.println("Bitte Koordinaten zwischen a0 - j9 eingeben: ");
            String inputString = Input.readString().toUpperCase();
            char tempX = inputString.charAt(0);

            for(int i = 0; i < characters.length; i++) {
                if(tempX == characters[i]) {
                    input[1] = i;
                    break;
                }
            }

            int tempY = inputString.charAt(1) - '0';
            if(tempY >= 0 && tempY <= 9) {
                input[0] = tempY;
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
                if(countBombsAround(y, x) == 0) {
                    cascadeReveal(y, x);
                } else {
                    board[y][x].setRevealed();
                    board[y][x].setValue(Character.forDigit(countBombsAround(y, x), 10));
                }
                break;
            case "flag":
                board[y][x].setValue('#');
                break;
        }


        return SUCCESS;
    }

    public static void cascadeReveal(int y, int x) {


        board[y][x].setRevealed();
        int bombCount = countBombsAround(y, x);

        if (bombCount == 0) {
            board[y][x].setValue(' ');


            try { if(!board[y][x + 1].isRevealed() && !board[y][x + 1].isBomb()) { cascadeReveal(y, x + 1); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
            try { if(!board[y][x - 1].isRevealed() && !board[y][x - 1].isBomb()) { cascadeReveal(y, x - 1); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
            try { if(!board[y + 1][x + 1].isRevealed() && !board[y + 1][x + 1].isBomb()) { cascadeReveal(y + 1, x + 1); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
            try { if(!board[y + 1][x].isRevealed() && !board[y + 1][x].isBomb()) { cascadeReveal(y + 1, x); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
            try { if(!board[y + 1][x - 1].isRevealed() && !board[y + 1][x - 1].isBomb()) { cascadeReveal(y + 1, x - 1); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
            try { if(!board[y - 1][x + 1].isRevealed() && !board[y - 1][x + 1].isBomb()) { cascadeReveal(y - 1, x + 1); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
            try { if(!board[y - 1][x].isRevealed() && !board[y - 1][x].isBomb()) { cascadeReveal(y - 1, x); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
            try { if(!board[y - 1][x - 1].isRevealed() && !board[y - 1][x - 1].isBomb()) { cascadeReveal(y - 1, x - 1); } } catch (ArrayIndexOutOfBoundsException e) { /*do nothing*/ }
        } else {
            board[y][x].setValue(Character.forDigit(bombCount, 10));
        }
    }


    public static int countBombsAround(int y, int x) {
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

            String action = getAction();
            int[] input = getInput(action);

            switch (handleInput(input, action)) {
                case SUCCESS:
                    break;
                case FAIL:
                    System.out.println("Eine Bombe ist explodiert. \n Game Over.");
                    return;
                case INVALID:
                    System.out.println("Diese Koordinate wurde bereits aufgedeckt.");
                    break;
            }


        }



    }

}