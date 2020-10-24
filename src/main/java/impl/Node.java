package impl;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class Node {
    int index;
    ArrayList<Edge> outputEdges = new ArrayList<>(4);

    public Node(int index) {
        this.index = index;
    }

    public Edge getEdgeTo(Node node) {
        for (Edge edge : outputEdges) {
            if (edge.target == node) {
                return edge;
            }
        }
        throw new NotFoundException(String.format("There is no connection from %d to %d", index, node.index));
    }

    public float cost(Edge edge) {
        if (isStartNode() || isEndNode()) {
            return 0;
        }
        float sum = 0;
        for (Edge outputEdge : outputEdges) {
            sum += outputEdge.bandwidth;
        }
        return abs(edge.bandwidth - sum);
    }

    public boolean isStartNode() {
        return index == 1;
    }

    public boolean isEndNode() {
        return outputEdges.isEmpty();
    }
}
