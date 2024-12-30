package example.com.recursion;

public class DivisibleByXorY {
    public static int myMethod(int N) {
        int x = 3;
        int y = 8;

        // Base case
        if (N <= 0) {
            return N;
        }

        if (N % x == 0 || N % y == 0) {
            System.out.println(N);
            return N + myMethod(N - 1);
        } else {
            return myMethod(N - 1);
        }
    }

    public static void main(String[] args) {
        //System.out.println(myMethod(55));
        System.out.println(myMethod(13));
    }
}
