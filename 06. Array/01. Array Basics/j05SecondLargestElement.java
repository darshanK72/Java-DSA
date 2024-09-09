import java.util.Scanner;
public class j05SecondLargestElement{
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
        int secMaxIndex = -1;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > arr[maxIndex]) {
                secMaxIndex = maxIndex;
                maxIndex = i;
            }else if(arr[i] != arr[maxIndex]){             
                if(secMaxIndex == -1 || arr[i] > arr[secMaxIndex]){
                    secMaxIndex = i;
                }
            }

        }
        return secMaxIndex;
    }
}