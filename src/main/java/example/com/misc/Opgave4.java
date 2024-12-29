package example.com.misc;

public class Opgave4 {
    public static int[] findClosestPowerOfTwo(int[] arr) {
        int[] bestResult = new int[4];
        int closestDifference = Integer.MAX_VALUE;

        // Iterér over de mulige kombinationer af tre unikke tal
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    int sum = arr[i] + arr[j] + arr[k];
                    int closestPower = nearestPowerOfTwo(sum);
                    int difference = Math.abs(closestPower - sum);

                    // Her tjekker vi, om denne specifikke kombination er
                    // tættere på en potens af 2
                    if (difference < closestDifference) {
                        closestDifference = difference;
                        bestResult[0] = arr[i];
                        bestResult[1] = arr[j];
                        bestResult[2] = arr[k];
                        bestResult[3] = closestPower;
                    }
                }
            }
        }

        return bestResult;
    }

    // Metode til at finde den nærmeste potens af 2, større end eller lig med et givent tal
    public static int nearestPowerOfTwo(int num) {
        int power = 1;
        while (power < num) {
            power *= 2;
        }

        // Her finder vi den nærmeste potens af 2 (enten den nuværende eller tidligere)
        int lowerPower = power / 2;
        return (Math.abs(power - num) < Math.abs(lowerPower - num)) ? power : lowerPower;
    }

    public static void main(String[] args) {
        int[] array = {23, 56, 22, 11, 65, 89, 3, 44, 87, 910, 45, 35, 98};
        int[] result = findClosestPowerOfTwo(array);
        System.out.println("De tre tal er: " + result[0] + ", " + result[1] + ", " + result[2]);
        System.out.println("Den nærmeste potens af 2 er: " + result[3]);
    }
}
