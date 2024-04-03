import java.util.Scanner;

public class j2FindUnique{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int[] arr = new int[s];
        for(int i = 0; i < s; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(findUniqueFromArray(arr));
        in.close(); 
    }

    public static int findUniqueFromArray(int[] arr){
        int ans = 0;
        for(int n : arr){
            ans ^= n;
        }
        return ans;
    }
}