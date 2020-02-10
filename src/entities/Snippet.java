package entities;

import java.sql.Date;

public class Snippet {
    private long id;
    private String title;
    private String text;
    private String lang;
    private long creatorId;
    private String publicId;

    private Date createdOn;
    private Date updatedOn;

    public Snippet() {
    }

    public Snippet(long id, String title, String lang, String text, Date createdOn, Date updatedOn, long creatorId, String publicId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.lang = lang;
        this.creatorId = creatorId;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.publicId = publicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
