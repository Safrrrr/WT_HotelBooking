package dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ConnectionPool {
    private static Logger log = LogManager.getLogger("connection");
    private static final List<Connection> connections = new LinkedList<>();
    private static ConnectionPool pool;
    private static final int POOL_SIZE = 4;

    public static ConnectionPool getConnectionPool() throws ConnectionPoolException {
        if (pool == null) {
            try {
                var initContext = new InitialContext();
                var envContext = (Context) initContext.lookup("java:/comp/env");
                var ds = (DataSource) envContext.lookup("jdbc/HotelBooking");

                for (int i = 0; i < POOL_SIZE; i++) {
                    connections.add(ds.getConnection());
                }

                pool = new ConnectionPool();
            } catch (SQLException | NamingException e) {
                throw new ConnectionPoolException(e);
            }
        }
        return pool;
    }


    public Connection getConnection() {
        while (connections.size() == 0) {
            Thread.yield();
        }

        var connection = connections.stream().findFirst().orElseThrow();
        connections.remove(connection);
        return connection;
    }

    public void returnConnection(Connection connection) {
        if (!connections.contains(connection)) {
            connections.add(connection);
        }
    }

}
