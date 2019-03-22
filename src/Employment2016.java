public class Employment2016 {
    private String areaName; // 3
    private double civilianLaborForce; // 18
    private double employed2010; // 19
    private double unemployed2010; // 20

    public Employment2016(String areaName, double civilianLaborForce, double employed2010, double unemployed2010) {
        this.areaName = areaName;
        this.civilianLaborForce = civilianLaborForce;
        this.employed2010 = employed2010;
        this.unemployed2010 = unemployed2010;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public double getCivilianLaborForce() {
        return civilianLaborForce;
    }

    public void setCivilianLaborForce(double civilianLaborForce) {
        this.civilianLaborForce = civilianLaborForce;
    }

    public double getEmployed2010() {
        return employed2010;
    }

    public void setEmployed2010(double employed2010) {
        this.employed2010 = employed2010;
    }

    public double getUnemployed2010() {
        return unemployed2010;
    }

    public void setUnemployed2010(double unemployed2010) {
        this.unemployed2010 = unemployed2010;
    }
}
