package beans;

import entities.Snippet;
import models.SnippetModel;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;

@ManagedBean(name = "snippetsView")
@ViewScoped
public class SnippetsView implements Serializable {

    @ManagedProperty(value = "#{userBean.user.id}")
    private long userId;

    private DashboardModel model;

    private ArrayList<Snippet> snippets;

    @PostConstruct
    public void init() {
        model = new DefaultDashboardModel();
        SnippetModel snippetModel = new SnippetModel();
        try {
            this.snippets = snippetModel.getSnippets(this.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();

        if (this.snippets != null) {
            for (Snippet snippet : this.snippets) {
                String widgetName = "snippet" + snippet.getId();
                if (snippet.getId() % 2 == 0) {
                    column1.addWidget(widgetName);
                } else {
                    column2.addWidget(widgetName);
                }
            }
        }

        model.addColumn(column1);
        model.addColumn(column2);
    }

    public void handleReorder(DashboardReorderEvent event) {

    }

    public DashboardModel getModel() {
        return model;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public ArrayList<Snippet> getSnippets() {
        return snippets;
    }

    public void setSnippets(ArrayList<Snippet> snippets) {
        this.snippets = snippets;
    }
}
