package Command;

public enum Request {
    COMMAND("command"),
    ERROR("error"),
    LOGIN("login"),
    PASSWORD("password"),
    LOCALE("locale"),
    USER("user"),
    ERROR_LOGIN_PASS("errorLoginPassword"),
    DEPARTURE_DATE("departure-date"),
    EMAIL("email"),
    ARRIVAL_DATE("arrival-date"),
    NUMBER_OF_GUESTS("number-of-guests"),
    GUESTS_NAMES("guests-names"),
    SUCCESSFUL_BOOKING("successful-book"),
    NAME("first-name"),
    SURNAME("last-name"),
    BOOKING_ID("booking-id"),
    AVAILABLE_ROOM("available-room"),
    ROOM("room"),
    ERROR_FINDING_BOOKINGS("errorFindingBookings"),
    BOOKINGS("bookings");

    private String value;

    Request(String value) {
        this.value = value;
    }

}
