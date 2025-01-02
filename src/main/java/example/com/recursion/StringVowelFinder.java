package example.com.recursion;

public class StringVowelFinder {

    /**
     * Method to check if a character is a vowel.
     *
     * @param c The character to check.
     * @return True if the character is a vowel, false otherwise.
     */
    public static boolean erVokal(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y';
    }

    /**
     * Recursive method to count the number of vowels in a string.
     *
     * @param str The input string to process.
     * @param l The current index in the string being checked.
     * @return The total number of vowels found in the string up to the given index.
     */
    public static int antalVokaler(String str, int l) {
        // Base case: If the index is less than or equal to 0, return 0 (no vowels left to count)
        if (l <= 0) {
            return 0;
        }

        // Check if the character at the current index is a vowel
        if (erVokal(str.charAt(l - 1))) { // Use l - 1 to match zero-based indexing
            return 1 + antalVokaler(str, l - 1); // Add 1 and recurse for the rest of the string
        } else {
            return antalVokaler(str, l - 1); // Recurse without adding if the character is not a vowel
        }
    }

    public static void main(String[] args) {
        // Test the vowel counting method with a sample string
        String testString = "stationsbygninger";

        // Print the total number of vowels in the string
        System.out.println("Antal vokaler i '" + testString + "': " + antalVokaler(testString, testString.length()));
    }
}
