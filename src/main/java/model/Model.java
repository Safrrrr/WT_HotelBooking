package model;

import entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model implements IModel {

    private static Model instance = new Model();

    private List<User> userList;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        userList = new ArrayList<>();
    }


    @Override
    public void RegisterNewUser(User user) {
        userList.add(user);
    }

    @Override
    public boolean CheckRegistration(User user) {
        for (User regUser : userList) {
            if (regUser.getLogin().equals(user.getLogin()))
                return true;
        }
        return  false;
    }

    @Override
    public User UserLogin(String login, String password) {
        for (User user : userList) {
            if (user.getLogin().equals(login) && user.getPassword() == password.hashCode())
                return user;
        }
        return  null;
    }
}