package beans;

import entities.User;
import helpers.EncryptPassword;
import models.UserModel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.validator.ValidatorException;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {

    private User user = new User();

    private boolean isLoggedIn = false;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
    }

    public String redirectToDashboard() {
        return "dashboard?faces-redirect=true";
    }

    public String redirectToHome() {
        return "index?faces-redirect=true";
    }

    public String login() {
        try {
            UserModel userModel = new UserModel();
            User user = userModel.find(this.user.getUsername());
            if (user != null && EncryptPassword.validatePassword(this.user.getPassword(), user.getPassword(), user.getPasswordSalt())) {
                this.user = user;
                this.setLoggedIn(true);

                return this.redirectToDashboard();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        MessageBean.showMessage(FacesMessage.SEVERITY_WARN, "Внимание!", "Невалидно потребителско име или парола!");

        return "index";
    }

    public String register() {
        try {
            UserModel userModel = new UserModel();
            String passwordSalt = EncryptPassword.generateSalt(30);
            this.user.setPassword(EncryptPassword.generateSecurePassword(this.user.getPassword(), passwordSalt));
            this.user.setPasswordSalt(passwordSalt);

            if (userModel.create(this.user)) {
                return this.redirectToHome();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        MessageBean.showMessage(FacesMessage.SEVERITY_ERROR, "Внимание!", "Възникна грешка. Не можахме да създадем акаунт!");

        return "register?faces-redirect=true";
    }

    public String logout() {
        this.user.clear();
        this.setLoggedIn(false);

        return this.redirectToHome();
    }
}