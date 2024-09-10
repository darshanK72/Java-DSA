import java.util.Arrays;
import java.util.Scanner;

public class j24SumOfArrays {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt();
        int[] arr1 = new int[n1];
        for(int i = 0; i < n1; i++){
            arr1[i] = in.nextInt();
        }
        int n2 = in.nextInt();
        int[] arr2 = new int[n2];
        for(int i = 0; i < n2; i++){
            arr2[i] = in.nextInt();
        }
        System.out.println(Arrays.toString(sumOfArrays(arr1,arr2)));
        in.close();
    }

    public static int[] sumOfArrays(int[] arr1,int[] arr2){
        int n1 = arr1.length;
        int n2 = arr2.length;
        int m = n1 > n2 ? n1 : n2;
        int[] output = new int[m];

        int carry = 0;
        for(int i = 0; i < m; i++){
            int sum = 0;
            if(n1-1-i >= 0) sum += arr1[n1-1-i];
            if(n2-1-i >= 0) sum += arr2[n2-1-i];
            if(carry > 0) sum += carry;
            output[m-1-i] = sum % 10;
            carry = sum / 10;
        }

        return output;
    }
}
