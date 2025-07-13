import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class j01CheapestFlightsWithinKStops {
    static class Flight {
        int to;
        int price;

        Flight(int to, int price) {
            this.to = to;
            this.price = price;
        }
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<Flight>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < flights.length; i++) {
            int from = flights[i][0];
            int to = flights[i][1];
            int price = flights[i][2];
            adj[from].add(new Flight(to, price));
        }

        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);

        // PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
        // if(a[1] != b[1]) return a[1] - b[1];
        // else return a[2] - b[2];
        // });
        Queue<int[]> pq = new LinkedList<>();
        pq.add(new int[] { src, 0, 0 });
        prices[src] = 0;
        while (!pq.isEmpty()) {
            int[] flight = pq.poll();
            if (flight[2] > k)
                continue;
            for (Flight neb : adj[flight[0]]) {
                int newPrice = flight[1] + neb.price;
                if (prices[neb.to] > newPrice && flight[2] <= k) {
                    prices[neb.to] = newPrice;
                    pq.add(new int[] { neb.to, newPrice, flight[2] + 1 });
                }
            }
        }

        if (prices[dst] == Integer.MAX_VALUE)
            return -1;
        return prices[dst];
    }
}
