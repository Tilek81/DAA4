import common.Graph;
import io.JsonGraphIO;
import graph.scc.TarjanSCC;
import graph.scc.SCCCondensation;
import graph.topo.TopologicalSort;
import graph.dagsp.DAGShortestPath;
import graph.dagsp.DAGLongestPath;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        String[] files = {
                "small_dag_1.json", "small_mix_2.json", "small_mix_3.json",
                "med_mix_1.json", "med_dag_2.json", "med_mix_3.json",
                "large_mix_1.json", "large_dag_2.json", "large_mix_3.json"
        };

        for (String f : files) {

            JsonGraphIO.Input input = JsonGraphIO.load("data/" + f);
            Graph g = JsonGraphIO.toGraph(input);

            System.out.println();
            System.out.println(f);
            System.out.println();

            List<List<Integer>> scc = new TarjanSCC(g).run();
            Graph dag = SCCCondensation.buildCondensedGraph(g, scc);
            List<Integer> topo = TopologicalSort.kahn(dag);

            int srcComp = 0;
            for (int i = 0; i < scc.size(); i++)
                if (scc.get(i).contains(input.source))
                    srcComp = i;

            int[] shortest = DAGShortestPath.shortest(dag, topo, srcComp);
            DAGLongestPath.Res longest = DAGLongestPath.longest(dag, topo, srcComp);
            List<Integer> path = DAGLongestPath.path(longest.parent, longest.best);

            System.out.println("SCC count: " + scc.size());
            System.out.println("Topo order: " + topo);
            System.out.println("Shortest: " + Arrays.toString(shortest));
            System.out.println("Longest length: " + longest.dist[longest.best]);
            System.out.println("Longest path: " + path);
        }
    }
}
