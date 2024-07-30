import java.util.Scanner;

public class j31BitDifferencePairSum{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0;i < n; i++){
            arr[i] = in.nextInt();
        }

        System.out.println(sumOfBitDifferenceOfAllPairsNive(arr));
        System.out.println(sumOfBitDifferenceOfAllPairsEfficient(arr));
        in.close();
    }

    // O(n^2)
    public static int sumOfBitDifferenceOfAllPairsNive(int[] arr){
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            for(int j = i+1; j < arr.length; j++){
                sum += 2 * Integer.bitCount(arr[i] ^ arr[j]);
            }
        }
        return sum;
    }

    // O(32 * n)
    public static int sumOfBitDifferenceOfAllPairsEfficient(int[] arr){
        int sum = 0;
        for(int i = 0; i < 32; i++){
            int onBits = 0;
            for(int j = 0; j < arr.length; j++){
                if((arr[j] & (1 << i)) != 0){
                    onBits++;
                }
            }
            sum += 2 * (onBits * (arr.length - onBits));
        }
        return sum;
    }
}