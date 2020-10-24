package impl;

import java.util.ArrayList;

public class Path {
    private ArrayList<Node> nodes;
    Node lastNode = null;
    public static String separator = " --> ";

    public Path() {
        this.nodes = new ArrayList<>(10);
    }

    public Path append(Node node) {
        nodes.add(node);
        lastNode = node;
        return this;
    }

    public Path(Path path) {
        this.nodes = new ArrayList<>(path.nodes);
        this.lastNode = path.lastNode;
    }

    public int length() {
        return nodes.size();
    }

    public float getCost() {
        int length = length();
        if (length <= 1) {
            return 0f;
        }
        float sum = 0;
        Edge edge;
        for (int i = 0; i < length - 2; i++) {
            edge = nodes.get(i).getEdgeTo(nodes.get(i + 1));
            sum += edge.cost() + nodes.get(i + 1).cost(edge);
        }
        return sum + nodes.get(length - 2).getEdgeTo(nodes.get(length - 1)).cost();
    }

    public String toString() {
        if (length() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(nodes.get(0).index);
        for (int i = 1; i < nodes.size(); i++) {
            builder.append(separator).append(nodes.get(i).index);
        }
        return builder.toString();
    }
}
