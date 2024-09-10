import java.util.Scanner;

public class j35ValidMountainArray {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(validMountainArray(arr));
        in.close();
    }

    public static boolean validMountainArray(int[] arr) {
        int i = 0;
        while (i + 1 < arr.length && arr[i] < arr[i + 1]) {
            i++;
        }
        if (i == 0)
            return false;
        if (i == arr.length - 1)
            return false;
        while (i + 1 < arr.length && arr[i] > arr[i + 1]) {
            i++;
        }
        return (i == arr.length - 1) ? true : false;
    }
}
