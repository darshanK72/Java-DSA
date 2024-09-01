import java.util.Scanner;

public class j7MissingNumber {
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(missingNumber(arr));
        in.close();
    }

    public static int missingNumber(int[] arr){
        for(int i = 0; i < arr.length; i++){
            int original = arr[i] % (arr.length + 1);
            if(original < arr.length)
                arr[original] += (arr.length + 1);
        }

        for(int i = 0; i < arr.length; i++){
            if(arr[i] / (arr.length+1) == 0) return i;
        }
        return -1;
    }
}
