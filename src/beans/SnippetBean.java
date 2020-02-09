package beans;

import entities.Snippet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "snippetBean")
@RequestScoped
public class SnippetBean {

    private Snippet snippet = new Snippet();

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

    public String save() {
        return "dashboard?faces-redirect=true";
    }
}