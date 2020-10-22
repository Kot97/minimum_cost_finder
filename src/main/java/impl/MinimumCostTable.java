package impl;

import java.util.HashMap;

public class MinimumCostTable {
    private final Graph graph;
    private int source = 1;
    private HashMap<Integer, Path> results = new HashMap<>(20);

    public MinimumCostTable(Graph graph) {
        this.graph = graph;
    }

    public MinimumCostTable(Graph graph, int source) {
        this.graph = graph;
        this.source = source;
    }

    public void recompute(int source) {
        this.source = source;
        compute();
    }

    public Path at(Integer target) {
        return results.get(target);
    }

    private void compute() {

    }
}
