package Command;

import services.BookingService;
import services.UserService;

public enum CommandType {
    LOGIN (new SigninCommand(new UserService())),
    REGISTER(new SignupCommand(new UserService())),
    LOGOUT (new LogoutCommand()),
    BOOKINGS(new ShowBookingsCommand(new BookingService())),
    BOOK_ROOM(new BookRoomCommand(new UserService())),
    CHECK_DATES(new CheckAvailableCommand(new BookingService())),
    CHANGE_LANGUAGE(new LanguageCommand()),
    CANCEL_BOOKING(new CancelBookingCommand(new BookingService())),
    TO_PROFILE(new UserProfileCommand()),
    ;

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
