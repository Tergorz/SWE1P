public class Testat04 {

    public static void main(String[] args) {

        /*
        #############################
        ######## Aufgabe 1 ##########
        #############################
        */


        System.out.println("Bitte geben sie einen Satz mit mindestens 8 Wörtern ein");
        String input = Input.readString();
        int countWords = input.split("\\s").length;
        String[] words = input.split("\\s");
        StringBuilder password = new StringBuilder();


        while(countWords < 8) {
            System.out.println("Der Satz ist zu kurz.");
            System.out.println("Bitte geben sie einen Satz mit mindestens 8 Wörtern ein");
            input = Input.readString();
            countWords = input.split("\\s").length;
        }



        /*
        Linear congruential generator
        (Pseudorandom number generator)
        */

        int max = countWords;
        int seed = (int) (System.currentTimeMillis());
        seed = (32719 * seed + 3) % 327; //X(n+1) = ( a * X(n) + c ) mod m
        int rand = seed % max; //Damit Ergebnis im Bereich 0 - countWords ist
        rand = (rand < 0 ? -rand : rand); //Damit Ergebnis nicht negativ ist


        for (String s : words) {
            if (s.charAt(0) == 'I' || s.charAt(0) == 'i') {
                password.append('1');
                continue;
            } else if (s.charAt(0) == 'E' || s.charAt(0) == 'e') {
                password.append('3');
                continue;
            } else if (s.charAt(0) == 'O' || s.charAt(0) == 'o') {
                password.append('0');
                continue;
            }


            char word = s.charAt(0);
            if ((password.length() + 1) % 3 == 0) {
                word = Character.toUpperCase(word);
            } else {
                word = Character.toLowerCase(word);
            }
            password.append(word);
        }

        password = new StringBuilder(password.substring(0, rand) + "SWE" + password.substring(rand, password.length()));

        if(password.length() % 2 == 0) {
            password.append('?');
        }  else {
            password.append('!');
        }

        System.out.println(password);

        /*
        #############################
        ######## Aufgabe 2 ##########
        #############################
        */

        System.out.println("Bitte gib das Passwort zur Überprüfung ein.");
        String eingabe = Input.readString();

        System.out.println("""
                Mittels welcher Methode soll deine Eingabe mit dem richtigen Passwort verglichen werden?\s
                a: referenceString == myStringB\s
                b: referenceString.equals(myStringB)\s
                c: referenceString.equalsIgnoreCase(myStringB)""");


        char compare = Input.readChar();
        boolean correct;

        switch(compare) {
            case 'a':
                correct = (eingabe == password.toString());
                System.out.println("Eingabe ist: " + correct);
                break;
            case 'b':
                correct = (eingabe.equals(password.toString()));
                System.out.println("Eingabe ist: " + correct);
                break;
            case 'c':
                correct = (eingabe.equalsIgnoreCase(password.toString()));
                System.out.println("Eingabe ist: " + correct);
                break;
            default:
                System.out.println("Ungültige Vergleichswahl!");
        }
        /*
         ##### Erklärung ################################################################################################################
         # a: Überprüft referenzielle Gleichheit (Ist gleiches String Object? → In diesem Fall nicht → false)                           #
         # b: Überprüft Gleichheit der Werte (Identische Charactersequenz? → bei korrekter Eingabe true)                                #
         # c: Überprüft Gleichheit der Werte ohne Case-Sensitivity → bei korrekter (auch reiner lowercase/uppercase) Eingabe true       #
         ################################################################################################################################
        */

    }


}