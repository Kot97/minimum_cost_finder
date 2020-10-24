package impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchTableTest {
    static Graph graph;
    static MinimumCostTable costTable;
    static final double DELTA = 0.00001;

    @BeforeAll
    static void setUp() {
        Path.separator = ".";
        graph = makeGraph();
        SearchTable.add("test", graph, 0);
    }

    @Test
    void testResults() {
        assertEquals(String.format(SearchTable.FORMAT, SearchTable.PATH_DESCRIPTION, "0",
                SearchTable.COST_DESCRIPTION, 0f), SearchTable.getResult("test", 0));
        assertEquals(String.format(SearchTable.FORMAT, SearchTable.PATH_DESCRIPTION, "0.1",
                SearchTable.COST_DESCRIPTION, 0.1f), SearchTable.getResult("test", 1));
        assertEquals(String.format(SearchTable.FORMAT, SearchTable.PATH_DESCRIPTION, "0.1.2",
                SearchTable.COST_DESCRIPTION, 2.3f), SearchTable.getResult("test", 2));
        assertEquals(String.format(SearchTable.FORMAT, SearchTable.PATH_DESCRIPTION, "0.3",
                SearchTable.COST_DESCRIPTION, 0.001f), SearchTable.getResult("test", 3));
        assertEquals(String.format(SearchTable.FORMAT, SearchTable.PATH_DESCRIPTION, "0.3.4",
                SearchTable.COST_DESCRIPTION, 0.002f), SearchTable.getResult("test", 4));
        assertEquals(String.format(SearchTable.FORMAT, SearchTable.PATH_DESCRIPTION, "0.3.4.5",
                SearchTable.COST_DESCRIPTION, 0.003f), SearchTable.getResult("test", 5));
    }

    public static Graph makeGraph() {
        ArrayList<Node> nodes = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            nodes.add(new Node(i));
        }

        nodes.get(0).outputEdges.add(new Edge(nodes.get(1), 10));
        nodes.get(0).outputEdges.add(new Edge(nodes.get(3), 1000));

        nodes.get(1).outputEdges.add(new Edge(nodes.get(2), 5));
        nodes.get(1).outputEdges.add(new Edge(nodes.get(4), 3));

        nodes.get(2).outputEdges.add(new Edge(nodes.get(5), 70));
        nodes.get(3).outputEdges.add(new Edge(nodes.get(4), 1000));
        nodes.get(4).outputEdges.add(new Edge(nodes.get(5), 1000));

        return new Graph(nodes);
    }
}