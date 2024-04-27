import java.util.Scanner;
import java.util.Arrays;
public class j4FindAllAperance{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        System.out.println(Arrays.toString(findAll(arr,0,k,0)));
        in.close();
    }

    public static int[] findAll(int[] arr,int index,int k,int csf){
        if(index == arr.length){
            return new int[csf];
        }
        if(arr[index] == k){
            int[] idall = findAll(arr,index+1,k,csf+1);
            idall[csf] = index;
            return idall;
        }
        else{
            int[] idall= findAll(arr,index+1,k,csf);
            return idall;
        }
    }
}