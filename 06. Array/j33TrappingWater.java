import java.util.Scanner;
public class j33TrappingWater{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(trapedWaterEfficient(arr));
        in.close();
    }

    // O(n^2)
    public static int trapedWater(int[] arr){
        int result = 0;
        int n = arr.length;
        for(int i = 1; i < arr.length - 1; i++){
          int lmax = arr[i];
          for(int j = 0; j < i; j++){
            if(arr[j] > lmax) lmax = arr[j];
          }
          int rmax = arr[i];
          for(int j = i+1; j < n; j++){
            if(arr[j] > rmax) rmax = arr[j];
          }
            result += Math.min(lmax,rmax) - arr[i];
        }

        return result;
    }

    // O(n)
    public static int trapedWaterEfficient(int[] arr){
        int result = 0;
        int n = arr.length;
        int[] lMax = new int[n];
        int[] rMax = new int[n];
        lMax[0] = arr[0];
        for(int i = 1; i < n; i++){
            lMax[i] = Math.max(arr[i],lMax[i-1]);
        }
        rMax[n-1] = arr[n-1];
        for(int i = n - 2; i >= 0; i--){
            rMax[i] = Math.max(arr[i],rMax[i+1]);
        }

        for(int i = 1; i < n-1; i++){
            result += Math.min(lMax[i],rMax[i]) - arr[i];
        }
        return result;
    }
}