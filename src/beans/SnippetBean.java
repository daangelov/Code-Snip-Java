package beans;

import entities.Snippet;
import models.SnippetModel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "snippetBean")
@RequestScoped
public class SnippetBean {

    @ManagedProperty(value = "#{userBean.user.id}")
    private long creatorId;

    private Snippet snippet = new Snippet();

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    public long getCreatorId() {
        return this.creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public String save() {
        boolean success = true;
        try {
            SnippetModel snippetModel = new SnippetModel();
            this.snippet.setCreatorId(this.getCreatorId());
            if (!snippetModel.create(this.snippet)) {
                success = false;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            success = false;
        }

        if (!success) {
            MessageBean.showMessage(FacesMessage.SEVERITY_ERROR, "Внимание!", "Възникна грешка. Не можахме да създадем акаунт!");
        }

        return "dashboard?faces-redirect=true";
    }
}