import java.util.Scanner;
public class j16MaximumConsecutive1s{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(maxConsOnes(arr));
        in.close();
    }

    public static int maxConsOnes(int[] arr){
        int count = 0;
        int result = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 1){
                count++;
            }
            else{
                if(count > result) result = count;
                count = 0;
            }
        }
        if(count > result) return count;
        return result;
    }
}