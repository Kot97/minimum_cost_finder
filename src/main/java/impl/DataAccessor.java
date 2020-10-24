package impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccessor {
    private final String connectionString;
    private static final String QUERY = "SELECT * FROM Data";

    public DataAccessor(String connectionString) {
        this.connectionString = connectionString;
    }

    public void loadData() {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            loadDataFrom(connection);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void loadData(String user, String password) {
        try (Connection connection = DriverManager.getConnection(connectionString, user, password)) {
            loadDataFrom(connection);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void loadDataFrom(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(QUERY)) {
                List<DatabaseRow> data = new ArrayList<>(20);

                while (resultSet.next()) {
                    data.add(new DatabaseRow(resultSet.getInt("x"), resultSet.getInt("y"), resultSet.getFloat("p")));
                }

                SearchTable.add(connectionString, GraphBuilder.build(data));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }
}
