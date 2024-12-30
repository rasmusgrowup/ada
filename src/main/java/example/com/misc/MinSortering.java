package example.com.misc;

public class MinSortering {
    public static void minSortering(int[] arr) {
        int[] count = new int[201];

        for(int num : arr) {
            count[num]++;
        }

        for (int i = 1; i <= 200; i++)
        {
            for (int j = 0; j < count[i]; j++)
            {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {183, 72, 145, 73, 44, 160, 165, 118, 165, 188, 58, 103, 12, 99, 138, 15, 61, 127, 4, 1, 26, 93, 19, 192, 93, 200, 117, 70, 170, 37, 81, 57, 103, 139, 54, 116, 89, 176, 135, 172, 72, 3, 156, 15, 128, 148, 153, 141, 161, 181, 182, 96, 197, 8, 156, 9, 145, 36, 95, 42, 152, 2, 71, 182, 162, 157, 127, 183, 110, 83, 144, 110, 153, 196, 160, 143, 156, 137, 158, 82, 100, 122, 63, 151, 171, 10, 142, 98, 15, 65, 193, 84, 84, 6, 107, 195, 146, 96, 38, 148};
        minSortering(arr);
    }
}
