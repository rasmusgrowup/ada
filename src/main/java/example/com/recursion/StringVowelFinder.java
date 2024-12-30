package example.com.recursion;

public class StringVowelFinder {
    public static boolean erVokal(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y';
    }

    public static int antalVokaler(String str, int l) {
        if (l <= 0) {
            return 0;
        }

        if (erVokal(str.charAt(l))) {
            return 1 + antalVokaler(str, l - 1);
        } else {
            return antalVokaler(str, l - 1);
        }
    }


    public static void main(String[] args) {
        System.out.println(antalVokaler("stationsbygninger", 16));
    }
}
