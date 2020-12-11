package servlet;

import Command.Command;
import Command.CommandFactory;
import Command.CommandResult;
import Command.RequestContent;
import dao.ConnectionPool;
import dao.ConnectionPoolException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainController {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {

        RequestContent requestcontent = RequestContent.createContent(request);
        CommandFactory commandFactory = CommandFactory.getInstance();

        Command command = commandFactory.defineCommand(requestcontent);
        CommandResult commandResult = command.execute(requestcontent);

        commandResult.getAttributes().forEach(request::setAttribute);
        commandResult.getSessionAttributes().forEach(request.getSession()::setAttribute);

        if (command.getClass().isAssignableFrom(LogoutCommand.class)) {
            request.getSession().invalidate();
        }

        if (commandResult.getResponseType() == CommandResult.ResponseType.FORWARD) {
            RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher(commandResult.getPage());
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getServletContext().getContextPath() + commandResult.getPage());
        }
    }

    @Override
    public void destroy(){
        try {
            ConnectionPool.getInstance().closePool();
        } catch (ConnectionPoolException e) {
            throw new RuntimeException("Cant destroy pool connection");
        }
    }
}
