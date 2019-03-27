public class CrimeData2001 {
    private String primaryType;
    private double longitude;
    private double latitude;

    public CrimeData2001(String primaryType, double longitude, double latitude) {
        this.primaryType = primaryType;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public void setPrimaryType(String primaryType) {
        this.primaryType = primaryType;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}

