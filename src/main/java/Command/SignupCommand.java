package Command;


import entities.User;
import services.ServiceException;
import services.UserService;

import java.util.HashMap;
import java.util.Map;

public class SignupCommand implements Command {

    private static Logger log = LogManager.getLogger("signup");

    private UserService userService;

    public SignupCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContent requestContent) {
        CommandResult commandResult;
        String login = requestContent.getRequestParameter(Request.LOGIN.toString())[0];
        String password = requestContent.getRequestParameter(Request.PASSWORD.toString())[0];
        String email = requestContent.getRequestParameter(Request.EMAIL.toString())[0];
        String name = requestContent.getRequestParameter(Request.NAME.toString())[0];
        String surname = requestContent.getRequestParameter(Request.SURNAME.toString())[0];
        User user;

        LoginValidator loginValidator = new LoginValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        EmailValidator emailValidator = new EmailValidator();

        if (loginValidator.isValidated(login) && passwordValidator.isValidated(password) && emailValidator.isValidated(email)) {
            try {
                    user = userService.signup(login, password, email, name, surname, false);
            } catch (ServiceException e) {
                log.error("Unable to signup");
                return new DefaultCommand().execute(requestContent);
            }
            Map<String, Object> users = new HashMap<>();
            users.put(Request.USER.toString(), user);
            commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, Pages.SIGNUP_PAGE.toString(), users);
            log.debug(user + "was successfully signup");
        } else {
            commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, Pages.SIGNUP_PAGE.toString());
        }
        return commandResult;
    }
}
