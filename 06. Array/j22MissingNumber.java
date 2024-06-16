import java.util.Scanner;

public class j22MissingNumber {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(missingNumber(arr));
        System.out.println(missingNumberEfficient(arr));
        in.close();
    }

    public static int missingNumber(int[] arr){
        int[] temp = new int[arr.length + 1];
        for(int i = 0; i < arr.length; i++){
            temp[arr[i]]++;
        }

        for(int i = 0; i < temp.length; i++){
            if(temp[i] == 0) return i;
        }
        return -1;
    }

    public static int missingNumberEfficient(int[] arr){
        int ans = 0;
        for(int i = 0; i < arr.length; i++){
            ans ^= arr[i];
            ans ^= (i+1);
        }
        return ans;
    }
}
