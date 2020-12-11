package dao;

import entities.Booking;
import entities.Room;
import entities.RoomTypes;
import specification.Specification;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class BookingDao extends AbstractDao<Booking> implements Dao<Booking>{
    private static Logger log = LogManager.getLogger("dao");
  private static final String ADD_BOOKING = "INSERT INTO booking(user_login, room_number," +
            " arrival_date, departure_date, number_of_guests, guests)" +
            "VALUES (?,?,?,?,?,?);";
  private static final String DELETE_BOOKING = "DELETE FROM booking WHERE booking.booking_id = ?";
    @Override
    public boolean add(Booking booking) throws DaoException {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getConnectionPool();
            connection = pool.getConnection();
            var statement = connection.prepareStatement(ADD_BOOKING, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, booking.getUserLogin());)
            statement.setInt(2, booking.getRoom().getNumber());
            statement.setString(3, booking.getArrival().toString());
            statement.setString(4, booking.getDeparture().toString());
            statement.setInt(5, booking.getGuestsNumber());
            statement.setString(6, booking.getGuestName());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ConnectionPoolException e) {
            log.error("Can't add to BookingRepository", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
            if (pool != null)
                pool.returnConnection(connection);
        }
    }
    @Override
    public boolean remove(Booking booking) throws DaoException {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getConnectionPool();
            connection = pool.getConnection();
            var statement = connection.prepareStatement(DELETE_BOOKING);
            statement.setInt(1, booking.getBookingId());
            statement.executeUpdate();
            return true;
        }catch (SQLException | ConnectionPoolException e){
            throw new DaoException(e);
        }finally {
            closeStatement(statement);
            if (pool != null)
                pool.returnConnection(connection);
        }
    }

    @Override
    public boolean update(Booking entity) {
        return false;
    }

    @Override
    public List<Booking> query(Specification specification) throws DaoException {
        Booking booking;
        List<Booking> bookings = new ArrayList<>();
        ResultSet resultSet = null;
        ConnectionPool pool = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            pool = ConnectionPool.getConnectionPool();
            connection = pool.getConnection();

            statement = specification.specify(connection);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int bookingId = resultSet.getInt(1);
                String userLogin = resultSet.getString(2);
                int roomNumber = resultSet.getInt(3);
                LocalDate arrival = LocalDate.parse(resultSet.getString(4));
                LocalDate departure = LocalDate.parse(resultSet.getString(5));
                int guests = resultSet.getInt(6);
                String guestsName = resultSet.getString(7);
                RoomTypes roomType = RoomTypes.valueOf(resultSet.getString(8).toUpperCase());
                int bedsNumber = resultSet.getInt(9);
                long price = resultSet.getLong(10);

                Room room = new Room(roomNumber, roomType, bedsNumber, price);
                booking = new Booking(bookingId, userLogin, arrival, departure, room, guests, guestsName);

                bookings.add(booking);
                log.debug("Query to database executed successfully");
            }
        } catch ( SQLException | ConnectionPoolException e) {
            log.error("Error in execution query BookingRepository");
            throw new DaoException(e);
        }finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            if (pool != null)
                pool.returnConnection(connection);
        }
        return bookings;
    }
}
