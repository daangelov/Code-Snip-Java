package beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "messageBean")
@RequestScoped
public class MessageBean {
    public static void showMessage(FacesMessage.Severity type, String title, String message) {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(type, title, message));
    }
}
