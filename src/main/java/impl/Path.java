package impl;

import java.util.ArrayList;

public class Path implements Cloneable {
    private ArrayList<Node> nodes = new ArrayList<>(10);
    Node lastNode = null;
    public static String separator = " --> ";

    public Path append(Node node) {
        nodes.add(node);
        lastNode = node;
        return this;
    }

    public int length() {
        return nodes.size();
    }

    public void clear() {
        nodes.clear();
    }

    public Float cost() {
        if (nodes.isEmpty()) {
            return Float.MAX_VALUE;
        }
        else {
            float sum = 0;
            for (int i = 0; i < length() - 1; i++) {
                sum += Graph.getCostBetween(nodes.get(i), nodes.get(i + 1));
            }
            return sum;
        }
    }

    public Path clone() {
        Path path = new Path();
        path.nodes = new ArrayList<>(nodes);
        path.lastNode = lastNode;
        return path;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(nodes.get(0).index);
        for(Node node : nodes) {
            builder.append(separator).append(node.index);
        }
        return builder.toString();
    }
}
