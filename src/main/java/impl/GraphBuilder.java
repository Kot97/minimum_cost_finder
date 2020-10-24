package impl;

import java.util.ArrayList;
import java.util.List;

public class GraphBuilder {
    public static Graph build(List<DatabaseRow> data) {
        List<Node> nodes = new ArrayList<>(20);
        for (DatabaseRow databaseRow : data) {
            checkNodeExistence(nodes, databaseRow);
            createEdge(nodes, databaseRow);
        }
        return new Graph(nodes);
    }

    private static void checkNodeExistence(List<Node> nodes, DatabaseRow databaseRow) {
        if (nodeIndexNotPresent(nodes, databaseRow.x)) {
            nodes.add(new Node(databaseRow.x));
        }
        if (nodeIndexNotPresent(nodes, databaseRow.y)) {
            nodes.add(new Node(databaseRow.y));
        }
    }

    private static boolean nodeIndexNotPresent(List<Node> nodes, int index) {
        return nodes.stream().noneMatch(n -> n.index == index);
    }

    private static void createEdge(List<Node> nodes, DatabaseRow databaseRow) {
        nodes.stream().filter(n -> n.index == databaseRow.x).findAny().get().outputEdges.add(
                new Edge(nodes.stream().filter(n -> n.index == databaseRow.y).findAny().get(), databaseRow.p));
    }
}
