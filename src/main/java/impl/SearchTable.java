package impl;

import javax.ws.rs.NotFoundException;
import java.util.HashMap;

public class SearchTable {
    private static HashMap<String, MinimumCostTable> costTables = new HashMap<>(3);

    static void add(String connection, Graph graph) {
        costTables.put(connection, new MinimumCostTable(graph));
    }

    public static String getResult(String connection, int target) {
        if (!costTables.containsKey(connection)) {
            DataAccessor dataAccessor = new DataAccessor(connection);
            dataAccessor.loadData();
        }

        Path path = costTables.get(connection).at(target);
        return String.format("Minimum cost path length: %d\nMinimum cost path: %s",
                                    path.length(), path.toString());
    }

    public static int clearAll() {
        int count = costTables.size();
        costTables.clear();
        return count;
    }

    public static void clear(String connection) throws NotFoundException {
        if (costTables.remove(connection) == null) {
            throw new NotFoundException(connection + " related search table not found");
        }
    }
}
