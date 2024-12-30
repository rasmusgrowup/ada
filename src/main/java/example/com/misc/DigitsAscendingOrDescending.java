package example.com.misc; // Declare the package name

public class DigitsAscendingOrDescending {
    // This method checks if a number N is a 5-digit number and whether its digits are
    // in either ascending or descending order.
    public static boolean category(int N) {
        // Check if the number is a 5-digit number.
        // If it's not, return false immediately.
        if (N < 10000 || N > 99999) {
            return false;
        }

        // Check if the digits of the number are in descending order OR ascending order.
        // If either condition is true, return true.
        return isDescending(N) || isAscending(N);
    }

    // This method checks if the digits of the given number are in descending order.
    public static boolean isDescending(int number) {
        // Loop until the number becomes less than 10 (single-digit).
        while (number > 10) {
            // Compare the last digit of the number (number % 10) with the
            // second-last digit ((number / 10) % 10).
            if (number % 10 > (number / 10) % 10) {
                // If any digit is greater than the digit before it, the number is not descending.
                return false;
            }
            // Remove the last digit by performing integer division by 10.
            number = number / 10;
        }
        // If the loop completes without returning false, the digits are in descending order.
        return true;
    }

    // This method checks if the digits of the given number are in ascending order.
    public static boolean isAscending(int number) {
        // Loop until the number becomes less than 10 (single-digit).
        while (number > 10) {
            // Compare the last digit of the number (number % 10) with the
            // second-last digit ((number / 10) % 10).
            if (number % 10 < (number / 10) % 10) {
                // If any digit is less than the digit before it, the number is not ascending.
                return false;
            }
            // Remove the last digit by performing integer division by 10.
            number = number / 10;
        }
        // If the loop completes without returning false, the digits are in ascending order.
        return true;
    }

    // Main method to test the functionality of the program.
    public static void main(String[] args) {
        // Define a test number.
        int number = 12345;

        // Print the result of the category method.
        // It will print `true` if the number is a 5-digit number
        // and its digits are either in ascending or descending order.
        System.out.println(category(number));
    }
}