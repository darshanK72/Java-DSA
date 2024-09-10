import java.util.Scanner;

public class j09BarChart {
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        barChart(arr);
        in.close();
    }

    public static void barChart(int[] arr){
        int max = arr[0];
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }

        int k = max;
        for(int i = 0; i < max; i++){
            for(int j = 0; j < arr.length; j++){
                if(k <= arr[j]) System.out.print("* ");
                else System.out.print("  ");
            }
            k--;
            System.out.println();
        }
    }
}
