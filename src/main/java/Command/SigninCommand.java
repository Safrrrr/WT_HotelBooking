package Command;

import entities.User;
import services.ServiceException;
import services.UserService;
import validator.LoginValidator;
import validator.PasswordValidator;

import java.awt.print.PageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class SigninCommand implements Command {

    private static Logger log = LogManager.getLogger("login");

    private UserService userService;

    public SigninCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(RequestContent requestContent) {
        User user;
        CommandResult commandResult;
        String login = requestContent.getRequestParameter(Request.LOGIN.toString())[0];
        String password = requestContent.getRequestParameter(Request.PASSWORD.toString())[0];
        Map<String, Object> requestAttributes = new HashMap<>();
        Map<String, Object> users = new HashMap<>();
        LoginValidator loginValidator = new LoginValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        if (loginValidator.isValidated(login) && passwordValidator.isValidated(password)) {
            try {
                if (userService.signIn(login, password).isEmpty()) {
                    users.put(Request.ERROR_LOGIN_PASS.toString(), MessageHandler.getMessage("warning.signin_password", (String) requestContent.getSessionAttribute(Request.LOCALE.toString())));
                    commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, Pages.SIGNIN_PAGE.toString(), requestAttributes, users);
                    log.debug("Login error: can't find user");
                } else {
                    user = userService.signIn(login, password).get(0);
                    if (!user.isAdmin()) {
                        users.put(Request.USER.toString(), user);
                        commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, Pages.USER_MAIN_PAGE.toString(), requestAttributes, users);
                        log.debug(user + " logged in as user");
                    } else {
                        user = userService.signIn(login, password).get(0);
                        users.put(Request.USER.toString(), user);
                        commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, Pages.ADMIN_PAGE.toString(), requestAttributes, users);
                        log.debug(user + " logged in as admin");
                    }
                }
            } catch (ServiceException e) {
                log.debug("Unable to log in", e);
                return new DefaultCommand().execute(requestContent);
            }
        } else {
            commandResult = new CommandResult(CommandResult.ResponseType.FORWARD, Pages.SIGNIN_PAGE.toString());
        }
        return commandResult;
    }
}
