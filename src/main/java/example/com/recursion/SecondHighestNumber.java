package example.com.recursion;

public class SecondHighestNumber {
    // Variables to store the highest and second highest numbers globally
    public static int highestNumber;
    public static int secondHighestNumber;

    /**
     * Recursive method to find the second largest element in the array.
     *
     * @param arr The input array of integers.
     * @param length The current length being processed (index in recursion).
     * @return The second highest number in the array.
     */
    public static int secondLargestElement(int[] arr, int length) {
        // Base case: If length is zero or less, return the second highest number found so far
        if (length <= 0) {
            return secondHighestNumber;
        }

        // Get the current number from the array
        int current = arr[length - 1];

        // Update highest and second highest numbers based on the current number
        if (current > highestNumber) {
            secondHighestNumber = highestNumber;
            highestNumber = current;
        } else if (current > secondHighestNumber && current != highestNumber) {
            secondHighestNumber = current;
        }

        // Recursive call to process the remaining elements in the array
        return secondLargestElement(arr, length - 1);
    }

    public static void main(String[] args) {
        // Test array of integers
        int[] arr = {23, 117, 10, 14, 18, 22, 29, 39, 51, 45, 49, 99, 61, 65, 68, 81, 89};

        // Length of the array
        int length = arr.length;

        // Print the initial state of the variables
        System.out.println("First call, highest number: " + highestNumber + ", second highest number: " + secondHighestNumber);

        // First call to the recursive method
        System.out.println("Second highest element: " + secondLargestElement(arr, length));

        // Print the state of the variables after the first call
        System.out.println("Second call, highest number: " + highestNumber + ", second highest number: " + secondHighestNumber);

        // Second call to demonstrate the variables' persistence
        System.out.println("Second highest element: " + secondLargestElement(arr, length));
    }
}
