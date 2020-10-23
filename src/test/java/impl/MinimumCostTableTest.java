package impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

class MinimumCostTableTest {
    Graph graph;
    MinimumCostTable costTable;

    @BeforeEach
    void setUp() {
        Path.separator = ".";
        graph = CommonData.getGraphOne();
        costTable = new MinimumCostTable(graph);
    }

    @Test
    void DijkstraModifiedAlgorithmTest() {
        costTable.compute();
        assertEquals(0.001, costTable.get(4).cost(), 0.0001);
    }
}