package impl;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    ArrayList<Node> nodes;

    public Graph(List<Node> nodes) {
        this.nodes = new ArrayList<>(nodes);
    }

    public Node getNode(int index) {
        for (Node node : nodes) {
            if (node.index == index) {
                return node;
            }
        }
        throw new NotFoundException(String.format("There is no Node of index %d", index));
    }
}
