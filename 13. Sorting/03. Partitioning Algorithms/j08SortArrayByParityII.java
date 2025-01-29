public class j08SortArrayByParityII {
    public static int[] sortArrayByParityII(int[] nums) {
        int[] out = new int[nums.length];
        int k = 0;
        int l = 1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] % 2 == 0){
                out[k] = nums[i];
                k += 2;
            }else{
                out[l] = nums[i];
                l += 2;
            }
        }
        return out;
    }
}
