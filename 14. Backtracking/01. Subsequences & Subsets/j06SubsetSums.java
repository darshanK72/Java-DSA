import java.util.ArrayList;

public class j06SubsetSums {
    public ArrayList<Integer> subsetSums(int[] arr) {
        ArrayList<Integer> set = new ArrayList<>();
        set.add(0);
        generateSubsetSum(arr, 0, 0, set);
        return set;
    }

    public static void generateSubsetSum(int[] arr, int index, int currSum, ArrayList<Integer> set) {
        if (index == arr.length)
            return;
        currSum += arr[index];
        set.add(currSum);
        generateSubsetSum(arr, index + 1, currSum, set);
        currSum -= arr[index];
        generateSubsetSum(arr, index + 1, currSum, set);
    }
}
