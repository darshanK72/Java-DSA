import java.util.Scanner;

public class j07CountGoodTriplets {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        System.out.println(countGoodTriplets(arr, a,b,c));
        System.out.println(countGoodTripletsEfficient(arr, a,b,c));
        in.close();
    }

    public static int countGoodTriplets(int[] arr, int a, int b, int c) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if (Math.abs(arr[i] - arr[j]) <= a
                            && Math.abs(arr[j] - arr[k]) <= b
                            && Math.abs(arr[i] - arr[k]) <= c)
                        count++;
                }
            }
        }
        return count;
    }

    public static int countGoodTripletsEfficient(int[] arr, int a, int b, int c) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    if (Math.abs(arr[i] - arr[j]) <= a
                            && Math.abs(arr[j] - arr[k]) <= b
                            && Math.abs(arr[i] - arr[k]) <= c)
                        count++;
                }
            }
        }
        return count;
    }
}
