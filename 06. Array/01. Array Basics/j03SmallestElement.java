import java.util.Scanner;

public class j03SmallestElement {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(minElementIndex(arr));
        System.out.println(minElementOfArray(arr, Integer.MAX_VALUE));
        in.close();
    }

    public static int minElementIndex(int[] arr) {
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr[index]) {
                index = i;
            }
        }
        return index;
    }

    public static int minElementOfArray(int[] arr, int index) {
        if (index == arr.length - 1)
            return arr[index];
        int min = minElementOfArray(arr, index + 1);
        if (min < arr[index])
            return min;
        return arr[index];
    }
}
