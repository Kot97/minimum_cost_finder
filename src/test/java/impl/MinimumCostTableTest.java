package impl;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinimumCostTableTest {
    static Graph graph;
    static MinimumCostTable costTable;
    static final double DELTA = 0.00001;

    @BeforeAll
    static void setUp() {
        Path.separator = ".";
        graph = makeGraph();
        costTable = new MinimumCostTable(graph, 0);
        costTable.compute();
    }

    @Test
    void testPaths() {
        assertEquals("0", costTable.getMinimumCostPath(0).toString());
        assertEquals("0.1", costTable.getMinimumCostPath(1).toString());
        assertEquals("0.1.2", costTable.getMinimumCostPath(2).toString());
        assertEquals("0.3", costTable.getMinimumCostPath(3).toString());
        assertEquals("0.3.4", costTable.getMinimumCostPath(4).toString());
        assertEquals("0.3.4.5", costTable.getMinimumCostPath(5).toString());
    }

    @Test
    void testCosts() {
        assertEquals(0, costTable.getMinimumCostPath(0).getCost(), DELTA);
        assertEquals(0.1, costTable.getMinimumCostPath(1).getCost(), DELTA);
        assertEquals(2.3, costTable.getMinimumCostPath(2).getCost(), DELTA);
        assertEquals(0.001, costTable.getMinimumCostPath(3).getCost(), DELTA);
        assertEquals(0.002, costTable.getMinimumCostPath(4).getCost(), DELTA);
        assertEquals(0.003, costTable.getMinimumCostPath(5).getCost(), DELTA);
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