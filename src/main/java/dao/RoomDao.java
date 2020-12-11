package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDao extends AbstractDao<Room> implements Dao<Room> {
    private final Object specification;

    private static Logger log = LogManager.getLogger("dao");

    @Override
    public boolean add(Room entity) {
        return false;
    }

    @Override
    public boolean remove(Room entity) {
        return false;
    }

    @Override
    public boolean update(Room entity) {
        return false;
    }

    @Override
    public List<Room> query(Specification specification) throws DaoException {
        List<Room> rooms = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getConnectionPool();
            connection = pool.getConnection();
            try {
                connection = ConnectionPool.getConnectionPool();
                statement = specification.specify(connection);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int number = resultSet.getInt(1);
                    RoomType type = RoomType.valueOf(resultSet.getString(2).toUpperCase());
                    int bedsNumber = resultSet.getInt(3);
                    long price = resultSet.getlong(4);
                    Room room = new Room(number, type, bedsNumber, price);
                    rooms.add(room);
                }
            } catch (ConnectionPoolException | SQLException e) {
                log.error("Error in execution query RoomRepository", e);
                throw new DaoException(e);
            } finally {
                closeResultSet(resultSet);
                closeStatement(statement);
                if (pool != null)
                    pool.returnConnection(connection);
            }
            return rooms;
        }
    }
}
