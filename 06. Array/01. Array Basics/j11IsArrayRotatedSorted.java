import java.util.Scanner;

public class j11IsArrayRotatedSorted {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(isArrayRotatedSorted(arr));
        in.close();
    }

    public static boolean isArrayRotatedSorted(int[] arr) {
        int rotate = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[(i + 1) % arr.length])
                rotate++;
        }
        if (rotate <= 1)
            return true;
        return false;
    }
}
