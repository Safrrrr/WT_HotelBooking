package Command;

public class UserProfileCommand implements Command {

    @Override
    public CommandResult execute(RequestContent requestContent) {
        return new CommandResult(CommandResult.ResponseType.FORWARD, Pages.USER_PROFILE_PAGE.toString());
    }
}
