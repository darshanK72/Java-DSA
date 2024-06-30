import java.util.Scanner;

public class j26MinimumXorValuePair{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(minimumXorValuePair(arr));
        in.close();
    }

    public static int minimumXorValuePair(int[] arr){
        Arrays.sort(arr);
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length - 1;i++){
            result = Math.min(result,arr[i] ^ arr[i+1]);
        }
        return result;
    }
}