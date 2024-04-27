import java.util.Scanner;
public class j1MaxOfArray{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        System.out.println(maxOfArray(arr,0));
        in.close();
    }

    public static int maxOfArray(int[] arr,int index){
        if(index == arr.length - 1) return arr[index];
        int max = maxOfArray(arr,index+1);
        if(max > arr[index]) return max;
        return arr[index];
    }
}