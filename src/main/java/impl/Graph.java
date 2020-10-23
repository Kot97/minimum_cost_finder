package impl;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;

public class Graph {
    ArrayList<Node> nodes;

    public Graph(ArrayList<Node> nodes) {
        this.nodes = new ArrayList<>(nodes);
    }

    public Node getNode(int index) throws NotFoundException {
        for (Node node : nodes) {
            if (node.index == index) {
                return node;
            }
        }
        throw new NotFoundException(String.format("There is no Node of index %d", index));
    }

    public static float getCostBetween(Node source, Node target) {
        Edge edge = source.getEdgeTo(target);
        return edge.cost() + target.cost(edge);
    }
}
