package example.com.misc;

import java.util.HashMap;
import java.util.Map;

public class PatternFinder {
    /**
     * Method to find a pattern in the text based on the rules described.
     *
     * @param text The input text containing words separated by spaces.
     * @param word The pattern word to search for in the text.
     * @return An integer based on the matching criteria:
     *         1 - Exact match found.
     *         2 - A word with the same letters in any order is found.
     *         3 - A word containing all letters of the word in the same or different order.
     *         0 - No match found.
     */
    public static int findPattern(String text, String word) {
        // Create a frequency map for the letters in 'word'
        Map<Character, Integer> wordMap = buildFrequencyMap(word);
        boolean containsInOrder = false; // Track if any word contains all letters in order

        // Use two pointers to iterate through the text and extract words
        int start = 0, end = 0, textLength = text.length();

        // Loop through the entire text
        while (end <= textLength) {
            // Check for end of word (space or end of text)
            if (end == textLength || text.charAt(end) == ' ') {
                // Extract the current word from text
                String currentWord = text.substring(start, end);

                // Case 1: Exact match with 'word'
                if (currentWord.equals(word)) {
                    return 1;
                }
                // Case 2: Same letters in any order
                else if (isPermutation(currentWord, wordMap)) {
                    return 2;
                }
                // Case 3: Contains all letters of 'word' in order
                else if (containsAllInOrder(currentWord, word)) {
                    containsInOrder = true; // Mark that Case 3 is satisfied
                }

                // Move the start pointer to the next word
                start = end + 1;
            }

            // Increment end pointer to continue reading the text
            end++;
        }

        // Return 3 if Case 3 was satisfied but not Case 1 or Case 2
        return containsInOrder ? 3 : 0;
    }

    /**
     * Helper method to build a frequency map of characters in a string.
     *
     * @param word The input string.
     * @return A map where keys are characters, and values are their frequencies.
     */
    private static Map<Character, Integer> buildFrequencyMap(String word) {
        Map<Character, Integer> map = new HashMap<>();
        // Loop through each character in the word
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // Increment the count of the character in the map
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    /**
     * Checks if a given word is a permutation of the target word.
     *
     * @param currentWord The word to check.
     * @param wordMap The frequency map of the target word.
     * @return True if 'currentWord' is a permutation of 'word', false otherwise.
     */
    private static boolean isPermutation(String currentWord, Map<Character, Integer> wordMap) {
        // Build a frequency map for the current word
        Map<Character, Integer> currentMap = new HashMap<>();
        for (int i = 0; i < currentWord.length(); i++) {
            char c = currentWord.charAt(i);
            currentMap.put(c, currentMap.getOrDefault(c, 0) + 1);
        }
        // Compare the frequency maps
        return currentMap.equals(wordMap);
    }

    /**
     * Checks if all characters of 'word' are present in 'currentWord' in the same order.
     *
     * @param currentWord The word to check.
     * @param word The target word.
     * @return True if 'currentWord' contains all characters of 'word' in order, false otherwise.
     */
    private static boolean containsAllInOrder(String currentWord, String word) {
        int j = 0; // Pointer for 'word'
        // Iterate through 'currentWord'
        for (int i = 0; i < currentWord.length() && j < word.length(); i++) {
            // If characters match, move the pointer for 'word'
            if (currentWord.charAt(i) == word.charAt(j)) {
                j++;
            }
        }
        // If we successfully matched all characters of 'word', return true
        return j == word.length();
    }

    /**
     * Main method to test the functionality of the algorithm.
     */
    public static void main(String[] args) {
        String text = "ostemad storkemor som mos";
        String word = "storkemor";

        // Output should be 1 (exact match found)
        System.out.println(findPattern(text, word));
    }
}

