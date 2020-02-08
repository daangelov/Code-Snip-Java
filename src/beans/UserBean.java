package beans;

import entities.User;
import helpers.EncryptPassword;
import models.UserModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {

    private User user = new User();
    private String errorMessage = "";

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String login() {
        try {
            UserModel userModel = new UserModel();
            User user = userModel.find(this.user.getUsername());
            if (user != null && EncryptPassword.validatePassword(this.user.getPassword(), user.getPassword(), user.getPasswordSalt())) {
                return "dashboard";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.setErrorMessage("Невалидно потребителско име или парола!");
        return "index";
    }

    public String register() {
        try {
            UserModel userModel = new UserModel();
            String passwordSalt = EncryptPassword.generateSalt(30);
            this.user.setPassword(EncryptPassword.generateSecurePassword(this.user.getPassword(), passwordSalt));
            this.user.setPasswordSalt(passwordSalt);

            userModel.create(this.user);

            return "index";
        } catch (Exception e) {
            this.setErrorMessage("Възникна грешка. Не можахме да създадем акаунт!");

            return "register";
        }
    }
}