import java.util.Arrays;
import java.util.Scanner;

public class j08MergeTwoSortedArraysInFirst {
     public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int[] arr1 = new int[m + n];
        int[] arr2 = new int[n];
        for(int i = 0; i < m; i++){
            arr1[i] = in.nextInt();
        }
        for(int i = 0; i < n; i++){
            arr2[i] = in.nextInt();
        }
        mergeArraysEfficient(arr1,m,arr2,n);
        System.out.println(Arrays.toString(arr1));
        in.close();
    }

    public static void mergeArraysEfficient(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = nums1.length - 1;
        while(i >= 0 && j >= 0){
            if(nums1[i] >= nums2[j]){
                nums1[k] = nums1[i];
                i--;
            }else{
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
        while(i >= 0){
            nums1[k] = nums1[i];
            i--;
            k--; 
        }
        while(j >= 0){
            nums1[k] = nums2[j];
            j--;
            k--; 
        }
    }
}
