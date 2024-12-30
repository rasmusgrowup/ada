package example.com.recursion;

public class LogToN {
    public static int logTo(int N) {
        // Hvis N == 1, stop rekursion
        if (N <= 1) {
            return 0;
        }

        // Ellers, returnér 1, og kald den rekursive metode hvor N divideres med 2
        return 1 + logTo(N / 2);
    }

    public static void main(String[] args) {
        System.out.println(logTo(4096));
        System.out.println(logTo(32));
    }
}
