import java.util.Scanner;

public class j36MaxRepeatedNumber {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println(maxRepeating(arr,k));
        in.close();
    }

    public static int maxRepeating(int[] arr,int k) {
        int n = arr.length;
        for(int i = 0;i < n; i++){
            int original = arr[i] % n;
            arr[original] += n;
        }
        
        int maxRepetingNum = -1;
        int maxCount = 0;
        for(int i = 0; i < n; i++){
            if(arr[i]/n > maxCount){
                maxRepetingNum = i;
                maxCount = arr[i]/n;
            }
        }
        return maxRepetingNum;
    }
}
