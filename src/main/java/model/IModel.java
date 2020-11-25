package model;

import entities.User;

public interface IModel {
    void RegisterNewUser(User user);

    boolean CheckRegistration(User user);

    User UserLogin(String login, String password);
}