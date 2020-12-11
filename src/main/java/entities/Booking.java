package entities;
import java.time.LocalDate;

public class Booking {
    private String userLogin;
    private String guestName;
    private int guestsNumber;
    private Room room;
    private LocalDate arrival;
    private LocalDate departure;
    private int bookingId;

    public Booking(){}

    public Booking(String userLogin, String guestName,  LocalDate arrival, LocalDate departure, Room room, int guestsNumber, int bookingId) {
        this.userLogin = userLogin;
        this.guestName = guestName;
        this.guestsNumber = guestsNumber;
        this.arrival = arrival;
        this.departure = departure;
        this.room = room;
        this.bookingId = bookingId;
    }
    /*public Booking(String userLogin, LocalDate arrival, LocalDate departure, Room room, int guestsNumber, String guestName) {
        this.userLogin = userLogin;
        this.arrival = arrival;
        this.departure = departure;
        this.room = room;
        this.guestsNumber = guestsNumber;
        this.guestName = guestName;
    }*/
    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public int getGuestsNumber() {
        return guestsNumber;
    }

    public void setGuestsNumber(int guestsNumber) {
        this.guestsNumber = guestsNumber;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

}
