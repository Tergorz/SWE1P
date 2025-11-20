import java.util.Random;

public class Testat05 {

    static char[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
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
            this.value = '0';
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

    public static int[] getInput() {
        int[] input = {-1, -1};
        while(input[0] == -1 || input[1] == -1) {

            System.out.println("Bitte y-Koordinate (A-J) eingeben: ");
            char tempY = Character.toUpperCase(Input.readChar());
            for(int i = 0; i < characters.length; i++) {
                if(tempY == characters[i]) {
                    input[0] = i;
                    break;
                }
            }
            System.out.println("Bitte x-Koordinate (0-9) eingeben: ");
            int tempX = Input.readInt();
            if(tempX >= 0 && tempX <= 9) {
                input[1] = tempX;
            }
        }

        return input;
    }

    public static boolean isBoardComplete() {
        return false;
    }



    public static void main(String[] args) {
        generateBoard();
        randomizeBombs();
        while(!isBoardComplete()) {
            printBoard();
            int[] input = getInput();
        }



    }

}