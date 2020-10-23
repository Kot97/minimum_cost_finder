package impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class MinimumCostTable {
    private final Graph graph;
    private int source = 1;
    private HashMap<Integer, Path> results;

    public MinimumCostTable(Graph graph) {
        this(graph, 1);
    }

    public MinimumCostTable(Graph graph, int source) {
        this.graph = graph;
        this.source = source;
        results = new HashMap<>(graph.nodes.size());
        for (Node node : graph.nodes) {
            results.put(node.index, new Path());
        }
    }

    public Path get(Integer target) {
        return results.get(target);
    }

    public void recompute(int source) {
        this.source = source;
        compute();
    }

    public void compute() {
        PriorityQueue<Node> priorityQueue = initPriorityQueue();
        while (!priorityQueue.isEmpty()) {
            relaxNeighbours(priorityQueue.poll());
        }
    }

    private PriorityQueue<Node> initPriorityQueue() {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(
                Comparator.comparing(v -> results.get(v.index).cost()));
        for (Node node : graph.nodes) {
            if (node.index != source) {
                priorityQueue.add(node);
            }
        }
        relaxNeighbours(graph.getNode(source));
        return priorityQueue;
    }

    private void relaxNeighbours(Node actual) {
        for (Node neighbor : actual.getNeighbors()) {
            relaxation(actual, neighbor);
        }
    }

    private void relaxation(Node actual, Node neighbor) {
        if (results.get(actual.index).cost() + Graph.getCostBetween(actual, neighbor) < results.get(neighbor.index).cost()) {
            results.replace(neighbor.index, results.get(actual.index).clone().append(neighbor));
        }
    }
}
