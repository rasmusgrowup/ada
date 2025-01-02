package example.com.misc;

public class SumAfToTalLigParameter {

    // Method to find if two numbers in the array sum up to X using O(N^2) complexity
    public static boolean sumAfToTalLigParameter(int[] arr, int l, int X) {
        // Loop through each pair of numbers in the array
        for (int i = 0; i < l - 1; i++) {
            for (int j = i + 1; j < l; j++) {
                // Check if the sum of arr[i] and arr[j] equals X
                if (arr[i] + arr[j] == X) {
                    return true; // Return true if a pair is found
                }
            }
        }
        // Return false if no such pair exists
        return false;
    }

    // Optimized method to find if two numbers in the array sum up to X using O(N) complexity
    public static boolean sumAfToTalLigParameter2(int[] arr, int l, int X) {
        // Initialize two pointers: one at the start and one at the end of the array
        int left = 0;
        int right = l - 1;

        // Continue until the two pointers meet
        while (left < right) {
            // Calculate the sum of the elements at the two pointers
            int sum = arr[left] + arr[right];

            if (sum == X) {
                return true; // Return true if the sum equals X
            } else if (sum < X) {
                // Move the left pointer to the right if the sum is less than X
                left++;
            } else {
                // Move the right pointer to the left if the sum is greater than X
                right--;
            }
        }

        // Return false if no such pair exists
        return false;
    }

    public static void main(String[] args) {
        // Test array
        int[] arr = {1, 4, 4, 5, 6, 8};

        // Test the optimized method (O(N) complexity)
        System.out.println(sumAfToTalLigParameter2(arr, 6, 14)); // Expected: true

        // Test the brute-force method (O(N^2) complexity)
        System.out.println(sumAfToTalLigParameter(arr, 6, 14)); // Expected: true
    }
}
