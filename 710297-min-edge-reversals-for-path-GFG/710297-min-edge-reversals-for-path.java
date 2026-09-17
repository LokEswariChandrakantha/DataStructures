import java.util.*;

class Solution {
    public int minimumEdgeReversal(int[][] edges, int n, int src, int dst) {

        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // Original direction has cost 0
        // Reverse direction has cost 1
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(new int[]{v, 0});
            graph.get(v).add(new int[]{u, 1});
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Deque<Integer> deque = new ArrayDeque<>();

        dist[src] = 0;
        deque.addFirst(src);

        while (!deque.isEmpty()) {

            int u = deque.pollFirst();

            for (int[] next : graph.get(u)) {

                int v = next[0];
                int cost = next[1];

                if (dist[u] + cost < dist[v]) {

                    dist[v] = dist[u] + cost;

                    if (cost == 0) {
                        deque.addFirst(v);
                    } else {
                        deque.addLast(v);
                    }
                }
            }
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna