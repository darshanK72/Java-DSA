import java.util.Scanner;

public class j02ContainerWithMostWater {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(maxArea(arr));
        in.close();
    }

    public static int maxArea(int[] height) {
        int n = height.length;
        int s = 0;
        int e = n - 1;
        int ans = 0;
        while (s < e) {
            if (height[s] < height[e]) {
                ans = Math.max(ans, height[s] * (e - s));
                s++;
            } else {
                ans = Math.max(ans, height[e] * (e - s));
                e--;
            }
        }
        return ans;
    }
}
