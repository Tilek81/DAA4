package common;

import java.util.*;

public class Graph {
    public int n;
    public List<List<int[]>> adj;

    public Graph(int n) {
        this.n = n;
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
    }

    public void addEdge(int u, int v, int w) {
        adj.get(u).add(new int[]{v, w});
    }

    public List<int[]> neighbors(int u) {
        return adj.get(u);
    }
}
