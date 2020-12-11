package Command;

import entities.Booking;
import message.MessageHandler;
import services.BookingService;
import services.ServiceException;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class CancelBookingCommand implements Command {

    private static Logger log = LogManager.getLogger("cancel booking");

    private BookingService bookingService;

    public CancelBookingCommand(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public CommandResult execute(RequestContent requestContent) {
        CommandResult commandResult;
        int bookingId = Integer.parseInt(requestContent.getRequestParameter(Request.BOOKING_ID.toString())[0]);
        System.out.println(bookingId);
        try {
            Booking booking = bookingService.cancelBooking(bookingId);
        } catch (ServiceException e) {
            log.error("Error while canceling booking", e);
            return new DefaultCommand().execute(requestContent);
        }


        Map<String, Object> attributes = new HashMap<>();
        attributes.put(Request.SUCCESSFUL_BOOKING.toString(), MessageHandler.getMessage("message.successful_canceling", (String) requestContent.getSessionAttribute(Request.LOCALE.toString())));
        commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, Pages.BOOKINGS_PAGE.toString(), attributes);
        log.debug("Booking was successfully canceled");
        return commandResult;
    }
}

