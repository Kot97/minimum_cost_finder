package impl;

import java.util.ArrayList;
import javax.ws.rs.NotFoundException;

import static java.lang.Math.abs;

public class Node {
    int index;
    ArrayList<Edge> outputEdges;

    public Node(int index) {
        this.index = index;
        outputEdges = new ArrayList<>(4);
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
        if (isEndNode()) {
            return 0;
        }
        float sum = 0;
        for (Edge outputEdge : outputEdges) {
            sum += outputEdge.bandwidth;
        }
        return abs(edge.bandwidth - sum);
    }

    private boolean isEndNode() {
        return outputEdges.isEmpty();
    }
}
