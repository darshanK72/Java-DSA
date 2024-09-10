import java.util.Scanner;

public class j04FindXorTriplets {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println(findXorTriplets(arr));
        in.close();
    }

    // O(n^2)
    public static int findXorTriplets(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int xor = 0;
            for (int j = i; j < arr.length; j++) {
                xor ^= arr[j];
                if (xor == 0)
                    count += (j - i);
            }
        }
        return count;
    }
}