package del.ifs24024;

import java.util.*;

public class program1{

    public static void solve(Scanner scanner) {

        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int K = scanner.nextInt();

        long[] curse = new long[N + 1]; 
        for (int i = 0; i < K; i++) {
            int city     = scanner.nextInt();
            long penalty = scanner.nextLong();
            curse[city]  = penalty;
        }

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

        final long INF = Long.MAX_VALUE / 2;
        long[][] dist = new long[N + 1][2];
        for (long[] row : dist) Arrays.fill(row, INF);
        dist[S][0] = 0L;

        PriorityQueue<long[]> pq = new PriorityQueue<>(
            Comparator.comparingLong(a -> a[0])
        );
        pq.offer(new long[]{0L, S, 0L});

        while (!pq.isEmpty()) {
            long[] top    = pq.poll();
            long   cost   = top[0];
            int    u      = (int) top[1];
            int    portal = (int) top[2];  

            if (cost > dist[u][portal]) continue;

            if (u == T) break;

            long cursePenalty = (portal == 0) ? curse[u] : 0L;

            for (int[] edge : adj.get(u)) {
                int  v = edge[0];
                long w = edge[1];

                long newCost = cost + w + cursePenalty;
                if (newCost < dist[v][portal]) {
                    dist[v][portal] = newCost;
                    pq.offer(new long[]{newCost, v, portal});
                }

                if (portal == 0) {
                    long portalCost = cost + 0L + cursePenalty; 
                    if (portalCost < dist[v][1]) {
                        dist[v][1] = portalCost;
                        pq.offer(new long[]{portalCost, v, 1L});
                    }
                }
            }
        }
        
        long ans = Math.min(dist[T][0], dist[T][1]);

        if (ans >= INF) {
            System.out.println("Minimum cost: -1");
        } else {
            System.out.println("Minimum cost: " + ans);
        }
    }
}


