package graph.dagsp;

import common.Graph;
import java.util.*;

public class DAGLongestPath {
    static final int NEG = -1_000_000_000;

    public static class Res {
        public int[] dist, parent;
        public int best;
        Res(int[] d, int[] p, int b) { dist = d; parent = p; best = b; }
    }

    public static Res longest(Graph g, List<Integer> order, int src) {
        int[] dist = new int[g.n];
        int[] parent = new int[g.n];
        Arrays.fill(dist, NEG);
        Arrays.fill(parent, -1);
        dist[src] = 0;

        for (int u : order) {
            if (dist[u] == NEG) continue;
            for (int[] e : g.neighbors(u)) {
                int v = e[0], w = e[1];
                int cand = dist[u] + w;
                if (cand > dist[v]) {
                    dist[v] = cand;
                    parent[v] = u;
                }
            }
        }

        int best = src;
        for (int i = 0; i < g.n; i++)
            if (dist[i] > dist[best]) best = i;

        return new Res(dist, parent, best);
    }

    public static List<Integer> path(int[] parent, int t) {
        List<Integer> p = new ArrayList<>();
        for (int cur = t; cur != -1; cur = parent[cur]) p.add(cur);
        Collections.reverse(p);
        return p;
    }
}
