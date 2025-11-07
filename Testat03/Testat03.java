import java.util.Random;

public class Testat03 {

    static char[] characters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
    static int guesscount = 1;
    static String history = "Deine bisherigen Eingaben: \n";

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
        String inputString;
        System.out.println("\n \nVersuch: " + guesscount + "\n Bitte Passwort eingeben: ");
        inputString = Input.readString().toUpperCase();

        while(inputString.length() != 4 || !inputString.matches("[A-H]{4}")) {
            if(inputString.length() != 4) {
                System.out.println("Bitte geben sie genau 4 Buchstaben ein.");
            }
            if(!inputString.matches("[A-H]*")) {
                System.out.println("Bitte geben sie nur Buchstaben zwischen 'A' und 'H' ein.");
            }

            System.out.println("\n \n Versuch: " + guesscount + "\n Bitte Passwort eingeben: ");
            inputString = Input.readString().toUpperCase();
        }
        input[0] = inputString.charAt(0);
        input[1] = inputString.charAt(1);
        input[2] = inputString.charAt(2);
        input[3] = inputString.charAt(3);
        guesscount++;
        return input;
    }

    

    public static void main(String[] args) {

        char[] password = generatePassword();
        int correctcount = 0;
        int semicorrectcount = 0;


        while (guesscount <= 10) {
            char[] input = inputPassword();
            if (input[0] == password[0]) {
                correctcount++;
            } else {
                for (int i = 0; i < password.length; i++) {
                    if (input[i] == password[0]) {
                        semicorrectcount++;
                        break;
                    }
                }
            }
            if (input[1] == password[1]) {
                correctcount++;
            } else {
                for (int i = 0; i < password.length; i++) {
                    if (input[i] == password[1]) {
                        semicorrectcount++;
                        break;
                    }
                }
            }
            if (input[2] == password[2]) {
                correctcount++;
            } else {
                for (int i = 0; i < password.length; i++) {
                    if (input[i] == password[2]) {
                        semicorrectcount++;
                        break;
                    }
                }
            }
            if (input[3] == password[3]) {
                correctcount++;
            } else {
                for (int i = 0; i < password.length; i++) {
                    if (input[i] == password[3]) {
                        semicorrectcount++;
                        break;
                    }
                }
            }
            if (correctcount == 4) {
                System.out.println("Passwort is korrekt! Du hast " + (guesscount - 1) + " Versuche benötigt.");
                return;
            } else {
                history += "" + input[0] + input[1] + input[2] + input[3] + " | " + correctcount +"x richtig, " + semicorrectcount + "x falsche Pos. \n";
                System.out.println("Falsch! \n " + correctcount + " Buchstaben sind an der richtigen Position, " + semicorrectcount + " sind an der falsche Position.");
                System.out.println(history);
            }


            correctcount = 0;
            semicorrectcount = 0;
        }

        String solution = new String(password);
        System.out.println("Keine Versuche übrig. \n Das Passwort ist " + solution);

    }


}
