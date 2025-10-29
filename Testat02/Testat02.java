public class Testat02 {

    public static int inputSegment() {
        System.out.println("Bitte Anzahl der Segmente (Zahl zw. 4 & 12) festlegen. \n Eingabe: ");
        int anzahl = Input.readInt();
        while (anzahl < 3 || anzahl > 13) {
            if (anzahl < 3) { System.out.println("Zahl zu klein. \n Eingabe: "); }
            else { System.out.println("Zahl zu groß. \n Eingabe: "); }
            anzahl = Input.readInt();
        }
        return anzahl;
    }


    public static char inputPattern() {
        System.out.println("Bitte ein Pattern für das schraffierte Segement festlegen. \n Eingabe:");
        char pattern = Input.readChar();
        while(pattern == ' ' || pattern == '0') {
            System.out.println("Bitte ein akzeptiertes Pattern eingeben. \n Eingabe: ");
            pattern = Input.readChar();
        }
        return pattern;
    }

    public static int getGroesse( int anzahl) {
        int groesse = 0;
        if (anzahl >= 10) { groesse = 1; }
        else if (anzahl >= 7 && anzahl <= 9) { groesse = 2; }
        else { groesse = 3; }
        return groesse;
    }

    //Mit 3 Schleifen
    public static void altSolution(int anzahl, char pattern, int groesse) {
        for (int i = 0; i < anzahl; i++) {
            for( int k = 0; k < groesse; k++) {
                for (int j = 0; j < (anzahl / 2); j++) {
                    switch (groesse) {
                        case 1:
                            if(i % 2 == 0) {
                                System.out.print(pattern);
                                System.out.print('0');
                            } else {
                                System.out.print('0');
                                System.out.print(pattern);
                            }
                            break;
                        case 2:
                            if(i % 2 == 0) {
                                System.out.print("" + pattern + pattern);
                                System.out.print("" + '0' + '0');
                            } else {
                                System.out.print("" + '0' + '0');
                                System.out.print("" + pattern + pattern);
                            }
                            break;
                        case 3:
                            if(i % 2 == 0) {
                                System.out.print("" + pattern + pattern + pattern);
                                System.out.print("" + '0' + '0' + '0');
                            } else {
                                System.out.print("" + '0' + '0' + '0');
                                System.out.print("" + pattern + pattern + pattern);
                            }
                    }
                }
                System.out.print('\n');
            }
        }
    }

    //Mit 2 Schleifen
    public static void solution(int anzahl, char pattern, int groesse) {
        for (int i = 0; i < anzahl; i++) {
            for (int k = 0; k < groesse; k++) {
                String zeile = "";
                int bloecke = anzahl / 2;

                String patternBlock = "" + pattern;
                patternBlock = patternBlock.repeat(groesse);
                String nullBlock = "" + '0';
                nullBlock = nullBlock.repeat(groesse);
                if (i % 2 == 0) {
                    zeile = zeile + ((patternBlock + nullBlock).repeat(bloecke));
                } else {
                    zeile = zeile + ((nullBlock + patternBlock).repeat(bloecke));
                }
                System.out.println(zeile);
            }
        }
    }

    public static void main(String[] args) {

        int anzahl = inputSegment();
        char pattern = inputPattern();
        int groesse = getGroesse(anzahl);

        //altSolution(anzahl, pattern, groesse);
        solution(anzahl, pattern, groesse);



    }


}
