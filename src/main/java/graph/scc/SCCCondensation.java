package graph.scc;

import common.Graph;
import java.util.*;

public class SCCCondensation {
    public static Graph buildCondensedGraph(Graph g, List<List<Integer>> scc) {
        int n = scc.size();
        Graph dag = new Graph(n);
        int[] comp = new int[g.n];

        for (int i = 0; i < n; i++)
            for (int v : scc.get(i))
                comp[v] = i;

        Set<String> seen = new HashSet<>();
        for (int u = 0; u < g.n; u++) {
            for (int[] e : g.neighbors(u)) {
                int v = e[0];
                int cu = comp[u], cv = comp[v];
                if (cu != cv) {
                    String key = cu + "-" + cv;
                    if (!seen.contains(key)) {
                        dag.addEdge(cu, cv, e[1]);
                        seen.add(key);
                    }
                }
            }
        }
        return dag;
    }
}
