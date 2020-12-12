package services;

import dao.DaoException;
import dao.UserDao;
import entities.User;
import specification.Specification;

import java.time.LocalDate;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class UserService {
    private static Logger log = LogManager.getLogger("user service");

    public List<User> signIn(String login, String password) throws ServiceException {
        java.util.List<User> users;
        Specification specification = new UserSigninSpecification(login, password);
        UserDao dao = new UserDao();
        try {
            users = dao.query(specification);
        } catch (DaoException e) {
            log.error("DaoException while login");
            throw new ServiceException(e);
        }
        return users;
    }

        public User signup(String login, String password, String email, String name, String surname, boolean isAdmin) throws ServiceException {
            UserDao dao = new UserDao();
            User user = new User(login, password, name, surame, email, isAdmin);
            try {
                dao.add(user);
            } catch (DaoException e) {
                log.error("DaoException while registering new user");
                throw new ServiceException(e);
            }
            return user;
        }

    public User updateProfile(String login, String password, String email, String name,
                              String surname, boolean isAdmin) throws ServiceException {

        UserDao dao = new UserDao();
        User user = new User(login, password, name, surname, email, isAdmin);
        try {
            dao.update(user);
        } catch (DaoException e) {
            log.error("DaoException while registering new user");
            throw new ServiceException(e);
        }
        return user;

    }

    public Booking addBooking(String userLogin, String arrival, String departure, Room room, int guestsNumber, String guestName) throws ServiceException {
        BookingDao dao = new BookingDao();
        Booking booking = new Booking(userLogin, LocalDate.parse(arrival), LocalDate.parse(departure), room, guestsNumber, guestName);
        try {
            dao.add(booking);
        } catch (DaoException e) {
            log.error("DaoException while add booking", e);
            throw new ServiceException(e);
        }
        return booking;
    }


}

