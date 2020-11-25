package entities;

public class User {
    private String name;
    private String surname;
    private String login;
    private int password;

    public User(String name, String surname, String password, String login) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password.hashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return  name + ' ' + surname + ", " + login;
    }
}