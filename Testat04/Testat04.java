public class Testat04 {

    public static void main(String[] args) {

        System.out.println("Bitte geben sie einen Satz mit mindestens 8 Wörtern ein");
        String input = Input.readString();
        int countWords = input.split("\\s").length;
        while(countWords < 8) {
            System.out.println("Der Satz ist zu kurz.");
            System.out.println("Bitte geben sie einen Satz mit mindestens 8 Wörtern ein");
            input = Input.readString();
            countWords = input.split("\\s").length;
        }


        String[] words = input.split("\\s");
        StringBuilder password = new StringBuilder();


        //Random number generation (Linear congruential generator (LCG))
        int max = countWords;
        int last = (int) (System.currentTimeMillis() % max);
        last = (last * 32719 + 3) % 32749;
        int rand = last % max;


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

        password = new StringBuilder(password.substring(0, rand) + "SWE" + password.substring(rand + 1, password.length()));

        if(password.length() % 2 == 0) {
            password.append('?');
        }  else {
            password.append('!');
        }

        System.out.println(password);

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

    }


}