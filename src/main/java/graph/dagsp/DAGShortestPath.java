package graph.dagsp;

import common.Graph;
import java.util.*;

public class DAGShortestPath {
    public static int[] shortest(Graph g, List<Integer> order, int src) {
        int INF = 1_000_000_000;
        int[] dist = new int[g.n];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int u : order)
            if (dist[u] < INF)
                for (int[] e : g.neighbors(u))
                    dist[e[0]] = Math.min(dist[e[0]], dist[u] + e[1]);

        return dist;
    }
}
