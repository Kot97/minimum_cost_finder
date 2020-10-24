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

    public void clear() {
        nodes.clear();
    }

    public Float cost() {
        float sum = 0;
        for (int i = 0; i < length() - 1; i++) {
            sum += Graph.getCostBetween(nodes.get(i), nodes.get(i + 1));
        }
        return sum;
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
