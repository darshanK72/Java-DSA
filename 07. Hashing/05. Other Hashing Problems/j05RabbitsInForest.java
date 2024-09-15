import java.util.HashMap;
import java.util.Scanner;

public class j05RabbitsInForest {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        System.out.println(numRabbits1(arr));
        System.out.println(numRabbitsEfficient(arr));
        in.close();
    }

    public static int numRabbits1(int[] answers) {
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == 0) {
                ans++;
            } else {
                if (map.containsKey(answers[i] + 1)) {
                    if (map.get(answers[i] + 1) <= answers[i]) {
                        map.put(answers[i] + 1, map.get(answers[i] + 1) + 1);
                    } else {
                        ans += answers[i] + 1;
                        map.put(answers[i] + 1, 1);
                    }
                } else {
                    map.put(answers[i] + 1, 1);
                }
            }
        }
        for (int key : map.keySet()) {
            ans += key;
        }
        return ans;
    }

    public static int numRabbits2(int[] answers) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int totalRabbits = 0;

        for (int answer : answers) {
            if (answer == 0) {
                totalRabbits++; // Each rabbit saying 0 is a separate rabbit
            } else {
                map.put(answer, map.getOrDefault(answer, 0) + 1);
                // If we filled a group of 'answer + 1' rabbits
                if (map.get(answer) == answer + 1) {
                    totalRabbits += answer + 1;
                    map.remove(answer); // Reset the group
                }
            }
        }

        // Any remaining incomplete groups need to be added as well
        for (int key : map.keySet()) {
            totalRabbits += key + 1;
        }

        return totalRabbits;
    }

    public static int numRabbitsEfficient(int[] answers) {
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int answer : answers) {
            map.put(answer, map.getOrDefault(answer, 0) + 1);
        }

        for (int key : map.keySet()) {
            int size = key + 1;
            int rab = map.get(key);
            int grp = (int) Math.ceil((1.0 * rab) / (1.0 * size));
            ans += size * grp;
        }
        return ans;
    }
}
