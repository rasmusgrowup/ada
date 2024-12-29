package example.com;

public class Opgave1 {
    public static double sumofsquared(int n)
    {
        // Hvis tallet er lige, start de rekursive kald på det næste tal i "rækken"
        if (n % 2 == 0) {
            return sumofsquared(n - 1);
        }

        // Stop de rekursive tal, når n <= 0
        if (n <= 0) {
            return 0;
        } else {
            double sum = (Math.pow(n, 2) + sumofsquared(n - 2));
            System.out.println("Calculated " + n + "^2 = " + sum);
            return sum;
        }
    }

    public static void main(String[] args) {
        sumofsquared(11);
    }
}
