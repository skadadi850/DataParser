public class CrimeData2010 {
    private String crimeCodeDescription;
    private double longitude;
    private double latitude;

    public CrimeData2010(String crimeCodeDescription, double longitude, double latitude) {
        this.crimeCodeDescription = crimeCodeDescription;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getCrimeCodeDescription() {
        return crimeCodeDescription;
    }

    public void setCrimeCodeDescription(String crimeCodeDescription) {
        this.crimeCodeDescription = crimeCodeDescription;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
