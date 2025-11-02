package graph.topo;

import common.Graph;
import java.util.*;

public class TopologicalSort {
    public static List<Integer> kahn(Graph g) {
        int[] in = new int[g.n];
        for (int u = 0; u < g.n; u++)
            for (int[] e : g.neighbors(u)) in[e[0]]++;

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < g.n; i++) if (in[i] == 0) q.add(i);

        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            for (int[] e : g.neighbors(u)) {
                if (--in[e[0]] == 0) q.add(e[0]);
            }
        }
        return order;
    }
}
