package services;

import dao.BookingDao;
import entities.Booking;
import entities.Room;
import specification.Specification;

import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class BookingService {
    private static Logger log = LogManager.getLogger("booking service");

    public List<Booking> findBookings(String login) throws ServiceException {
        List<Booking> bookings;
        Specification specification = new BookingByUserSpecification(login);
        BookingDao dao = new BookingDao();
        try {
            bookings = dao.query(specification);
        } catch (DaoException e) {
            log.error("DaoException while finding bookings");
            throw new ServiceException(e);
        }
        return bookings;
    }
    public List<Room> findAvailableRooms(String arrival, String departure) throws ServiceException {
        List<Room> rooms;
        Specification specification = new FindAvailableSpecification(arrival, departure);
        RoomDao dao = new RoomDao();
        try {
            rooms = dao.query(specification);
        } catch (DaoException e) {
            log.error("DaoException while finding available rooms");
            throw new ServiceException(e);
        }
        return rooms;
    }
    public Booking cancelBooking(int bookingId) throws ServiceException {
        BookingDao dao = new BookingDao();
        Booking booking = new Booking();
        booking.setBookingId(bookingId);
        try {
            dao.remove(booking);
        } catch (DaoException e) {
            log.error("DaoException while canceling booking", e);
            throw new ServiceException(e);
        }
        return booking;
    }
    public List<Booking> findBookingById(int bookingId) throws ServiceException {
        List<Booking> bookings;
        Specification specification = new BookingByIdSpecification(bookingId);
        BookingDao dao = new BookingDao();
        try {
            bookings = dao.query(specification);
        } catch (DaoException e) {
            log.error("DaoException while finding booking by Id");
            throw new ServiceException(e);
        }
        return bookings;
    }

}
