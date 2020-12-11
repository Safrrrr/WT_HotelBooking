package dao;

public class ConnectionPoolException extends Exception{
    public ConnectionPoolException(Exception e) {
        super(e);
    }
}