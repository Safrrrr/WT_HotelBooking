package dao;

import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDao extends AbstractDao<User> implements Dao<User> {
    private static Logger log = LogManager.getLogger("dao");
    private static final String ADD_USER = "INSERT INTO user(login, password, email," +
            " name, surname, isAdmin) " +
            "VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_USER =
            "UPDATE user " +
                    "SET login = ?, password = ?, email = ?, name = ?, surname = ?, isAdmin = ? " +
                    "WHERE login = ?;";

    @Override
    public boolean add(User user) throws DaoException {
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool();
            statement = preparedStatement(ADD_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getName());
            statement.setString(5, user.getSurname());
            statement.setBoolean(6, user.isAdmin());

            statement.executeUpdate();
            log.debug(user + "was added successfully to database");
            return true;
        } catch (ConnectionPoolException | SQLException e) {
            log.error("Can't add to UserDao", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
            if (pool != null)
                pool.returnConnection(connection);
        }
    }
    @Override
    public boolean remove(User user) {
        return false;
    }

    @Override
    public boolean update(User user) throws DaoException {
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnectionPool();
            statement = preparedStatement(UPDATE_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getName());
            statement.setString(5, user.getSurname());
            statement.setBoolean(6, user.isAdmin());
            statement.setString(7, user.getLogin());
            statement.executeUpdate();
            log.debug(user + "was updated successfully");
            return true;
        } catch (ConnectionPoolException | SQLException e) {
            log.error("Can't update user", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
            if (pool != null)
                pool.returnConnection(connection);
        }
    }
    @Override
    public List<User> query(Specification specification) throws DaoException {
        List<User> users = new ArrayList<>();
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
            if (resultSet.next()) {
                String login = resultSet.getString(1);
                String password = resultSet.getString(2);
                String email = resultSet.getString(3);
                String name = resultSet.getString(4);
                String surname = resultSet.getString(5);
                boolean isAdmin = Boolean.parseBoolean(resultSet.getString(6));
                User user = new User(login, password, name, surname, email, isAdmin);
                users.add(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            log.error("Error in execution query UserDao", e);
            throw new DaoException(e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            if (pool != null)
                pool.returnConnection(connection);
        }
        return users;
    }

}
