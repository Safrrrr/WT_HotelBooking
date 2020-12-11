package Command;

public class DefaultCommand implements Command {

    @Override
    public CommandResult execute(RequestContent requestContent) {
        return new CommandResult() ;
    }
}
