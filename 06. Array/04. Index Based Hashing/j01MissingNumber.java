import java.util.Scanner;

public class j01MissingNumber {
    
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println(missingNumber(arr));
        in.close();
    }

    public static int missingNumber(int[] arr) {
        int sum = (arr.length * (arr.length + 1)) / 2;
        for (int i = 0; i < arr.length; i++) {
            sum -= arr[i];
        }
        return sum;
    }

    public static int missingNumberArrayHashing(int[] arr) {
        int[] temp = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            temp[arr[i]]++;
        }

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 0)
                return i;
        }
        return -1;
    }

    public static int missingNumberIndexBasedHashing(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int original = arr[i] % (arr.length + 1);
            if (original < arr.length)
                arr[original] += (arr.length + 1);
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] / (arr.length + 1) == 0)
                return i;
        }
        return -1;
    }

    public static int missingNumberEfficient(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans ^= arr[i];
            ans ^= (i + 1);
        }
        return ans;
    }
}
