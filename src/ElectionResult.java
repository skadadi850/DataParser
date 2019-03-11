public class ElectionResult {
    private double DemVotes, GOPVotes, totalVotes, perDem, perGOP, perPointDiff, difference;
    private String stateAbbr, countyName;
    private int fips;

    public ElectionResult (double DV, double GOPV, double total_votes, double per_dem, double per_gop, double diff, double per_point_diff, String state_abbr, String county_name, int combined_fips){
        this.DemVotes = DV;
        this.GOPVotes = GOPV;
        this.totalVotes = total_votes;
        this.perDem = per_dem;
        this.perGOP = per_gop;
        this.difference = diff;
        this.perPointDiff = per_point_diff;
        this.stateAbbr = state_abbr;
        this.countyName = county_name;
        this.fips = combined_fips;
    }

    public String getStateAbbr() {
        return stateAbbr;
    }

    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public double getGOPVotes() {
        return GOPVotes;
    }

    public void setGOPVotes(double GOPVotes) {
        this.GOPVotes = GOPVotes;
    }

    public double getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(double totalVotes) {
        this.totalVotes = totalVotes;
    }

    public double getPerDem() {
        return perDem;
    }

    public void setPerDem(double perDem) {
        this.perDem = perDem;
    }

    public double getPerGOP() {
        return perGOP;
    }

    public void setPerGOP(double perGOP) {
        this.perGOP = perGOP;
    }

    public double getPerPointDiff() {
        return perPointDiff;
    }

    public void setPerPointDiff(double perPointDiff) {
        this.perPointDiff = perPointDiff;
    }

    public Double getDifference() {
        return difference;
    }

    public void setDifference(double difference) {
        this.difference = difference;
    }

    public double getDemVotes() {
        return DemVotes;
    }

    public void setDemVotes(double demVotes) {
        DemVotes = demVotes;
    }

    public int getFips() {
        return fips;
    }

    public void setFips(int fips) {
        this.fips = fips;
    }

    public String toString (){
        return (DemVotes + ", " + GOPVotes + ", " + totalVotes + ", " + perDem + ", " + perGOP + ", "
                + difference + ", " + perPointDiff + ", " + stateAbbr + ", " + countyName + ", " + fips);

    }


}
