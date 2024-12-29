package example.com.recursion;

public class EksamensOpgave22023 {
    public static int highestNumber;
    public static int secondHighestNumber;

    public static int secondLargestElement(int[] arr, int length) {
        // base case
        if (length <= 0) {
            return secondHighestNumber;
        }

        // declare the current number
        int current = arr[length - 1];

        if (current > highestNumber) {
            secondHighestNumber = highestNumber;
            highestNumber = current;
        } else if (current > secondHighestNumber && current != highestNumber) {
            secondHighestNumber = current;
        }

        return secondLargestElement(arr, length - 1);
    }

    public static void main(String[] args) {
        int[] arr = {23,117,10,14,18,22,29,39,51,45,49,99,61,65,68,81,89};
        int length = arr.length;
        System.out.println("First call," + "highest number:" + highestNumber + ", second highest number:" + secondHighestNumber);
        System.out.println(secondLargestElement(arr, length));
        System.out.println("Second call," + "highest number:" + highestNumber + ", second highest number:" + secondHighestNumber);
        System.out.println(secondLargestElement(arr, length));
    }
}
