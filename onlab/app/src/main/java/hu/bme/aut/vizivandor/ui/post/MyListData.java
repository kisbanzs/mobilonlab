package hu.bme.aut.vizivandor.ui.post;

public class MyListData{
    private String title;
    private String description;
    private String user;
    //private int imgId;
    public MyListData(String title, String description, String user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }
    public MyListData(){};

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
}