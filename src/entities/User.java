package entities;

public class User {
    private String username;
    private String password;
    private String passwordSalt;
    private String email;
    private String firstName;
    private String lastName;

    public User() {
    }

    public User(String username, String password, String passwordSalt, String email, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.passwordSalt = passwordSalt;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void clear() {
        this.username = null;
        this.password = null;
        this.passwordSalt = null;
        this.firstName = null;
        this.lastName = null;
        this.email = null;
    }
}
