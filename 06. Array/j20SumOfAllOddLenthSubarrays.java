import java.util.Scanner;
import java.util.HashMap;

public class j20SumOfAllOddLenthSubarrays{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(sumOfOddLengthSubarrays(arr));
        in.close();
    }

    public static int sumOfOddLengthSubarrays(int[] arr){
        int answer = 0;
        for(int i = 0; i < arr.length; i++){
            int sum = 0;
            for(int j = i; j < arr.length; j++){
                sum += arr[j];
                if((j-i+1) % 2 == 1) answer += sum;
            }
        }
        return answer;
    }
}


// (1) + (2) + (3) + (4) + (5) = 15
// (1 + 2 + 3) + (2 + 3 + 4) + (3 + 4 + 5) = 6 + 9 + 12 = 27
// (1 + 2 + 3 + 4 + 5) = 15

// 15 + 27 + 15 = 57