import java.util.Arrays;
import java.util.Scanner;

public class j15CeilAndFloor {
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();

        System.out.println(Arrays.toString(ceilAndFloor(arr,k)));
        in.close();
    }

    public static int[] ceilAndFloor(int[] arr,int k){
        int ceil = Integer.MIN_VALUE;
        int floor = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] >= k && arr[i] <= floor){
                floor = arr[i];
            }
            if(arr[i] <= k && arr[i] >= ceil){
                ceil = arr[i];
            }
        }
        return new int[]{ceil,floor};
    }

}
