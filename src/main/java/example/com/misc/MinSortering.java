package example.com.misc;

public class MinSortering {

    // Method to sort and print numbers in ascending order
    public static void minSortering(int[] arr) {
        // Array to count occurrences of each number (assuming numbers are between 1 and 200)
        int[] count = new int[201];

        // Populate the count array: increment the count for each number in the input array
        for (int num : arr) {
            count[num]++;
        }

        // Iterate through possible numbers (1 to 200)
        for (int i = 1; i <= 200; i++) {
            // Print each number 'count[i]' times
            for (int j = 0; j < count[i]; j++) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        // Input array of integers to be sorted
        int[] arr = {183, 72, 145, 73, 44, 160, 165, 118, 165, 188, 58, 103, 12, 99, 138, 15, 61, 127, 4, 1, 26, 93,
                19, 192, 93, 200, 117, 70, 170, 37, 81, 57, 103, 139, 54, 116, 89, 176, 135, 172, 72, 3, 156, 15, 128,
                148, 153, 141, 161, 181, 182, 96, 197, 8, 156, 9, 145, 36, 95, 42, 152, 2, 71, 182, 162, 157, 127, 183,
                110, 83, 144, 110, 153, 196, 160, 143, 156, 137, 158, 82, 100, 122, 63, 151, 171, 10, 142, 98, 15, 65,
                193, 84, 84, 6, 107, 195, 146, 96, 38, 148};

        // Call the sorting method
        minSortering(arr);
    }
}
