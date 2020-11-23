package hu.bme.aut.vizivandor.ui.toplista;

public class MyToplistaData {

    private String username;
    private String distance;

    public MyToplistaData(String username, String distance) {
        this.username = username;
        this.distance = distance;
    }

    public MyToplistaData(){};


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
