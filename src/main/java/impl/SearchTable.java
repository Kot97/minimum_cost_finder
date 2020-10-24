package impl;

import javax.ws.rs.NotFoundException;
import java.util.HashMap;

public class SearchTable {
    static HashMap<String, MinimumCostTable> costTables = new HashMap<>(3);
    static final String FORMAT = "%s%s%n%s%f";
    static final String PATH_DESCRIPTION = "Minimum cost path : ";
    static final String COST_DESCRIPTION = "Minimum cost: ";

    static void add(String connection, Graph graph) {
        add(connection, graph, 1);
    }

    static void add(String connection, Graph graph, int source) {
        MinimumCostTable minimumCostTable = new MinimumCostTable(graph, source);
        minimumCostTable.compute();
        costTables.put(connection, minimumCostTable);
    }

    public static String getResult(String connection, int target) {
        if (!costTables.containsKey(connection)) {
            DataAccessor dataAccessor = new DataAccessor(connection);
            dataAccessor.loadData();
        }

        Path path = costTables.get(connection).getMinimumCostPath(target);
        return String.format(FORMAT, PATH_DESCRIPTION, path.toString(), COST_DESCRIPTION, path.getCost());
    }

    public static int clearAll() {
        int count = costTables.size();
        costTables.clear();
        return count;
    }

    public static void clear(String connection) {
        if (costTables.remove(connection) == null) {
            throw new NotFoundException(connection + " related search table not found");
        }
    }
}
