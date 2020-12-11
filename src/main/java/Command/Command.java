package Command;

@FunctionalInterface
public interface Command {
    CommandResult execute(RequestContent requestContent);
}
