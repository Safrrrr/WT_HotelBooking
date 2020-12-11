package Command;

public class LogoutCommand implements Command{
    @Override
    public CommandResult execute(RequestContent requestContent) {
        return new CommandResult(CommandResult.ResponseType.FORWARD, Pages.INDEX_PAGE.toString());
    }
}
