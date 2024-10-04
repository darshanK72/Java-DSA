import java.util.Scanner;

public class j01MaximumConsecutiveOnesIII {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(maxConsOnesAfterKFlips(arr, k));
        System.out.println(maxConsOnesAfter1Flips(arr, k));
        System.out.println(maxConsOnesAfterKFlipsEfficient(arr, k));
        in.close();
    }

    public static int maxConsOnesAfterKFlips(int[] arr, int k) {
        int maxL = 0;
        for(int i = 0; i < arr.length; i++){
            int zeros = 0;
            for(int j = i; j < arr.length; j++){
                if(arr[j] == 0) zeros++;
                if(zeros > k) break;
                else{
                    maxL = Math.max(maxL,j - i + 1);
                } 
            }
        }
        return maxL;
    }

    public static int maxConsOnesAfter1Flips(int[] arr, int k) {
        int maxL = 0;
        int zeros = 0;
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                zeros++;
            }
            while (zeros > 1) {
                if (arr[j] == 0)
                    zeros--;
                j++;
            }
            maxL = Math.max(maxL, i - j + 1);
        }
        return maxL;
    }

    public static int maxConsOnesAfterKFlipsEfficient(int[] arr, int k) {
        int maxL = 0;
        int zeros = 0;
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                zeros++;
            }
            while (zeros > k) {
                if (arr[j] == 0)
                    zeros--;
                j++;
            }
            maxL = Math.max(maxL, i - j + 1);
        }
        return maxL;
    }

}
