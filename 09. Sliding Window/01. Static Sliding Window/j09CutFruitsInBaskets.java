import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class j09CutFruitsInBaskets {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        System.out.println(totalFruit(nums));
        System.out.println(totalFruitEfficient(nums));
        in.close();
    }

    public static int totalFruit(int[] fruits) {
        int maxL = 0;
        for (int i = 0; i < fruits.length; i++) {
            HashSet<Integer> set = new HashSet<>();
            for (int j = i; j < fruits.length; j++) {
                set.add(fruits[j]);
                if (set.size() <= 2) {
                    maxL = Math.max(maxL, j - i + 1);
                } else {
                    break;
                }
            }
        }
        return maxL;
    }

    public static int totalFruitEfficient(int[] fruits) {
        int maxL = 0;
        int j = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < fruits.length; i++) {
            map.put(fruits[i], map.getOrDefault(fruits[i], 0) + 1);
            while (map.size() > 2) {
                map.put(fruits[j], map.get(fruits[j]) - 1);
                if (map.get(fruits[j]) == 0)
                    map.remove(fruits[j]);
                j++;
            }
            maxL = Math.max(maxL, i - j + 1);
        }
        return maxL;
    }
}
