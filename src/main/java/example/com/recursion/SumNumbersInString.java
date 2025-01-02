package example.com.recursion;

public class SumNumbersInString {

    /**
     * Recursive method to determine if any three consecutive digits in a string are additive.
     *
     * @param s The input string containing digits.
     * @return True if there exists three consecutive digits where the sum of the first two equals the third, false otherwise.
     */
    public static boolean additive(String s) {
        // Base case: Check if the string has fewer than three characters
        if (s.length() < 3) {
            return false; // Not enough digits to evaluate
        }

        // Convert the first three characters into integers
        int firstDigit = s.charAt(0) - '0'; // Convert character to integer
        int secondDigit = s.charAt(1) - '0';
        int thirdDigit = s.charAt(2) - '0';

        // Check if the sum of the first two digits equals the third digit
        if ((firstDigit + secondDigit) == thirdDigit) {
            return true; // Match found
        }

        // Recursive call to check the remaining substring starting from the next character
        return additive(s.substring(1));
    }

    public static void main(String[] args) {
        // Test input string with digits
        String s = "3122437";

        // Call the additive method and determine the result
        String result = additive(s) ? "Yes" : "No, sorry";

        // Output the result
        System.out.println("Is there a match? " + result + ".");
    }
}
