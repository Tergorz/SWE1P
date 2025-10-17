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


    public static void main(String[] args) {

        char[] password = generatePassword();

        System.out.println("Bite Passwort eingeben. \n Position 1: ");
        char input1 = Input.readChar();
        System.out.println("Position 2: ");
        char input2 = Input.readChar();
        System.out.println("Position 3: ");
        char input3 = Input.readChar();
        System.out.println("Position 4: ");
        char input4 = Input.readChar();

        char[] input = new char[4];
        input[0] = input1;
        input[1] = input2;
        input[2] = input3;
        input[3] = input4;

        while(input != password) {

            }
        }


}

