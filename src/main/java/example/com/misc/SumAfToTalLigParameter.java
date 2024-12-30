package example.com.misc;

public class SumAfToTalLigParameter {

    // O(N^2) tidskompleksitet
    public static boolean sumAfToTalLigParameter(int[] arr, int l, int X) {
        for (int i = 0; i < l - 1; i++) {
            for (int j = i + 1; j < l; j++) {
                if (arr[i] + arr[j] == X) {
                    return true;
                }
            }
        }
        return false;
    }

    // O(N) tidskompleksitet
    public static boolean sumAfToTalLigParameter2(int[] arr, int l, int X) {
        int left = 0;
        int right = l - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == X) {
                return true;
            } else if (sum < X) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,4,5,6,8};
        System.out.println(sumAfToTalLigParameter2(arr, 6, 14));
        System.out.println(sumAfToTalLigParameter(arr, 6, 14));
    }
}
