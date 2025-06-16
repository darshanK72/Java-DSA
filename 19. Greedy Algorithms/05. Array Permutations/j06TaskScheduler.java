import java.util.ArrayList;
import java.util.PriorityQueue;

public class j06TaskScheduler {
    
    static class Pair {
        char c;
        int count;

        Pair(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }

    public static int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            map[tasks[i] - 'A']++;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.count - a.count);

        for (int i = 0; i < 26; i++) {
            if (map[i] > 0)
                pq.add(new Pair((char) ('A' + i), map[i]));
        }

        int totalTime = 0;

        while (!pq.isEmpty()) {
            ArrayList<Pair> list = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                if (!pq.isEmpty()) {
                    Pair pair = pq.remove();
                    pair.count--;
                    list.add(pair);
                }
            }

            for (Pair p : list) {
                if (p.count > 0)
                    pq.add(p);
            }

            if (pq.isEmpty())
                totalTime += list.size();
            else
                totalTime += (n + 1);
        }

        return totalTime;
    }
}
