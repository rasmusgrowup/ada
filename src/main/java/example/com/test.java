package example.com;

public class test {
    public static int myMethod(int N) {
        if (N <= 0) {
            return N;
        }

        if (N % 3 == 0 || N % 8 == 0) {
            System.out.println(N);
            return N + myMethod(N - 1);
        } else {
            return myMethod(N - 1);
        }
    }

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
        //System.out.println(myMethod(55));
        System.out.println(antalVokaler("stationsbygninger", 16));
    }
}
