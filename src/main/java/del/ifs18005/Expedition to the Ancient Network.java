package del.alstrudat;

import java.util.*;

/**
 * IMPLEMENTASI SOLUSI LENGKAP
 * Problem: Expedition to the Ancient Network
 *
 * Algorithm : Modified Dijkstra on 2D state space (city, portalUsed)
 * Time      : O((N + M) log N)
 * Space     : O(N + M)
 */
public class Program {

    public static void solve(Scanner scanner) {

        // ----------------------------------------------------------
        // 1. READ INPUT
        // ----------------------------------------------------------
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int K = scanner.nextInt();

        long[] curse = new long[N + 1]; // curse[u] = penalty for leaving u (if portal unused)
        for (int i = 0; i < K; i++) {
            int city     = scanner.nextInt();
            long penalty = scanner.nextLong();
            curse[city]  = penalty;
        }

        // adj.get(u) = list of {v, w}
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            adj.get(u).add(new int[]{v, w});
        }

        int S = scanner.nextInt();
        int T = scanner.nextInt();

        // ----------------------------------------------------------
        // 2. DIJKSTRA ON EXPANDED STATE (city, portalUsed)
        //
        // dist[u][0] = min cost to reach city u WITHOUT having used portal
        // dist[u][1] = min cost to reach city u HAVING used portal
        // ----------------------------------------------------------
        final long INF = Long.MAX_VALUE / 2;
        long[][] dist = new long[N + 1][2];
        for (long[] row : dist) Arrays.fill(row, INF);
        dist[S][0] = 0L;

        // PriorityQueue stores {cost, city, portalUsed}
        // Sorted by cost ascending (min-heap)
        PriorityQueue<long[]> pq = new PriorityQueue<>(
            Comparator.comparingLong(a -> a[0])
        );
        pq.offer(new long[]{0L, S, 0L});

        while (!pq.isEmpty()) {
            long[] top    = pq.poll();
            long   cost   = top[0];
            int    u      = (int) top[1];
            int    portal = (int) top[2];   // 0 = not used, 1 = used

            // Stale-entry check — essential for performance
            if (cost > dist[u][portal]) continue;

            // Early exit if we reached the destination
            if (u == T) break;

            // Curse penalty applies when LEAVING city u, only if portal unused
            long cursePenalty = (portal == 0) ? curse[u] : 0L;

            for (int[] edge : adj.get(u)) {
                int  v = edge[0];
                long w = edge[1];

                // ---- Option 1: move WITHOUT using portal ----
                long newCost = cost + w + cursePenalty;
                if (newCost < dist[v][portal]) {
                    dist[v][portal] = newCost;
                    pq.offer(new long[]{newCost, v, portal});
                }

                // ---- Option 2: USE portal at city u (only if portal == 0) ----
                // Road becomes FREE (w = 0), but curse still applies first.
                if (portal == 0) {
                    long portalCost = cost + 0L + cursePenalty; // road is free
                    if (portalCost < dist[v][1]) {
                        dist[v][1] = portalCost;
                        pq.offer(new long[]{portalCost, v, 1L});
                    }
                }
            }
        }

        // ----------------------------------------------------------
        // 3. COMPUTE ANSWER
        // ----------------------------------------------------------
        long ans = Math.min(dist[T][0], dist[T][1]);

        if (ans >= INF) {
            System.out.println("Minimum cost: -1");
        } else {
            System.out.println("Minimum cost: " + ans);
        }
    }
}
