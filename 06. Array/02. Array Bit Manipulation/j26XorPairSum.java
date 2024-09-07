public class j26XorPairSum{
    public static void main(String args[]){
        int[] arr = new int[]{10,20,30,40,50,60,70,80};

        System.out.println(getXorPairSum(arr));
    }
    public static int getXorPairSum(int[] arr){
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            sum ^= 2 * arr[i];
        }
        return sum;
    }
}