package impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinimumCostTableTest {
    Graph graph;
    MinimumCostTable costTable;

    @BeforeEach
    void setUp() {
        Path.separator = ".";
        graph = CommonData.getGraphOne();
        costTable = new MinimumCostTable(graph, 0);
    }

    @Test
    void DijkstraModifiedAlgorithmTest() {
        costTable.compute();
        assertEquals(0.001, costTable.get(3).cost(), 0.0001);
    }
}