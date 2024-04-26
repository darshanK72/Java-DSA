import java.util.Scanner;

public class j15FrequencyInSortedArray{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }

        frequency(arr);
        in.close();
    }

    public static void frequency(int[] arr){
        int f = 1;
        int i = 1;
        while(i < arr.length){
            while(i < arr.length && arr[i] == arr[i-1]){
                f++;
                i++;
            }
            System.out.println(arr[i-1] + " " + f);
            f = 1;
            i++;
        }
        if(arr.length == 1 || arr[arr.length-1] != arr[arr.length-2]){
            System.out.println(arr[arr.length-1] + " " + f);
        }
    }

}