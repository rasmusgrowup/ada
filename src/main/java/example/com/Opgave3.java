package example.com;

public class Opgave3 {
    public static boolean additive(String s)
    {
        // tjek om der er nok numre i strengen
        if (s.length() < 3) {
            return false;
        }

        // konverter numrene til integers
        int firstDigit = s.charAt(0) - '0';
        int secondDigit = s.charAt(1) - '0';
        int thirdDigit = s.charAt(2) - '0';

        // returnér true hvis der er et match
        if ((firstDigit + secondDigit) == thirdDigit) {
            return true;
        }

        // rekursivt kald
        return additive(s.substring(1));
    }

    public static void main(String[] args) {
        // Der er faktisk to subtrings der matcher kriteriet i nedenstående streng.
        // Burde der tages højde for dette?
        String s = "3122437";
        String result = additive(s) ? "Yes" : "No, sorry";
        System.out.println("Is there a match? " + result + ".");
    }
}
