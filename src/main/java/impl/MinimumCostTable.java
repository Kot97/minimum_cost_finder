package impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class MinimumCostTable {
    private final Graph graph;
    private int source;
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
        results.get(source).append(graph.getNode(source));
    }

    public Path getMinimumCostPath(Integer target) {
        return results.get(target);
    }

    public void recompute(int source) {
        this.source = source;
        compute();
    }

    public void compute() {
        PriorityQueue<PriorityQueueNode> priorityQueue = initPriorityQueue();
        HashMap<Integer, PriorityQueueNode> lookupTable = initLookupTable(priorityQueue);
        while (!priorityQueue.isEmpty()) {
            relaxNeighbours(priorityQueue.poll(), lookupTable);
        }
    }

    private PriorityQueue<PriorityQueueNode> initPriorityQueue() {
        PriorityQueue<PriorityQueueNode> priorityQueue = new PriorityQueue<>(Comparator.comparing(v -> v.cost));
        for (Node node : graph.nodes) {
            if (node.index != source) {
                priorityQueue.add(new PriorityQueueNode(node, Float.MAX_VALUE));
            }
        }
        priorityQueue.add(new PriorityQueueNode(graph.getNode(source), 0));
        return priorityQueue;
    }

    private HashMap<Integer, PriorityQueueNode> initLookupTable(PriorityQueue<PriorityQueueNode> priorityQueue) {
        HashMap<Integer, PriorityQueueNode> lookupTable = new HashMap<>(priorityQueue.size());
        for (PriorityQueueNode priorityQueueNode : priorityQueue) {
            lookupTable.put(priorityQueueNode.graphNode.index, priorityQueueNode);
        }
        return lookupTable;
    }

    private void relaxNeighbours(PriorityQueueNode actual, HashMap<Integer, PriorityQueueNode> lookupTable) {
        for (PriorityQueueNode neighbor : getNeighbors(actual, lookupTable)) {
            relaxation(actual, neighbor);
        }
    }

    private ArrayList<PriorityQueueNode> getNeighbors(PriorityQueueNode actual, HashMap<Integer, PriorityQueueNode> lookupTable) {
        ArrayList<PriorityQueueNode> neighbors = new ArrayList<>(5);
        for (Edge edge : actual.graphNode.outputEdges) {
            neighbors.add(lookupTable.get(edge.target.index));
        }
        return neighbors;
    }

    private void relaxation(PriorityQueueNode actual, PriorityQueueNode neighbor) {
        Path potentiallyFasterPath = new Path(results.get(actual.graphNode.index)).append(neighbor.graphNode);
        float potentiallyLowerCost = potentiallyFasterPath.getCost();
        if (potentiallyLowerCost < neighbor.cost) {
            results.replace(neighbor.graphNode.index, potentiallyFasterPath);
            neighbor.cost = potentiallyLowerCost;
        }
    }

    private static class PriorityQueueNode {
        Node graphNode;
        float cost;

        public PriorityQueueNode(Node graphNode, float cost) {
            this.graphNode = graphNode;
            this.cost = cost;
        }
    }
}
