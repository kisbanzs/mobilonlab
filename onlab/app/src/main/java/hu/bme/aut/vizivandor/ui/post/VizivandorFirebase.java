package hu.bme.aut.vizivandor.ui.post;



public class VizivandorFirebase {

    private String title;
    private String description;
    private String imageUrl;
    private String username;



    public VizivandorFirebase() {
    }

    public VizivandorFirebase(String title, String description, String imageUrl, String username) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.username = username;
    }


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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /* private String title, description, imageUrl, username;

        public VizivandorFirebase(String title, String description, String imageUrl, String username) {
            this.title = title;
            this.description = description;
            this.imageUrl=imageUrl;
            this.username = username;
        }

        public VizivandorFirebase() {
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
        public void setTitle(String title) {
            this.title = title;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }*/

    }
