package impl;

import java.util.ArrayList;

public class CommonData {
    public static Graph getGraphOne() {
        ArrayList<Node> nodes = new ArrayList<>(6);
        for (int i = 1; i < 7; i++) {
            nodes.add(new Node(i));
        }

        nodes.get(1).outputEdges.add(new Edge(nodes.get(1), 10));
        nodes.get(1).outputEdges.add(new Edge(nodes.get(3), 1000));

        nodes.get(2).outputEdges.add(new Edge(nodes.get(2), 5));
        nodes.get(2).outputEdges.add(new Edge(nodes.get(4), 3));

        nodes.get(3).outputEdges.add(new Edge(nodes.get(5), 70));
        nodes.get(4).outputEdges.add(new Edge(nodes.get(4), 1000));
        nodes.get(5).outputEdges.add(new Edge(nodes.get(5), 1000));

        return new Graph(nodes);
    }

}
