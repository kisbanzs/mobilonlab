package hu.bme.aut.vizivandor.ui.post;

import android.widget.Button;

public class MyListData{
    private String title;
    private String description;
    private String username;
    private String imageUrl;

    //private int imgId;
    public MyListData(String title, String description, String user, String imageUrl) {
        this.title = title;
        this.description = description;
        this.username = user;
        this.imageUrl = imageUrl;
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

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}