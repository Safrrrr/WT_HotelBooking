package services;


import dao.DaoException;

public class ServiceException extends Exception {

    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(DaoException message) {
        super(message);
    }

}

