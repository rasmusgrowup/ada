package example.com.recursion;

import java.sql.SQLOutput;

public class EksamensOpgave32022 {
    public static int myMethod(int N) {
        // base case
        if (N <= 0) {
            return 0;
        }

        if (N % 2 == 1) {
            return (int) (Math.pow(N, 2) + myMethod(N - 2));
        } else {
            return myMethod(N - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(myMethod(6));
    }
}
