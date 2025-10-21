import java.util.Random;

public class Testat03 {

    static char[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

    public static char[] generatePassword() {
        Random r = new Random();
        int key1 = r.nextInt(7);
        int key2 = r.nextInt(7);
        while(key2 == key1) {
            key2 = r.nextInt(7);
        }
        int key3 = r.nextInt(7);
        while(key3 == key1 || key3 == key2) {
            key3 = r.nextInt(7);
        }
        int key4 = r.nextInt(7);
        while(key4 == key1 || key4 == key2 || key4 == key3) {
            key4 = r.nextInt(7);
        }
        char[] password = new char[4];
        password[0] = characters[key1];
        password[1] = characters[key2];
        password[2] = characters[key3];
        password[3] = characters[key4];
        return password;
    }

    public static char[] inputPassword() {
        char[] input = new char[4];
        System.out.println("Bite Passwort eingeben. \n Position 1: ");
        char input[0] = Input.readChar();
        System.out.println("Position 2: ");
        char input[1] = Input.readChar();
        System.out.println("Position 3: ");
        char input[2] = Input.readChar();
        System.out.println("Position 4: ");
        char input[3] = Input.readChar();
        return input;
    }

    

    public static void main(String[] args) {

        char[] password = generatePassword();
        int guesscount = 0;
        int correctcount = 0;
        int semicorrectcount = 0;
        char[] input = inputPassword();


        while(input != password && guesscount < 10) {
            guesscount++;
            if(input[0] == password[0]) {
                correctcount++;
            } else {
                for(int i = 0; i < password.length; i++) {
                    if(input[i] == password[0]) {
                        semicorrectcount++;
                    }
                }
            }
            if(input[1] == password[1]) {
                correctcount++;
            } else {
                for(int i = 0; i < password.length; i++) {
                    if(input[i] == password[1]) {
                        semicorrectcount++;
                    }
                }
            }
            if(input[2] == password[2]) {
                correctcount++;
            } else {
                for(int i = 0; i < password.length; i++) {
                    if(input[i] == password[2]) {
                        semicorrectcount++;
                    }
                }
            }
            if(input[3] == password[3]) {
                correctcount++;
            } else {
                for(int i = 0; i < password.length; i++) {
                    if(input[i] == password[3]) {
                        semicorrectcount++;
                    }
                }
            }

            switch(correctcount) {
                case 0:
                case 1:
                case 2:
                case 3:
                    System.out.println("Falsch! \n " + correctcount + " Buchstaben sind an der richtigen Position, " + semicorrectcount + " sind an der falsche Position.");
                    input = inputPassword();
                    break;
                case 4:
                    System.out.println("Passwort is korrekt! Du hast " + guesscount + " Versuche benötigt.");
                    break;
            }
            correctcount = 0;
            semicorrectcount = 0;   
        }
        if(guesscount == 10) {
            System.out.println("Keine Versuche übrig");
        }
    }


}
