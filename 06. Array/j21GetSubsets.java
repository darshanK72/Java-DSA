import java.util.Scanner;

public class j21GetSubsets {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        printSubsets(arr);
        in.close();
    }

    public static void printSubsets(int[] arr){
        for(int i = 0; i < Math.pow(2,arr.length); i++){
            for(int j = 0; j < arr.length; j++){
                if((i & (1 << j)) != 0){
                    System.out.print(arr[j] + " ");
                }
                else{
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
}
