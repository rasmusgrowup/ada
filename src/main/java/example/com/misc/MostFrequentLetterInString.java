package example.com.misc;

public class MostFrequentLetterInString {
    public static String mostFrequentLetter(String s) {
        // Array to store the frequency of each letter (a-z), initialized to zero
        int[] freq = new int[26];

        // Iterate through each character in the input string
        for (char c : s.toCharArray()) {
            // Calculate the index for the letter (0 for 'a', 1 for 'b', ..., 25 for 'z')
            // Increment the count for this letter in the frequency array
            freq[c - 'a']++;
        }

        // Variables to track the most frequent letter and its frequency
        char maxChar = 'a'; // Default to 'a' initially
        int maxFreq = 0;    // Start with zero frequency

        // Iterate through the frequency array to find the maximum frequency
        for (int i = 0; i < 26; i++) {
            if (freq[i] > maxFreq) { // Check if the current frequency is the highest
                maxFreq = freq[i]; // Update the maximum frequency
                maxChar = (char) (i + 'a'); // Convert the index back to a character
            }
        }

        // Return the most frequent letter concatenated with its frequency as a string
        return maxChar + String.valueOf(maxFreq);
    }

    public static void main(String[] args) {
        // Test case 1: Input string with multiple letters
        System.out.println(mostFrequentLetter("inagrassrootcampaignunlikeanyotherbeforeorsince")); // Output: a5

        // Test case 2: Input string with tied frequencies (returns one of the tied letters)
        System.out.println(mostFrequentLetter("aaaabbbbcccc")); // Example dynamic input: a4
    }
}
