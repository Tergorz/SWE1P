public class Exam {

    public static void main(String[] args) {
        //Aufgabe1();
        //Aufgabe2();
        Windspeed wind = new Windspeed(100.0);
        System.out.println(wind.getBeaufortScale());
    }

    public static void Aufgabe1() {

        double length = 2.0;
        double diameter = 0.001;
        double rhoCopper = 0.0017;
        double rhoAluminium = 0.0028;

        double resistanceCopper;
        double resistanceAlu;
        double area;

        area = diameter * diameter * Math.PI / 4.0;
        resistanceCopper  =  rhoCopper * (length / area);
        resistanceAlu = rhoAluminium * (length / area);

        System.out.printf("Widerstand Kupferdraht: %5.2f Ohm\n", resistanceCopper);
        System.out.printf("Widerstand Aluminiumdraht: %5.2f Ohm\n", resistanceAlu);

        double current = 0.25;
        double voltage = current * resistanceCopper;

        System.out.printf("Eine Spannung von %3.2f Volt ist nötig, um eine Stromstärke von %3.2f Ampere durch einen Kupferdraht zu leiten\n", voltage, current);

    }

    public static boolean isGoodPassword(char[] password) {
        int noUppercaseChars  = 0;
        int noLowercaseChars  = 0;
        int noSpecialChars    = 0;
        int noDigits          = 0;
        boolean digitOrSpecialChars = false;
        for (int i = 0; i < password.length; i++) {
            if(Character.isLetter(password[i]) && Character.isUpperCase(password[i])) {
                noUppercaseChars++;
            }
            if(Character.isLetter(password[i]) && Character.isLowerCase(password[i])) {
                noLowercaseChars++;
            }
            if(Character.isDigit(password[i])) {
                noDigits++;
            }
            if(!Character.isLetterOrDigit(password[i]) && !Character.isWhitespace(password[i])) {
                noSpecialChars++;
            }
        }
        for (int i = 1; !digitOrSpecialChars && i < password.length - 1; i++) {
            if(Character.isDigit(password[i]) || (!Character.isWhitespace(password[i]) && !Character.isLetterOrDigit(password[i]))) {
                digitOrSpecialChars = true;
            }
        }

        return password.length >= 8 && noUppercaseChars >= 1 && noLowercaseChars >= 1 && noDigits >= 2 && noSpecialChars >= 1 && digitOrSpecialChars;


    }

    public static void Aufgabe2() {

        char[] password2;
        char[] password = {'A', 'x', 'B', '1', '&', 'a', '8', 'c'};
        password2 = new char[]{'1', 'P', 'a', 's', 's', 'w', '0', 'r', 't'};
        System.out.println(isGoodPassword(password));
        System.out.println(isGoodPassword(password2));

    }

    public static class Windspeed {
        private double kiloemeterPerHour;

        public Windspeed(double kilometerPerHour) {
            this.kiloemeterPerHour = kilometerPerHour;
        }
        public boolean windstill() {
            return this.kiloemeterPerHour < 2.0;
        }
        public boolean orkan() {
            return this.kiloemeterPerHour > 120.0;
        }
        public double getKilometerPerHour() {
            return this.kiloemeterPerHour;
        }
        public double getKnotsPerHour() {
            return this.kiloemeterPerHour / 1.852;
        }
        public int getBeaufortScale() {
            /*double bhochdreihalbe;
            bhochdreihalbe = this.kiloemeterPerHour / 3.01;
            int b = (int) (bhochdreihalbe / Math.sqrt(bhochdreihalbe));
            */
            int b = (int) (Math.pow(getKilometerPerHour() / 3.01, 0.6666) + 0.5);
            if(b > 12) { b = 12; }
            return b;

        }

    }

}