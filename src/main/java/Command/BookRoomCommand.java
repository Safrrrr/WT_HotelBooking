package Command;

import entities.Room;
import entities.User;
import services.ServiceException;
import services.UserService;

import java.util.Map;

public class BookRoomCommand implements Command {

    private static Logger log = LogManager.getLogger("booking room");

    private UserService userService;

    public BookRoomCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContent requestContent) {
        CommandResult commandResult;
        User user = (User) requestContent.getSessionAttribute(Request.USER.toString());
        String login = user.getLogin();
        String arrival = requestContent.getRequestParameter(Request.ARRIVAL_DATE.toString())[0];
        String departure = requestContent.getRequestParameter(Request.DEPARTURE_DATE.toString())[0];
        Room room = new Room();
        room.setNumber(Integer.parseInt(requestContent.getRequestParameter(Request.ROOM.toString())[0]));
        int guestsNumber = Integer.parseInt(requestContent.getRequestParameter(Request.NUMBER_OF_GUESTS.toString())[0]);
        String guestsNames = requestContent.getRequestParameter(Request.GUESTS_NAMES.toString())[0];
        try {
            userService.addBooking(login, arrival, departure, room, guestsNumber, guestsNames);
        } catch (ServiceException e) {
            log.error("Error while booking room", e);
            return new DefaultCommand().execute(requestContent);
        }
        Map<String, Object> attributes = new HashMap<>();
        attributes.put(Request.SUCCESSFUL_BOOKING.toString(), MessageHandler.getMessage("message.successful_book", (String) requestContent.getSessionAttribute(Request.LOCALE.toString())));
        commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, Pages.USER_MAIN_PAGE.toString(), attributes);
        log.debug("Room was successfully booked");
        return commandResult;
    }
}

