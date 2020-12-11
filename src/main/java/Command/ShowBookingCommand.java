package Command;

import entities.Booking;
import entities.User;
import services.BookingService;
import services.ServiceException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ShowBookingsCommand implements Command {

    private static Logger log = LogManager.getLogger("show bookings");

    private BookingService bookingService;

    public ShowBookingsCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public CommandResult execute(RequestContent requestContent) {
        CommandResult commandResult;
        User user = (User) requestContent.getSessionAttribute(Request.USER.toString());
        Map<String, Object> requestAttributes = new HashMap<>();
        try {
            List<Booking> bookingList = bookingService.findBookingById(user.getId());
            if (bookingList.isEmpty()) {
                requestAttributes.put(Request.ERROR_FINDING_BOOKINGS.toString(), MessageHandler.getMessage("message.no_bookings", (String) requestContent.getSessionAttribute(Request.LOCALE.toString())));
                commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, Pages.USER_MAIN_PAGE.toString(), requestAttributes);
            } else {
                requestAttributes.put(Request.BOOKINGS.toString(), bookingList);
                commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, Pages.BOOKINGS_PAGE.toString(), requestAttributes);
            }
            return commandResult;
        } catch (ServiceException e) {
            log.error("Error in receiving bookings");
            return new DefaultCommand().execute(requestContent);
        }
    }
}
