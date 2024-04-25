import java.util.Scanner;
public class j6LargestElement{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        System.out.println(maxElement(arr));
        in.close();
    }

    public static int maxElement(int[] arr){
        int maxIndex = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > arr[maxIndex]) maxIndex = i;
        }
        return maxIndex;
    }
}