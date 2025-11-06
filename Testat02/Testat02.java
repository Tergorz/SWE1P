public class Testat02 {


    public static int inputSegment() {
        System.out.println("Bitte Anzahl der Segmente (Zahl zw. 4 & 12) festlegen. \n Eingabe: ");
        int anzahl = Input.readInt();
        while (anzahl < 4 || anzahl > 12) {
            if (anzahl < 4) { System.out.println("Zahl zu klein. \n Eingabe: "); }
            else { System.out.println("Zahl zu groß. \n Eingabe: "); }
            anzahl = Input.readInt();
        }
        return anzahl;
    }

    public static int[] inputSegmentOptional() {
        System.out.println("Bitte Anzahl der vertikalen Segmente (Zahl zw. 4 & 12) festlegen. \n Eingabe: ");
        int y = Input.readInt();
        while (y < 4 || y > 12) {
            if (y < 4) { System.out.println("Zahl zu klein. \n Eingabe: "); }
            else { System.out.println("Zahl zu groß. \n Eingabe: "); }
            y = Input.readInt();
        }
        System.out.println("Bitte Anzahl der horizontalen Segmente (Zahl zw. 4 & 12) festlegen. \n Eingabe: ");
        int x = Input.readInt();
        while (x < 4 || x > 12) {
            if (x < 4) { System.out.println("Zahl zu klein. \n Eingabe: "); }
            else { System.out.println("Zahl zu groß. \n Eingabe: "); }
            x = Input.readInt();
        }
        return new int[]{y, x};
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

    public static int inputGroesse() {
        System.out.println("Bitte geben sie die Groesse des Segments ein. (Zahl zw. 1 & 3) \n Eingabe: ");
        int groesse = Input.readInt();
        while (groesse < 1 || groesse > 3) {
            System.out.println("Bitte geben sie eine Zahl zwischen 1 und 3 ein. \n Eingabe: ");
            groesse = Input.readInt();
        }
        return groesse;
    }


    public static void solution(int anzahl, char pattern, int groesse) {
        for (int i = 0; i < anzahl; i++) {
            for( int k = 0; k < groesse; k++) {
                for (int j = 0; j < anzahl; j++) {
                    switch (groesse) {
                        case 1:
                            if(i % 2 == 0) {
                                if(j % 2 == 0) {
                                    System.out.print(pattern);
                                } else {
                                    System.out.print('0');
                                }
                            } else {
                                if(j % 2 == 0) {
                                    System.out.print('0');
                                } else {
                                    System.out.print(pattern);
                                }
                            }
                            break;
                        case 2:
                            if(i % 2 == 0) {
                                if(j % 2 == 0) {
                                    System.out.print("" + pattern + pattern);
                                } else {
                                    System.out.print("" + '0' + '0');
                                }
                            } else {
                                if(j % 2 == 0) {
                                    System.out.print("" + '0' + '0');
                                } else {
                                    System.out.print("" + pattern + pattern);
                                }
                            }
                            break;
                        case 3:
                            if(i % 2 == 0) {
                                if(j % 2 == 0) {
                                    System.out.print("" + pattern + pattern + pattern);
                                } else {
                                    System.out.print("" + '0' + '0' + '0');
                                }
                            } else {
                                if(j % 2 == 0) {
                                    System.out.print("" + '0' + '0' + '0');
                                } else {
                                    System.out.print("" + pattern + pattern + pattern);
                                }
                            }
                    }
                }
                System.out.print('\n');
            }
        }
    }

    public static void solutionOptional(int y, int x, char pattern, int groesse) {
        for (int i = 0; i < y; i++) {
            for( int k = 0; k < groesse; k++) {
                for (int j = 0; j < x ; j++) {
                    switch (groesse) {
                        case 1:
                            if(i % 2 == 0) {
                                if(j % 2 == 0) {
                                    System.out.print(pattern);
                                } else {
                                    System.out.print('0');
                                }
                            } else {
                                if(j % 2 == 0) {
                                    System.out.print('0');
                                } else {
                                    System.out.print(pattern);
                                }
                            }
                            break;
                        case 2:
                            if(i % 2 == 0) {
                                if(j % 2 == 0) {
                                    System.out.print("" + pattern + pattern);
                                } else {
                                    System.out.print("" + '0' + '0');
                                }
                            } else {
                                if(j % 2 == 0) {
                                    System.out.print("" + '0' + '0');
                                } else {
                                    System.out.print("" + pattern + pattern);
                                }
                            }
                            break;
                        case 3:
                            if(i % 2 == 0) {
                                if(j % 2 == 0) {
                                    System.out.print("" + pattern + pattern + pattern);
                                } else {
                                    System.out.print("" + '0' + '0' + '0');
                                }
                            } else {
                                if(j % 2 == 0) {
                                    System.out.print("" + '0' + '0' + '0');
                                } else {
                                    System.out.print("" + pattern + pattern + pattern);
                                }
                            }
                    }
                }
                System.out.print('\n');
            }
        }
    }



    public static void main(String[] args) {

        System.out.println("Sollen Rechtecke erlaubt werden? (y/n)");
        char option = Character.toLowerCase(Input.readChar());
        //Rechteckiges Feld
        if (option == 'y') {
            int[] size = inputSegmentOptional();
            int y = size[0];
            int x = size[1];
            char pattern = inputPattern();
            int groesse = inputGroesse();

            solutionOptional(y, x, pattern, groesse);

            System.out.println("Möchten sie noch ein weiteres Muster entwerfen? (y/n)");
            char repeat = Character.toLowerCase(Input.readChar());
            while (repeat == 'y') {
                size = inputSegmentOptional();
                y = size[0];
                x = size[1];
                pattern = inputPattern();
                groesse = inputGroesse();
                solutionOptional(y, x, pattern, groesse);
                System.out.println("Möchten sie noch ein weiteres Muster entwerfen? (y/n)");
                repeat = Character.toLowerCase(Input.readChar());
            }
        }
        //Quadratisches Feld
        else {
            int anzahl = inputSegment();
            char pattern = inputPattern();
            int groesse = getGroesse(anzahl);

            solution(anzahl, pattern, groesse);

            System.out.println("Möchten sie noch ein weiteres Muster entwerfen? (y/n)");
            char repeat = Character.toLowerCase(Input.readChar());
            while (repeat == 'y') {
                anzahl = inputSegment();
                pattern = inputPattern();
                groesse = getGroesse(anzahl);
                solution(anzahl, pattern, groesse);
                System.out.println("Möchten sie noch ein weiteres Muster entwerfen? (y/n)");
                repeat = Character.toLowerCase(Input.readChar());
            }
        }





    }


}
