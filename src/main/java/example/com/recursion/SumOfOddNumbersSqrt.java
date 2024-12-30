package example.com.recursion;

public class SumOfOddNumbersSqrt {
    public static int myMethod(int N) {
        // base case
        if (N <= 0) {
            return 0;
        }

        if (N % 2 == 1) { // ændr 1 til 0 hvis summen skal være af de lige tal
            return (int) (Math.pow(N, 2) + myMethod(N - 2));
        } else {
            return myMethod(N - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(myMethod(8));
    }
}
