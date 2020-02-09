package validator;

import entities.User;
import models.UserModel;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("usernameValidator")
public class UsernameValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component,
                         Object value) throws ValidatorException {

        UserModel userModel = new UserModel();
        User user = null;
        try {
            user = userModel.find(value.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (user != null) {
            FacesMessage msg = new FacesMessage("Невалидно потребителско име",
                    "Потребителското име е заето");
            msg.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(msg);
        }
    }
}