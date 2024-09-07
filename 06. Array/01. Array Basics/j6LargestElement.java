import java.util.Scanner;
public class j6LargestElement{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        System.out.println(maxElementIndex(arr));
        System.out.println(maxElementOfArray(arr,0));
        in.close();
    }

    public static int maxElementIndex(int[] arr){
        int index = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > arr[index]) {
                index = i;
            }
        }
        return index;
    }

    public static int maxElementOfArray(int[] arr,int index){
        if(index == arr.length - 1) return arr[index];
        int max = maxElementOfArray(arr,index+1);
        if(max > arr[index]) return max;
        return arr[index];
    }
}