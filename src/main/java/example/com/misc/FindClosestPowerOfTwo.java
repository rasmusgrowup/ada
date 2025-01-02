package example.com.misc;

public class FindClosestPowerOfTwo {

    // Method to find three numbers in an array whose sum is closest to a power of two
    public static int[] findClosestPowerOfTwo(int[] arr) {
        // Array to store the best result: the three numbers and the closest power of two
        int[] bestResult = new int[4];
        // Initialize the smallest difference to the maximum possible integer value
        int closestDifference = Integer.MAX_VALUE;

        // Iterate through the array to find all combinations of three unique numbers
        for (int i = 0; i < arr.length - 2; i++) { // First number in the combination
            for (int j = i + 1; j < arr.length - 1; j++) { // Second number in the combination
                for (int k = j + 1; k < arr.length; k++) { // Third number in the combination
                    // Calculate the sum of the current combination of three numbers
                    int sum = arr[i] + arr[j] + arr[k];

                    // Find the nearest power of two to this sum
                    int closestPower = nearestPowerOfTwo(sum);

                    // Calculate the absolute difference between the sum and the nearest power of two
                    int difference = Math.abs(closestPower - sum);

                    // Check if the current combination gives a closer difference than the best so far
                    if (difference < closestDifference) {
                        // Update the closest difference
                        closestDifference = difference;
                        // Store the current three numbers and the closest power of two in the result array
                        bestResult[0] = arr[i];
                        bestResult[1] = arr[j];
                        bestResult[2] = arr[k];
                        bestResult[3] = closestPower;
                    }
                }
            }
        }

        // Return the best result found
        return bestResult;
    }

    // Helper method to find the nearest power of two to a given number
    public static int nearestPowerOfTwo(int num) {
        // Start with the smallest power of two (2^0 = 1)
        int power = 1;

        // Incrementally double the power of two until it is greater than or equal to the number
        while (power < num) {
            power *= 2;
        }

        // At this point, 'power' is the smallest power of two greater than or equal to 'num'
        // Calculate the next smaller power of two by dividing by 2
        int lowerPower = power / 2;

        // Return the closer of the two powers of two (either the current or the lower one)
        return (Math.abs(power - num) < Math.abs(lowerPower - num)) ? power : lowerPower;
    }

    public static void main(String[] args) {
        // Input array of integers
        int[] array = {23, 56, 22, 11, 65, 89, 3, 44, 87, 910, 45, 35, 98};

        // Call the method to find the three numbers and the nearest power of two
        int[] result = findClosestPowerOfTwo(array);

        // Print the result: the three numbers and the closest power of two
        System.out.println("De tre tal er: " + result[0] + ", " + result[1] + ", " + result[2]);
        System.out.println("Den nærmeste potens af 2 er: " + result[3]);
    }
}
