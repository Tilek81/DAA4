package graph.scc;

import common.Graph;
import java.util.*;

public class TarjanSCC {
    Graph g;
    int[] disc, low;
    boolean[] st;
    Deque<Integer> stack = new ArrayDeque<>();
    int time = 0;

    public List<List<Integer>> comps = new ArrayList<>();

    public TarjanSCC(Graph g) {
        this.g = g;
        disc = new int[g.n];
        low = new int[g.n];
        st = new boolean[g.n];
        Arrays.fill(disc, -1);
    }

    void dfs(int u) {
        disc[u] = low[u] = time++;
        stack.push(u);
        st[u] = true;

        for (int[] e : g.neighbors(u)) {
            int v = e[0];
            if (disc[v] == -1) {
                dfs(v);
                low[u] = Math.min(low[u], low[v]);
            } else if (st[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        if (low[u] == disc[u]) {
            List<Integer> comp = new ArrayList<>();
            while (true) {
                int v = stack.pop();
                st[v] = false;
                comp.add(v);
                if (v == u) break;
            }
            comps.add(comp);
        }
    }

    public List<List<Integer>> run() {
        for (int i = 0; i < g.n; i++) if (disc[i] == -1) dfs(i);
        return comps;
    }
}
