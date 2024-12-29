package example.com.recursion;

public class Opgave6 {
    public static int sumDivisibleBy3(int N) {
        // stop rekursion hvis N <= 0
        if (N <= 0) {
            return 0;
        }

        // sumér tal der er delelige med 3
        if (N % 3 == 0) {
            return N + sumDivisibleBy3(N - 3);
        } else {
            return sumDivisibleBy3(N - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(sumDivisibleBy3(18));
    }
}
