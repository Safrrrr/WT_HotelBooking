package Command;

import entities.Booking;
import entities.Room;
import message.MessageHandler;
import services.BookingService;
import services.ServiceException;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class CheckAvailableCommand  implements Command{
    private static Logger log = LogManager.getLogger("check available rooms");

    private BookingService bookingService;

    public CheckAvailableCommand(BookingService bookingServiceService) {
        this.bookingService = bookingService;
    }

    @Override
    public CommandResult execute(RequestContent requestContent) {
        CommandResult commandResult;
        String arrivalDate = requestContent.getRequestParameter(Request.ARRIVAL_DATE.toString())[0];
        String departureDate = requestContent.getRequestParameter(Request.DEPARTURE_DATE.toString())[0];
        try {
            Map<String, Object> requestAttributes = new HashMap<>();
            Map<String, Object> sessionAttributes = new HashMap<>();
            List<Room> roomList = bookingService.findAvailableRooms(arrivalDate, departureDate);
            if (roomList.isEmpty()) {
                requestAttributes.put(NO_AVAILABLE_ROOMS, MessageHandler.getMessage("message.no_available_rooms", (String) requestContent.getSessionAttribute(Request.LOCALE.toString())));
                commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, Pages.USER_MAIN_PAGE.toString(), requestAttributes);
            } else {
                requestAttributes.put(Request.ROOM.toString(), roomList);
                sessionAttributes.put(Request.ARRIVAL_DATE.toString(), arrivalDate);
                sessionAttributes.put(Request.DEPARTURE_DATE.toString(), departureDate);
                commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, Request.AVAILABLE_ROOM.toString().toString(), requestAttributes, sessionAttributes);
            }
            return commandResult;
        } catch (ServiceException e) {
            log.error("Error while receiving rooms", e);
            return new DefaultCommand().execute(requestContent);
        }
    }
}
