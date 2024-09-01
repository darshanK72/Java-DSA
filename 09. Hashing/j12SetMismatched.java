import java.util.Scanner;

public class j12SetMismatched {
     public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int[] ans = findErrorNums(arr);
        System.out.println("Missing : " + ans[0] + "\nDuplicate : " + ans[1]);
        in.close();
    }

    public static int[] findErrorNums(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int original = arr[i] % (arr.length + 1);
            arr[original - 1] += (arr.length + 1);
        }
        int[] out = new int[2];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] / (arr.length + 1) == 0)
                out[1] = i + 1;
            if (arr[i] / (arr.length + 1) > 1)
                out[0] = i + 1;
        }
        return out;
    }
}
