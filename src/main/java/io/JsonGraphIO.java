package io;

import com.google.gson.*;
import common.Graph;
import java.io.FileReader;

public class JsonGraphIO {
    static class Edge { int u, v, w; }
    public static class Input {
        public int n;
        public Edge[] edges;
        public int source;
    }

    public static Input load(String file) throws Exception {
        return new Gson().fromJson(new FileReader(file), Input.class);
    }

    public static Graph toGraph(Input d) {
        Graph g = new Graph(d.n);
        for (Edge e : d.edges) g.addEdge(e.u, e.v, e.w);
        return g;
    }
}
