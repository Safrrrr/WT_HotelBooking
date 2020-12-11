package dao;

import entities.Entity;

import java.sql.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class AbstractDao  <T extends Entity> implements Dao<T> {
    private static Logger log = LogManager.getLogger("dao");

    protected Connection connection;
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Can't close connection");

            }
        }
    }

    public PreparedStatement preparedStatement(String sql) throws DaoException {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            log.error("Can't create statement", e);
            throw new DaoException(e);
        }
        return statement;
    }
    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.error("Can't close statement");
            }
        }
    }
    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.error("Can't close ResultSet");
            }
        }
    }
}
