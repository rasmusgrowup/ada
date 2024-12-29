package example.com.recursion;

public class DivisbleBy3Or8 {
    public static int myMethod(int N) {

        // Base case
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

    public static void main(String[] args) {
        System.out.println(myMethod(55));
    }
}
