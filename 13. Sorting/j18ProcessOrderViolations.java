import java.util.HashMap;
import java.util.Map;

public class j18ProcessOrderViolations {
    public static void main(String[] args) {
        int[] processingOrder = { 3, 5, 4, 2, 1 };
        int[] executionOrder = { 4, 3, 5, 1, 2 };

        System.out.println("Number of Violations: " + countViolations(processingOrder, executionOrder));
    }

    public static int countViolations(int[] processingOrder, int[] executionOrder) {
        int ans = 0;
        int s = 0;

        for (int i = 0; i < processingOrder.length; i++) {
            boolean found = false;

            for (int j = s; j < executionOrder.length; j++) {
                if (processingOrder[i] == executionOrder[j]) {
                    s = j + 1; // Update 's' to continue from the next position
                    found = true;
                    break;
                }
            }

            // If the process in `processingOrder` wasn't found in order, count it as a
            // violation
            if (!found) {
                ans++;
            }
        }

        return ans;
    }

    public static int countViolationsEfficient(int[] processingOrder, int[] executionOrder) {
        // Step 1: Map each process to its index in the processingOrder
        Map<Integer, Integer> processIndexMap = new HashMap<>();
        for (int i = 0; i < processingOrder.length; i++) {
            processIndexMap.put(processingOrder[i], i);
        }

        int violations = 0;
        int maxIndexSeen = -1;

        // Step 2: Check for violations in executionOrder
        for (int process : executionOrder) {
            int currentIndex = processIndexMap.get(process); // Retrieve intended position

            // If the current index is less than maxIndexSeen, it's a violation
            if (currentIndex < maxIndexSeen) {
                violations++;
            } else {
                maxIndexSeen = currentIndex; // Update max index if in correct order
            }
        }

        return violations;
    }

}
