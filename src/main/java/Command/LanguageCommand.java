package Command;

import java.util.HashMap;
import java.util.Map;

public class LanguageCommand implements Command {


    @Override
    public CommandResult execute(RequestContent requestContent) {
        CommandResult commandResult;
        String page = requestContent.getRequestParameter(Pages.PAGE.toString())[0];
        String locale = requestContent.getRequestParameter(Request.LOCALE.toString())[0];

        Map<String, Object> attributes = new HashMap<>();
        attributes.put(Request.LOCALE.toString(), locale);
        Map<String, Object> sessionAttributes = new HashMap<>();
        sessionAttributes.put(Request.LOCALE.toString(), locale);
        commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, page, attributes, sessionAttributes);
        return commandResult;
    }
}
