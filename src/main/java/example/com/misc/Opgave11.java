package example.com.misc;

import java.util.HashMap;
import java.util.Map;

public class Opgave11 {
    public static int candidateSearch(int[] arr) {
        // Definér antallet af stemmer, der skal til for at vinde afstemningen
        int votesToWin = arr.length / 2 + 1;

        // Brug et HashMap til at opbevare kandidaterne, og antallet af stemmer på kandidaten
        Map<Integer, Integer> candidates = new HashMap<>();

        // Loop gennem arr, og optæl stemmmerne
        for (int i = 0; i < arr.length - 1; i++) {
            if (candidates.containsKey(arr[i])) { // Hvis kandidaten allerede findes, fx. "7", lægges en stemme til
                candidates.put(arr[i], candidates.get(arr[i]) + 1);
            } else { // Hvis ikke kandidaten findes, sættes kandidaten, fx. "7", ind i map'et med én stemme
                candidates.put(arr[i], 1);
            }
        }

        // returværdi for, hvis der ikke er en kandidat der opnår over 50% i stemmerne
        int winningCandidate = -1;

        // Her tæller vi stemmerne op
        for (int key: candidates.keySet()) {
            if (candidates.get(key) > votesToWin) { // Hvis en kandidat har mere end 50% af stemmerne, returneres kandidaten
                return key;
            }
        }

        return winningCandidate;
    }

    public static void main(String[] args) {
        int[] votesWithClearWinner = {7,4,3,5,3,7,7,7,7,7,7,7,7,7,7,6,4,5,1,7,5};
        int[] votesWithNoWinner = {7,4,3,5,3,1,6,4,5,1,7,5};
        System.out.println(candidateSearch(votesWithNoWinner));
    }
}
