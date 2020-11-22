package hu.bme.aut.vizivandor.ui.turainditas;

public class LocationHelper {

    private double Longitude;
    private double Latitude;

    public LocationHelper(double latitude, double longitude) {
        Longitude = longitude;
        Latitude = latitude;
    }
    public LocationHelper(){;}

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }
}
