package entities;

public class User {
    private String name;
    private String surname;
    private String login;
    private int password;
    private String email;
    private boolean isAdmin;

    public User() {
    }

    public User(String name, String surname, String password, String login, String email, boolean isAdmin) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password.hashCode();
        this.email = email;
        this.isAdmin = isAdmin;
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

    public String getLogin() { return login; }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}