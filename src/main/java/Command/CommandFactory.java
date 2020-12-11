package Command;

public class CommandFactory {

    private static final CommandFactory instance = new CommandFactory();

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    public Command defineCommand(RequestContent content) {
        String commandName = content.getRequestParameters().containsKey(Request.COMMAND.toString()) ?
                content.getRequestParameter(Request.COMMAND.toString())[0] : Request.ERROR.toString();
        try {
            return CommandType.valueOf(commandName.toUpperCase().replaceAll("[-]", "_")).getCommand();
        } catch (IllegalArgumentException e) {
            return new DefaultCommand();
        }
    }
}
