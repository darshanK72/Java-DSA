import java.util.Scanner;
import java.util.Arrays;

public class j5DifferenceOfArray {
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
        System.out.println(Arrays.toString(diffOfArrays(arr1,arr2)));
        in.close();
    }

     // Method to compute the difference of two arrays
     public static int[] diffOfArrays(int[] arr1,int[] arr2){
        // Getting lengths of both arrays
        int n1 = arr1.length;
        int n2 = arr2.length;
        // Determine the size of the output array
        int m = n1 > n2 ? n1 : n2;
        // Initialize output array
        int[] output = new int[m];

        int carry = 0;
        // Loop through the arrays from right to left
        for(int i = 0; i < m; i++){
            int d1 = arr1[n1-1-i];
            int d2 = arr2[n2-1-i];

            // Check if d1 is greater than or equal to (d2 + carry)
            if(d1 >= (d2+carry)) {
                // If true, subtract normally and set carry to 0
                output[m-1-i] = (d1 - d2 - carry);
                carry = 0;
            }
            else{
                // If false, add 10 to d1, subtract d2 and carry, and set carry to 1
                output[m-1-i] = (d1+10-d2-carry);
                carry = 1;
            }
        }

        return output;
    }
}
