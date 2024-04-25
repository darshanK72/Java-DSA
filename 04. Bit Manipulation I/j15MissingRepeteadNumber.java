import java.util.Arrays;
public class j15MissingRepeteadNumber{
    public static void main(String args[]){
       int[] arr = new int[]{1,2,3,2};
       System.out.println(Arrays.toString(repeatAndMissing(arr)));
    }

    public static int[] repeatAndMissing(int[] arr){
        int xor = 0;
        for(int i = 0;i < arr.length; i++){
            xor ^= arr[i];
        }

        for(int i = 1; i <= arr.length; i++){
            xor ^= i;
        }

        int rd = xor & -xor;
        int first = 0;
        int second = 0;
        for(int i = 0; i < arr.length; i++){
            if((arr[i] & rd) == 0){
                first ^= arr[i];
            }
            else{
                second ^= arr[i];
            }
        }
        for(int i = 1; i <= arr.length; i++){
            if((i & rd) == 0){
                first ^= i;
            }
            else{
                second ^= i;
            }
        }

        return new int[]{first,second};
    }
}